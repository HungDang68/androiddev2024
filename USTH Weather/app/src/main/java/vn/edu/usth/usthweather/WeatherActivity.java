package vn.edu.usth.usthweather;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.tabs.TabLayout;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class WeatherActivity extends AppCompatActivity {
    private BottomNavigationView mbottomNavigationView;
    private Toolbar toolbar;
    private ViewPager2 mviewPager;
    private ArrayList<Fragment> fragmentManager=new ArrayList<>();
    private MediaPlayer mediaPlayer;
    private static final int REQUEST_WRITE_EXTERNAL_STORAGE = 1;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_WRITE_EXTERNAL_STORAGE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                try {
                    extractAndPlayAudio();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else {Log.e("WeatherActivity", "Permission denied to write to external storage");
            }
        }
    }

    private void extractAndPlayAudio() throws IOException {
        try {
            // Extract the MP3 file to sdcard
            InputStream is = getResources().openRawResource(R.raw.sample);
            File outputFile = new File(Environment.getExternalStorageDirectory(), "sample_3s.mp3");
            FileOutputStream fos = new FileOutputStream(outputFile);

            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                fos.write(buffer, 0, length);
            }

            is.close();
            fos.close();

            // Play the extracted audio file
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setDataSource(outputFile.getAbsolutePath());
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            Log.e("WeatherActivity", "Error extracting and playing audio", e);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    REQUEST_WRITE_EXTERNAL_STORAGE);
        } else {
            try {
                extractAndPlayAudio();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        setContentView(R.layout.activity_weather);
        EdgeToEdge.enable(this);

        fragmentManager.add(new ForecastFragment());
        fragmentManager.add(new WeatherAndForecastFragment());
        fragmentManager.add(new WeatherFragment());


        mviewPager = findViewById(R.id.view_pager);
        mbottomNavigationView = findViewById(R.id.bottom_navigation);

        AdapterViewPager adapter = new AdapterViewPager(this, fragmentManager);
        mviewPager.setAdapter(adapter);

        mviewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                switch (position)
                {
                    case 0:
                        mbottomNavigationView.getMenu().findItem(R.id.Hanoi).setChecked(true);
                        break;
                    case 1:
                        mbottomNavigationView.getMenu().findItem(R.id.Sth).setChecked(true);
                        break;
                    case 2:
                        mbottomNavigationView.getMenu().findItem(R.id.Sth2).setChecked(true);
                        break;


                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });
        mbottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.Hanoi) {
                    mviewPager.setCurrentItem(0, true); // Switch to the first fragment
                    return true;
                }
                if (item.getItemId() == R.id.Sth) {
                    mviewPager.setCurrentItem(1, true); // Switch to the first fragment
                    return true;
                }
                if (item.getItemId() == R.id.Sth2) {
                    mviewPager.setCurrentItem(2, true); // Switch to the first fragment
                    return true;
                }
                return false;
            }
        });


        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.refesh_button) {
                    Toast.makeText(WeatherActivity.this, "Refresh clicked", Toast.LENGTH_SHORT).show();
                    return true;
                } else if (item.getItemId() == R.id.newActivity) {
                    Intent intent = new Intent(WeatherActivity.this, PrefActivity.class);
                    startActivity(intent);
                    return true;
                }
                return false;

            }
        });



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.toolbar,menu);
        return true;
    }


}


