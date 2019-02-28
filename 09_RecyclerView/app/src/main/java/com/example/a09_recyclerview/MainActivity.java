package com.example.a09_recyclerview;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    String[] list = {
            "hello", "world", "oracle", "java",
            "hello", "world", "oracle", "java",
            "hello", "world", "oracle", "java",
            "hello", "world", "oracle", "java"
    };

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        MyAdapter adapter = new MyAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));    // 어떤 LayoutManager를 사용하는지 명시해줘야함.
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textView;
        String str;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView);
            itemView.setOnClickListener(this);
        }

        public void bindString(String str){
           this.str = str;
           textView.setText(this.str);
        }

        @Override
        public void onClick(View v) {
            Toast.makeText(MainActivity.this, str, Toast.LENGTH_SHORT).show();;
        }
    }

    class MyAdapter extends RecyclerView.Adapter<MyViewHolder>{

        // ViewHolder 생성
        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            LayoutInflater inf = LayoutInflater.from(MainActivity.this);
            View v = inf.inflate(R.layout.item_view, viewGroup, false);
            return new MyViewHolder(v);
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
           // myViewHolder.textView.setText(list[i]);
            myViewHolder.bindString(list[i]);
        }

        @Override
        public int getItemCount() {
            return list.length;
        }
    }
}
