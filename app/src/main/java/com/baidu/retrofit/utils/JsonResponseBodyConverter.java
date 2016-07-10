package com.baidu.retrofit.utils;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * 自定义响应ResponseBody
 * @param <T>
 */
public class JsonResponseBodyConverter<T> implements Converter<ResponseBody,T> {
    private final Gson gson;//gson对象
    private final TypeAdapter<T> adapter;

    /**
     * 构造器
     */
    public JsonResponseBodyConverter(Gson gson, TypeAdapter<T> adapter) {
        this.gson = gson;
        this.adapter = adapter;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        JSONObject jsonObj;
        try {
            jsonObj = new JSONObject(value.string());
            Log.e("lzx","jsonObj " + jsonObj.toString());
            return adapter.fromJson(jsonObj.toString());
        } catch(JSONException e) {
            return null;
        }
    }
}
