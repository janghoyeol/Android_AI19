package com.example.myapplication;

import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private Handler handler;
    private Runnable runnable;
    private String[] textArray;
    private int currentIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        handler = new Handler();
        textArray = new String[]{"첫 번째 텍스트", "두 번째 텍스트", "세 번째 텍스트"};
        currentIndex = 0;

        // 5초마다 텍스트 업데이트를 위한 Runnable 생성
        runnable = new Runnable() {
            @Override
            public void run() {
                // 출력할 텍스트 선택
                String text = textArray[currentIndex];

                // 다음 인덱스로 이동
                currentIndex = (currentIndex + 1) % textArray.length;

                // 다음 호출을 5초 후에 예약
                handler.postDelayed(this, 10000);
            }
        };

        // 최초 호출을 5초 후에 예약
        handler.postDelayed(runnable, 10000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 액티비티가 종료되면 예약된 호출 취소
        handler.removeCallbacks(runnable);
    }
}

