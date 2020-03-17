package com.example.androiddemo;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.androiddemo.ui.dashboard.DashboardFragment;
import com.example.androiddemo.ui.home.HomeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Context context = this;
    private static final String TAG = "Home界面";
    private ViewPager hViewPager;//翻页框架,用于支持滑动切换左右界面

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //===========底栏设置===========
        final BottomNavigationView navView = findViewById(R.id.nav_view);//绑定底栏布局
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);//底栏按钮监听

        //============主页面===========
        hViewPager=findViewById(R.id.vp_fragment);//绑定布局文件的vp
        List<Fragment> slideFrag = new ArrayList<>();//保存左右两个页面
        Fragment firstFrag = new HomeFragment();//第一页
        Fragment secFrag = new DashboardFragment();//第二页

        slideFrag.add(firstFrag);//添加第一页
        slideFrag.add(secFrag);//添加第二页

        homePagerAdapter hAdapter=new homePagerAdapter(getSupportFragmentManager(),this,slideFrag);//构建主页适配器
        hViewPager.setAdapter(hAdapter);//绑定适配器
        hViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener(){//主页页面切换监听器
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.d(TAG,"监测到滑动操作");
            }

            @Override
            public void onPageSelected(int position) {
                Log.d(TAG,"监测到页面切换操作");
                Toast.makeText(context,"现在是第"+(position+1)+"页",Toast.LENGTH_SHORT).show();
                navView.getMenu().getItem(position).setChecked(true);//按钮对应页面变化
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });




    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener//根据底栏点击，切换上方页面
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        //绑定按钮对应页面
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    hViewPager.setCurrentItem(0);
                    return true;
                case R.id.navigation_dashboard:
                    hViewPager.setCurrentItem(1);
                    return true;
            }
            return false;
        }
    };//底栏按钮

    class homePagerAdapter extends FragmentStatePagerAdapter {//自定义的主页翻页框架适配器

        Context context;
        List<Fragment> slideFrag;//绑定左右两页的存储列表

        public homePagerAdapter(FragmentManager fm, Context context, List<Fragment> slideFrag)
        {
            super(fm);
            this.slideFrag = slideFrag;
            this.context=context;
        }//初始化

        @Override
        public int getCount() {
            return slideFrag.size();
        }//返回页数

        @Override
        public Fragment getItem(int position) {
            return slideFrag.get(position);
        }//获取当前页号

    }
}
