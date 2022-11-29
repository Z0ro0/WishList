package com.example.wishlist;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class write extends AppCompatActivity {
    EditText name, link;
    Button setOn, addphoto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);
        init();
        SettingListener();
    }
        private void init(){
            name = findViewById(R.id.name);
            link = findViewById(R.id.link);
            setOn = findViewById(R.id.setOn);
        }

        private void SettingListener(){
            // 버튼에 클릭 이벤트 적용
            setOn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String input = name.getText().toString();
                    String input2 = link.getText().toString();

                    Intent intent = new Intent(write.this, MainActivity.class);
                    intent.putExtra("text", input);
                    intent.putExtra("text", input2);
                    startActivity(intent);
                }
            });
        }


    }
