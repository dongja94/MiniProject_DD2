package com.begentgroup.miniproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.begentgroup.miniproject.data.NetworkResult;
import com.begentgroup.miniproject.data.User;
import com.begentgroup.miniproject.login.SimpleLoginActivity;
import com.begentgroup.miniproject.manager.NetworkManager;
import com.begentgroup.miniproject.manager.NetworkRequest;
import com.begentgroup.miniproject.manager.PropertyManager;
import com.begentgroup.miniproject.request.FriendListRequest;
import com.begentgroup.miniproject.request.LogOutRequest;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.listView)
    ListView listView;

    ArrayAdapter<User> mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mAdapter = new ArrayAdapter<User>(this, android.R.layout.simple_list_item_1);
        listView.setAdapter(mAdapter);

        FriendListRequest request = new FriendListRequest(this);
        NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<NetworkResult<List<User>>>() {
            @Override
            public void onSuccess(NetworkRequest<NetworkResult<List<User>>> request, NetworkResult<List<User>> result) {
                List<User> users = result.getResult();
                mAdapter.addAll(users);
            }

            @Override
            public void onFail(NetworkRequest<NetworkResult<List<User>>> request, int errorCode, String errorMessage, Throwable e) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_logout) {
            LogOutRequest request = new LogOutRequest(this);
            NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<NetworkResult<String>>() {
                @Override
                public void onSuccess(NetworkRequest<NetworkResult<String>> request, NetworkResult<String> result) {
                    PropertyManager.getInstance().setEmail("");
                    PropertyManager.getInstance().setPassword("");
                    Intent intent = new Intent(MainActivity.this, SimpleLoginActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                }

                @Override
                public void onFail(NetworkRequest<NetworkResult<String>> request, int errorCode, String errorMessage, Throwable e) {

                }
            });
        }
        return super.onOptionsItemSelected(item);
    }
}
