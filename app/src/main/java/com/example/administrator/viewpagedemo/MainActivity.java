package com.example.administrator.viewpagedemo;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.ListFragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.administrator.viewpagedemo.adapter.ButtonAdapter;
import com.example.administrator.viewpagedemo.adapter.PageFragmentAdapter;
import com.example.administrator.viewpagedemo.entity.ChannelList;
import com.example.administrator.viewpagedemo.entity.News;
import com.example.administrator.viewpagedemo.fragment.NewsFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends FragmentActivity implements ViewPager.OnPageChangeListener  {
    HorizontalScrollView horizontalScrollView;
    RadioGroup radioGroup;
    ViewPager viewPager;
    List<ChannelList> channelLists;
    private List<ListFragment> fragmentList=new ArrayList<ListFragment>();
    private PageFragmentAdapter adapter=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button allCategory=(Button)findViewById(R.id.allCategory);
        allCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout linearLayout=(LinearLayout)findViewById(R.id.allCategoryLayout);
                if (linearLayout.getVisibility()==View.VISIBLE){
                    linearLayout.setVisibility(View.INVISIBLE);
                }else{
                    linearLayout.setVisibility(View.VISIBLE);
                }


                GridView gridview = (GridView) findViewById(R.id.gridview);
                ButtonAdapter adapter=new ButtonAdapter(MainActivity.this);
                gridview.setAdapter(adapter);
            }
        });

        horizontalScrollView=(HorizontalScrollView)findViewById(R.id.channelScrollView);
        radioGroup=(RadioGroup)findViewById(R.id.channelRadioGroup);
        viewPager=(ViewPager)findViewById(R.id.newsListViewPager);

        //点击radioGroup中的item后更新viewPager
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                viewPager.setCurrentItem(checkedId);
            }
        });

        viewPager.setOnPageChangeListener(this);
        initData();
        initTab();
        initViewPager();
        //初始化显示第一个channel
        radioGroup.check(0);
    }

    public void initData(){
        channelLists=new ArrayList<ChannelList>();
        channelLists.add(new ChannelList("头条", Arrays.asList(new News[]{new News("头条一"),new News("头条二")})));
        channelLists.add(new ChannelList("娱乐", Arrays.asList(new News[]{new News("娱乐一"),new News("娱乐二")})));
        channelLists.add(new ChannelList("体育", Arrays.asList(new News[]{new News("体育一"),new News("体育二")})));
        channelLists.add(new ChannelList("财经", Arrays.asList(new News[]{new News("财经一"),new News("财经二")})));
        channelLists.add(new ChannelList("科技", Arrays.asList(new News[]{new News("科技一"),new News("科技二")})));
        channelLists.add(new ChannelList("汽车", Arrays.asList(new News[]{new News("汽车一"),new News("汽车二")})));
        channelLists.add(new ChannelList("时尚", Arrays.asList(new News[]{new News("时尚一"),new News("时尚二")})));
        channelLists.add(new ChannelList("社会", Arrays.asList(new News[]{new News("社会一"),new News("社会二")})));
        channelLists.add(new ChannelList("热点", Arrays.asList(new News[]{new News("热点一"),new News("热点二")})));
    }
    public void initTab(){
        for(int i=0;i<channelLists.size();i++){
            RadioButton rb=(RadioButton) LayoutInflater.from(this).inflate(R.layout.tab_rb, null);
            rb.setId(i);
            rb.setText(channelLists.get(i).getChannelName());
            radioGroup.addView(rb);
        }
    }
    public void initViewPager(){
        for(int i=0;i<channelLists.size();i++){
            ListFragment frag=new NewsFragment();
            Bundle bundle=new Bundle();
            ArrayList<String> newsTitles=new ArrayList<String>();
            for (int j=0;j<channelLists.get(i).getNewsList().size();j++){
                newsTitles.add(channelLists.get(i).getNewsList().get(j).getTitle());
            }
            bundle.putStringArrayList("newsTitles",newsTitles);
            frag.setArguments(bundle);
            fragmentList.add(frag);
        }
        //把fragmentList放到viewPage中
        adapter=new PageFragmentAdapter(super.getSupportFragmentManager(),fragmentList);
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        updateScorllView(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public void updateScorllView(int position){
        RadioButton rb=(RadioButton)radioGroup.getChildAt(position);
        rb.setChecked(true);
        int left=rb.getLeft();
        int width=rb.getMeasuredWidth();
        DisplayMetrics metrics=new DisplayMetrics();
        super.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int screenWidth=metrics.widthPixels;
        int len=left+width/2-screenWidth/2;
        horizontalScrollView.smoothScrollTo(len, 0);//滑动ScroollView
    }
}
