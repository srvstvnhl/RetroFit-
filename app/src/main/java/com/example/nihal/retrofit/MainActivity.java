package com.example.nihal.retrofit;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.nihal.retrofit.Interface.RetrieveDataInterface;
import com.example.nihal.retrofit.Interface.onItemClickListener;
import com.example.nihal.retrofit.Pojo.Detail;
import com.example.nihal.retrofit.Pojo.JSONResponse;
import com.example.nihal.retrofit.Pojo.Post;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements onItemClickListener {

    private ProgressDialog dialog;
    private  RecyclerView recyclerView;

    private RecyclerViewAdapter recyclerViewAdapter;
    private ArrayList<Post> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView(){

        dialog = ProgressDialog.show(MainActivity.this,
                " ",
                "Loading Please wait...",
                true);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        loadJson();
    }

    private void loadJson(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.ultimatebattle.in/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrieveDataInterface request = retrofit.create(RetrieveDataInterface.class);
        Call<JSONResponse> result = request.getJson();

        result.enqueue(new Callback<JSONResponse>() {
            @Override
            public void onResponse(Call<JSONResponse> call, Response<JSONResponse> response) {

                dialog.dismiss();
                JSONResponse jsonResponse = response.body();
                data = new ArrayList<>(Arrays.asList(jsonResponse.getData()));
                recyclerViewAdapter = new RecyclerViewAdapter(data,MainActivity.this);
                recyclerView.setAdapter(recyclerViewAdapter);
            }
            @Override
            public void onFailure(Call<JSONResponse> call, Throwable t) {

                Toast.makeText(MainActivity.this, "Please try again", Toast.LENGTH_SHORT).show();

            }
        });
    }
    public void onItemClick(int position, View view){
        String id1 = data.get(position).getId();
        //Toast.makeText(MainActivity.this, "please try again", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(MainActivity.this,DetailActivity.class);
        intent.putExtra("ID",id1);
        startActivity(intent);

    }

}
