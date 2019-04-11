package com.example.chen.wanandroiddemo.presenter.activity;

import android.text.TextUtils;

import com.example.chen.wanandroiddemo.app.Constants;
import com.example.chen.wanandroiddemo.base.presenter.BasePresenter;
import com.example.chen.wanandroiddemo.contract.RegisterContract;
import com.example.chen.wanandroiddemo.core.DataManager;
import com.example.chen.wanandroiddemo.core.bean.BaseResponse;
import com.example.chen.wanandroiddemo.core.bean.LoginData;
import com.example.chen.wanandroiddemo.utils.RxUtils;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Coder : chenshuaiyu
 * Time : 2019/3/25 22:24
 */
public class RegisterPresenter extends BasePresenter<RegisterContract.View> implements RegisterContract.Presenter {
    @Inject
    public RegisterPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void getRegisterData(String useranme, String password, String repassword) {
        if (TextUtils.isEmpty(useranme) || TextUtils.isEmpty(password) || TextUtils.isEmpty(repassword)) {
            mView.showErrorMesssage("用户名或密码为空");
            return;
        }

        mDataManager.getRegisterData(useranme, password, repassword)
                .compose(RxUtils.switchSchedulers())
                .subscribe(new Observer<BaseResponse<LoginData>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(BaseResponse<LoginData> loginDataBaseResponse) {
                        LoginData data = loginDataBaseResponse.getData();
                        if (loginDataBaseResponse.getErrorCode() == Constants.SUCCESS_CODE
                                && data != null)
                            mView.showSuccessfulMesssage();
                        else
                            mView.showErrorMesssage(loginDataBaseResponse.getErrorMsg());
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
