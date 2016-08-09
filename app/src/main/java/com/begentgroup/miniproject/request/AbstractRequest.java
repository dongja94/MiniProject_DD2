package com.begentgroup.miniproject.request;

import com.begentgroup.miniproject.manager.NetworkRequest;

import okhttp3.HttpUrl;

/**
 * Created by Administrator on 2016-08-09.
 */
public abstract class AbstractRequest<T> extends NetworkRequest<T> {

    protected HttpUrl.Builder getBaseUrlBuilder() {
        HttpUrl.Builder builder = new HttpUrl.Builder();
        builder.scheme("https");
        builder.host("dongjatestweb.appspot.com");
        return builder;
    }
}
