package com.example.shall_we_merge.login.signup;

import android.graphics.Rect;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.InputType;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;

import com.example.shall_we_merge.R;
import com.example.shall_we_merge.Util;
import com.example.shall_we_merge.databinding.ActivitySignupBinding;

public class SignUpActivity extends AppCompatActivity {
    private SignUpActivity getContext(){
        return this;
    }
    private ActivitySignupBinding binding;
    private SignUpViewModel signUpViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        signUpViewModel = new SignUpViewModel();
        binding = DataBindingUtil.setContentView(this, R.layout.activity_signup);
        binding.setSignUpViewModel(signUpViewModel);



        binding.closeButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                getContext().finish();
            }
        });

        //Edit Text Input 제한
        binding.idEditSignUp.setFilters(new InputFilter[]{new InputFilter.LengthFilter(10), new Util.CustomInputFilter()});
        binding.pwEditSignUp.setFilters(new InputFilter[]{new InputFilter.LengthFilter(16)});
        binding.pwCheck.setFilters(new InputFilter[]{new InputFilter.LengthFilter(16)});


        binding.idDupCheckButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                binding.idEditSignUp.setInputType(InputType.TYPE_NULL);
                binding.idDupCheckButton.setVisibility(View.GONE);
                binding.pwCheck.setVisibility(View.VISIBLE);
                binding.pwCheckTag.setVisibility(View.VISIBLE);
                binding.pwEditSignUp.setVisibility(View.VISIBLE);
                binding.pwTag.setVisibility(View.VISIBLE);

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
            }
        });




        // PW, PW재입력 같은지 확인하는 Observer 부착
        final Observer<Boolean> pwCheckedObserver = new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable final Boolean isChecked) {
                // Update the UI, in this case, a TextView.
                if(isChecked) {
                    binding.pwNotSameText.setVisibility(View.GONE);
                    binding.signUpButton.setVisibility(View.VISIBLE);
                }
                else{
                    binding.pwNotSameText.setVisibility(View.VISIBLE);
                    binding.signUpButton.setVisibility(View.GONE);
                }
            }
        };
        signUpViewModel.getPwChecked().observe(this, pwCheckedObserver);
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
