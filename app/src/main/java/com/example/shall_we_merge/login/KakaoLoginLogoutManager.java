package com.example.shall_we_merge.login;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.example.shall_we_merge.MainActivity;
import com.example.shall_we_merge.api.IdDataClass;
import com.example.shall_we_merge.api.ShallWeMergeAPI;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kakao.sdk.auth.model.OAuthToken;
import com.kakao.sdk.user.UserApiClient;

import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class KakaoLoginLogoutManager {

    private final Activity activity;
    private String id;

    public KakaoLoginLogoutManager(Activity activity) {
        this.activity = activity;
    }

    String url = "http://192.249.18.219";

    Gson gson = new GsonBuilder().setLenient().create();

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();
    ShallWeMergeAPI shallWeMergeAPI = retrofit.create(ShallWeMergeAPI.class);

    public void signInKakao() {
        // @brief : 카카오톡이 설치되어 있으면 카카오톡으로 로그인, 아니면 카카오계정으로 로그인
        if (UserApiClient.getInstance().isKakaoTalkLoginAvailable(activity))
            UserApiClient.getInstance().loginWithKakaoTalk(activity, callback);
        else UserApiClient.getInstance().loginWithKakaoAccount(activity, callback);
    }

    /**
     * @brief : 로그인 결과 수행에 관한 콜백메서드
     * @see : token이 전달되면 로그인 성공, token 전달 안되면 로그인 실패
     */
    Function2<OAuthToken, Throwable, Unit> callback = (oAuthToken, throwable) -> {
        if (oAuthToken != null) {
            Log.i("[카카오] 로그인", "성공 " + oAuthToken.toString());
            updateKakaoLogin();
        }
        if (throwable != null) {
            Log.i("[카카오] 로그인", "실패");
            Log.e("signInKakao()", throwable.getLocalizedMessage());
        } return null;
    };

    private void updateKakaoLogin() {
        UserApiClient.getInstance().me((user, throwable) -> {
            if (user != null) {
                // @brief : 로그인 성공
                Log.i("[카카오] 로그인 정보", user.toString());
                Log.i("[카카오] 로그인 정보", user.getId().toString());

                IdDataClass kakaoId = new IdDataClass(user.getId().toString());

                shallWeMergeAPI.kakaoLogin(kakaoId).enqueue(new Callback<IdDataClass>() {
                    @Override
                    public void onResponse(Call<IdDataClass> call, Response<IdDataClass> response) {
                        Intent intent = new Intent(activity, MainActivity.class);
                        ((KakaoApplication)activity.getApplication()).setId(user.getId().toString());
                        activity.startActivity(intent);
                        activity.finish();
                    }

                    @Override
                    public void onFailure(Call<IdDataClass> call, Throwable t) {
                        Log.e("kakaoLogin", t.toString());

                    }
                });



            } else {
                // @brief : 로그인 실패
            }
            return null;
        });
    }

    public void singOutKakao() {
        UserApiClient.getInstance().logout((throwable) -> {
            if (throwable != null) {
                // @brief : 로그아웃 실패
                Log.e("[카카오] 로그아웃", "실패", throwable);
                Toast.makeText(activity, "카카오 로그아웃을 실패했습니다.", Toast.LENGTH_SHORT).show();
            } else {
                // @brief : 로그아웃 성공
                Log.i("[카카오] 로그아웃", "성공");
                Toast.makeText(activity, "카카오 로그아웃이 정상적으로 수행됐습니다.", Toast.LENGTH_SHORT).show();
            }
            return null;
        });
        // @brief : 카카오 연결 끊기
        UserApiClient.getInstance().unlink((throwable) -> {
            if (throwable != null) {
                // @brief : 연결 끊기 실패
                Log.e("[카카오] 로그아웃", "연결 끊기 실패", throwable);
            } else {
                // @brief : 연결 끊기 성공
                Log.i("kakaoLogout", "연결 끊기 성공. SDK에서 토큰 삭제");
            }
            return null;
        });
    }
}
