package com.example.netalk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Handler handler = new Handler();

//        1) 중복 확인 클릭 시 닉네임 중복 확인
        EditText editText = findViewById(R.id.et_nickname);
        Button button = findViewById(R.id.btn_confirm);
        TextView warning = findViewById(R.id.text_warning);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                1-1) editText 값 가져오기
                String nickname = editText.getText().toString();
//                1-2) TODO: 중복 확인
                if(false){
//                    1-2-1) 중복일 때, 안내 문구 띄우기
                    warning.setVisibility(View.VISIBLE);
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            warning.setVisibility(View.INVISIBLE);
                        }
                    }, 2000);
                }
                else {
//                    1-2-2) 중복이 아닐 때, SelectActivity 이동, 닉네임 값 전달
                    Intent intent = new Intent(MainActivity.this, SelectActivity.class);
                    intent.putExtra("nickname", nickname);
                    startActivity(intent);
                }
            }
        });
    }
}