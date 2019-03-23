package com.example.chen.wanandroiddemo.presenter;

import com.example.chen.wanandroiddemo.base.presenter.BasePresenter;
import com.example.chen.wanandroiddemo.contract.SearchContract;
import com.example.chen.wanandroiddemo.core.DataManager;
import com.example.chen.wanandroiddemo.core.bean.BaseResponse;
import com.example.chen.wanandroiddemo.core.bean.HotWord;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Coder : chenshuaiyu
 * Time : 2019/3/22 22:15
 */
public class SearchPresenter extends BasePresenter<SearchContract.View> implements SearchContract.Presenter {
    @Inject
    public SearchPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void getHotWord() {
        mDataManager.getHotWord()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseResponse<List<HotWord>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }
                    @Override
                    public void onNext(BaseResponse<List<HotWord>> listBaseResponse) {
                        view.showHotWord(listBaseResponse.getData());
                    }
                    @Override
                    public void onError(Throwable e) {
                    }
                    @Override
                    public void onComplete() {
                    }
                });
    }
}
