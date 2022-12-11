package com.anilkumar.mycountryapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.anilkumar.mycountryapp.Adapte.CountryAdapter;
import com.anilkumar.mycountryapp.Modal.CountryModal;
import com.anilkumar.mycountryapp.Modal.Result;
import com.anilkumar.mycountryapp.Service.GetCountryData;
import com.anilkumar.mycountryapp.Service.RetrofitInstance;

import java.util.ArrayList;

import kotlin.contracts.Returns;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ArrayList <Result>results;

    private RecyclerView recyclerView;
    private CountryAdapter countryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GetCountries();
    }

    public Object GetCountries() {
        GetCountryData getCountryData = RetrofitInstance.getCountryData();

        Call<CountryModal>call=getCountryData.getCountryModal();
        call.enqueue(new Callback<CountryModal>() {
            @Override
            public void onResponse(Call<CountryModal> call, Response<CountryModal> response) {
                CountryModal countryModal=response.body();

                if(countryModal!=null&&countryModal.getResult()!=null){

                    results=(ArrayList<Result>)countryModal.getResult();

//                    for (Result c: results){
////                        Log.i("TAG",""+c.getName());
//
//                    }
                    ViewData();
                }

            }

            @Override
            public void onFailure(Call<CountryModal> call, Throwable t) {

            }
        });

        return results;
    }

    private void ViewData() {

        recyclerView=findViewById(R.id.Recyclerview);
        CountryAdapter countryAdapter= new CountryAdapter(results);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(countryAdapter);

    }
}