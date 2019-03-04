package com.example.a11_style;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView selectedTextView, workingTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        selectedTextView = findViewById(R.id.selectedTextView);
        workingTextView = findViewById(R.id.workingTextView);

        View.OnClickListener numberListener = new View.OnClickListener() {
            // View는 버튼에 붙으면 버튼이 됨
            @Override
            public void onClick(View v) {
                String str = ((Button) v).getText().toString();
                String working = workingTextView.getText().toString();
                if(working.equals("0")){
                    workingTextView.setText(str);
                } else {
                    workingTextView.append(str);
                }

            }
        };

        Button zeroButton = findViewById(R.id.zeroButton);
        zeroButton.setOnClickListener(numberListener);
        Button oneButton = findViewById(R.id.oneButton);
        oneButton.setOnClickListener(numberListener);

        Button enterButton = findViewById(R.id.enterButton);
        enterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = workingTextView.getText().toString();
                workingTextView.setText("0");
                selectedTextView.setText(str);
            }
        });
    }
}
