package com.baidu.retrofit.utils;

import android.util.Log;

import com.baidu.retrofit.bean.APIBodyData;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Converter;


public class JsonRequestBodyConverter<T> implements Converter<T, RequestBody> {

    private MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=UTF") ;
    private final Gson gson;
    private final TypeAdapter<T> adapter;
    /**
     * 构造器
     */

    public JsonRequestBodyConverter(Gson gson, TypeAdapter<T> adapter) {
        this.gson = gson;
        this.adapter = adapter;
    }

    @Override
    public RequestBody convert(T value) throws IOException {
        APIBodyData apiBodyData = new APIBodyData();
        Log.e("lzx", "request中传递的json数据：" + value.toString());
        apiBodyData.data = XXTEA.Encrypt(value.toString(),XXTEA.KEY_STR);
        String postBody = gson.toJson(apiBodyData); //对象转化成json
        Log.e("lzx", "转化后的数据：" + postBody);
        return RequestBody.create(MEDIA_TYPE,postBody);
    }
}
