package com.example.chen.wanandroiddemo.contract;

import com.example.chen.wanandroiddemo.base.presenter.IPresenter;
import com.example.chen.wanandroiddemo.base.view.BaseView;
import com.example.chen.wanandroiddemo.core.bean.Article;
import com.example.chen.wanandroiddemo.core.bean.HotWord;

import java.util.List;

/**
 * Coder : chenshuaiyu
 * Time : 2019/3/22 22:14
 */
public interface SearchContract {
    interface Presenter extends IPresenter<View> {
        void getHotWord();
    }

    interface View extends BaseView {
        void showHotWord(List<HotWord> hotWords);
    }
}
