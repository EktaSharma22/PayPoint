package com.gws.pargati.paypoint.fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.gws.pargati.paypoint.R;
import com.gws.pargati.paypoint.activities.UploadActivity;
import com.gws.pargati.paypoint.adapters.ItemAdapter;
import com.gws.pargati.paypoint.api.RetrofitClient;
import com.gws.pargati.paypoint.model.Category;
import com.gws.pargati.paypoint.model.CategoryResponse;
import com.gws.pargati.paypoint.storage.SharedPrefManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeFragment extends Fragment implements View.OnClickListener{

    LinearLayout llAddMoney, llUpload, llRechargeBill, llHomeUtilities,llDthTopUp, llOrders;
    //   LinearLayout llReports;
    LinearLayout llNotification;
    LinearLayout llUser, llAdminDealer;
    LinearLayout  llAddMoneyUser, llUploadsUser, llOrdersUser;
    TextView tvNRrate, tvBalance;

    private RecyclerView recyclerView;
    private Category category;
    ProgressDialog pd;

    // public static final String ACCESS_KEY = "26a8623db40293f6be72b85439969e9b";
    // public static final String BASE_URL = "http://apilayer.net/api/";
    // public static final String ENDPOINT = "live";

    // this object is used for executing requests to the (REST) API
    //   static CloseableHttpClient httpClient = HttpClients.createDefault();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home,container,false);
        llAddMoney = (LinearLayout)view.findViewById(R.id.llAddMoney);
        llUpload = (LinearLayout)view.findViewById(R.id.llUploads);
        // llReports=(LinearLayout)view.findViewById(R.id.llReports);
        llOrders=(LinearLayout)view.findViewById(R.id.llOrders) ;
        llAdminDealer= (LinearLayout)view.findViewById(R.id.llAdminDealer);
        llUser=(LinearLayout)view.findViewById(R.id.llUser);
        llAddMoneyUser = (LinearLayout)view.findViewById(R.id.llAddMoneyUser);
        llUploadsUser = (LinearLayout)view.findViewById(R.id.llUploadsUser);
        llOrdersUser = (LinearLayout)view.findViewById(R.id.llOrdersUser);
        llNotification = (LinearLayout)view.findViewById(R.id.llNotification);

        tvNRrate =(TextView)view.findViewById(R.id.tvNRrate);
        tvBalance = (TextView)view.findViewById(R.id.tvBalance);
        llUpload.setOnClickListener(this);
        llAddMoney.setOnClickListener(this);
        //   llRechargeBill.setOnClickListener(this);
        //   llReports.setOnClickListener(this);
        llOrders.setOnClickListener(this);
        llAddMoneyUser.setOnClickListener(this);
        llUploadsUser.setOnClickListener(this);
        llOrdersUser.setOnClickListener(this);
        //    llHomeUtilities.setOnClickListener(this);
        //     llDthTopUp.setOnClickListener(this);
        llNotification.setOnClickListener(this);

       /* pd = new ProgressDialog(getContext());
        pd.setMessage("Fetchng Data......");
        pd.setCancelable(false);
        pd.show();*/
        recyclerView = (RecyclerView)view.findViewById(R.id.recyclerView);
        LinearLayoutManager horizontalLayoutManagaer = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(horizontalLayoutManagaer);
        recyclerView.smoothScrollToPosition(0);
        loadJson();

        return view;
    }

    private void loadJson()
    {
        try {

            SharedPreferences preferences = getActivity().getSharedPreferences("MY_APP",Context.MODE_PRIVATE);
            String retrivedToken  = preferences.getString("TOKEN",null);

            Call<CategoryResponse> call = RetrofitClient.getmInstance().getApi().getImageDetails(retrivedToken);
            call.enqueue(new Callback<CategoryResponse>() {
                @Override
                public void onResponse(Call<CategoryResponse> call, Response<CategoryResponse> response) {
                    List<Category> items = response.body().getData();
                    recyclerView.setAdapter(new ItemAdapter(getContext(),items));
                    recyclerView.smoothScrollToPosition(0);
                    //pd.hide();
                }
                @Override
                public void onFailure(Call<CategoryResponse> call, Throwable t) {
                    Log.d("Error",t.getMessage());
                    Toast.makeText(getContext(),"Error Fetching data....",Toast.LENGTH_SHORT).show();
                  //  pd.hide();
                }
            });
        }catch (Exception e)
        {
            Log.d("Error",e.getMessage());
            Toast.makeText(getContext(),e.toString(),Toast.LENGTH_SHORT).show();

        }
    }




    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

       /* tvId.setText(String.valueOf(SharedPrefManager.getInstance(getActivity()).getUser().getId()));
        tvName.setText(SharedPrefManager.getInstance(getActivity()).getUser().getName());
        tvEmail.setText(SharedPrefManager.getInstance(getActivity()).getUser().getEmail());*/

        String user_type = SharedPrefManager.getInstance(getActivity()).getUser().getUser_type();
        if(user_type.equals("user"))
        {
            llUser.setVisibility(View.VISIBLE);
            llAdminDealer.setVisibility(View.GONE);

        }
        if(user_type.equals("dealer"))
        {
            llUser.setVisibility(View.GONE);
            llAdminDealer.setVisibility(View.VISIBLE);

        }
        if(user_type.equals("admin"))
        {
            llUser.setVisibility(View.GONE);
            llAdminDealer.setVisibility(View.VISIBLE);

        }

        String wallet_amount = String.valueOf(SharedPrefManager.getInstance(getActivity()).getUser().getWallet_balance());
        tvBalance.setText(wallet_amount);


    }

    @Override
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.llAddMoney:
               /* AddMoneyFragment addMoneyFragment = new AddMoneyFragment();
                FragmentManager fragmentManager1 = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction1 = fragmentManager1.beginTransaction();
                fragmentTransaction1.replace(R.id.rvContainer, addMoneyFragment);
                fragmentTransaction1.addToBackStack(null);
                fragmentTransaction1.commit();*/

                Toast.makeText(getContext(),"Add Money Feature Coming Soon",Toast.LENGTH_SHORT).show();

                break;

            case R.id.llUploads:
                UploadFragment uploadFragment = new UploadFragment();
                FragmentManager fragmentManagerupload = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransactionupload = fragmentManagerupload.beginTransaction();
                fragmentTransactionupload.replace(R.id.rvContainer, uploadFragment);
                fragmentTransactionupload.addToBackStack(null);
                fragmentTransactionupload.commit();
                break;

           /* case R.id.llRechargeBill:
                RechargeBillFragment rechargeBillFragment = new RechargeBillFragment();
                FragmentManager fragmentManagerRecharge = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransactionRecharge = fragmentManagerRecharge.beginTransaction();
                fragmentTransactionRecharge.replace(R.id.rvContainer, rechargeBillFragment);
                fragmentTransactionRecharge.addToBackStack(null);
                fragmentTransactionRecharge.commit();
                break;

            case R.id.llHomeUtilities:
                HomeUtilitiesFragment homeUtilitiesFragment = new HomeUtilitiesFragment();
                FragmentManager fragmentManagerhomeU = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransactionhomeU = fragmentManagerhomeU.beginTransaction();
                fragmentTransactionhomeU.replace(R.id.rvContainer, homeUtilitiesFragment);
                fragmentTransactionhomeU.addToBackStack(null);
                fragmentTransactionhomeU.commit();

                break;

            case R.id.llDthTopUp:
                DthFragment dthFragment = new DthFragment();
                FragmentManager fragmentManagerdth = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransactiondth = fragmentManagerdth.beginTransaction();
                fragmentTransactiondth.replace(R.id.rvContainer, dthFragment);
                fragmentTransactiondth.addToBackStack(null);
                fragmentTransactiondth.commit();
                break;*/

         /*   case R.id.llReports:
                ReportsFragment reportsFragment = new ReportsFragment();
                FragmentManager fragmentManagerReport = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransactionReport = fragmentManagerReport.beginTransaction();
                fragmentTransactionReport.replace(R.id.rvContainer, reportsFragment);
                fragmentTransactionReport.addToBackStack(null);
                fragmentTransactionReport.commit();

                break;*/

            case R.id.llNotification:
                NotificationFragment notificationFragment = new NotificationFragment();
                FragmentManager fragmentManagernotify = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransactionnotify = fragmentManagernotify.beginTransaction();
                fragmentTransactionnotify.replace(R.id.rvContainer, notificationFragment);
                fragmentTransactionnotify.addToBackStack(null);
                fragmentTransactionnotify.commit();
                break;


            case R.id.llOrders:
                OrdersFragment ordersFragment = new OrdersFragment();
                FragmentManager fragmentManagerOrders = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransactionOrders = fragmentManagerOrders.beginTransaction();
                fragmentTransactionOrders.replace(R.id.rvContainer, ordersFragment);
                fragmentTransactionOrders.addToBackStack(null);
                fragmentTransactionOrders.commit();
                break;

            case R.id.llOrdersUser:
                OrdersFragment ordersFragmentUser = new OrdersFragment();
                FragmentManager fragmentManagerOrdersUser = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransactionOrdersUsers = fragmentManagerOrdersUser.beginTransaction();
                fragmentTransactionOrdersUsers.replace(R.id.rvContainer, ordersFragmentUser);
                fragmentTransactionOrdersUsers.addToBackStack(null);
                fragmentTransactionOrdersUsers.commit();
                break;

            case R.id.llAddMoneyUser:
               /* AddMoneyFragment fragment = new AddMoneyFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.rvContainer, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();*/

                Toast.makeText(getContext(),"Add Money Feature Coming Soon",Toast.LENGTH_SHORT).show();

                break;

            case R.id.llUploadsUser:
                Intent uploadUser = new Intent(getContext(),UploadActivity.class);
                startActivity(uploadUser);
                break;


        }

    }

    /*public void sendLiveRequest(){

        // The following line initializes the HttpGet Object with the URL in order to send a request
        HttpGet get = new HttpGet(BASE_URL + ENDPOINT + "?access_key=" + ACCESS_KEY);

        try {
            CloseableHttpResponse response =  httpClient.execute(get);
            HttpEntity entity = response.getEntity();

            // the following line converts the JSON Response to an equivalent Java Object
            JSONObject exchangeRates = new JSONObject(EntityUtils.toString(entity));

            // Parsed JSON Objects are accessed according to the JSON resonse's hierarchy, output strings are built
            Date timeStampDate = new Date((long)(exchangeRates.getLong("timestamp")*1000));
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss a");
            String formattedDate = dateFormat.format(timeStampDate);

            tvNRrate.setText("1 " + exchangeRates.getString("source") + " in GBP : " + exchangeRates.getJSONObject("quotes").getDouble("USDGBP") + " (Date: " + formattedDate + ")");

            response.close();
        } catch (ClientProtocolException e) {

            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        } catch (JSONException e) {

            e.printStackTrace();
        }
    }*/

}
