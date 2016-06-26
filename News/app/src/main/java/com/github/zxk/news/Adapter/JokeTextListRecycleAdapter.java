package com.github.zxk.news.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.zxk.news.Bean.Joke.JokeText;
import com.github.zxk.news.R;

import java.util.List;

/**
 * Created by 庄欣锴zz on 2016/6/21.
 */
public class JokeTextListRecycleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_TIME = 0;
    private static final int TYPE_FOOT = 1;
    private List<JokeText.ContentlistBean> mData;
    private Context mContext;
    private LayoutInflater mLayoutInflater;

    public JokeTextListRecycleAdapter(Context context, List<JokeText.ContentlistBean> data) {
        mData = data;
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    /**
     * 思路是滚动到末尾位置时加载一个Foot布局
     * getItemCount返回长度，注意需要加载更多时长度是数据长度+1
     *
     * @param position
     * @return
     */
    public int getItemViewType(int position) {
        if (position + 1 == getItemCount()) {
            return TYPE_FOOT;
        } else {
            return TYPE_TIME;
        }
    }

    /**
     * 创建获取视图
     *
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_TIME) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_joke_text, parent, false);
            return new MyViewHolder(view);
        } else {
            View view = LayoutInflater.from(mContext).inflate(R.layout.list_view_footer, parent, false);
            return new RecyclerView.ViewHolder(view) {
                @Override
                public String toString() {
                    return super.toString();
                }
            };
        }
    }

    /**
     * 数据界面的绑定
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MyViewHolder) {
            JokeText.ContentlistBean contentlistBean = mData.get(position);
            ((MyViewHolder) holder).mTitle.setText(contentlistBean.getTitle());
            ((MyViewHolder) holder).mContent.setText(contentlistBean.getText());
        }
    }

    /**
     * 返回列表长度
     *
     * @return
     */
    @Override
    public int getItemCount() {
        return mData.size()+1;
    }

    /**
     * 自定义ViewHolder
     * 持有界面元素
     */
    private class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView mTitle;
        private TextView mContent;

        public MyViewHolder(View itemView) {
            super(itemView);
            mTitle = (TextView) itemView.findViewById(R.id.tv_title);
            mContent = (TextView) itemView.findViewById(R.id.tv_content);
        }
    }
}
