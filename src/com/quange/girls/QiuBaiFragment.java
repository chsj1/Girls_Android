package com.quange.girls;

import com.quange.views.GQiuBaiListView;
import com.quange.views.PagerSlidingTabStrip;

import android.R.color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.app.ActionBar.Tab;
import android.support.v7.app.ActionBar.TabListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class QiuBaiFragment extends Fragment implements TabListener{
	private View fgmView;
	private ViewPager qiubaiViewPaper;
	private PagerSlidingTabStrip qiubaiTabs;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		if (fgmView == null) {
			fgmView = inflater.inflate(R.layout.fragment_qiubai, container, false);
		
			qiubaiViewPaper = (ViewPager) fgmView.findViewById(R.id.qiubaiViewPaper);
			
			qiubaiViewPaper.addView(new GQiuBaiListView(getActivity(),0).getView());
			qiubaiViewPaper.addView(new GQiuBaiListView(getActivity(),1).getView());
			qiubaiViewPaper.addView(new GQiuBaiListView(getActivity(),2).getView());

			qiubaiViewPaper.setAdapter(new PagerAdapter() {
				String[] title = { "最新", "纯图", "最热" };
				public boolean isViewFromObject(View view, Object o) {
					return view == o;
				}

				public int getCount() {
					return qiubaiViewPaper.getChildCount();
				}

				public void destroyItem(View container, int position, Object object) {
				}

				public Object instantiateItem(View container, int position) {
					return qiubaiViewPaper.getChildAt(position);
				}
		
				public CharSequence getPageTitle(int position) {
					return title[position];
				}
				
			});

			//
			qiubaiTabs = (PagerSlidingTabStrip)fgmView.findViewById(R.id.tabs);
			qiubaiTabs.setOnPageChangeListener(mPagerChangerListener);
			qiubaiTabs.setViewPager(qiubaiViewPaper);
			 //tab 宽度均分
			qiubaiTabs.setShouldExpand(true);
			qiubaiTabs.setDividerColor(color.white);
	        //设置选中的滑动指示
			qiubaiTabs.setIndicatorColor(this.getResources().getColor(R.color.red));
			qiubaiTabs.setIndicatorHeight(3);
	        //设置背景颜色
			qiubaiTabs.setBackgroundColor(getResources().getColor(R.color.white));
			

		}

		
		return fgmView;
	}
	
	OnPageChangeListener mPagerChangerListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        	qiubaiTabs.setTranslationX(0);
        }

        @Override
        public void onPageSelected(int position) {
            switch (position){
                case 0:
                    //MyToast.makeText(MainActivity.this, "the page is " + "messager", Toast.LENGTH_SHORT);
                    break;
                case 1:
                    //MyToast.makeText(MainActivity.this, "the page is " + "news", Toast.LENGTH_SHORT);
                    break;
                case 2:
                    //MyToast.makeText(MainActivity.this, "the page is " + "user", Toast.LENGTH_SHORT);
                    break;
            }


        }

        @Override
        public void onPageScrollStateChanged(int state) {


        }
    };


    @Override
    public void onTabSelected(Tab tab, FragmentTransaction fragmentTransaction) {

    	qiubaiViewPaper.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(Tab tab, FragmentTransaction fragmentTransaction) {

    }

    @Override
    public void onTabReselected(Tab tab, FragmentTransaction fragmentTransaction) {

    }
}

