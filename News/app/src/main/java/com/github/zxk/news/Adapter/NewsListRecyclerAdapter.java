package com.github.zxk.news.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.zxk.news.Bean.News.Contentlist;
import com.github.zxk.news.R;
import com.github.zxk.news.UI.activity.HomeActivity;
import com.github.zxk.news.UI.activity.NewsDetailActivity;
import com.github.zxk.news.Util.ImageUtil;

import java.util.List;

/**
 * Created by 庄欣锴zz on 2016/6/18.
 */
public class NewsListRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_ITEM = 0;
    private static final int TYPE_FOOTER = 1;
    public Context mContext;
    public List<Contentlist> mData;
    public LayoutInflater mLayoutInflater;



    public NewsListRecyclerAdapter(Context context, List<Contentlist> data) {
        mContext = context;
        mData = data;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemViewType(int position) {
        if (position + 1 == getItemCount()) {
            return TYPE_FOOTER;
        } else {
            return TYPE_ITEM;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news, parent, false);
            return new MViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_view_footer, parent, false);
            return new RecyclerView.ViewHolder(view) {
                @Override
                public String toString() {
                    return super.toString();
                }
            };
        }
    }

    /**
     * ViewHolder  设置元素
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        //instanceof 判断holder是否为MViewHolder的实例
        if (holder instanceof MViewHolder) {
            Contentlist contentEntity = mData.get(position);
            ((MViewHolder) holder).mTitle.setText(contentEntity.getTitle());
            ((MViewHolder) holder).mDescription.setText(contentEntity.getDesc());
            ImageUtil.displayImage(mContext, contentEntity.getImageurls().get(0).getUrl(), ((MViewHolder) holder).mImage);
        }
    }

    /**
     * 返回数据总数
     * @return
     */
    @Override
    public int getItemCount() {
        return mData.size() + 1;
    }

    /**
     * 重写自定义ViewHolder
     */
    public class MViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView mTitle;
        public TextView mDescription;
        public ImageView mImage;

        public MViewHolder(View view) {
            super(view);
            mTitle = (TextView) view.findViewById(R.id.tv_title);
            mDescription = (TextView) view.findViewById(R.id.tv_description);
            mImage = (ImageView) view.findViewById(R.id.imageView);
            view.setOnClickListener(this);
        }

        /**
         * 点击跳转详情页面
         *
         * @param v
         */
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(mContext, NewsDetailActivity.class);
            intent.putExtra("contentList", mData.get(this.getLayoutPosition()));
            ActivityOptionsCompat options =
                    ActivityOptionsCompat.makeSceneTransitionAnimation((HomeActivity) mContext,
                            mImage, mContext.getString(R.string.transition_img));

            ActivityCompat.startActivity((HomeActivity) mContext, intent, options.toBundle());

        }
    }
}
