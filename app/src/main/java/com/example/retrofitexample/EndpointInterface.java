package com.example.retrofitexample;

        import java.util.List;

        import retrofit2.Call;
        import retrofit2.http.GET;

public interface EndpointInterface {
    @GET("dayone/country/IN")
    //Call<String> getData() ;
    Call<List<Repo>> getData() ;
}


