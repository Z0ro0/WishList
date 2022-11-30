package com.example.wishlist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.util.DisplayMetrics;
import android.util.SparseBooleanArray;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    TextView edtid;
    // 팝업창 닫는 버튼
    private Button close;

    // 팝업창을 띄우는 버튼
    private  Button btnAdd;

    // 팝업 윈도우
    private PopupWindow popupWindow;

    // 팝업 레이아웃에 위치한 editTxt
    private EditText name;
    private EditText link;

    // 팝업 레이아웃에 위치한 리스트 생성버튼
    private Button setOn;

    // 리스트뷰
    private ListView listView;

    // 데이터를 저장할 리스트
    List list = new ArrayList<>();

    ArrayAdapter adapter;

    private int mWidthPixel = 0;
    private int mHeightPixel = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        String text = intent.getStringExtra("id");

        edtid = findViewById(R.id.edtid);
        edtid.setText(text);

        // 윈도우 매니저, displayMetrics : 가로와 세로길이를 지정하기 위해
        WindowManager wm = getWindowManager(); // 윈도우 매니저 객체
        Display dp = wm.getDefaultDisplay();
        DisplayMetrics dm = new DisplayMetrics();
        dp.getMetrics(dm);

        mWidthPixel = dm.widthPixels;
        mHeightPixel = dm.heightPixels;



        // 리스트뷰 객체
        listView = (ListView) findViewById(R.id.listView);

        //리스트뷰와 리스트를 연결하기 위해 사용되는 어댑터
        adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, list);

        //리스트뷰의 어댑터를 지정해준다.
        listView.setAdapter(adapter);

        Button btnPopupLoad = (Button) findViewById(R.id.btnAdd);
        btnPopupLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 팝업윈도우를 띄울 메소드 호출
                initiatePopupWindow();
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(), list.get(i).toString() + "삭제되었습니다", Toast.LENGTH_SHORT).show();
                list.remove(i);
                adapter.notifyDataSetChanged();
            }
        });
    }

    // 팝업윈도우 띄움 메소드
    private void initiatePopupWindow(){
        try {
            // Mainactivity.this 권장, 팝업윈도우는 부분화면을 인플레이터 함.
            LayoutInflater inflater = (LayoutInflater) MainActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            // 뷰그룹으로 형변환
            View layout = inflater.inflate(R.layout.activity_write, (ViewGroup) findViewById(R.id.popupLayout));

            // focusable은 팝업윈도우를 실행시켰을 때 윈도우상의 아이템에 초점을 둘지 말지..
            popupWindow = new PopupWindow(layout, mWidthPixel-100, mHeightPixel-500, true);

            // 팝업윈도우 위치, 숫자값을 임의로 준다면 center에서 숫자값만큼 이동
            popupWindow.showAtLocation(layout, Gravity.CENTER, 0,0);

            // 팝업윈도우에 위치한 버튼을 사용할땐 뷰.findViewById로 해줌.
            close = (Button) layout.findViewById(R.id.close);
            close.setOnClickListener(disListener);

            // 팝업윈도우에 위치한 editText, 리스트 생성 button
            name = (EditText) layout.findViewById(R.id.name);
            link = (EditText) layout.findViewById(R.id.link);
            setOn = (Button) layout.findViewById(R.id.setOn);

            setOn.setOnClickListener(addListener);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    // popupWindow dismiss 리스너
    private View.OnClickListener disListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            popupWindow.dismiss();
        }
    };

    // btnData, 리스트 생성버튼을 눌렀을 시 적용될 리스너
    private View.OnClickListener addListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            // editText data를 arrayList에 add함
            list.add(name.getText().toString());
            list.add(link.getText().toString());
            //리스트뷰 동기화
            adapter.notifyDataSetChanged();
        }
    };

    // 삭제 버튼 클릭시 리스트 삭제
}