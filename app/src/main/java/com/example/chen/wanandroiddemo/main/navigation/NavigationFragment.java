package com.example.chen.wanandroiddemo.main.navigation;

import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.chen.wanandroiddemo.R;
import com.example.chen.wanandroiddemo.adapter.NavigationAdapter;
import com.example.chen.wanandroiddemo.app.WanAndroidApp;
import com.example.chen.wanandroiddemo.base.fragment.BaseLoadFragment;
import com.example.chen.wanandroiddemo.main.navigation.contract.NavigationContract;
import com.example.chen.wanandroiddemo.core.bean.Navigation;
import com.example.chen.wanandroiddemo.di.component.DaggerNavigationComponent;
import com.example.chen.wanandroiddemo.di.module.NavigationModule;
import com.example.chen.wanandroiddemo.main.navigation.presenter.NavigationPresenter;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import q.rorbin.verticaltablayout.VerticalTabLayout;
import q.rorbin.verticaltablayout.adapter.TabAdapter;
import q.rorbin.verticaltablayout.widget.ITabView;
import q.rorbin.verticaltablayout.widget.TabView;

/**
 * @author : chenshuaiyu
 * @date : 2019/3/22 15:19
 */
public class NavigationFragment extends BaseLoadFragment<NavigationPresenter> implements NavigationContract.View {

    @BindView(R.id.vertical_tab_layout)
    VerticalTabLayout mVerticalTabLayout;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    private List<Navigation> mNavigations;
    private NavigationAdapter mNavigationAdapter;
    private TabAdapter mTabAdapter;
    private LinearLayoutManager mLayoutManager;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_nagivation;
    }

    @Override
    protected void inject() {
        DaggerNavigationComponent.builder()
                .appComponent(((WanAndroidApp)getActivity().getApplication()).getAppComponent())
                .navigationModule(new NavigationModule())
                .build()
                .inject(this);
    }

    @Override
    protected void initData() {
        presenter.subscribeEvent();

        mNavigations = new ArrayList<>();
        mNavigationAdapter = new NavigationAdapter(R.layout.item_navigation, mNavigations);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mNavigationAdapter);
        leftAndRightLinkage();
    }

    private void leftAndRightLinkage() {
        mVerticalTabLayout.addOnTabSelectedListener(new VerticalTabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabView tab, int position) {
                selectTag(position);
            }

            @Override
            public void onTabReselected(TabView tab, int position) {

            }
        });

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }

    private void selectTag(int position) {
        mRecyclerView.stopScroll();
        scrollToPosition(position);
    }

    private void scrollToPosition(int currentPosition) {
        int firstPosition = mLayoutManager.findFirstVisibleItemPosition();
        int lastPosition = mLayoutManager.findLastVisibleItemPosition();
        Toast.makeText(getActivity(), firstPosition + " : " + lastPosition, Toast.LENGTH_SHORT).show();
        if (currentPosition <= firstPosition) {
            mRecyclerView.smoothScrollToPosition(currentPosition);
        } else if (currentPosition <= lastPosition) {
            int top = mRecyclerView.getChildAt(currentPosition - firstPosition).getTop();
            mRecyclerView.smoothScrollBy(0, top);
        } else {
            mRecyclerView.smoothScrollToPosition(currentPosition);
//            needScroll = true;
        }
    }

    @Override
    public void reLoad() {
        presenter.getNavigationTab();
    }

    @Override
    public void showNavigationTab(List<Navigation> navigations) {
        mNavigations.clear();
        mNavigations.addAll(navigations);
        mNavigationAdapter.notifyDataSetChanged();

        mTabAdapter = new TabAdapter() {
            @Override
            public int getCount() {
                return mNavigations.size();
            }

            @Override
            public ITabView.TabBadge getBadge(int position) {
                return null;
            }

            @Override
            public ITabView.TabIcon getIcon(int position) {
                return null;
            }

            @Override
            public ITabView.TabTitle getTitle(int position) {
                return new TabView.TabTitle.Builder()
                        .setContent(mNavigations.get(position).getName())
                        .setTextColor(ContextCompat.getColor(getActivity(), R.color.sky_blue),
                                ContextCompat.getColor(getActivity(), R.color.gray))
                        .build();
            }

            @Override
            public int getBackground(int position) {
                return -1;
            }
        };
        mVerticalTabLayout.setTabAdapter(mTabAdapter);
        mVerticalTabLayout.setIndicatorColor(R.color.white);
    }
}
