package com.github.zxk.news.Util;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

import java.io.File;

/**
 * Created by 庄欣锴zz on 2016/6/18.
 * ImageView 工具轮子
 */
public class ImageUtil {
    /**
     * url
     * @param context
     * @param url
     * @param imageView
     */
    public static void displayImage (Context context, String url, ImageView imageView ) {
        Glide.with ( context ).load ( url ).into ( imageView );
    }

    /**
     * file
     * @param context
     * @param file
     * @param imageView
     */
    public static void displayImage (Context context, File file, ImageView imageView ) {
        Glide.with ( context ).load ( file ).into ( imageView );
    }

    /**
     * 圆形图片
     * @param context
     * @param url
     * @param imageView
     */
    public static void displayCircularImage ( final Context context, String url, final ImageView imageView ) {
        Glide.with ( context ).load ( url ).asBitmap ( ).centerCrop ( ).into ( new BitmapImageViewTarget( imageView ) {
            @Override
            protected void setResource ( Bitmap resource ) {
                RoundedBitmapDrawable circularBitmapDrawable =
                        RoundedBitmapDrawableFactory.create ( context.getResources ( ), resource );
                circularBitmapDrawable.setCircular ( true );
                imageView.setImageDrawable ( circularBitmapDrawable );

            }
        } );
    }

        /*public static void displayBlurImage ( Context context, String url, ImageView imageView ) {
                Glide.with ( context ).load ( url )
                        .bitmapTransform ( new BlurTransformation ( context ) )
                        .into ( imageView );
        }
        public static void displayBlurImage ( Context context, String url, final View view ) {
                Glide.with ( context ).load ( url )
                        .bitmapTransform ( new BlurTransformation ( context ) ).into ( new SimpleTarget< GlideDrawable > ( ) {
                        @Override
                        public void onResourceReady ( GlideDrawable resource, GlideAnimation< ? super GlideDrawable > glideAnimation ) {
                                view.setBackground ( resource );
                        }
                } );
        }*/

}
