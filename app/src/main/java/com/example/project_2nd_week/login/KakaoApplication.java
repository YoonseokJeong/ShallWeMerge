package com.example.project_2nd_week.login;

import android.app.Application;

import com.example.project_2nd_week.R;
import com.kakao.sdk.common.KakaoSdk;

public class KakaoApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        // @brief : kakao 네이티브 앱키로 초기화
        String kakao_app_key = getResources().getString(R.string.kakao_app_key);
        KakaoSdk.init(this, kakao_app_key);
    }
}
