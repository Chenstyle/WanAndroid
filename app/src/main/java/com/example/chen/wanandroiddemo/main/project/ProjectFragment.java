package com.example.chen.wanandroiddemo.main.project;

import android.support.v4.app.Fragment;

import com.example.chen.wanandroiddemo.adapter.ViewPagerAdapter;
import com.example.chen.wanandroiddemo.base.fragment.BaseViewPagerFragment;
import com.example.chen.wanandroiddemo.core.DataManager;
import com.example.chen.wanandroiddemo.main.project.contract.ProjectContract;
import com.example.chen.wanandroiddemo.core.bean.Tab;
import com.example.chen.wanandroiddemo.main.project.presenter.ProjectPresenter;
import com.example.chen.wanandroiddemo.widget.StateLayout.StateLayoutManager;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : chenshuaiyu
 * @date : 2019/3/21 8:34
 */
public class ProjectFragment extends BaseViewPagerFragment<ProjectPresenter> implements ProjectContract.View {

    private List<Fragment> mFragments;
    private ViewPagerAdapter mPagerAdapter;

    @Override
    protected ProjectPresenter getPresenter() {
        return new ProjectPresenter(DataManager.getInstance());
    }

    @Override
    protected StateLayoutManager getStateLayoutManager() {
        StateLayoutManager manager = super.getStateLayoutManager();
        manager.setOnReLoadListener(() -> mPresenter.getProjectTab());
        return manager;
    }

    @Override
    protected void initView() {
        mPresenter.subscribeEvent();

        mFragments = new ArrayList<>();
        mPagerAdapter = new ViewPagerAdapter(getChildFragmentManager(), mFragments);
        mViewPager.setAdapter(mPagerAdapter);
        mTabLayout.setViewPager(mViewPager);
    }

    @Override
    public void showTab(List<Tab> projectTabList) {
        mFragments.clear();
        for (Tab tab : projectTabList) {
            ProjectTabFragment tabFragment = new ProjectTabFragment();
            tabFragment.setTab(tab);
            mFragments.add(tabFragment);
        }
        mPagerAdapter.notifyDataSetChanged();
        mTabLayout.notifyDataSetChanged();
    }
}
