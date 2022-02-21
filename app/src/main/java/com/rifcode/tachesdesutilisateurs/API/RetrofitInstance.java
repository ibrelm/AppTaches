package com.rifcode.tachesdesutilisateurs.API;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.ncornette.cache.OkCacheControl;

import java.io.File;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static java.util.concurrent.TimeUnit.MINUTES;

public class RetrofitInstance {

    private static Retrofit retrofit=null;
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com/";
    private static File httpCacheDirectory;
    private static OkHttpClient httpClient;


    public static GetData getService(Context context){

        if(retrofit==null){

            int cacheSize = 10 * 1024 * 1024; // 10 MB
            Cache cache = new Cache(context.getCacheDir(), cacheSize);

            OkCacheControl.NetworkMonitor networkMonitor = new
                    OkCacheControl.NetworkMonitor() {
                        @Override
                        public boolean isOnline() {
                            return isOnlinef(context);
                        }
                    };
            OkHttpClient okHttpClient = OkCacheControl.on(new OkHttpClient.Builder())
                    .overrideServerCachePolicy(30, MINUTES)
                    .forceCacheWhenOffline(networkMonitor)
                    .apply() // return to the OkHttpClient.Builder instance
                    //.addInterceptor(provideHttpLoggingInterceptor())
                    .cache(cache)
                    .build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        }

        return retrofit.create(GetData.class);
    }

    public static boolean isOnlinef(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }


}
