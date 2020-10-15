package com.wzh.fruitgo.Service;

import com.wzh.fruitgo.Config.DBConstant;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class UserService {
    OkHttpClient okHttpClient = new OkHttpClient();

    public boolean SignUp(String tel, String name, String password){
        FormBody formBody = new FormBody.Builder()
                .add("tel", tel)
                .add("name",name)
                .add("password",password)
                .build();
        Request request = new Request.Builder()
                .url(DBConstant.USER_URL+"addUser")
                .post(formBody)
                .build();
        try(Response response = okHttpClient.newCall(request).execute()) {
            if (Boolean.parseBoolean(response.body().string())){
                return true;
            }
            else{
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
