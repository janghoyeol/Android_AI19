package com.example.myapplication;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private Handler handler;
    private Runnable runnable;
    private long startTime;
    private Button button;
    private MediaPlayer mediaPlayer;
    private int[] audioResources = {R.raw.hello, R.raw.hello2, R.raw.hello3, R.raw.hello4};
    private int currentAudioIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

                // 특정 시간에 오디오 파일 재생
                if (seconds == 5 || seconds == 15 || seconds == 25 || seconds == 35) {
                    playAudio();
                }

                // 다음 호출을 1초 후에 예약
                handler.postDelayed(this, 1000);
            }
        };

        // 최초 호출을 1초 후에 예약
        handler.postDelayed(runnable, 1000);

        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 버튼 클릭 시 다른 페이지로 이동
                Intent intent = new Intent(MainActivity.this, OtherActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 액티비티가 종료되면 예약된 호출 취소
        handler.removeCallbacks(runnable);

        // MediaPlayer 정리
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    private void playAudio() {
        // 현재 인덱스에 해당하는 오디오 파일 재생
        if (currentAudioIndex < audioResources.length) {
            Uri audioUri = Uri.parse("android.resource://" + getPackageName() + "/" + audioResources[currentAudioIndex]);
            mediaPlayer = MediaPlayer.create(this, audioUri);
            mediaPlayer.start();
            currentAudioIndex++;
        }
    }

}