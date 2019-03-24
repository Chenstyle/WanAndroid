package com.example.chen.wanandroiddemo.ui.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.example.chen.wanandroiddemo.R;
import com.example.chen.wanandroiddemo.adapter.ProjectTabViewPagerAdapter;
import com.example.chen.wanandroiddemo.base.fragment.BaseFragment;
import com.example.chen.wanandroiddemo.contract.ProjectContract;
import com.example.chen.wanandroiddemo.core.bean.Tab;
import com.example.chen.wanandroiddemo.di.component.DaggerProjectComponent;
import com.example.chen.wanandroiddemo.di.module.ProjectModule;
import com.example.chen.wanandroiddemo.presenter.ProjectPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Coder : chenshuaiyu
 * Time : 2019/3/21 8:34
 */
public class ProjectFragment extends BaseFragment<ProjectPresenter> implements ProjectContract.View {
    @BindView(R.id.view_pager)
    ViewPager mViewPager;
    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;

    private List<Fragment> mFragments;
    private ProjectTabViewPagerAdapter mPagerAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_project;
    }

    @Override
    protected void inject() {
        DaggerProjectComponent.builder().projectModule(new ProjectModule()).build().inject(this);
    }

    @Override
    protected void initData() {
        mFragments = new ArrayList<>();
        mPagerAdapter = new ProjectTabViewPagerAdapter(getChildFragmentManager(), mFragments);
        mViewPager.setAdapter(mPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);

        presenter.getProjectTab();
    }


    @Override
    public void showTab(List<Tab> projectTabList) {
        for (Tab tab : projectTabList) {
            mFragments.add(new ProjectTabFragment(tab));
        }
        mPagerAdapter.notifyDataSetChanged();
    }
}
