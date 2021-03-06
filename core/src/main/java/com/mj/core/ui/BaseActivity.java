package com.mj.core.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;


/**
 * 基础activity类.
 * Created By: Robin Yang
 * Created At: 2014-11-24 15:13
 */
public abstract class BaseActivity extends ActionBarActivity {
    public final static int REQUEST_CODE_FIRSTTIME_SETTING = 1;
    public final static int REQUEST_CODE_CHANGE_SETTING = 2;

    public Context mContext;

    protected ProgressDialog mProgressDialog;
    protected boolean mIsLoading = false;

    final public CompositeSubscription compositeSubscription = new CompositeSubscription();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;

        /*
        LinearLayout frame = new LinearLayout(this);
        frame.setId(android.R.id.content);
        setContentView(frame, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        */
    }

    @Override
    public void onDestroy() {
        compositeSubscription.clear();
        super.onDestroy();
    }

    /**
     * 不能后退到上一个Fragment
     *
     * @param fragment
     */
    public void replaceFragment(final Fragment fragment) {
        findViewById(android.R.id.content).post(new Runnable() {
            public void run() {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(android.R.id.content, fragment);
                transaction.commit();
            }
        });
    }

    public void replaceFragment(final Fragment fragment, final int contentId) {
        findViewById(contentId).post(new Runnable() {
            public void run() {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(contentId, fragment);
                transaction.commit();
            }
        });
    }

    /**
     * 可以后退到上一个Fragment
     *
     * @param fragment
     */
    public void addFragment(final Fragment fragment) {
        findViewById(android.R.id.content).post(new Runnable() {
            @Override
            public void run() {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(android.R.id.content, fragment);
                transaction.addToBackStack(((Object) fragment).getClass().getName());
                transaction.commit();
            }
        });
    }


    /**
     * 后退到后退栈中的某个Fragment
     *
     * @param fragmentName
     */
    public void backToFragment(String fragmentName) {
        FragmentManager fragmentManager = this.getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        fragmentManager.popBackStackImmediate(fragmentName, 0);
        transaction.commit();
    }

    /**
     * 后退到第一个Fragment
     */
    public void backToTop() {
        FragmentManager fragmentManager = this.getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        for (int i = 0; i < fragmentManager.getBackStackEntryCount(); ++i) {
            fragmentManager.popBackStack();
        }
        transaction.commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.isCheckable()) {
            item.setCheckable(true);
        }
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            default:
                break;
        }
        return false;
    }
}
