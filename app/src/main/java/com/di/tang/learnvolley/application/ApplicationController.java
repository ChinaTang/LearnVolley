package com.di.tang.learnvolley.application;

import android.app.Application;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.util.LruCache;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

/**
 * Created by tangdi on 2016/9/2.
 */
public class ApplicationController extends Application {

    private RequestQueue requestQueue = null;

    private static ApplicationController applicationController = null;

    private static final String TAG = "ApplicationController";

    private ImageLoader imageLoader;

    @Override
    public void onCreate(){
        super.onCreate();
        applicationController = this;
    }

    public ImageLoader getImageLoader(){
        if(imageLoader == null){
            //imageLoader=new ImageLoader(this.requestQueue,new LruBitmapCache());
        }
        return null;
    }

    public static synchronized ApplicationController getInstance(){
        return applicationController;
    }

    public RequestQueue getRequestQueue(){
        if(requestQueue == null){
            requestQueue = Volley.newRequestQueue(getApplicationContext());
        }
        return requestQueue;
    }
    /*将Request对象添加进RequestQueue，由于Request有*StringRequest,JsonObjectResquest...等多种类型，
    所以需要用到*泛型。同时可将*tag作为可选参数以便标示出每一个不同请求*/
    public <T> void addToRequestQueue(Request<T> request, String tag){
        request.setTag(TextUtils.isEmpty(tag)?TAG:tag);
        getRequestQueue().add(request);
    }

    //通过各Request对象的Tag属性取消请求
    public void cancleRequest(String tag){
        if(requestQueue != null){
            requestQueue.cancelAll(tag);
        }
    }

    public class LruBitmapCache extends LruCache<String,Bitmap> implements ImageLoader.ImageCache {

        /**
         * @param maxSize for caches that do not override {@link #sizeOf}, this is
         *                the maximum number of entries in the cache. For all other caches,
         *                this is the maximum sum of the sizes of the entries in this cache.
         */
        public LruBitmapCache(int maxSize) {
            super(maxSize);
        }

        @Override
        public Bitmap getBitmap(String url) {
            return null;
        }

        @Override
        public void putBitmap(String url, Bitmap bitmap) {

        }

        /*public static int getDefaultLruCacheSize(){
            final int maxMemory=(int)(Runtime.getRuntime().maxMemory/1024);
            final int cacheSize=maxMemory/8;
            return cacheSize;
        }*/
    }

}
