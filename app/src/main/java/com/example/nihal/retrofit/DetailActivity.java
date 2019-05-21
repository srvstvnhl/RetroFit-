package com.example.nihal.retrofit;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nihal.retrofit.Interface.RetrieveDataInterface;
import com.example.nihal.retrofit.Pojo.MyResponse;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailActivity extends AppCompatActivity {

    ProgressDialog progressDialog;
    String tournamentId;
    TextView title,dateTournament,maxPart,part,platform,prizeMoney,venue;
    ImageView imageView;
    MyResponse response1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_detail_activity);

        title = findViewById(R.id.tvDetailTitle);
        dateTournament=findViewById(R.id.tvDetailtournament_date);
        maxPart=findViewById(R.id.tvDetailmax_participants);
        platform=findViewById(R.id.tvDetailplatform);
        prizeMoney=findViewById(R.id.tvDetailprize_money);
        venue=findViewById(R.id.tvDetailtournament_venue);
        imageView=findViewById(R.id.imgDetail);
        Button btnBack = findViewById(R.id.btnBack);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
        progressDialog = ProgressDialog.show(DetailActivity.this,"","please wait...",true);


        if(getIntent().hasExtra("ID")){

            tournamentId=getIntent().getStringExtra("ID");
            Log.e("TAG",""+tournamentId);
        }

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.ultimatebattle.in/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrieveDataInterface retrieveDataInterface = retrofit.create(RetrieveDataInterface.class);

        Call<MyResponse> call = retrieveDataInterface.getTournamentDetails(tournamentId);
        call.enqueue(new Callback<MyResponse>() {
            @Override
            public void onResponse(Call<MyResponse> call, Response<MyResponse> response) {

                progressDialog.dismiss();
                response1 = response.body();

                title.setText(response1.getData().getTitle());
                dateTournament.setText(response1.getData().getTournamentDate());
                platform.setText(response1.getData().getPlatform());
                Integer num=response1.getData().getMaxParticipants();
                String ans=num.toString();
                maxPart.setText(ans);
                prizeMoney.setText(response1.getData().getPrizeMoney());
                part.setText(response1.getData().getNoOfParticipants());
                Picasso.with(getBaseContext()).load(response1.getData().getImage()).into(imageView);

            }

            @Override
            public void onFailure(Call<MyResponse> call, Throwable t) {

                progressDialog.dismiss();
                Toast.makeText(DetailActivity.this, "failed to load", Toast.LENGTH_SHORT).show();

            }
        });
    }

}
