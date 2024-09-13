package vn.edu.usth.usthweather;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import java.util.ArrayList;

public class AdapterViewPager extends FragmentStateAdapter {

    ArrayList<Fragment> arr;
    public AdapterViewPager(@NonNull FragmentActivity fragmentManager, ArrayList<Fragment> arr) {
        super(fragmentManager);
        this.arr = arr;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position)
        {
            case 1:
                return new ForecastFragment();
            case 2:
                return new WeatherAndForecastFragment();
            case 0:
            default:
                return new WeatherFragment();
        }
    }

    @Override
    public int getItemCount() {
        return arr.size();
    }
}


