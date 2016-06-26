package com.github.zxk.news.UI.fragment;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.github.zxk.news.Presenter.ChatPresenter;
import com.github.zxk.news.Presenter.i.IChatPresenter;
import com.github.zxk.news.R;
import com.github.zxk.news.UI.fragment.base.BaseFragment;
import com.github.zxk.news.View.ChatView;
import com.github.zxk.news.Widget.ChatItemView;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by 庄欣锴zz on 2016/6/24.
 */
public class ChatFragment extends BaseFragment implements ChatView {
    View mView;
    @Bind(R.id.scrollView_chat)
    ScrollView mScrollView;
    @Bind(R.id.layout_chat)
    LinearLayout mLinearLayout;
    @Bind(R.id.edit_chat)
    EditText mEditText;
    @Bind(R.id.tv_bar_chat)
    TextView mSend;


    IChatPresenter mPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        mView = inflater.inflate(R.layout.fragment_chat, null);
//        ButterKnife.bind(this, mView);
        return mView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();

    }

    private void initView() {
        mEditText.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //如果输入指数大于0，mSend 改变背景颜色
                if (count != 0) {
                    mSend.setBackgroundResource(R.drawable.bg_chat_send_normal);
                    mSend.setTextColor(getResources().getColor(R.color.textWhite));
                } else {
                    mSend.setBackgroundResource(R.drawable.bg_chat_send_disabled);
                    mSend.setTextColor(getResources().getColor(R.color.textPrimaryGrey));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    /**
     * 第一次进入页面
     */
    @Override
    public void onFirstUserVisible() {
        super.onFirstUserVisible();
        mPresenter = new ChatPresenter(this);
        mPresenter.onSendMessage("现在时间");
        mToolbar.setTitle(R.string.Chat);
    }

    /**
     * mSend 点击事件
     *
     * @param view
     */
    @OnClick(R.id.tv_bar_chat)
    public void click(View view) {
        switch (view.getId()) {
            case R.id.tv_bar_chat:
                mSend.setBackgroundResource(R.color.colorPrimary);
                String message = mEditText.getText().toString();
                //发送之后清空
                mEditText.setText("");

                if (!TextUtils.isEmpty(message)) {
                    ChatItemView itemView = new ChatItemView(mContext);
                    itemView.setItem(false, message);
                    mLinearLayout.addView(itemView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup
                            .LayoutParams.WRAP_CONTENT));
                    mPresenter.onSendMessage(message);

                }
                break;
        }
    }

    /**
     * 接收响应
     *
     * @param message
     */
    @Override
    public void onReceiveRespond(String message) {
        ChatItemView itemView = new ChatItemView(mContext);
        itemView.setItem(true, message);
        mLinearLayout.addView(itemView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        scrollToBottom();
    }

    private void scrollToBottom() {
        mScrollView.post(new Runnable() {
            @Override
            public void run() {
                mScrollView.fullScroll(ScrollView.FOCUS_DOWN);
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_home, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
        }
        return false;
    }
}
