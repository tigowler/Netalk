package com.example.netalk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ChatbotActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatbot);

        LinearLayout layoutChatbotBody = (LinearLayout) findViewById(R.id.layout_chatbot_body);
        ImageButton btnSend = (ImageButton) findViewById(R.id.btn_chatbot_send);
        EditText editTextChatbot = (EditText)findViewById(R.id.edittext_chatbot);
        ImageButton btnBack = (ImageButton)findViewById(R.id.btn_back_chatbot);

//        1) 채팅 입력 시 내용 출력
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                1-1) send 버튼 클릭 시 edittext 값 받아오기
                String num = editTextChatbot.getText().toString();
//                1-2) TODO: 시간 받아오기 / 만일 서버에서 보내는 기능이 없으면 여기서 짜기
                String minute = "43";
                String hour = "11";
                String time = "PM";
//                1-3) edittext 값과 시간 값 반영하여 채팅 view 추가하기
                layoutChatbotBody.addView(createSendNumView(num, hour, minute, time));
//                1-4) edittext 비우기
                editTextChatbot.setText("");
            }
        });

//        2) TODO: 챗봇 반환 값 출력
//        2-1) TODO: 반환 내용 받기 // 내용, 시간
        String content = "🔥오늘의 메뉴🔥\n\n쉑쉑버거 어때요?👍";
        String minute = "59";
        String hour = "11";
        String time = "PM";
//        2-2) 내용 반영하여 채팅 view 추가하기
        layoutChatbotBody.addView(createReceiveChatbotView(content, hour, minute, time));

//        3) 뒤로 버튼 누르면 mainActivity로 이동
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChatbotActivity.this, SelectActivity.class);
                startActivity(intent);
            }
        });
    }

    private LinearLayout createReceiveChatbotView(String content, String hour, String minute, String time) {
        LinearLayout receiveChatbotLayout = (LinearLayout) View.inflate(this, R.layout.sub_chatbot_receive_text, null);
        LinearLayout contentLayout = (LinearLayout) receiveChatbotLayout.getChildAt(1);
        TextView contentTextView = (TextView) contentLayout.getChildAt(0);
        contentTextView.setText(content);

        TextView timeTextView = (TextView) contentLayout.getChildAt(1);
        timeTextView.setText(hour+":"+minute+" "+time);
        return receiveChatbotLayout;
    }

    private LinearLayout createSendNumView(String num, String hour, String minute, String time) {
        LinearLayout sendChatLayout = (LinearLayout) View.inflate(this, R.layout.sub_chatting_send_text, null);
        TextView sendChatText = (TextView) sendChatLayout.getChildAt(0);
        sendChatText.setText(num);

        TextView sentChatTime = (TextView) sendChatLayout.getChildAt(1);
        sentChatTime.setText(hour+":"+minute+" "+time);
        return sendChatLayout;
    }
}