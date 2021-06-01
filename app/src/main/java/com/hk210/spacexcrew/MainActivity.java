package com.hk210.spacexcrew;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.hk210.spacexcrew.Adapter.CrewAdapter;
import com.hk210.spacexcrew.Dao.CrewDao;
import com.hk210.spacexcrew.Model.Crew;
import com.hk210.spacexcrew.Network.ApiClient;
import com.hk210.spacexcrew.Repository.CrewRepository;
import com.hk210.spacexcrew.ViewModel.CrewViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CrewViewModel crewViewModel;
    private CrewAdapter crewAdapter;
    private List<Crew> list;
    private String URL_DATA = "https://api.spacexdata.com/v4/";
    private CrewRepository crewRepository;
    private Button refresh, delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.list_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        crewRepository = new CrewRepository(getApplication());
        list = new ArrayList<>();
        crewAdapter = new CrewAdapter(this, list);
        refresh = findViewById(R.id.refresh_button);
        delete = findViewById(R.id.delete_button);

        crewViewModel = new ViewModelProvider(this).get(CrewViewModel.class);
        crewViewModel.getAllCrew().observe(this, new Observer<List<Crew>>() {
            @Override
            public void onChanged(List<Crew> crewList) {
                crewAdapter.getALlData(crewList);
                recyclerView.setAdapter(crewAdapter);
                Log.i("main", "{}"+ crewList);
            }
        });

        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isNetworkConnected() == true){
                    networkRequest();
                    Toast.makeText(MainActivity.this, "new Data fetched and list is updated.", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(MainActivity.this, "Connect to Internet Old data is in the list.", Toast.LENGTH_SHORT).show();
                }


            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               crewViewModel.delete(list);
            }
        });

        networkRequest();


    }
    // if internet is available
    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }

    //fetching data
    private void networkRequest() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL_DATA)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiClient apiClient = retrofit.create(ApiClient.class);
        Call<List<Crew>> listCall = apiClient.getAllCrew();
        listCall.enqueue(new Callback<List<Crew>>() {
            @Override
            public void onResponse(Call<List<Crew>> call, Response<List<Crew>> response) {
                crewRepository.insert(response.body());

            }

            @Override
            public void onFailure(Call<List<Crew>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Something Went wrong: "+ t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}