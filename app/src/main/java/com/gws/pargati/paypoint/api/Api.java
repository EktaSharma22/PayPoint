package com.gws.pargati.paypoint.api;

import com.gws.pargati.paypoint.model.AddUserResponse;
import com.gws.pargati.paypoint.model.CategoryResponse;
import com.gws.pargati.paypoint.model.RechargeCheckResponse;
import com.gws.pargati.paypoint.model.RechargeStatusResponse;
import com.gws.pargati.paypoint.model.UploadUserTypeResponse;
import com.gws.pargati.paypoint.model.LoginResponse;
import com.gws.pargati.paypoint.model.MakeDealerResponse;
import com.gws.pargati.paypoint.model.ServiceProviderAllResponse;
import com.gws.pargati.paypoint.model.SettingsWalletResponse;
import com.gws.pargati.paypoint.model.UserResponse;
import com.gws.pargati.paypoint.model.WalletApproveResponse;
import com.gws.pargati.paypoint.model.WalletNotificationResponse;
import com.gws.pargati.paypoint.model.WalletRequestResponse;
import com.gws.pargati.paypoint.model.WorldlinkResponse;

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
            @Header("device_token") String device_token,
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

    @FormUrlEncoded
    @POST("recharge/check/service")
    Call<RechargeCheckResponse> rechargeCheck(
            @Header("token") String token,
            @Field("mobile") String mobile,
            @Field("service_provider") String service_provider
    );

    @FormUrlEncoded
    @POST("recharge/online/payment")
    Call<RechargeStatusResponse> recharge(
            @Header("token") String token,
            @Field("mobile_number") String mobile_number,
            @Field("service_provider") String service_provider,
            @Field("amount") int amount
    );

    @FormUrlEncoded
    @POST("recharge/worldlink/bill/query")
    Call<WorldlinkResponse> query(
            @Header("token") String token,
            @Field("msisdn") String msisdn
    );

}

