package com.example.shall_we_merge.login;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.shall_we_merge.MainActivity;
import com.example.shall_we_merge.R;
import com.example.shall_we_merge.api.AccountDataClass;
import com.example.shall_we_merge.api.ShallWeMergeAPI;
import com.example.shall_we_merge.Util;
import com.example.shall_we_merge.databinding.ActicityLoginBinding;
import com.example.shall_we_merge.login.signup.SignUpActivity;
import com.example.shall_we_merge.login.signup.SignUpViewModel;
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
    private LoginViewModel loginViewModel;

    private ShallWeMergeAPI shallWeMergeAPI = Util.getAPI();

    private LoginActicity getContext(){
        return this;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);

        binding = DataBindingUtil.setContentView(this, R.layout.acticity_login);
        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);

        binding.setLoginViewModel(loginViewModel);

        //Edit Text Input 제한
        binding.idEdit.setFilters(new InputFilter[]{new InputFilter.LengthFilter(10), new Util.CustomInputFilter()});
        binding.passwordEdit.setFilters(new InputFilter[]{new InputFilter.LengthFilter(16)});


        binding.kakaoLoginButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view){
                manager.signInKakao();
            }
        });

        final Observer<Boolean> emptyIdObserver = new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable final Boolean emptyId) {
                // Update the UI, in this case, a TextView.
                if (!emptyId) {
                    binding.exceptionText.setVisibility(View.GONE);
                }
            }
        };
        loginViewModel.getIdEmpty().observe(getContext(), emptyIdObserver);

        final Observer<Boolean> emptyPwObserver = new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable final Boolean emptyPw) {
                // Update the UI, in this case, a TextView.
                if (!emptyPw) {
                    binding.exceptionText.setVisibility(View.GONE);
                }
            }
        };
        loginViewModel.getPwEmpty().observe(getContext(), emptyPwObserver);

        binding.toSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getContext(), SignUpActivity.class);
                intent.putExtra("data","Test Popup"); startActivity(intent);
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
                    AccountDataClass accountDataClass = new AccountDataClass(binding.idEdit.getText().toString(), binding.passwordEdit.getText().toString());

                    shallWeMergeAPI.login(accountDataClass)
                            .enqueue(new Callback<AccountDataClass>() {
                                @Override
                                public void onResponse(Call<AccountDataClass> call, Response<AccountDataClass> response) {
                                    Log.d("response", response.body().toString());
                                    if(response.body().loginSuccessed()){
                                        Toast toast = Toast.makeText(getApplicationContext(), "돌아오신 것을 환영합니다!", Toast.LENGTH_SHORT);
                                        toast.show();

                                        Intent intent = new Intent(getContext(), MainActivity.class);
                                        ((KakaoApplication)getApplication()).setId(binding.idEdit.getText().toString());
                                        startActivity(intent);
                                        finish();
                                    }
                                    else {
                                        Toast toast = Toast.makeText(getApplicationContext(), "ID/PW를 확인해주세요.", Toast.LENGTH_SHORT);
                                        toast.show();
                                    }
                                }

                                @Override
                                public void onFailure(Call<AccountDataClass> call, Throwable t) {
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
