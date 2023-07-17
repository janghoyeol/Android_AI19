package com.example.myapplication.chat;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ChatBubbleView extends LinearLayout {
    private TextView messageTextView;

    public ChatBubbleView(Context context) {
        super(context);
        init();
    }

    public ChatBubbleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        setOrientation(LinearLayout.VERTICAL);
        setGravity(Gravity.START);

        // 말풍선 배경 설정
        GradientDrawable backgroundDrawable = new GradientDrawable();
        backgroundDrawable.setCornerRadius(16);
        backgroundDrawable.setColor(Color.GREEN);
        setBackground(backgroundDrawable);

        // 메시지 텍스트 뷰 설정
        messageTextView = new TextView(getContext());
        messageTextView.setTextColor(Color.WHITE);
        messageTextView.setPadding(16, 16, 16, 16);
        addView(messageTextView);
    }

    public void setMessage(String message) {
        messageTextView.setText(message);
    }
}
