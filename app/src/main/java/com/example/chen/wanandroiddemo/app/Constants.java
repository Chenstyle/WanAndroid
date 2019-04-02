package com.example.chen.wanandroiddemo.app;

import android.graphics.Color;

/**
 * Coder : chenshuaiyu
 * Time : 2019/3/16 11:12
 */
public class Constants {

    // URL
    public static final String BASE_URL = "https://www.wanandroid.com/";


    public static final int SUCCESS_CODE = 0;

    // prefs
    public static final String SHAREDPREFERENCES_NAME = "prefs";
    public static final String LOGIN_STATUS = "login_status";
    public static final String LOGIN_ACCOUNT = "login_account";
    public static final String LOGIN_PASSWORD = "login_password";


    // db
    public static final String DB_NAME = "wanandroid.db";

    // Intent key
    public static final String ARTICLE_URL = "article_url";
    public static final String ARTICLE_TITLE = "article_title";
    public static final String SYSTEM = "system_id";
    public static final String SEARCH_KEY = "search_key";


    public static final int[] COLOR_HOTWORD = new int[]{
            Color.parseColor("#90C5F0"),
            Color.parseColor("#91CED5"),
            Color.parseColor("#F88F55"),
            Color.parseColor("#C0AFD0"),
            Color.parseColor("#E78F8F"),
            Color.parseColor("#67CCB7"),
            Color.parseColor("#F6BC7E")
    };
}
