package com.example.a18_viewpager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.a18_viewpager.model.PodcastItem;
import com.example.a18_viewpager.model.PodcastItemManger;

public class PlayerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        ViewPager viewPager = findViewById(R.id.viewPager);
        MyPlayerPagerAdapter adapter = new MyPlayerPagerAdapter(
                getSupportFragmentManager() );
        viewPager.setAdapter(adapter);
        int position = getIntent().getIntExtra("position", 0);
        viewPager.setCurrentItem(position);
    }

    class MyPlayerPagerAdapter extends FragmentStatePagerAdapter{
        PodcastItemManger manager;

        public MyPlayerPagerAdapter(FragmentManager fm) {
            super(fm);
            manager = PodcastItemManger.getInstance();
        }

        @Override
        public Fragment getItem(int i) {
            PodcastItem item = manager.getPodcastItemList().get(i);
            return PlayerFragment.newInstance(item);
        }

        @Override
        public int getCount() {
            return manager.getPodcastItemList().size();
        }
    }
}
