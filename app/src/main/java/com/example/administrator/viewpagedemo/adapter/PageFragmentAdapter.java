package com.example.administrator.viewpagedemo.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.ListFragment;

import java.util.List;

public class PageFragmentAdapter extends FragmentPagerAdapter {
	private List<ListFragment> fragmentList;
	private FragmentManager fm;
	public PageFragmentAdapter(FragmentManager fm, List<ListFragment> fragmentList) {
		super(fm);
		// TODO Auto-generated constructor stub
		this.fragmentList=fragmentList;
		this.fm=fm;
	}

	@Override
	public Fragment getItem(int idx) {
		// TODO Auto-generated method stub
		return (Fragment)(fragmentList.get(idx%fragmentList.size()));
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return fragmentList.size();
	}
	@Override  
	public int getItemPosition(Object object) {  
	   return POSITION_NONE;//没有找到child要求重新加载
	}
}