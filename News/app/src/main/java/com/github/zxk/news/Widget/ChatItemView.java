package com.github.zxk.news.Widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.zxk.news.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by 庄欣锴zz on 2016/6/24.
 */
public class ChatItemView extends LinearLayout {
    @Bind(R.id.image_left_chat)
    ImageView mLeft;
    @Bind(R.id.image_right_chat)
    ImageView mRight;
    @Bind(R.id.Relative_chat)
    RelativeLayout mRelativeLayout;
    @Bind(R.id.tv_chat)
    TextView mTextView;

    public ChatItemView(Context context) {
        this(context, null);
    }

    public ChatItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.item_chat, this);
        ButterKnife.bind(this, this);
    }

    /**
     * @param isRespond 设置自定义聊天内容布局是否为返回的聊天语句样式
     * @param message   content
     */
    public void setItem(boolean isRespond, String message) {
        if (isRespond) {
            //响应的信息
            mRight.setVisibility(View.INVISIBLE);
            mTextView.setBackgroundResource(R.drawable.bg_chat_respond);

        } else {
            //请求信息
            mLeft.setVisibility(View.INVISIBLE);
            mTextView.setBackgroundResource(R.drawable.bg_chat_request);
            mRelativeLayout.setGravity(Gravity.RIGHT);

        }
        mTextView.setText(message);
    }
}
