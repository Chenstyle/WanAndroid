package com.example.chen.wanandroiddemo.presenter;

import com.example.chen.wanandroiddemo.base.presenter.BasePresenter;
import com.example.chen.wanandroiddemo.contract.HomeContract;
import com.example.chen.wanandroiddemo.core.DataManager;
import com.example.chen.wanandroiddemo.core.bean.Articles;
import com.example.chen.wanandroiddemo.core.bean.Banner;
import com.example.chen.wanandroiddemo.core.bean.BaseResponse;
import com.example.chen.wanandroiddemo.utils.RxUtils;
import java.util.List;
import javax.inject.Inject;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Coder : chenshuaiyu
 * Time : 2019/3/17 16:08
 */
public class HomePresenter extends BasePresenter<HomeContract.View> implements HomeContract.Presenter {

    @Inject
    public HomePresenter(DataManager dataManager) {
        super(dataManager);
    }

    public void getBanner() {
        mDataManager.getBanner()
                .compose(RxUtils.switchSchedulers())
                .subscribe(new Observer<BaseResponse<List<Banner>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }
                    @Override
                    public void onNext(BaseResponse<List<Banner>> listBaseResponse) {
                        mView.showBanner(listBaseResponse.getData());
                    }
                    @Override
                    public void onError(Throwable e) {
                    }
                    @Override
                    public void onComplete() {
                    }
                });
    }

    @Override
    public void getArticles(int page) {
        mDataManager.getArticles(page)
                .compose(RxUtils.switchSchedulers())
                .subscribe(new Observer<BaseResponse<Articles>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }
                    @Override
                    public void onNext(BaseResponse<Articles> articlesBaseResponse) {
                        mView.showArticles(articlesBaseResponse.getData().getDatas());
                    }
                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }
                    @Override
                    public void onComplete() {
                    }
                });
    }
}
