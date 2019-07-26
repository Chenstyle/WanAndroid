package com.example.chen.wanandroiddemo.di.component;

import com.example.chen.wanandroiddemo.di.module.SearchArticlesModule;
import com.example.chen.wanandroiddemo.ui.search.SearchArticlesActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @author : chenshuaiyu
 * @date : 2019/3/22 22:19
 */
@Singleton
@Component(modules = SearchArticlesModule.class)
public interface SearchArticlesComponent {
    void inject(SearchArticlesActivity searchArticlesActivity);

}
