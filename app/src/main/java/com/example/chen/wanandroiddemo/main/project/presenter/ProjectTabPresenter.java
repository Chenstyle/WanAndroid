package com.example.chen.wanandroiddemo.main.project.presenter;

import com.example.chen.wanandroiddemo.base.presenter.BasePresenter;
import com.example.chen.wanandroiddemo.main.project.contract.ProjectTabContract;
import com.example.chen.wanandroiddemo.core.DataManager;
import com.example.chen.wanandroiddemo.core.bean.Articles;
import com.example.chen.wanandroiddemo.core.bean.BaseResponse;
import com.example.chen.wanandroiddemo.utils.RxUtils;


import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * @author : chenshuaiyu
 * @date : 2019/3/21 8:48
 */
public class ProjectTabPresenter extends BasePresenter<ProjectTabContract.View> implements ProjectTabContract.Presenter {

    public ProjectTabPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void getProjectTabArticles(int page, int cid) {
        addSubcriber(
                mDataManager.getProjectTabArticles(page, cid)
                        .compose(RxUtils.switchSchedulers())
                        .subscribe(articlesBaseResponse -> {
                            mView.showProjectTabArticles(articlesBaseResponse.getData().getDatas());
                            mView.showContentView();
                        }, Throwable::printStackTrace)
        );
    }
}
