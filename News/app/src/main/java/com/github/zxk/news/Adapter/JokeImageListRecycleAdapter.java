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

import com.github.zxk.news.Bean.Joke.JokeImage;
import com.github.zxk.news.R;
import com.github.zxk.news.UI.activity.HomeActivity;
import com.github.zxk.news.UI.activity.ImageBrowseActivity;
import com.github.zxk.news.Util.ImageUtil;

import java.util.List;

/**
 * Created by 庄欣锴zz on 2016/6/22.
 */
public class JokeImageListRecycleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_TIME = 0;
    private static final int TYPE_FOOT = 1;
    private List<JokeImage.ContentlistBean> mData;
    private LayoutInflater mLayoutInflater;
    private Context mContext;

    public JokeImageListRecycleAdapter(Context context, List<JokeImage.ContentlistBean> data) {
        mData = data;
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    /**
     * 判断是否滚到Foot
     * getItemCount() 得到返回长度
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
     * 创建视图
     *
     * @param parent
     * @param viewType
     * @return
     */
    @Override

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_TIME) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_joke_image, parent, false);
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
     * 数据与界面的绑定
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof MyViewHolder) {
            JokeImage.ContentlistBean contentlist = mData.get(position);
            ((MyViewHolder) holder).mTitle.setText(contentlist.getTitle());
            final ImageView imageView = ((MyViewHolder) holder).mImageView;
            ImageUtil.displayImage(mContext, contentlist.getImg(), imageView);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(mContext, ImageBrowseActivity.class);
                    intent.putExtra("url", mData.get(position).getImg());
                    ActivityOptionsCompat options =
                            ActivityOptionsCompat.makeSceneTransitionAnimation((HomeActivity) mContext, imageView, ((HomeActivity) mContext).getString(R.string.transition_img));
                    ActivityCompat.startActivity((HomeActivity) mContext, intent, options.toBundle());
                }
            });
        }
    }

    /**
     * 得到列表长度
     *
     * @return
     */
    @Override
    public int getItemCount() {
        return mData.size() + 1;
    }

    /**
     * 自定义ViewHolder
     */
    private class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView mTitle;
        ImageView mImageView;

        public MyViewHolder(View view) {
            super(view);
            mTitle = (TextView) view.findViewById(R.id.tv_title);
            mImageView = (ImageView) view.findViewById(R.id.image_joke);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

        }
    }
}
