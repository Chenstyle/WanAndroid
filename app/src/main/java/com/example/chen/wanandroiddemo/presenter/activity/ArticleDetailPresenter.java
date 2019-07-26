package com.example.chen.wanandroiddemo.presenter.activity;

import com.example.chen.wanandroiddemo.base.presenter.BasePresenter;
import com.example.chen.wanandroiddemo.contract.ArticleDetailContract;
import com.example.chen.wanandroiddemo.core.DataManager;
import javax.inject.Inject;

/**
 * @author : chenshuaiyu
 * @date : 2019/3/19 20:09
 */
public class ArticleDetailPresenter extends BasePresenter<ArticleDetailContract.View> implements ArticleDetailContract.Presenter {

    @Inject
    public ArticleDetailPresenter(DataManager dataManager) {
        super(dataManager);
    }


}
