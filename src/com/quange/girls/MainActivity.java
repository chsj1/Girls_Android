package com.quange.girls;
import java.util.Timer;
import java.util.TimerTask;


import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.TabHost.OnTabChangeListener;
import com.umeng.analytics.MobclickAgent;

public class MainActivity extends FragmentActivity {

	public static FragmentTabHost mTabHost;
	private Class<?> fragmentArray[] = {QiuBaiFragment.class,DouBanFragment.class};
	private int titleArray[] = {R.string.qiubai,R.string.douban};
	private Resources re;
	private TextView tv[] = { null, null };
	private int iconArray[] = {R.drawable.btn_qiubai_drawable,R.drawable.btn_douban_drawable};
	private View mseduView;
	protected static boolean isQuit = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        setupTabView();
        MobclickAgent.updateOnlineConfig(this);
		MobclickAgent.openActivityDurationTrack(false);
    }
    @Override
	protected void onResume() {
		super.onResume();
	
		MobclickAgent.onPageStart("导航页");
		MobclickAgent.onResume(this);
	}

	@Override
	protected void onPause() {
		super.onPause();
	
		MobclickAgent.onPageEnd("导航页");
		MobclickAgent.onPause(this);
	}

	@Override
	protected void onRestart() {
		super.onRestart();
	}

	protected void onDestroy() {
		super.onDestroy();
		
	}

	private void init() {
		re = this.getResources();
		
	}
	// 底部导航的文本颜色
		protected void setTvTextColor(String tabId) {
			for (int i = 0; i < 2; i++) {
				if (tv[i].getText().toString().equals(tabId)) {
					tv[i].setTextColor(re.getColor(R.color.color_two));
				} else {
					tv[i].setTextColor(re.getColor(R.color.black_deep));
				}
			}
		}
    private void setupTabView() {
    	mTabHost = (FragmentTabHost) findViewById(R.id.tabhost); 
		mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);
		mTabHost.getTabWidget().setDividerDrawable(null);
		int count = fragmentArray.length;
		for (int i = 0; i < count; i++) {
			TabHost.TabSpec tabSpec;
			mseduView = getTabItemView(i);
			tabSpec = mTabHost.newTabSpec(re.getString(titleArray[i])).setIndicator(mseduView);
			mTabHost.addTab(tabSpec, fragmentArray[i], null);
		}

		mTabHost.setOnTabChangedListener(new OnTabChangeListener() {
			public void onTabChanged(String tabId) {
				setTvTextColor(tabId);
			}
		});
    }

    private View getTabItemView(int index) {
		LayoutInflater layoutInflater = LayoutInflater.from(this);
		int lyTab = R.layout.tab_item_view;
		View view = layoutInflater.inflate(lyTab, null);

		ImageView imageView = (ImageView) view.findViewById(R.id.iv_icon);
		imageView.setImageResource(iconArray[index]);

		TextView textView = (TextView) view.findViewById(R.id.tv_icon);
		tv[index] = textView;
		textView.setText(re.getString(titleArray[index]));
		if (index == 0) {
			textView.setTextColor(re.getColor(R.color.bottom_tab_text_true));
		} else {
			textView.setTextColor(re.getColor(R.color.black_deep));
		}
		return view;
	}

    @Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			boolean flag = false;
			if (isQuit) {
				// Intent home = new Intent(Intent.ACTION_MAIN);
				// home.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				// home.addCategory(Intent.CATEGORY_HOME);
				// startActivity(home);
				// Process.killProcess(Process.myPid());
				finish();
			} else {
				Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();
				isQuit = true;
				Timer timer = new Timer(); // 实例化Timer定时器对象
				timer.schedule(new TimerTask() { // schedule方法(安排,计划)需要接收一个TimerTask对象和一个代表毫秒的int值作为参数
							@Override
							public void run() {
								isQuit = false;
							}
						}, 3000);
			}
			return flag;
		}
		return super.onKeyDown(keyCode, event);

	}
}
