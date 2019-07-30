package com.example.chen.wanandroiddemo.presenter.activity;

import com.example.chen.wanandroiddemo.base.presenter.BasePresenter;
import com.example.chen.wanandroiddemo.contract.SettingsContract;
import com.example.chen.wanandroiddemo.core.DataManager;

import javax.inject.Inject;

/**
 * @author : chenshuaiyu
 * @date : 2019/3/26 21:33
 */
public class SettingsPresenter extends BasePresenter<SettingsContract.View> implements SettingsContract.Presenter {
    @Inject
    public SettingsPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void subscribeEvent() {
        super.subscribeEvent();
    }

    @Override
    public void setNightMode(boolean isNightMode) {
        mDataManager.setNightMode(isNightMode);
    }

    @Override
    public void getNightMode() {
        mView.showNightMode(mDataManager.getNightMode());
    }

    @Override
    public void setNoImageMode(boolean isNoImageMode) {
        mDataManager.setNoImageMode(isNoImageMode);
    }

    @Override
    public void getNoImageMode() {
        mView.showNoImageMode(mDataManager.getNoImageMode());
    }

    @Override
    public void setAutoCache(boolean isAutoCache) {
        mDataManager.setAutoCache(isAutoCache);
    }

    @Override
    public void getAutoCache() {
        mView.showAutoCache(mDataManager.getAutoCache());
    }
}
