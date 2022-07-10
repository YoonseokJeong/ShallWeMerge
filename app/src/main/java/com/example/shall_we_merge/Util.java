package com.example.shall_we_merge;

import android.text.InputFilter;
import android.text.Spanned;

import java.util.regex.Pattern;

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
}
