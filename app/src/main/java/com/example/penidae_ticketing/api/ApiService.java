package com.example.penidae_ticketing.api;

import com.example.penidae_ticketing.model.Attraction;
import com.example.penidae_ticketing.model.Auth;
import com.example.penidae_ticketing.model.Boat;
import com.example.penidae_ticketing.model.Hotel;
import com.example.penidae_ticketing.model.Payment;
import com.example.penidae_ticketing.model.Room;
import com.example.penidae_ticketing.model.Schedule;
import com.example.penidae_ticketing.model.TourPack;
import com.example.penidae_ticketing.model.TransBoat;
import com.example.penidae_ticketing.model.TransHotel;
import com.example.penidae_ticketing.model.TransTour;
import com.example.penidae_ticketing.model.TransVehicle;
import com.example.penidae_ticketing.model.TransWatersport;
import com.example.penidae_ticketing.model.Upload;
import com.example.penidae_ticketing.model.Vehicle;
import com.example.penidae_ticketing.model.VehicleOwner;
import com.example.penidae_ticketing.model.Watersport;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

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

    @FormUrlEncoded
    @POST("api.php?apicall=editprofile")
    Call<Auth>edit(@Field("name") String name,
                   @Field("username") String username,
                   @Field("updated_at") String updated_at,
                   @Field("phone") String phone,
                   @Field("email") String email,
                   @Field("id") Integer id);

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
                              @Field("departure") String departure,
                              @Field("guest") Integer guest);

    @FormUrlEncoded
    @POST("boat.php?apicall=TransBoat")
    Call<Payment>transBoat(@Field("depart_date") String depart_date,
                            @Field("schedule") Integer schedule,
                            @Field("reserve_date") String reserve_date,
                            @Field("qty") Integer qty,
                            @Field("total_price") Integer total_price,
                            @Field("id_user") Integer id_user);

    @POST("vehicle.php?apicall=allowner")
    Call<VehicleOwner>allOwner();

    @FormUrlEncoded
    @POST("vehicle.php?apicall=searchRental")
    Call<VehicleOwner>searchRental(@Field("pick_up") String pick_up,
                           @Field("return") String re,
                           @Field("cat") String cat);

    @FormUrlEncoded
    @POST("vehicle.php?apicall=searchVehicle")
    Call<Vehicle>searchVehicle(@Field("id_rental") Integer id,
                               @Field("pick_up") String pickup_date,
                               @Field("return") String return_date);

    @FormUrlEncoded
    @POST("vehicle.php?apicall=TransRent")
    Call<Payment>transVehicle(@Field("id_user") Integer id_user,
                           @Field("take") String take,
                           @Field("return") String re,
                           @Field("total_price") Integer total_price,
                           @Field("id_vehicle") Integer id_vehicle,
                           @Field("transdate") String transdate);

    @FormUrlEncoded
    @POST("watersport.php?apicall=allatt")
    Call<Attraction>allatt(@Field("id") Integer id);

    @POST("watersport.php?apicall=allwatersport")
    Call<Watersport>allwatersport();

    @FormUrlEncoded
    @POST("watersport.php?apicall=TransWatersport")
    Call<Payment>transWatersport(@Field("id_attraction") Integer id_attraction,
                              @Field("reserve_date") String reserve_date,
                              @Field("qty") Integer qty,
                              @Field("date") String date,
                              @Field("total_price") Integer total_price,
                              @Field("id_user") Integer id_user);

    @FormUrlEncoded
    @POST("tour.php?apicall=alltour")
    Call<TourPack>alltour(@Field("tour_date") String tour_date,
                          @Field("guest") Integer guest);

    @FormUrlEncoded
    @POST("tour.php?apicall=TransTour")
    Call<Payment>transTour(@Field("tour_date") String tour_date,
                          @Field("total_price") Integer total_price,
                          @Field("id_user") Integer id_user,
                          @Field("id_tour_package") Integer id_tour_package,
                          @Field("qty") Integer qty,
                          @Field("reserve_date") String reserve_date);

    @FormUrlEncoded
    @POST("boat.php?apicall=getTransBoat")
    Call<TransBoat>getTransBoat(@Field("id_user") Integer id_user);

    @FormUrlEncoded
    @POST("hotel.php?apicall=getTransHotel")
    Call<TransHotel>getTransHotel(@Field("id_user") Integer id_user);

    @FormUrlEncoded
    @POST("vehicle.php?apicall=getTransVehicle")
    Call<TransVehicle>getTransVehicle(@Field("id_user") Integer id_user);

    @FormUrlEncoded
    @POST("watersport.php?apicall=getTransWatersport")
    Call<TransWatersport>getTransWatersport(@Field("id_user") Integer id_user);

    @FormUrlEncoded
    @POST("tour.php?apicall=getTransTour")
    Call<TransTour>getTransTour(@Field("id_user") Integer id_user);

    @Multipart
    @POST("boat.php?apicall=proofBoat")
    Call<Upload>uploadBoat(@Part MultipartBody.Part image, @Part("id_transaksi") RequestBody id_transaksi);

    @Multipart
    @POST("hotel.php?apicall=proofHotel")
    Call<Upload>uploadHotel(@Part MultipartBody.Part image, @Part("id_transaksi") RequestBody id_transaksi);

    @Multipart
    @POST("vehicle.php?apicall=proofVehicle")
    Call<Upload>uploadVehicle(@Part MultipartBody.Part image, @Part("id_transaksi") RequestBody id_transaksi);

    @Multipart
    @POST("watersport.php?apicall=proofWatersport")
    Call<Upload>uploadWatersport(@Part MultipartBody.Part image, @Part("id_transaksi") RequestBody id_transaksi);

    @Multipart
    @POST("tour.php?apicall=proofTour")
    Call<Upload>uploadTour(@Part MultipartBody.Part image, @Part("id_transaksi") RequestBody id_transaksi);

}
