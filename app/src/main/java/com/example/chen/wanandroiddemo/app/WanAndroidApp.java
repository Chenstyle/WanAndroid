package com.example.chen.wanandroiddemo.app;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.example.chen.wanandroiddemo.core.DataManager;
import com.example.chen.wanandroiddemo.core.dao.DaoMaster;
import com.example.chen.wanandroiddemo.core.dao.DaoSession;
import com.example.chen.wanandroiddemo.di.component.AppComponent;
import com.example.chen.wanandroiddemo.di.component.DaggerAppComponent;
import com.example.chen.wanandroiddemo.di.module.AppModule;
import com.example.chen.wanandroiddemo.utils.NetUtil;

/**
 * @author : chenshuaiyu
 * @date : 2019/3/11 21:20
 */
public class WanAndroidApp extends Application {

    private static WanAndroidApp app;
    private DaoSession mDaoSession;
    private AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;

        //接入腾讯Bugly
//        CrashReport.initCrashReport(getApplicationContext(), "87a5a6c3e8", true);

        //接入LeakCanary
//        if (LeakCanary.isInAnalyzerProcess(this)) {
//            return;
//        }
//        LeakCanary.install(this);

        mAppComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
        mAppComponent.inject(this);

        DataManager dataManager = mAppComponent.getDataManager();
        dataManager.setNetState(NetUtil.getNetworkType());
        dataManager.setNightMode(false);
        dataManager.setNoImageMode(false);
        dataManager.setAutoCache(false);

        initGreenDao();
    }

    private void initGreenDao() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(app, Constants.DB_NAME);
        SQLiteDatabase database = helper.getWritableDatabase();
        mDaoSession = new DaoMaster(database).newSession();
    }

    public static WanAndroidApp getInstance() {
        return app;
    }

    public DaoSession getDaoSession() {
        return mDaoSession;
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }
}
