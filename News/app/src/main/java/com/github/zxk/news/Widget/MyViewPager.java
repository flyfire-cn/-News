package com.github.zxk.news.Widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by 庄欣锴zz on 2016/6/18.
 */
public class MyViewPager extends ViewPager {
    //是否支持滑动
    private boolean isEnableScroll = false;

    public MyViewPager ( Context context, boolean isEnableScroll ) {
        super ( context );
        this.isEnableScroll = isEnableScroll;
    }

    public MyViewPager ( Context context, AttributeSet attrs ) {
        super ( context, attrs );
    }

    public void setScrollEnabled ( boolean isEnableScroll ) {
        this.isEnableScroll=isEnableScroll;
    }

    /**
     * down事件首先会传递到onInterceptTouchEvent()方法。该ViewGroup的onInterceptTouchEvent()在接收到down事件处理完成之后：

     (1)return false，那么后续的move, up等事件将继续会先传递给该ViewGroup，之后才和down事件一样传递给最终的目标view的onTouchEvent()处理。

     (2)return true，那么后续的move, up等事件将不再传递给onInterceptTouchEvent()，而是和down事件一样传递给该ViewGroup的onTouchEvent()处理，注意，目标view将接收不到任何事件。

     如果最终需要处理事件的view的onTouchEvent()返回了false，那么该事件将被传递至其上一层次的view的onTouchEvent()处理。

     如果最终需要处理事件的view 的onTouchEvent()返回了true，那么后续事件将可以继续传递给该view的onTouchEvent()处理。
     * @param ev
     * @return
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (isEnableScroll) {
            return super.onInterceptTouchEvent(ev);
        }
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (isEnableScroll) {
            return super.onInterceptTouchEvent(ev);
        }
        return false;
    }
}
