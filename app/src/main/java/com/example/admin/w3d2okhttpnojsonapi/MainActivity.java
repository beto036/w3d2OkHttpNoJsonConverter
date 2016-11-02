package com.example.admin.w3d2okhttpnojsonapi;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {


    private static final String TAG = "MainActivityTAG_";
    private OkHttpClient client;
    private String json;
    private ArrayList<User> users;

    public static final String NAME = "name";
    public static final String AGE = "age";
    public static final String GRADE = "grade";
    public static final String PASS = "password";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        client = new OkHttpClient();
        users = new ArrayList<User>();

    }

    String run(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    public boolean checkConnectivity() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        if (activeNetwork != null) {
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
                Log.d(TAG, "checkInternet: " + "Connected to WIFI");
                return true;
            } else if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
                Log.d(TAG, "checkInternet: " + "Connected to Mobile data");
                return true;
            }
        } else {
            Log.d(TAG, "checkInternet: " + "Not connected");
            return false;
        }
        return false;
    }

    public void showUsers(View view) {
        List<User> users = User.listAll(User.class);
        for (User user : users){
            Log.d(TAG, "showUsers: " + user);
        }
    }

    public void fillDB(View view) {
        try {
            json = run("http://www.mocky.io/v2/57a4dfb40f0000821dc9a3b8");
            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                User user = new User(jsonObject.getString(NAME),jsonObject.getString(PASS),jsonObject.getInt(AGE), jsonObject.getDouble(GRADE));
                user.save();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
