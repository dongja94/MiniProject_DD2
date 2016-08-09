package com.begentgroup.miniproject.request;

import android.content.Context;

import com.begentgroup.miniproject.data.NetworkResult;
import com.begentgroup.miniproject.data.NetworkResultTemp;
import com.begentgroup.miniproject.data.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

/**
 * Created by Administrator on 2016-08-09.
 */
public class SignUpRequest extends AbstractRequest<NetworkResult<User>> {
    Request request;
    public SignUpRequest(Context context,String username, String password,
                         String email, String regId) {
        HttpUrl.Builder builder = getBaseUrlBuilder();
        builder.addPathSegment("signup");

        RequestBody body = new FormBody.Builder()
                .add("username",username)
                .add("password",password)
                .add("email", email)
                .add("registrationId", regId)
                .build();

        request = new Request.Builder()
                .url(builder.build())
                .post(body)
                .tag(context)
                .build();
    }

    @Override
    public Request getRequest() {
        return request;
    }

    @Override
    protected NetworkResult<User> parse(ResponseBody body) throws IOException {
        String text = body.string();
        Gson gson = new Gson();
        NetworkResultTemp temp = gson.fromJson(text, NetworkResultTemp.class);
        if (temp.getCode() == 1) {
            Type type = new TypeToken<NetworkResult<User>>(){}.getType();
            NetworkResult<User> result = gson.fromJson(text, type);
            return result;
        } else {
            Type type = new TypeToken<NetworkResult<String>>(){}.getType();
            NetworkResult<String> result = gson.fromJson(text, type);
            throw new IOException(result.getResult());
        }
    }
}
