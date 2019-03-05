package com.example.a17_fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements  View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btnAdd).setOnClickListener(this);
        findViewById(R.id.btnRemove).setOnClickListener(this);
        findViewById(R.id.btnReplace).setOnClickListener(this);
        findViewById(R.id.btnHide).setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.frame);

        switch (v.getId()){
            case R.id.btnAdd:
                if(fragment == null){
                    fragment = new BlankFragment();
                    FragmentTransaction transaction = fm.beginTransaction();
                    transaction.add(R.id.frame, fragment, "counter");
                    transaction.addToBackStack(null);       // 백스택에 추가
                    transaction.commit();
                }
                break;
            case R.id.btnRemove:
                if(fragment!=null){
                    FragmentTransaction transaction = fm.beginTransaction();
                    transaction.remove(fragment);
                    transaction.commit();
                }
                break;
            case R.id.btnReplace:
                if(fragment!=null){
                    FragmentTransaction transaction = fm.beginTransaction();
                    if(fragment.getTag().equals("counter")){
                        TextFragment textFragment = new TextFragment();
                        transaction.replace(R.id.frame, textFragment, "text");
                    } else {
                        BlankFragment blankFragment = new BlankFragment();
                        transaction.replace(R.id.frame, blankFragment, "counter");
                    }
                    transaction.addToBackStack(null);        // 백스택에 추가
                    transaction.commit();
                }
                break;
            case R.id.btnHide:
                if(fragment!=null){
                    FragmentTransaction transaction = fm.beginTransaction();

                    if(fragment.isHidden()){
                        transaction.show(fragment);
                    } else {
                        transaction.hide(fragment);
                    }
                    transaction.commit();
                }
                break;
        }
    }
}
