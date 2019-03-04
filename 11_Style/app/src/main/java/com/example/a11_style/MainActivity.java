package com.example.a11_style;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
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

        TableLayout tableLayout = findViewById(R.id.tableLayout);
        // index로 요소를 가져옴

        int number = 1;
        for(int i=2; i<tableLayout.getChildCount() - 1; i++){
            TableRow row = (TableRow) tableLayout.getChildAt(i);
            for(int k=0; k<row.getChildCount(); k++){
                Button button = (Button) row.getChildAt(k);
                button.setText(""+number);
                button.setOnClickListener(numberListener);
                number++;
            }
        }

        TableRow bottomRow = (TableRow) tableLayout.getChildAt(tableLayout.getChildCount()-1);
        Button zeroButton = (Button) bottomRow.getChildAt(1);
        zeroButton.setText("0");
        zeroButton.setOnClickListener(numberListener);

        Button deleteButton = (Button) bottomRow.getChildAt(0);
        deleteButton.setText("delete");
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                workingTextView.setText("0");
            }
        });

        Button enterButton = (Button) bottomRow.getChildAt(2);
        enterButton.setText("enter");
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
