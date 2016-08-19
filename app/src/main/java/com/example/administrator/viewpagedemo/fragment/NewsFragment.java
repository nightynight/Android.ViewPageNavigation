package com.example.administrator.viewpagedemo.fragment;

import android.app.ListActivity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.administrator.viewpagedemo.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class NewsFragment extends ListFragment {
	private ArrayList<String> newsTitles;
	private View view;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		if(view==null){//优化View减少View的创建次数
			ListView listView=(ListView)LayoutInflater.from(getActivity()).inflate(R.layout.channel_layout,null);
			ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
			HashMap<String, String> map1 = new HashMap<String, String>();
			HashMap<String, String> map2 = new HashMap<String, String>();
			map1.put("news_title", newsTitles.get(0));
			map2.put("news_title", newsTitles.get(1));
			list.add(map1);
			list.add(map2);

			SimpleAdapter listAdapter = new SimpleAdapter(getActivity(), list,
					R.layout.news, new String[] { "news_title"},
					new int[] { R.id.newsTitle});
			setListAdapter(listAdapter);
			//设置fragment中的内容
			view=listView;
		}
		ViewGroup parent=(ViewGroup)view.getParent();
		if(parent!=null){//如果View已经添加到容器中，要进行删除，否则会报错
			parent.removeView(view);
		}
		return view;
	}
	@Override
	public void setArguments(Bundle bundle) {
		// TODO Auto-generated method stub
		newsTitles= bundle.getStringArrayList("newsTitles");
	}
}
