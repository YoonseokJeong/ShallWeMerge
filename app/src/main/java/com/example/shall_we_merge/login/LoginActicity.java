package com.example.shall_we_merge.login;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.shall_we_merge.MainActivity;
import com.example.shall_we_merge.R;
import com.example.shall_we_merge.ShallWeMergeAPI;
import com.example.shall_we_merge.Util;
import com.example.shall_we_merge.databinding.ActicityLoginBinding;
import com.example.shall_we_merge.login.signup.SignUpActivity;
import com.example.shall_we_merge.login.signup.TestDataClass;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActicity extends AppCompatActivity {


    private ActicityLoginBinding binding;
    private KakaoLoginLogoutManager manager = new KakaoLoginLogoutManager(this);

    String url = "http://192.249.18.219";

    Gson gson = new GsonBuilder().setLenient().create();

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();
     ShallWeMergeAPI shallWeMergeAPI = retrofit.create(ShallWeMergeAPI.class);

    private LoginActicity getContext(){
        return this;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String id = "10";


        binding = DataBindingUtil.setContentView(this, R.layout.acticity_login);
        Intent intent = new Intent(this, MainActivity.class);

        //Edit Text Input 제한
        binding.idEdit.setFilters(new InputFilter[]{new InputFilter.LengthFilter(10), new Util.CustomInputFilter()});
        binding.idEdit.setFilters(new InputFilter[]{new InputFilter.LengthFilter(16)});


        binding.kakaoLoginButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view){
                manager.signInKakao();

            }
        });

        binding.toSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getContext(), SignUpActivity.class);
                intent.putExtra("data","Test Popup"); startActivityForResult(intent, 1);

            }
        });

        binding.loginButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view){
                if(TextUtils.isEmpty(binding.idEdit.getText().toString())){

                    binding.exceptionText.setText("ID를 입력해주세요");
                    binding.exceptionText.setVisibility(View.VISIBLE);
                }
                else if(TextUtils.isEmpty(binding.passwordEdit.getText().toString())){
                    binding.exceptionText.setText("비밀번호를 입력해주세요");
                    binding.exceptionText.setVisibility(View.VISIBLE);
                }
                else{
                    LoginDataClass loginDataClass = new LoginDataClass();
                    loginDataClass.setId(binding.idEdit.getText().toString());
                    loginDataClass.setPassword(binding.passwordEdit.getText().toString());

                    TestDataClass testDateClass = new TestDataClass("asd","sadfasdfsdfsd","YYYY/MM/DD");

                    shallWeMergeAPI.addPlace(testDateClass)
                            .enqueue(new Callback<TestDataClass>() {
                                @Override
                                public void onResponse(Call<TestDataClass> call, Response<TestDataClass> response) {
                                    Log.d("response", response.toString());
                                }

                                @Override
                                public void onFailure(Call<TestDataClass> call, Throwable t) {
                                    Log.e("response", t.toString());

                                }
                            });

                    //if(getAccount("id") == null) {
                    //  binding.exceptionText.setText("ID를 확인해주세요");
                    //  binding.exceptionText.setVisibility(View.VISIBLE);
                    //}
                    //else if(getAccount("id").getPassword == binding.passwordEdit.getText().toString))
                    // -> login succeed
                    //else{
                    //  binding.exceptionText.setText("비밀번호를 확인해주세요");
                    //  binding.exceptionText.setVisibility(View.VISIBLE);
                    //}


                }
            }
        });

    }

    public boolean dispatchTouchEvent(MotionEvent ev) {
        View focusView = getCurrentFocus();
        if (focusView != null) {
            Rect rect = new Rect();
            focusView.getGlobalVisibleRect(rect);
            int x = (int) ev.getX(), y = (int) ev.getY();
            if (!rect.contains(x, y)) {
                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                if (imm != null)
                    imm.hideSoftInputFromWindow(focusView.getWindowToken(), 0);
                focusView.clearFocus();
            }
        }
        return super.dispatchTouchEvent(ev);

    }


}
