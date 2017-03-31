package weiyu.com.utovrdemo;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener {

	private BottomNavigationBar bottomNavigationBar;
	int lastSelectedPosition = 0;
	private LocationFragment mLocationFragment;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Fresco.initialize(this);

		String[] urls = getResources().getStringArray(R.array.url);
		List<String> list = Arrays.asList(urls);
		List<String> images = new ArrayList<>(list);

		Banner banner = (Banner) findViewById(R.id.banner_main);
		//简单使用
		banner.setImages(images)
				.setImageLoader(new GlideImageLoader())
				.setIndicatorGravity(BannerConfig.RIGHT)
				.start();
/*		Banner banner = new Banner(this);
		//设置banner样式
		banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
		//设置图片加载器
		banner.setImageLoader(new GlideImageLoader());
		//设置图片集合
		banner.setImages(images);
		//设置banner动画效果
		banner.setBannerAnimation(Transformer.DepthPage);
		//设置标题集合（当banner样式有显示title时）
//		banner.setBannerTitles(titles);
		//设置自动轮播，默认为true
		banner.isAutoPlay(true);
		//设置轮播时间
		banner.setDelayTime(1500);
		//设置指示器位置（当banner模式中有指示器时）
		banner.setIndicatorGravity(BannerConfig.CENTER);
		//banner设置方法全部调用完毕时最后调用
		banner.start();*/

		BottomNavigationBar bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_bar);
		bottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
		bottomNavigationBar
				.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC
				);
		bottomNavigationBar.addItem(new BottomNavigationItem(R.drawable.tb_music_on, "Home").setActiveColorResource(R.color.pink_))
				.addItem(new BottomNavigationItem(R.drawable.tb_fm_on, "Books").setActiveColorResource(R.color.pink_))
				.addItem(new BottomNavigationItem(R.drawable.tb_mymusic_on, "Music").setActiveColorResource(R.color.blue))
				.addItem(new BottomNavigationItem(R.drawable.tb_user_on, "Movies & TV").setActiveColorResource(R.color.green))
				.addItem(new BottomNavigationItem(R.drawable.tb3_on, "Games").setActiveColorResource(R.color.grey))
				.setFirstSelectedPosition(0)
				.initialise();
		bottomNavigationBar.setTabSelectedListener(this);
		setDefaultFragment();
	}

	/**
	 * 设置默认的
	 */
	private void setDefaultFragment() {
		FragmentManager fm = getFragmentManager();
		FragmentTransaction transaction = fm.beginTransaction();
		mLocationFragment = LocationFragment.newInstance("位置");
/*		transaction.replace(R.id.tabs, mLocationFragment);
		transaction.commit();*/
	}

	@Override
	public void onTabSelected(int position) {
		FragmentManager fm = this.getFragmentManager();
		//开启事务
		FragmentTransaction transaction = fm.beginTransaction();
		switch (position) {
			/*case 0:
				if (mLocationFragment == null) {
					mLocationFragment = LocationFragment.newInstance("位置");
				}
				transaction.replace(R.id.tb, mLocationFragment);
				break;*/
		/*	case 1:
				if (mFindFragment == null) {
					mFindFragment = FindFragment.newInstance("发现");
				}
				transaction.replace(R.id.tb, mFindFragment);
				break;
			case 2:
				if (mFavoritesFragment == null) {
					mFavoritesFragment = FavoritesFragment.newInstance("爱好");
				}
				transaction.replace(R.id.tb, mFavoritesFragment);
				break;
			case 3:
				if (mBookFragment == null) {
					mBookFragment = BookFragment.newInstance("图书");
				}
				transaction.replace(R.id.tb, mBookFragment);
				break;*/
			default:
				break;
		}
		// 事务提交
		transaction.commit();
	}

	@Override
	public void onTabUnselected(int position) {

	}

	@Override
	public void onTabReselected(int position) {

	}

}
