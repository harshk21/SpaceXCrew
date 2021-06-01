package com.hk210.spacexcrew.Network;

import com.hk210.spacexcrew.Model.Crew;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiClient {
    @GET("crew")
    Call<List<Crew>> getAllCrew();
}
