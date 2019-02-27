package com.example.a04_customlistview;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<MyData> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addMyData();
        ListView listView = findViewById(R.id.listView);
        MyAdapter adapter = new MyAdapter();
        listView.setAdapter(adapter);
    }

    private void addMyData(){
        for(int i=0; i<100; i++){
            int iconId = (i%2==0)?R.mipmap.ic_launcher : R.mipmap.ic_launcher_round;
            list.add(new MyData("title"+i, "desc"+i, iconId));
        }
    }


    private class MyAdapter extends BaseAdapter {

        // 데이터 갯수
        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        // 아이템뷰를 convertView라고 부름. adpater에서 가장 중요한 역할.
        // 리스트뷰가 adapter에게 convertView 몇개를 만들어달라고 getView()를 요청할 수 있음
        // 최초에는 itemView를 inflate하는 작업이 필요하다.
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView==null){
                // 아이템 뷰를 직접 만듦 (custom)
                LayoutInflater inf = LayoutInflater.from(MainActivity.this);    // 인플레이터를 가져오고
                convertView = inf.inflate(R.layout.item_view, parent, false);   // convertView를 인플레이션 함
            }
            MyData myData = list.get(position);

            // 그냥 findViewById()를 하게 되면 액티비티의 UI에서 찾기 때문에 convertView.findViewById()를 호출해야 된다
            ImageView imageView = convertView.findViewById(R.id.itemIcon);
            TextView textViewTitle = convertView.findViewById(R.id.textViewTitle);
            TextView textViewDesc = convertView.findViewById(R.id.textViewDesc);

            textViewTitle.setText(myData.getTitle());
            textViewDesc.setText(myData.getDescription());
            imageView.setImageResource(myData.getImgIcon());

            return convertView;
        }
    }


}
