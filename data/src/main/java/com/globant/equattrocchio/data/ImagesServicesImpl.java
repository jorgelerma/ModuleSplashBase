package com.globant.equattrocchio.data;

import android.util.Log;

import com.globant.equattrocchio.data.response.Result;
import com.globant.equattrocchio.data.service.api.SplashbaseApi;
import com.globant.equattrocchio.domain.service.ImagesServiceses;
//import com.globant.equattrocchio.domain.service.ImagesSer
//import com.globant.equattrocchio.domain.

import io.reactivex.Observer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ImagesServicesImpl implements ImagesServiceses {

    private static final String URL= "http://splashbase.co/";

    @Override
    public void getLatestImages() {
        Retrofit retrofit = new Retrofit.Builder().
                baseUrl(URL).
                addConverterFactory(GsonConverterFactory.create())
                .build();

        SplashbaseApi api  = retrofit.create(SplashbaseApi.class);

        Call<Result> call = api.getImages();

        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                Log.e(this.getClass().getSimpleName(), " on onResponse: ");
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                Log.e(this.getClass().getSimpleName(), " on onFailure: ");
            }
        });
    }
}


//public class ImagesServicesImpl implements ImagesServices {
//
//    private static final String URL= "http://splashbase.co/";
//
//
//
//
//    @Override
//    public void getLatestImages(Observer<Boolean> observer) {
//        Retrofit retrofit = new Retrofit.Builder().
//                baseUrl(URL).
//                addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        SplashbaseApi api  = retrofit.create(SplashbaseApi.class);
//
//        Call<Result> call = api.getImages();
//
//        call.enqueue(new Callback<Result>() {
//            @Override
//            public void onResponse(Call<Result> call, Response<Result> response) {
//                Log.e(this.getClass().getSimpleName(), " on onResponse: ");
//            }
//
//            @Override
//            public void onFailure(Call<Result> call, Throwable t) {
//                Log.e(this.getClass().getSimpleName(), " on onFailure: ");
//            }
//        });
//    }
//
//
//}
