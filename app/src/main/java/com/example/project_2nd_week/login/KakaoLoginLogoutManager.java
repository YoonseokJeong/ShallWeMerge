package com.example.project_2nd_week.login;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import com.kakao.sdk.auth.model.OAuthToken;
import com.kakao.sdk.user.UserApiClient;

import kotlin.Unit;
import kotlin.jvm.functions.Function2;

public class KakaoLoginLogoutManager {

    private final Activity activity;
    private String id;

    public KakaoLoginLogoutManager(Activity activity) {
        this.activity = activity;
    }

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
                Log.i("[카카오] 로그인 정보", user.getKakaoAccount().getEmail() + "");
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
