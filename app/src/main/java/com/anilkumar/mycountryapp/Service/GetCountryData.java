package com.anilkumar.mycountryapp.Service;

import com.anilkumar.mycountryapp.Modal.CountryModal;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetCountryData {
    @GET("countries")
    Call<CountryModal>getCountryModal();
}
