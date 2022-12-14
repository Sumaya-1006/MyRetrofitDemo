package com.example.myretrofitdemo.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.media.Image;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.myretrofitdemo.R;
import com.example.myretrofitdemo.adapter.ImageAdapter;
import com.example.myretrofitdemo.model.ImageModel;
import com.example.myretrofitdemo.model.Search;
import com.example.myretrofitdemo.utils.ApiUtilities;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<ImageModel> list;
    private int page = 10;
    private ProgressDialog dialog;
    GridLayoutManager manager;

    private  int pageSize = 28;
    private boolean isLoading;
    private boolean lastPage;


    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerId);
        recyclerView.setHasFixedSize(true);
        list = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ImageAdapter adapter = new ImageAdapter(this,list);
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);
        dialog = new ProgressDialog(this);
        dialog.setMessage("Loading.....");
        dialog.setCancelable(false);
        dialog.show();

        getData();

    }

    private void getData() {
        isLoading= true;
        ApiUtilities.getApiInterface().getImage(page,21)
                .enqueue(new Callback<List<ImageModel>>() {
                    @Override
                    public void onResponse(Call<List<ImageModel>> call, Response<List<ImageModel>> response) {
                        if(response.body() != null ){
                            list.addAll(response.body());

                        }
                        isLoading = false;
                        dialog.dismiss();


                        if(list.size()>0){
                            lastPage = list.size()<pageSize;

                        }else lastPage = true;
                    }

                    @Override
                    public void onFailure(Call<List<ImageModel>> call, Throwable t) {
                        dialog.dismiss();
                        Toast.makeText(MainActivity.this, "Error"+ t.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });
        }
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu, menu);
        MenuItem search = menu.findItem(R.id.searchId);
        SearchView searchView = (SearchView) search.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                dialog.show();
                searchData(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return true;
    }

    private void searchData(String query) {
        ApiUtilities.getApiInterface().searchImage(query)
                .enqueue(new Callback<Search>() {
                    @Override
                    public void onResponse(Call<Search> call, Response<Search> response) {
                        dialog.dismiss();
                        list.clear();
                        list.addAll(response.body().getResults());


                    }

                    @Override
                    public void onFailure(Call<Search> call, Throwable t) {

                    }
                });
    }
}