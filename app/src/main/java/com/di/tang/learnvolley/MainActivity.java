package com.di.tang.learnvolley;

import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.di.tang.learnvolley.application.ApplicationController;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private static final String URL = "http://image.baidu.com/search/detail?ct=503316480" +
            "&z=0&ipn=d&word=%E8%B5%B5%E4%B8%BD%E9%A2%96&step_word=&hs=0&pn=3&spn=0&di=" +
            "49348292320&pi=&rn=1&tn=baiduimagedetail&is=&istype=0&ie=utf-8&oe=utf-8&in" +
            "=&cl=2&lm=-1&st=undefined&cs=4275520511%2C284533333&os=856592963%2C3610776" +
            "607&simid=0%2C0&adpicid=0&ln=3964&fr=&fmq=1472796904226_R&fm=&ic=undefined" +
            "&s=undefined&se=&sme=&tab=0&width=&height=&face=undefined&ist=&jit=&cg=star" +
            "&bdtype=11&oriquery=&objurl=http%3A%2F%2Ffun.youth.cn%2Fyl24xs%2F201607%2F" +
            "W020160729521665211109.png&fromurl=ippr_z2C%24qAzdH3FAzdH3Fu7g_z%26e3By57pi" +
            "_z%26e3BvgAzdH3Fysd9xfAzdH3Fda8ma0AzdH3Fpda8ma0dl_bnmb99d_d_z%26e3Bip4&gsm" +
            "=0&rpstart=0&rpnum=0";
    private JsonObjectRequest jsonObjectRequest;
    private StringRequest stringRequest;
    private JsonArrayRequest jsonArrayRequest;

    private ImageView image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        image = (ImageView)findViewById(R.id.imageview);
        //this is Context;
        //源码中若第二个参数为空则会使用get方法，不为空则使用post方法
        jsonObjectRequest = new JsonObjectRequest(URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                //响应逻辑
                //BitmapFactory.decodeByteArray(response.)
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //错误处理逻辑
            }
        });
        //Volley中提供了一个方案：可以通过Request对象调用setRetryPolicy()方法,设置超时和重试请求
        //**DefaultRetryPolicy(int,int,float);中第一个代表超时时间：即超过20S认为超时，第二个参数代
        // 表最大重试次数，这里设置为1.0f代表如果超时，则不重试*/
        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(20*1000,1,1.0f));
        ApplicationController.getInstance().addToRequestQueue(jsonObjectRequest,
                TAG + "jsonObjectRequest");

        stringRequest = new StringRequest(URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        ApplicationController.getInstance().addToRequestQueue(stringRequest,
                TAG + "stringRequest");

        jsonArrayRequest = new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

            }
        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
        });
        ApplicationController.getInstance().addToRequestQueue(jsonArrayRequest,
                TAG + "jsonArrayRequest");
    }
}
