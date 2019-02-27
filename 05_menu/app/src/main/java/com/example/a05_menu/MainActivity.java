package com.example.a05_menu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // View.OnClickListener를 implements 했을 경우 이렇게 사용하고 @override onClick()을 구현하면 됨
        findViewById(R.id.textView).setOnClickListener(this);
    }

    // activity에서 메뉴 생성할 거 있으면 onCreateOptionsMenu() 호출해 줄 테니까 재정의 해라..
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    // 메뉴가 선택되었을 때 동작 재정의
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.item_menu_1:
                Toast.makeText(MainActivity.this, "메뉴1", Toast.LENGTH_SHORT).show();
                break;
            case R.id.item_menu_2:
                Toast.makeText(MainActivity.this, "메뉴2", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.textView){

        }
    }
}
