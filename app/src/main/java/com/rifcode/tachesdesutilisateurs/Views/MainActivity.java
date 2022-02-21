package com.rifcode.tachesdesutilisateurs.Views;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rifcode.tachesdesutilisateurs.API.GetData;
import com.rifcode.tachesdesutilisateurs.API.RetrofitInstance;
import com.rifcode.tachesdesutilisateurs.Adapters.UserAdapter;
import com.rifcode.tachesdesutilisateurs.Models.Tache;
import com.rifcode.tachesdesutilisateurs.Models.User;
import com.rifcode.tachesdesutilisateurs.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private UserAdapter adapter;
    private ArrayList<User> listUsers;
    private RecyclerView rcv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle(R.string.stg_users);
        widgets();
        listUsers=new ArrayList<>();
        adapter = new UserAdapter(listUsers,getApplicationContext());
        rcv.setAdapter(adapter);

        getUsers();
    }

    private void widgets() {
        rcv = findViewById(R.id.rcvUsers);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rcv.setLayoutManager(layoutManager);
    }

    private void getUsers() {

        GetData getData = RetrofitInstance.getService(this);
        Call<List<User>> call = getData.getUsers();

        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (response.body()!=null) {
                    listUsers.addAll(response.body());
                    adapter.notifyDataSetChanged();
                }
            }
            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Log.d("TAG","Response 2 = "+t.toString());
            }
        });

    }

}