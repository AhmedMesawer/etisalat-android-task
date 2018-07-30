package com.etisalat.sampletask.netwrok;

import android.support.annotation.NonNull;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Response;

import static com.etisalat.sampletask.utils.Util.isNetworkAvailable;

public class OfflineInterceptor implements Interceptor {

    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {

        Response originalResponse = chain.proceed(chain.request());
        if (!isNetworkAvailable()) {
        CacheControl.Builder cacheControlBuilder = new CacheControl.Builder();
            CacheControl cacheControl = cacheControlBuilder
                    .maxStale(7, TimeUnit.DAYS)
                    .build();
            return originalResponse.newBuilder()
                    .header("Cache-Control", cacheControl.toString())
                    .build();
        }
        return originalResponse;
    }
}
