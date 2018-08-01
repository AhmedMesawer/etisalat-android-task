package com.etisalat.sampletask.netwrok;

import android.support.annotation.NonNull;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Response;

import static com.etisalat.sampletask.utils.Util.isNetworkAvailable;

/**
 * implementation of {@link Interceptor} that uses to
 * intercepts http request to add cache control with maxStale = 7 days
 * to its header in offline state
 */
public class OfflineCachingInterceptor implements Interceptor {

    /**
     * intercept http request
     *
     * @param chain object that contain http request, response and connection before interception
     * @return Response response after adding cache control
     */
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
