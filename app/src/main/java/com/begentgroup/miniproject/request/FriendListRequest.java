package com.begentgroup.miniproject.request;

import android.content.Context;

import com.begentgroup.miniproject.data.NetworkResult;
import com.begentgroup.miniproject.data.User;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import okhttp3.HttpUrl;
import okhttp3.Request;

/**
 * Created by Administrator on 2016-08-09.
 */
public class FriendListRequest extends AbstractRequest<NetworkResult<List<User>>> {
    Request request;
    public FriendListRequest(Context context) {
        HttpUrl url = getBaseUrlBuilder()
                .addPathSegment("friendlist")
                .build();
        request = new Request.Builder()
                .url(url)
                .tag(context)
                .build();
    }
    @Override
    public Request getRequest() {
        return request;
    }

//    @Override
//    protected NetworkResult<List<User>> parse(ResponseBody body) throws IOException {
//        String text = body.string();
//        Gson gson = new Gson();
//        NetworkResultTemp temp = gson.fromJson(text, NetworkResultTemp.class);
//        if (temp.getCode() == 1) {
//            Type type = new TypeToken<NetworkResult<List<User>>>(){}.getType();
//            NetworkResult<List<User>> result = gson.fromJson(text, type);
//            return result;
//        } else {
//            Type type = new TypeToken<NetworkResult<String>>(){}.getType();
//            NetworkResult<String> result = gson.fromJson(text, type);
//            throw new IOException(result.getResult());
//        }
//    }

    @Override
    protected Type getType() {
        return new TypeToken<NetworkResult<List<User>>>(){}.getType();
    }
}
