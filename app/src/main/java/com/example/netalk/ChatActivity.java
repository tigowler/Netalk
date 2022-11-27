package com.example.netalk;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.OpenableColumns;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class ChatActivity extends AppCompatActivity {

    private static final int REQ_CODE = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatting);

        Intent intent = getIntent();
        String nickname = intent.getStringExtra("nickname");

        ImageButton btnBack = findViewById(R.id.btn_back_chatting);
        LinearLayout layoutChatBody = (LinearLayout)findViewById(R.id.layout_chatting_body);
        ImageButton btnSend = findViewById(R.id.btn_send);
        EditText editTextChat = findViewById(R.id.edittext_chat);
        ImageButton btnFile = findViewById(R.id.btn_file);

//        1) TODO: 입장 시 입장 문구 출력
        layoutChatBody.addView(createEnterTextView(nickname));

//        2) 채팅 입력
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                2-1) send 버튼 클릭 시 edittext 값 받아오기
                String chat = editTextChat.getText().toString();
//                2-2) TODO: 시간 받아오기
                String minute = "04";
                String hour = "9";
                String time = "PM";
//                2-3) edittext 값과 시간 값 반영하여 채팅 view 추가하기
                layoutChatBody.addView(createSendChatView(chat, hour, minute, time));
//                2-4) edittext 비우기
                editTextChat.setText("");
            }
        });

//        3) TODO: 채팅 도착 시
//        3-1) TODO: 채팅 도착 내용 받기 // 보낸 사용자 이름, 대화 내용, 시간
        String senderNickname = "이연수";
        String chatBody = "안녕 : )\n요새 뭐하고 지내?";
        String minute = "13";
        String hour = "9";
        String time = "PM";
//        3-2) 채팅 내용 반영하여 채팅 view 추가하기
        layoutChatBody.addView(createReceiveChatView(senderNickname, chatBody, hour, minute, time));

//        4) TODO: 파일 전송 버튼 클릭 시, 파일 화면 열기
        btnFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent().setType("*/*").setAction(Intent.ACTION_OPEN_DOCUMENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                startActivityForResult(Intent.createChooser(intent, "Select a file"), REQ_CODE);
            }
        });

//        5) 뒤로 버튼 누르면 mainActivity로 이동
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChatActivity.this, SelectActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQ_CODE && resultCode==RESULT_OK){
            Uri selectedFile = data.getData(); // The uri with the location of the file
//            경로 정보: selectedFile.getPath();, 전체 URI 정보: selectedFile.toString()
            Toast.makeText(getApplicationContext(), getFileNameFromUri(selectedFile)+"가 업로드 되었습니다.", Toast.LENGTH_LONG).show();
        }
    }

    @SuppressLint("Range")
    private String getFileNameFromUri(Uri uri) {
        String fileName = "";

        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        if(cursor!=null && cursor.moveToFirst()){
            fileName = cursor.getString(
                    cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
        }
        cursor.close();
        return fileName;
    }

    private LinearLayout createReceiveChatView(String senderNickname, String chatBody, String hour, String minute, String time) {
        LinearLayout receiveChatLayout = (LinearLayout) View.inflate(this, R.layout.sub_chatting_receive_text, null);
//        1) 사용자 이름 TextView 찾아서 수정
        LinearLayout profileLayout = (LinearLayout) receiveChatLayout.getChildAt(0);
        TextView senderNicknameTextView = (TextView) profileLayout.getChildAt(1);
        senderNicknameTextView.setText(senderNickname);

//        2) 채팅 내용, 시간 TextView 찾아서 수정
        LinearLayout chatBodyLayout = (LinearLayout) receiveChatLayout.getChildAt(1);
        TextView chatContentTextView = (TextView) chatBodyLayout.getChildAt(0);
        chatContentTextView.setText(chatBody);

        TextView chatTimeTextView = (TextView) chatBodyLayout.getChildAt(1);
        chatTimeTextView.setText(hour+":"+minute+" "+time);
        return receiveChatLayout;
    }

    private LinearLayout createSendChatView(String chat, String hour, String minute, String time) {
        LinearLayout sendChatLayout = (LinearLayout)View.inflate(this, R.layout.sub_chatting_send_text, null);
        TextView sendChatText = (TextView) sendChatLayout.getChildAt(0);
        sendChatText.setText(chat);

        TextView sentChatTime = (TextView) sendChatLayout.getChildAt(1);
        sentChatTime.setText(hour+":"+minute+" "+time);
        return sendChatLayout;
    }

    private LinearLayout createEnterTextView(String nickname) {
        LinearLayout enterTextLayout = (LinearLayout)View.inflate(this, R.layout.sub_chatting_enter_text, null);
        TextView enteredNickname = (TextView) enterTextLayout.getChildAt(0);
        enteredNickname.setText(nickname);
        return enterTextLayout;
    }
}