package com.example.chen.wanandroiddemo.ui.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.Log;
import android.view.Menu;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chen.wanandroiddemo.R;
import com.example.chen.wanandroiddemo.base.activity.BaseActivity;
import com.example.chen.wanandroiddemo.contract.MainContract;
import com.example.chen.wanandroiddemo.di.component.DaggerMainActivityComponent;
import com.example.chen.wanandroiddemo.di.module.MainActivityModule;
import com.example.chen.wanandroiddemo.presenter.MainPresenter;
import com.example.chen.wanandroiddemo.ui.fragment.HomeFragment;
import com.example.chen.wanandroiddemo.ui.fragment.ProjectFragment;
import com.example.chen.wanandroiddemo.ui.fragment.SystemFragment;
import com.example.chen.wanandroiddemo.ui.fragment.WXFragment;
import com.example.chen.wanandroiddemo.utils.BNVUtil;

import butterknife.BindView;

public class MainActivity extends BaseActivity<MainPresenter>
        implements NavigationView.OnNavigationItemSelectedListener, MainContract.View {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.nav_view)
    NavigationView mNavigationView;
    @BindView(R.id.bottom_navigation_view)
    BottomNavigationView mBottomNavigationView;
//    @BindView(R.id.login)
//    TextView login;

    private HomeFragment mHomeFragment = new HomeFragment();
    private SystemFragment mSystemFragment = new SystemFragment();
    private WXFragment mWXFragment = new WXFragment();
    private ProjectFragment mProjectFragment = new ProjectFragment();

    private FragmentManager mFragmentManager = getSupportFragmentManager();
    private Fragment curFragment;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void inject() {
        DaggerMainActivityComponent.builder().mainActivityModule(new MainActivityModule()).build().inject(this);
    }

    @Override
    protected void initData() {
        setSupportActionBar(mToolbar);
        mNavigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        BNVUtil.disableShiftMode(mBottomNavigationView);

        curFragment = mHomeFragment;
        mFragmentManager.beginTransaction()
                .add(R.id.fragment_container, mHomeFragment)
                .add(R.id.fragment_container, mSystemFragment)
                .add(R.id.fragment_container, mWXFragment)
                .add(R.id.fragment_container, mProjectFragment)
                .hide(mSystemFragment)
                .hide(mWXFragment)
                .hide(mProjectFragment)
                .show(curFragment)
                .commit();

        mBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                FragmentTransaction transaction = mFragmentManager.beginTransaction().hide(curFragment);
                switch (menuItem.getItemId()) {
                    case R.id.menu_home:
                        if (curFragment != mHomeFragment)
                            curFragment = mHomeFragment;
                        break;
                    case R.id.menu_system:
                        if (curFragment != mSystemFragment)
                            curFragment = mSystemFragment;
                        break;
                    case R.id.menu_wxarticle:
                        if (curFragment != mWXFragment)
                            curFragment = mWXFragment;
                        break;
                    case R.id.menu_navigation:

                        break;
                    case R.id.menu_project:
                        if (curFragment != mProjectFragment)
                            curFragment = mProjectFragment;
                        break;
                    default:
                        break;
                }
                transaction.show(curFragment).commit();
                return true;
            }
        });

//        login.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
//                startActivity(intent);
//            }
//        });
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START))
            mDrawerLayout.closeDrawer(GravityCompat.START);
        else
            super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_search:
                startActivity(new Intent(this, SearchActivity.class));
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        item.setChecked(true);
        switch (item.getItemId()) {
            case R.id.menu_wanandroid:

                break;
            case R.id.menu_collection:

                break;
            case R.id.menu_settings:

                break;
            default:
                break;
        }
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }


}
