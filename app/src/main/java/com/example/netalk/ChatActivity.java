package com.example.netalk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ChatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatting);

        Intent intent = getIntent();
        String nickname = intent.getStringExtra("nickname");

        ImageButton btnBack = findViewById(R.id.btn_back_chatting);
        LinearLayout layoutChatBody = findViewById(R.id.layout_chatting_body);
        LinearLayout layoutEnterText = findViewById(R.id.layout_enter_text);
        TextView enterNickname = findViewById(R.id.text_enter_nickname);

//        1) TODO: 입장 시 입장 문구 출력


//        2) 채팅 입력
//        2-1) send 버튼 클릭 시 edittext 값 받아오기
//        2-2) edittext 값 반영하여 채팅 view 추가하기

//        3) TODO: 채팅 도착 시
//        3-1) 채팅 도착 내용 받기
//        3-2) 채팅 내용 반영하여 채팅 view 추가하기

//        3) 뒤로 버튼 누르면 mainActivity로 이동
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChatActivity.this, SelectActivity.class);
                startActivity(intent);
            }
        });
    }
}