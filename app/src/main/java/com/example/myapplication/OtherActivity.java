package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class OtherActivity extends AppCompatActivity {
    private TextView textView;
    private Handler handler;
    private Runnable runnable;
    private long startTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);

        textView = findViewById(R.id.text_view);
        handler = new Handler();
        startTime = System.currentTimeMillis();

        // 1초마다 실행 시간을 업데이트하는 Runnable 생성
        runnable = new Runnable() {
            @Override
            public void run() {
                // 현재 시간과 시작 시간의 차이 계산
                long elapsedTime = System.currentTimeMillis() - startTime;
                int seconds = (int) (elapsedTime / 1000) % 60;
                int minutes = (int) ((elapsedTime / (1000 * 60)) % 60);

                // 시간 값을 형식에 맞게 포맷팅하여 문자열로 변환
                String currentTime = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);

                // 텍스트뷰에 현재 시간 표시
                textView.setText(currentTime);

                // 다음 호출을 1초 후에 예약
                handler.postDelayed(this, 1000);
            }
        };

        // 최초 호출을 1초 후에 예약
        handler.postDelayed(runnable, 1000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 액티비티가 종료되면 예약된 호출 취소
        handler.removeCallbacks(runnable);
    }
}
