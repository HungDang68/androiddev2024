package vn.edu.usth.usthweather;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class WeatherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_weather);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ForecastFragment forecastFragment = new ForecastFragment();


        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragmentContainerView, ForecastFragment.class, null);
        fragmentTransaction.commit();
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

