package com.example.shall_we_merge.login.signup;

import android.graphics.Rect;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.InputType;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.shall_we_merge.R;
import com.example.shall_we_merge.Util;
import com.example.shall_we_merge.api.AccountDataClass;
import com.example.shall_we_merge.api.IdDataClass;
import com.example.shall_we_merge.api.ShallWeMergeAPI;
import com.example.shall_we_merge.databinding.ActivitySignupBinding;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignUpActivity extends AppCompatActivity {
    private SignUpActivity getContext(){
        return this;
    }
    private ActivitySignupBinding binding;
    private SignUpViewModel signUpViewModel;
    private ShallWeMergeAPI shallWeMergeAPI = Util.getAPI();

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        signUpViewModel = new ViewModelProvider(this).get(SignUpViewModel.class);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_signup);
        binding.setSignUpViewModel(signUpViewModel);




        binding.closeButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                getContext().finish();
            }
        });

        //Edit Text Input 제한
        binding.idEditSignUp.setFilters(new InputFilter[]{new InputFilter.LengthFilter(12), new Util.CustomInputFilter()});
        binding.pwEditSignUp.setFilters(new InputFilter[]{new InputFilter.LengthFilter(16)});
        binding.pwCheck.setFilters(new InputFilter[]{new InputFilter.LengthFilter(16)});


        final Observer<Boolean> dupCheckedObserver = new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable final Boolean dupChecked) {
                // Update the UI, in this case, a TextView.
                if(dupChecked) {
                    binding.idEditSignUp.setInputType(InputType.TYPE_NULL);
                    binding.idDupCheckButton.setVisibility(View.GONE);
                    binding.pwCheck.setVisibility(View.VISIBLE);
                    binding.pwCheckTag.setVisibility(View.VISIBLE);
                    binding.pwEditSignUp.setVisibility(View.VISIBLE);
                    binding.pwTag.setVisibility(View.VISIBLE);
                    binding.validIdText.setText("사용 가능한 ID입니다.");
                    binding.validIdText.setTextColor(0xFF00FF00);
                    binding.validIdText.setVisibility(View.VISIBLE);

                    // PW 8자리 이상인지 확인하는 Observer 부착
                    final Observer<Boolean> validityObserver = new Observer<Boolean>() {
                        @Override
                        public void onChanged(@Nullable final Boolean isValid) {
                            // Update the UI, in this case, a TextView.
                            if(isValid) {
                                binding.invalidPWText.setVisibility(View.GONE);
                            }
                            else{
                                binding.invalidPWText.setVisibility(View.VISIBLE);
                            }
                        }
                    };
                    signUpViewModel.getPwValid().observe(getContext(), validityObserver);

                    // PW, PW재입력 같은지 확인하는 Observer 부착
                    final Observer<Boolean> pwCheckedObserver = new Observer<Boolean>() {
                        @Override
                        public void onChanged(@Nullable final Boolean isChecked) {
                            // Update the UI, in this case, a TextView.
                            if(isChecked) {
                                binding.pwNotSameText.setVisibility(View.GONE);
                            }
                            else{
                                binding.pwNotSameText.setVisibility(View.VISIBLE);
                            }
                        }
                    };
                    signUpViewModel.getPwChecked().observe(getContext(), pwCheckedObserver);

                    // 회원가입 버튼 활성화 Observer
                    final Observer<Boolean> signUpButtonObserver = new Observer<Boolean>() {
                        @Override
                        public void onChanged(Boolean enabled) {
                            if(enabled){
                                binding.signUpButton.setVisibility(View.VISIBLE);
                            }
                            else{
                                binding.signUpButton.setVisibility(View.GONE);
                            }
                        }
                    };
                    signUpViewModel.getSignUpButtonEnabled().observe(getContext(), signUpButtonObserver);

                }
            }
        };
        signUpViewModel.getDupChecked().observe(getContext(), dupCheckedObserver);




        binding.idDupCheckButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                IdDataClass id = new IdDataClass(binding.idEditSignUp.getText().toString());
                Log.d("id", id.getId());

                shallWeMergeAPI.checkId(id).enqueue(new Callback<IdDataClass>() {
                    @Override
                    public void onResponse(Call<IdDataClass> call, Response<IdDataClass> response) {
                        Log.d("Dup", response.body().getMessage());
                        Log.d("Dup", String.valueOf(response.body().idDuplicated()));
                        if(response.body().idDuplicated()){
                            binding.validIdText.setText("이미 사용중인 아이디입니다.");
                            binding.validIdText.setTextColor(0xFFFF0000);
                            binding.validIdText.setVisibility(View.VISIBLE);
                        }
                        else{
                            signUpViewModel.setDupChecked(true);
                        }
                    }

                    @Override
                    public void onFailure(Call<IdDataClass> call, Throwable t) {

                    }
                });
            }
        });

        binding.signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AccountDataClass accountDataClass = new AccountDataClass(binding.idEditSignUp.getText().toString(), binding.pwEditSignUp.getText().toString());
                shallWeMergeAPI.signUp(accountDataClass).enqueue(new Callback<AccountDataClass>() {
                    @Override
                    public void onResponse(Call<AccountDataClass> call, Response<AccountDataClass> response) {

                    }

                    @Override
                    public void onFailure(Call<AccountDataClass> call, Throwable t) {

                    }
                });
                finish();
            }
        });





    }

    // 바깥 클릭하면 회원가입 창 닫기
//    @Override
//    public boolean onTouchEvent(MotionEvent event){
//        return event.getAction() != MotionEvent.ACTION_OUTSIDE;
//    }


    //다른 곳 클릭하면 키보드 끄기
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
