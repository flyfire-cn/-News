package com.github.zxk.news.Util;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.github.zxk.news.BaseAppActivity;

import org.json.JSONObject;

import java.util.Map;

/**
 * Created by 庄欣锴zz on 2016/6/6.
 * 网络请求
 */
public class NetRequestUtil {
    Context mContext = BaseAppActivity.getContext();
    public static RequestQueue mRequestQueue = Volley.newRequestQueue(BaseAppActivity.getContext());
    private static NetRequestUtil netRequestUtil;

    private NetRequestUtil() {
    }

    /**
     * 设置单例模式 同步处理:避免全局共享引用出现bug
     *
     * @return
     */
    public static NetRequestUtil getInstance() {
        if (netRequestUtil == null) {
            synchronized (NetRequestUtil.class) {
                if (netRequestUtil == null)
                    netRequestUtil = new NetRequestUtil();
            }
        }
        return netRequestUtil;
    }

    /**
     * get请求 获取JSonObject
     *
     * @param url
     * @param param
     * @param listener
     */
    public void getJson(String url, final Map<String, String> param, final RequestListener listener) {
        url += prepareParam(param);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                listener.onResponse(response);
                Log.i(this.getClass().getSimpleName(), response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                listener.onError(error);
                Log.i(this.getClass().getSimpleName(), error.toString());

            }
        });
        mRequestQueue.add(jsonObjectRequest);
    }

    /**
     * get 请求获取带Headers参数的JSonObject
     *
     * @param url
     * @param param
     * @param listener
     */
    public void getJsonWithHeaders(String url, final Map<String, String> param, final Map<String, String> headers, final RequestListener listener) {
        url += prepareParam(param);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                listener.onResponse(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                listener.onError(error);
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                if (headers == null) {
                    return super.getHeaders();
                } else {
                    return headers;
                }

            }

        };
        mRequestQueue.add(jsonObjectRequest);

    }

    /**
     * 将map型转为请求参数型
     *
     * @param paramMap
     * @return
     */
    private String prepareParam(Map<String, String> paramMap) {

        if (paramMap == null || paramMap.isEmpty()) {
            return "";
        } else {
            StringBuffer sb = new StringBuffer();
            sb.append("?");
            for (String key : paramMap.keySet()) {
                String value = (String) paramMap.get(key);
                if (sb.length() < 1) {
                    sb.append(key).append("=").append(value);
                } else {
                    sb.append("&").append(key).append("=").append(value);
                }
            }
            return sb.toString();
        }
    }


    public interface RequestListener {
        public void onResponse(JSONObject response);

        public void onError(VolleyError error);
    }
}
