package com.example.netalk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class SelectActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);

        Intent intent = getIntent();
        String nickname = intent.getStringExtra("nickname");

        Button btnEnter = findViewById(R.id.btn_enter);
        RadioGroup radioGroup = findViewById(R.id.radio_group);
        RadioButton radioChatting = findViewById(R.id.radio_chatting);
        RadioButton radioChatbot = findViewById(R.id.radio_chatbot);
        ImageButton btnBack = findViewById(R.id.btn_back_select);

//        1) 입장하기 버튼 클릭 시 선택 항목 받아오기
        btnEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                1-1) Chatting이 선택된 경우
                if(radioChatting.isChecked()){
                    Intent intent = new Intent(SelectActivity.this, ChatActivity.class);
                    intent.putExtra("nickname", nickname);
                    startActivity(intent);
                }
//                1-2) Chatbot이 선택된 경우
                else if(radioChatbot.isChecked()){
                    Intent intent = new Intent(SelectActivity.this, ChatbotActivity.class);
                    intent.putExtra("nickname", nickname);
                    startActivity(intent);
                }
            }
        });

//        2) 뒤로 버튼 누르면 mainActivity로 이동
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SelectActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}