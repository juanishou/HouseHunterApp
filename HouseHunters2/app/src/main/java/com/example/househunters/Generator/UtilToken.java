package com.example.househunters.Generator;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.househunters.R;


public class UtilToken {
    public static void setToken(Context mContext, String token) {
        SharedPreferences sharedPreferences =
                mContext.getSharedPreferences(
                        "login",
                        Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(mContext.getString(R.string.jwt_key), token);
        editor.commit();
    }


    public static String getToken(Context mContext) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(
                "login",
                Context.MODE_PRIVATE
        );

        String jwt = sharedPreferences
                .getString("JWT_KEY", null);

        return jwt;
    }
}
