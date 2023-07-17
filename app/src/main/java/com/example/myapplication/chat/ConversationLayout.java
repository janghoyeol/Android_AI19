package com.example.myapplication.chat;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class ConversationLayout extends LinearLayout {
    public ConversationLayout(Context context) {
        super(context);
        init();
    }

    public ConversationLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        setOrientation(LinearLayout.VERTICAL);
    }

    public void addChatBubble(boolean isSentByUser, String message) {
        ChatBubbleView chatBubbleView = new ChatBubbleView(getContext());
        chatBubbleView.setMessage(message);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        if (isSentByUser) {
            layoutParams.gravity = Gravity.END;
        } else {
            layoutParams.gravity = Gravity.START;
        }
        layoutParams.setMargins(16, 16, 16, 16);
        chatBubbleView.setLayoutParams(layoutParams);

        addView(chatBubbleView);
    }
}
