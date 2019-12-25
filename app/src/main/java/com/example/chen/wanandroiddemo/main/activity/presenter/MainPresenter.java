package com.example.chen.wanandroiddemo.main.activity.presenter;

import com.example.chen.wanandroiddemo.app.Constants;
import com.example.chen.wanandroiddemo.base.presenter.BasePresenter;
import com.example.chen.wanandroiddemo.main.activity.contract.MainContract;
import com.example.chen.wanandroiddemo.core.DataManager;
import com.example.chen.wanandroiddemo.core.bean.BaseResponse;
import com.example.chen.wanandroiddemo.core.bean.LoginData;
import com.example.chen.wanandroiddemo.utils.RxUtil;
import javax.inject.Inject;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @author : chenshuaiyu
 * @date : 2019/3/16 17:10
 */
public class MainPresenter extends BasePresenter<MainContract.View> implements MainContract.Presenter {

    @Inject
    public MainPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void setLoginUser() {
        if (mDataManager.getLoginStatus()) {
            mView.showLoginUser(mDataManager.getLoginAccount());
            mView.setLogoutVisibility(true);
        } else {
            mView.resetLoginUser();
            mView.setLogoutVisibility(false);
        }
    }

    @Override
    public boolean isLogin() {
        return mDataManager.getLoginStatus();
    }

    @Override
    public void logout() {
        mDataManager.logout()
                .compose(RxUtil.switchSchedulers())
                .subscribe(new Observer<BaseResponse<LoginData>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }
                    @Override
                    public void onNext(BaseResponse<LoginData> loginDataBaseResponse) {
                        if (loginDataBaseResponse.getErrorCode() == Constants.SUCCESS_CODE) {
                            mDataManager.setLoginStatus(false);
                            mDataManager.setLoginAccount("");
                            mDataManager.setLoginPassword("");
                            mView.resetLoginUser();
                            mView.setLogoutVisibility(false);
                            mView.showLogoutSucceed();
                        } else {
                            mView.showLogoutFailed();
                        }
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