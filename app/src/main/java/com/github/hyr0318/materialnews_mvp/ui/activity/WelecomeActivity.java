package com.github.hyr0318.materialnews_mvp.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.github.hyr0318.baselibrary.base.activity.BaseActivity;
import com.github.hyr0318.baselibrary.eventbus.EventCenter;
import com.github.hyr0318.baselibrary.net.NetUtils;
import com.github.hyr0318.materialnews_mvp.R;
import com.github.hyr0318.materialnews_mvp.contract.WelecomeContract;
import com.github.hyr0318.materialnews_mvp.presenter.WelecomePresenterImpl;
import java.util.Date;

/**
 * Author：hyr on 2016/8/27 10:24
 * Email：2045446584@qq.com
 */
public class WelecomeActivity extends BaseActivity implements WelecomeContract.View {
    TextView tvVersionName;
    TextView welCopyright;

    final long WELCOME_TIME = 1500;
    private Date mStartDate;
    private WelecomePresenterImpl welecomePresenter;


    @Override protected int getContentViewLayoutID() {
        return R.layout.activity_welecome_layout;
    }


    @Override protected void onEventComming(EventCenter eventCenter) {

    }


    @Override protected void getViewById() {
         tvVersionName = (TextView) findViewById(R.id.tv_version);

        welCopyright = (TextView) findViewById(R.id.tv_copyright);
    }


    @Override protected View getLoadingView() {
        return null;
    }


    @Override protected void initViewsAndEvents() {

        mStartDate = new Date();

        welecomePresenter = new WelecomePresenterImpl(this, this);

        welecomePresenter.initialized();

        readyGoMain();
    }


    @Override protected void getIntentExtras(Bundle extras) {

    }


    private void readyGoMain() {

        if (getWaitTime() <= 0) {

            goMain();
        } else {
            tvVersionName.postDelayed(this::goMain, getWaitTime());
        }
    }


    private void goMain() {

        startNewActivityAndFinished(MainActivity.class);
    }


    private int getWaitTime() {

        long waitTime = WELCOME_TIME - ((new Date().getTime()) - mStartDate.getTime());

        return (int) waitTime;
    }


    @Override protected void onNetworkConnected(NetUtils.NetType type) {

    }


    @Override protected void onNetworkDisConnected() {

    }


    @Override protected boolean isBindEventBusHere() {
        return false;
    }


    @Override public void initializedView(String versionName, String copyright) {

        if (null != versionName && null != copyright) {

            tvVersionName.setText(getResources().getString(R.string.app_version) + versionName);

            welCopyright.setText(copyright);
        }

    }
}
