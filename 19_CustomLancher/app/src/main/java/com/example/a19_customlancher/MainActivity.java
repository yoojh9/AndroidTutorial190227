package com.example.a19_customlancher;

import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        setupAdapter();
    }

    private void setupAdapter(){
        Intent startupIntent = new Intent(Intent.ACTION_MAIN);
        startupIntent.addCategory(Intent.CATEGORY_LAUNCHER);

        List<ResolveInfo> activities = getPackageManager()
                .queryIntentActivities(startupIntent, 0);
        Collections.sort(activities, new Comparator<ResolveInfo>() {
            @Override
            public int compare(ResolveInfo o1, ResolveInfo o2) {
                return String.CASE_INSENSITIVE_ORDER.compare(
                        o1.loadLabel(getPackageManager()).toString(),
                        o2.loadLabel(getPackageManager()).toString()
                );
            }
        });

        Log.i("activities", "found : "+activities.size());

        recyclerView.setAdapter(new ActivitiesAdapter(activities));
    }

    class ActivitiesAdapter extends RecyclerView.Adapter<ActivitiesAdapter.ActivityHolder> {
        List<ResolveInfo> activities;

        public ActivitiesAdapter(List<ResolveInfo> activities) {
            this.activities = activities;
        }

        @NonNull
        @Override
        public ActivityHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View v = LayoutInflater.from(MainActivity.this)
                    .inflate(android.R.layout.simple_list_item_1, viewGroup, false);
            return new ActivityHolder(v);
        }

        @Override
        public void onBindViewHolder(@NonNull ActivityHolder activityHolder, int i) {
            activityHolder.bindActivity(activities.get(i));
        }

        @Override
        public int getItemCount() {
            return activities.size();
        }

        class ActivityHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            ResolveInfo resolveInfo;
            TextView textView;

            public ActivityHolder(@NonNull View itemView) {
                super(itemView);
                textView = (TextView) itemView;
                itemView.setOnClickListener(this);
            }
            public void bindActivity(ResolveInfo info){
                resolveInfo = info;
                textView.setText(info.loadLabel(getPackageManager()).toString());
            }

            @Override
            public void onClick(View v) {
                String packageName = resolveInfo.activityInfo.applicationInfo.packageName;
                String activityName = resolveInfo.activityInfo.name;
                Intent intent = new Intent(Intent.ACTION_MAIN)
                        .setClassName(packageName, activityName)
                        .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);   // 런처처럼 동작하게 하기 위해 NEW_TASK 플래그를 준다.
                startActivity(intent);
            }
        }
    }
}
