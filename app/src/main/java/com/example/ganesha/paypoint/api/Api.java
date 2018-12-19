package com.example.ganesha.paypoint.api;

import com.example.ganesha.paypoint.model.AddUserResponse;
import com.example.ganesha.paypoint.model.CategoryResponse;
import com.example.ganesha.paypoint.model.UploadUserTypeResponse;
import com.example.ganesha.paypoint.model.LoginResponse;
import com.example.ganesha.paypoint.model.MakeDealerResponse;
import com.example.ganesha.paypoint.model.ServiceProviderAllResponse;
import com.example.ganesha.paypoint.model.SettingsWalletResponse;
import com.example.ganesha.paypoint.model.UserResponse;
import com.example.ganesha.paypoint.model.WalletApproveResponse;
import com.example.ganesha.paypoint.model.WalletNotificationResponse;
import com.example.ganesha.paypoint.model.WalletRequestResponse;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface Api
{
    @FormUrlEncoded
    @POST("user/signup")
    Call<ResponseBody> createRegister(
            @Field("name") String name,
            @Field("email") String email,
            @Field("mobile") String mobile,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("user/signin")
    Call<LoginResponse> createLogin(
            @Field("email") String email,
            @Field("password") String password
    );

    @GET("user/users")
    Call<UserResponse> getUsers(@Header("token") String token);


   @FormUrlEncoded
    @POST("user/add")
    Call<AddUserResponse> addNewUser(
            @Header("token") String token,
            @Field("name") String name,
            @Field("email") String email,
            @Field("password") String password,
            @Field("mobile") String mobile
    );

    @FormUrlEncoded
    @POST("setting/add")
    Call<SettingsWalletResponse> addWalletCommission(
            @Header("token") String token,
            @Field("setting_name") String setting_name,
            @Field("setting_value") String setting_value
    );

    @Multipart
    @POST("wallet-request/add")
    Call<WalletRequestResponse> addWallet(
            @Header("token") String token,
            @Part("payment_mode") RequestBody payment_mode,
            @Part("cheque_no") RequestBody cheque_no,
            @Part("amount") RequestBody amount,
            @Part("bank_name") RequestBody bank_name,
            @Part("deposit_date") RequestBody deposit_date,
            @Part("user_id") RequestBody user_id,
            @Part MultipartBody.Part photo

            );

    //@PartMap Map<String, RequestBody> map
    // @Part MultipartBody.Part photo


      @FormUrlEncoded
    @POST("admin/make/dealer")
    Call<MakeDealerResponse> changestatus(
             @Header("token") String token,
             @Field("user_id") String user_id
    );

    @GET("wallet-request/all")
    Call<WalletNotificationResponse> getWallet(@Header("token") String token);

    @GET("category/all")
    Call<CategoryResponse> getImageDetails(@Header("token") String token);

    @FormUrlEncoded
    @POST("serviceproviders/all")
    Call<ServiceProviderAllResponse> serviceCategory(
            @Header("token") String token,
            @Field("category_id") String category_id
    );

    @FormUrlEncoded
    @POST("user/myusers")
    Call<UploadUserTypeResponse> getDealerUsers(@Header("token") String token, @Field("user_type") String user_type);



    /*@GET("wallet-request/single/{_id})
    Call<FilterResponse> getFilterList(@Path("_id") long customerId,
                                       @Query("Type") String responseType,
                                       @Query("SearchText") String searchText);*/


    @FormUrlEncoded
    @POST("wallet-request/approve")
    Call<WalletApproveResponse> walletApprove(
            @Header("token") String token,
            @Field("id") String id
    );


}

