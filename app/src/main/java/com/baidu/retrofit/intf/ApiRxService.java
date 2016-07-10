package com.baidu.retrofit.intf;


import com.baidu.retrofit.bean.FamousInfo;
import com.baidu.retrofit.bean.IpInfo;

import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;
import rx.Observable;

public interface ApiRxService {
    /*@GET("service/getIpInfo.php")
    Call<IpInfo> getIpinfoResult(@Query("ip") String keyword);*/

    @GET("service/getIpInfo.php")
    Observable<IpInfo> getIpinfoResult(@Query("ip") String keyword);

    @GET("service/getIpInfo.php")
    Observable<String> getIpinfoResult2(@Query("ip") String keyword);

    @GET("http://apis.baidu.com/avatardata/mingrenmingyan/lookup")
    Observable<FamousInfo> getFamousResult(@Header("apiKey") String apiKey,
                                     @Query("keyword") String keyword,
                                     @Query("page") int page,
                                     @Query("rows") int rows);
}
