package com.example.shall_we_merge;

import android.text.InputFilter;
import android.text.Spanned;

import com.example.shall_we_merge.api.ShallWeMergeAPI;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.regex.Pattern;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Util {

    static public class CustomInputFilter implements InputFilter {

        @Override
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
            Pattern ps = Pattern.compile("^[a-zA-Z0-9]+$");


            if (!ps.matcher(source).matches()) {
                return "";
            }
            return null;
        }
    };

    static public ShallWeMergeAPI getAPI(){
        String url = "http://192.249.18.219";

        Gson gson = new GsonBuilder().setLenient().create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        return retrofit.create(ShallWeMergeAPI.class);
    }
}

