package vn.edu.usth.usthweather;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;

public class WeatherActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager2;
    private HomeFragmentPagerAdapter homeFragmentPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_weather);
        tabLayout=findViewById(R.id.tab_layout);
        viewPager2=findViewById(R.id.view_pager);
        homeFragmentPagerAdapter=new HomeFragmentPagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager2.setAdapter(homeFragmentPagerAdapter);

        tabLayout.setupWithViewPager(viewPager2);



    }
    @Override
    protected void onStop(){
        super.onStop();
        Log.i("success","Stopped");
    }
    @Override
    protected void onResume(){
        super.onResume();
        Log.i("success","Resumed");
    }
    @Override
    protected void onPause(){
        super.onPause();
        Log.i("success","Paused");
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.i("success","Destroyed");
    }
    @Override
    protected void onStart(){
        super.onStart();
        Log.i("success","Started");
    }

}

