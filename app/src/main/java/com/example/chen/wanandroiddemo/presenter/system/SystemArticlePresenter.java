package com.example.chen.wanandroiddemo.presenter.system;

import com.example.chen.wanandroiddemo.base.presenter.BasePresenter;
import com.example.chen.wanandroiddemo.contract.SystemArticleContract;
import com.example.chen.wanandroiddemo.core.DataManager;
import com.example.chen.wanandroiddemo.core.bean.Articles;
import com.example.chen.wanandroiddemo.core.bean.BaseResponse;
import com.example.chen.wanandroiddemo.utils.RxUtil;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @author : chenshuaiyu
 * @date : 2019/3/22 20:16
 */
public class SystemArticlePresenter extends BasePresenter<SystemArticleContract.View> implements SystemArticleContract.Presenter {
    @Inject
    public SystemArticlePresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void getSystemArticles(int page, int cid) {
        mDataManager.getSystemArticles(page, cid)
                .compose(RxUtil.switchSchedulers())
                .subscribe(new Observer<BaseResponse<Articles>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }
                    @Override
                    public void onNext(BaseResponse<Articles> articlesBaseResponse) {
                        mView.showSystemArticles(articlesBaseResponse.getData().getDatas());
                        mView.showNormalView();
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
