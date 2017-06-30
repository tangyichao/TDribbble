package com.tyc.tdribbble.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;

import com.tyc.tdribbble.ui.user.UserActivity;

import java.util.List;

/**
 * 作者：tangyc on 2017/6/23
 * 邮箱：874500641@qq.com
 */
public class TFragmentPageAdapter extends FragmentPagerAdapter {
    private String[] tabStrs;
    private List<Fragment> list;

    public TFragmentPageAdapter(FragmentManager fm, String[] tabStrs, List<Fragment> list) {
        super(fm);
        this.tabStrs = tabStrs;
        this.list = list;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return super.isViewFromObject(view, object);
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabStrs[position];
    }
}
