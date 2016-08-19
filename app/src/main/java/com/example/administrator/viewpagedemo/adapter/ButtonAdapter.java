package com.example.administrator.viewpagedemo.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.administrator.viewpagedemo.MainActivity;
import com.example.administrator.viewpagedemo.R;

/**
 * Created by Administrator on 2016/8/16.
 */
public class ButtonAdapter extends BaseAdapter {
    private Activity activity;

    public ButtonAdapter(Activity c) {
        activity = c;
    }

    public int getCount() {
        return Buttons.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return position;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(final int position, View convertView, ViewGroup parent) {
        Button button;
        if (convertView == null) {  // if it's not recycled, initialize some attributes
            button = new Button(activity);
        } else {
            button = (Button) convertView;
        }

        button.setText(Buttons[position]);
        button.setId(position);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewPager viewPager=(ViewPager)activity.findViewById(R.id.newsListViewPager);
                viewPager.setCurrentItem(position);

                LinearLayout linearLayout=(LinearLayout)activity.findViewById(R.id.allCategoryLayout);
                linearLayout.setVisibility(View.INVISIBLE);
            }
        });
        return button;
    }

    // references to our images
    private String[] Buttons = {
            "头条","娱乐","体育","财经","科技","汽车","时尚","社会","热点"
    };
}
