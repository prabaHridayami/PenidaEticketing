package com.example.penidae_ticketing.api;

import com.example.penidae_ticketing.model.Attraction;
import com.example.penidae_ticketing.model.Boat;
import com.example.penidae_ticketing.model.Hotel;
import com.example.penidae_ticketing.model.Room;
import com.example.penidae_ticketing.model.TourPack;
import com.example.penidae_ticketing.model.User;
import com.example.penidae_ticketing.model.VehicleOwner;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiService {

    @FormUrlEncoded
    @POST("api.php?apicall=login")
    Call<User>login(@Field("username") String username, @Field("password") String password);

    @FormUrlEncoded
    @POST("api.php?apicall=signup")
    Call<User>register(@Field("name") String name,
                       @Field("username") String username,
                       @Field("email") String email,
                       @Field("password") String password,
                       @Field("phone") String phone);

    @POST("hotel.php?apicall=allhotel")
    Call<Hotel>allhotel();

    @FormUrlEncoded
    @POST("hotel.php?apicall=searchRoom")
    Call<Hotel>searchHotel(@Field("check_in") String check_in,
                           @Field("check_out") String check_out,
                           @Field("guest") Integer guest,
                           @Field("room") Integer room);

    @FormUrlEncoded
    @POST("hotel.php?apicall=getRoom")
    Call<Room>getRoom(@Field("check_in") String check_in,
                      @Field("check_out") String check_out,
                      @Field("id") Integer id);

    @POST("boat.php?apicall=allboat")
    Call<Boat>allboat();

    @POST("vehicle.php?apicall=allowner")
    Call<VehicleOwner>allOwner();

    @POST("watersport.php?apicall=allatt")
    Call<Attraction>allatt();

    @POST("tour.php?apicall=alltour")
    Call<TourPack>alltour();

}
