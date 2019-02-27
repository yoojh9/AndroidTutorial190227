package com.example.a01_widget;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    TextView textView;
    EditText myEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myEditText = findViewById(R.id.myEditText); // 이 때 메모리 주소를 가지면서 인스턴스를 가지게 됨
        textView = findViewById(R.id.textView);

        // set : 리스너를 하나만 관리하고 있어서 다른걸 추가하게 되면 없어지는데
        // add : 리스너를 여러개 등록할 수 있다.
        myEditText.addTextChangedListener(new TextWatcher() {
            // 글자 개수 제한
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                textView.setText(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        Button btnHello = findViewById(R.id.btnHello);
        btnHello.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = myEditText.getText().toString();   // EditText.getText()가 Editable 객체이므로 toString() 하는 작업이 필요함.
                textView.setText(str);
            }
        });

        // 체크 박스
        CheckBox checkBox = findViewById(R.id.checkBox);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    Toast.makeText(MainActivity.this, "checked", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "check box is checked");
                } else {
                    Log.d(TAG, "check box is unChecked");
                }
            }
        });

        // 라디오 버튼
        RadioGroup radioGroup = findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId){
                    case R.id.radio1:
                        break;
                    case R.id.radio2:
                        break;
                    case R.id.radio3:
                        break;
                    default:
                        break;
                }

            }
        });


    }
}
