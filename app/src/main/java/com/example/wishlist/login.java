package com.example.wishlist;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class login extends AppCompatActivity {
    EditText id, pw;
    Button btnlogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        id = findViewById(R.id.id);
        pw = findViewById(R.id.pw);
        btnlogin = findViewById(R.id.btnlogin);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dlg = new AlertDialog.Builder(login.this);
                dlg.setTitle("가입 정보 확인");
                dlg.setIcon(R.mipmap.ic_launcher_round);
                dlg.setMessage("아이디: "+id.getText()  +"\n비밀번호: "+ pw.getText());
                dlg.setPositiveButton("확인", null);
                dlg.show();
                dlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    }
                });
                dlg.setPositiveButton("확인", new DialogInterface.OnClickListener(){

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String input = id.getText().toString();

                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        intent.putExtra("id", input);
                        startActivity(intent);
                    }
                });
                dlg.show();
            }
        });
    }
}