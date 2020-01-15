package com.example.penidae_ticketing.api;

import com.example.penidae_ticketing.model.Attraction;
import com.example.penidae_ticketing.model.Auth;
import com.example.penidae_ticketing.model.Boat;
import com.example.penidae_ticketing.model.Hotel;
import com.example.penidae_ticketing.model.Payment;
import com.example.penidae_ticketing.model.Room;
import com.example.penidae_ticketing.model.RoomItem;
import com.example.penidae_ticketing.model.Schedule;
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
    Call<Auth>login(@Field("username") String username, @Field("password") String password);

    @FormUrlEncoded
    @POST("api.php?apicall=signup")
    Call<Auth>register(@Field("name") String name,
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
                      @Field("tot_room") Integer tot_room,
                      @Field("id") Integer id);

    @FormUrlEncoded
    @POST("hotel.php?apicall=getRoomId")
    Call<Room>getRoomId(@Field("check_in") String check_in,
                            @Field("check_out") String check_out,
                            @Field("id") Integer id);

    @FormUrlEncoded
    @POST("hotel.php?apicall=TransHotel")
    Call<Payment>transHotel(@Field("id_room") Integer id_room,
                         @Field("check_in") String check_in,
                         @Field("check_out") String check_out,
                         @Field("reserve_date") String reserve_date,
                         @Field("total_price") Integer total_price,
                         @Field("id_user") Integer id_user);

    @POST("boat.php?apicall=allboat")
    Call<Boat>allboat();

    @FormUrlEncoded
    @POST("boat.php?apicall=getSchedule")
    Call<Schedule>getSchedule(@Field("id_boat") Integer id_boat,
                              @Field("depart") String depart);

    @FormUrlEncoded
    @POST("boat.php?apicall=TransHotel")
    Call<Payment>transBoat(@Field("depart_date") String depart_date,
                            @Field("schedule") Integer schedule,
                            @Field("reserve_date") String reserve_date,
                            @Field("qty") Integer qty,
                            @Field("total_price") Integer total_price,
                            @Field("id_user") Integer id_user);

    @POST("vehicle.php?apicall=allowner")
    Call<VehicleOwner>allOwner();

    @POST("watersport.php?apicall=allatt")
    Call<Attraction>allatt();

    @POST("tour.php?apicall=alltour")
    Call<TourPack>alltour();

}
