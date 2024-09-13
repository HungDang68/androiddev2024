package vn.edu.usth.usthweather;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;


public class HomeFragmentPagerAdapter extends FragmentPagerAdapter {

    public HomeFragmentPagerAdapter(@NonNull FragmentManager fm,int beh) {
        super(fm,beh);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
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
    public int getCount() {
        return 0;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title="";
        switch (position){
            case 0:
                title="Ha Noi";
                break;
            case 1:
                title="Sth";
                break;
            case 2:
                title="Sth2";
                break;

    }
        return title;
}
}
