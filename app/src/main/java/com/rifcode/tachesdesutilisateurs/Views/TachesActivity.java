package com.rifcode.tachesdesutilisateurs.Views;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rifcode.tachesdesutilisateurs.API.GetData;
import com.rifcode.tachesdesutilisateurs.API.RetrofitInstance;
import com.rifcode.tachesdesutilisateurs.Adapters.TacheAdapter;
import com.rifcode.tachesdesutilisateurs.Models.Tache;
import com.rifcode.tachesdesutilisateurs.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TachesActivity extends AppCompatActivity {

    private RecyclerView rcv;
    private ArrayList<Tache> listTaches;
    private int id_user;
    private TacheAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taches);
        getSupportActionBar().setTitle(R.string.stg_taches);
        id_user = getIntent().getIntExtra("user_id",0);

        widgets();
        listTaches=new ArrayList<>();
        adapter = new TacheAdapter(listTaches,getApplicationContext());
        rcv.setAdapter(adapter);

        getTaches();
    }


    private void widgets() {
        rcv = findViewById(R.id.rcvTaches);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rcv.setLayoutManager(layoutManager);
    }

    private void getTaches() {

        GetData getData = RetrofitInstance.getService(this);
        Call<List<Tache>> call = getData.getTachesByUserId(id_user);

        call.enqueue(new Callback<List<Tache>>() {
            @Override
            public void onResponse(Call<List<Tache>> call, Response<List<Tache>> response) {
                if (response.body()!=null) {
                    listTaches.addAll(response.body());
                    adapter.notifyDataSetChanged();
                }
                //rcv.setAdapter(listTaches,this);
            }
            @Override
            public void onFailure(Call<List<Tache>> call, Throwable t) {
                Log.d("TAG","Response 2 = "+t.toString());
            }
        });

    }
}