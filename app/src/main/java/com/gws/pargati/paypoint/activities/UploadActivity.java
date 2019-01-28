package com.gws.pargati.paypoint.activities;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.bumptech.glide.Glide;
import com.gws.pargati.paypoint.BuildConfig;
import com.gws.pargati.paypoint.R;
import com.gws.pargati.paypoint.adapters.UploadDealerUserAdapter;
import com.gws.pargati.paypoint.api.RetrofitClient;
import com.gws.pargati.paypoint.model.UploadUserTypeResponse;
import com.gws.pargati.paypoint.model.WalletRequestResponse;
import com.gws.pargati.paypoint.storage.SharedPrefManager;

import org.json.JSONArray;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Logger;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UploadActivity extends AppCompatActivity implements
        View.OnClickListener
{
    ImageView ivBack, ivAddUsers, ivUpload;
    TextView tvTitle;

    private static final int REQUEST_TAKE_PHOTO = 0;
    private static final int REQUEST_PICK_PHOTO = 2;
    private Uri mMediaUri;
    private static final int CAMERA_PIC_REQUEST = 1111;
    private static final String TAG = UploadActivity.class.getSimpleName();
    private static final int CAMERA_CAPTURE_IMAGE_REQUEST_CODE = 100;
    public static final int MEDIA_TYPE_IMAGE = 1;

    private Uri fileUri;

    private String mediaPath;

    private String mImageFileLocation = "";
    public static final String IMAGE_DIRECTORY_NAME = "Android File Upload";

    ProgressDialog pDialog;
    private String postPath;

    private EditText etAmount, in_date, etChequeNumber, etBankName;
    ImageView btnDatePicker;
    EditText txtDate;
    private ImageView ivCamera, ivGallery;
    private Button btnUploadData;
    private RadioGroup radioGroupMode;
    private RadioGroup radioGroupUser;
    private RadioButton radioButton;
    private RadioButton radioButtonMe,radioButtonUser, radioButtonDealer;
    private LinearLayout llSelectUsersSpinner, llUploadData;
    private LinearLayout llAmount, llDateofDeposit, llChequeNumber, llBankNumber, llImageMode;
    private int mYear, mMonth, mDay;
    private TextView tv_result, tvUserUpload, tvAmtDepositMode;

    private RecyclerView rvDealerUsers;
    private UploadDealerUserAdapter adapter;
    private List<UploadUserTypeResponse.DealerUsers> userList;

    private Spinner spinner;

    private TextView tvUserName;

    //An ArrayList for Spinner Items
    private ArrayList<String> students;

    //JSON Array
    private JSONArray result;

    //TextViews to display details
    private TextView textViewName;
    private TextView textViewCourse;
    private TextView textViewSession;
    private TextView userID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        userID = (TextView)findViewById(R.id.tvUserId);
        radioGroupMode = (RadioGroup)findViewById(R.id.rg);
        radioGroupUser = (RadioGroup)findViewById(R.id.radioGroupUser);
        ivBack = (ImageView)findViewById(R.id.ivBack);
        ivAddUsers = (ImageView)findViewById(R.id.ivAddUsers);
        tvTitle = (TextView)findViewById(R.id.tvTitle);
        ivCamera = (ImageView)findViewById(R.id.ivCamera);
        ivGallery = (ImageView)findViewById(R.id.ivGallery);
        ivUpload = (ImageView) findViewById(R.id.ivUpload);
        tvAmtDepositMode = (TextView)findViewById(R.id.tvAmtDepositMode);
        btnUploadData = (Button)findViewById(R.id.btnUploadData);
        etAmount = (EditText)findViewById(R.id.etAmount);
        in_date = (EditText)findViewById(R.id.in_date);
        etChequeNumber =(EditText)findViewById(R.id.etChequeNumber);
        etBankName =(EditText)findViewById(R.id.etBankName);
        tv_result = (TextView) findViewById(R.id.tv_result);
        tvUserUpload = (TextView)findViewById(R.id.tvUserUpload);
        llUploadData = (LinearLayout)findViewById(R.id.llUploadData);
        llSelectUsersSpinner = (LinearLayout)findViewById(R.id.llSelectUsersSpinner);
        llAmount = (LinearLayout)findViewById(R.id.llAmount);
        llDateofDeposit = (LinearLayout)findViewById(R.id.llDateofDeposit);
        llChequeNumber = (LinearLayout)findViewById(R.id.llChequeNumber);
        llBankNumber = (LinearLayout)findViewById(R.id.llBankNumber);
        llImageMode = (LinearLayout)findViewById(R.id.llImageMode);
        ivUpload = (ImageView)findViewById(R.id.ivUpload);
        radioButtonMe = (RadioButton)findViewById(R.id.radioButtonMe);
        radioButtonUser = (RadioButton) findViewById(R.id.radioButtonUser);
        radioButtonDealer = (RadioButton) findViewById(R.id.radioButtonDealer);
        spinner = (Spinner)findViewById(R.id.spinner);

      //  userID = (TextView)findViewById(R.id.userID);

        String user_type = SharedPrefManager.getInstance(UploadActivity.this).getUser().getUser_type();

        if(user_type.equals("dealer"))
        {
            radioButtonDealer.setVisibility(View.GONE);
        }
        else if(user_type.equals("user"))
        {
            radioButtonDealer.setVisibility(View.GONE);
            radioButtonUser.setVisibility(View.GONE);
        }

        ////////////////////////////////////////////////////

      //  rvDealerUsers = (RecyclerView)findViewById(R.id.rvDealerUsers);

        ////////////////////////////////////////////////////////


        radioGroupUser.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // checkedId is the RadioButton selected
                int selectedId = radioGroupUser.getCheckedRadioButtonId();
                radioButton = (RadioButton) findViewById(selectedId);
                String radioText =  radioButton.getText().toString();
                tvUserUpload.setText(radioText);
                String user = tvUserUpload.getText().toString().trim();

                if(user.equals("Me"))
                {
                    llSelectUsersSpinner.setVisibility(View.GONE);
                    tvAmtDepositMode.setVisibility(View.VISIBLE);
                    radioGroupMode.setVisibility(View.VISIBLE);
                    llAmount.setVisibility(View.GONE);
                    llDateofDeposit.setVisibility(View.GONE);
                    llChequeNumber.setVisibility(View.GONE);
                    llBankNumber.setVisibility(View.GONE);
                    llImageMode.setVisibility(View.GONE);
                    ivUpload.setVisibility(View.GONE);
                    btnUploadData.setVisibility(View.GONE);

                }
                if(user.equals("User"))
                {
                    llSelectUsersSpinner.setVisibility(View.VISIBLE);
                    tvAmtDepositMode.setVisibility(View.VISIBLE);
                    radioGroupMode.setVisibility(View.VISIBLE);
                    llAmount.setVisibility(View.GONE);
                    llDateofDeposit.setVisibility(View.GONE);
                    llChequeNumber.setVisibility(View.GONE);
                    llBankNumber.setVisibility(View.GONE);
                    llImageMode.setVisibility(View.GONE);
                    ivUpload.setVisibility(View.GONE);
                    btnUploadData.setVisibility(View.GONE);

                //    rvDealerUsers.setLayoutManager(new LinearLayoutManager(UploadActivity.this));

                    SharedPreferences preferences = UploadActivity.this.getSharedPreferences("MY_APP",Context.MODE_PRIVATE);
                    String retrivedToken  = preferences.getString("TOKEN",null);
                    String type_user = "user";

                    Call<UploadUserTypeResponse> call = RetrofitClient.getmInstance().getApi().getDealerUsers(retrivedToken,type_user);
                    call.enqueue(new Callback<UploadUserTypeResponse>() {
                        @Override
                        public void onResponse(Call<UploadUserTypeResponse> call, Response<UploadUserTypeResponse> response) {

                           /* List<UploadUserTypeResponse.DealerUsers> uploadUserTypeResponses = new ArrayList<>();
                            UploadUserTypeResponse.DealerUsers uploadUserTypeResponse = new UploadUserTypeResponse.DealerUsers();
                            uploadUserTypeResponse.set_id(null);
                            uploadUserTypeResponse.setDealer_id(null);
                            uploadUserTypeResponse.setEmail(null);
                            uploadUserTypeResponse.setName(getResources().getString(R.string.select_user));
                            uploadUserTypeResponse.setUser_type(null);

                            uploadUserTypeResponses.add(uploadUserTypeResponse);
                            uploadUserTypeResponses.add((UploadUserTypeResponse.DealerUsers) response.body().getData());

                            ArrayAdapter<UploadUserTypeResponse.DealerUsers> adapter = new ArrayAdapter<>(getApplicationContext(), R.layout.spinner_item, uploadUserTypeResponses);
                            adapter.setDropDownViewResource(R.layout.spinner_item);
                            spinner.setAdapter(adapter);*/


                            userList = response.body().getData();
                            final ArrayList<UploadUserTypeResponse.DealerUsers> listUser= (ArrayList<UploadUserTypeResponse.DealerUsers>) userList;
                            ArrayList userNamelist=new ArrayList();
                            for (int i=0;i<listUser.size();i++){
                                userNamelist.add(listUser.get(i).getName());
                            }

                            Log.d("list",userNamelist.toString());

                            ArrayAdapter<UploadUserTypeResponse.DealerUsers> adapter = new ArrayAdapter<UploadUserTypeResponse.DealerUsers>(getApplicationContext(), R.layout.spinner_item, userNamelist);
                            adapter.setDropDownViewResource(R.layout.spinner_item);
                            spinner.setAdapter(adapter);

                            spinner.setOnItemSelectedListener(
                                    new AdapterView.OnItemSelectedListener() {
                                        public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                                            UploadUserTypeResponse.DealerUsers walletData = listUser.get(pos);
                                            String _id =  walletData.get_id();
                                            SharedPreferences preferencesID = UploadActivity.this.getSharedPreferences("MY_APP_ID",Context.MODE_PRIVATE);
                                            preferencesID.edit().putString("USERID",_id).apply();
                                        }
                                        public void onNothingSelected(AdapterView<?> parent) {
                                        }
                                    });



                           /* adapter = new UploadDealerUserAdapter(UploadActivity.this, userList);
                            rvDealerUsers.setAdapter(adapter);*/


                        }
                        @Override
                        public void onFailure(Call<UploadUserTypeResponse> call, Throwable t) {
                            Toast.makeText(UploadActivity.this,"Failure",Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                if(user.equals("Dealer"))
                {
                    llSelectUsersSpinner.setVisibility(View.VISIBLE);
                    tvAmtDepositMode.setVisibility(View.VISIBLE);
                    radioGroupMode.setVisibility(View.VISIBLE);
                    llAmount.setVisibility(View.GONE);
                    llDateofDeposit.setVisibility(View.GONE);
                    llChequeNumber.setVisibility(View.GONE);
                    llBankNumber.setVisibility(View.GONE);
                    llImageMode.setVisibility(View.GONE);
                    ivUpload.setVisibility(View.GONE);
                    btnUploadData.setVisibility(View.GONE);

              //      rvDealerUsers.setLayoutManager(new LinearLayoutManager(UploadActivity.this));

                    SharedPreferences preferences = UploadActivity.this.getSharedPreferences("MY_APP",Context.MODE_PRIVATE);
                    String retrivedToken  = preferences.getString("TOKEN",null);
                    String type_user = "dealer";

                    Call<UploadUserTypeResponse> call = RetrofitClient.getmInstance().getApi().getDealerUsers(retrivedToken,type_user);
                    call.enqueue(new Callback<UploadUserTypeResponse>() {
                        @Override
                        public void onResponse(Call<UploadUserTypeResponse> call, Response<UploadUserTypeResponse> response) {

                            userList = response.body().getData();
                            final ArrayList<UploadUserTypeResponse.DealerUsers> listUser= (ArrayList<UploadUserTypeResponse.DealerUsers>) userList;
                            ArrayList userNamelist=new ArrayList();
                            for (int i=0;i<listUser.size();i++){
                                userNamelist.add(listUser.get(i).getName());
                            }

                            Log.d("list",userNamelist.toString());

                            ArrayAdapter<UploadUserTypeResponse.DealerUsers> adapter = new ArrayAdapter<UploadUserTypeResponse.DealerUsers>(getApplicationContext(), R.layout.spinner_item, userNamelist);
                            adapter.setDropDownViewResource(R.layout.spinner_item);
                            spinner.setAdapter(adapter);

                            spinner.setOnItemSelectedListener(
                                    new AdapterView.OnItemSelectedListener() {
                                        public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                                            UploadUserTypeResponse.DealerUsers walletData = listUser.get(pos);
                                            String _id =  walletData.get_id();
                                            SharedPreferences preferencesID = UploadActivity.this.getSharedPreferences("MY_APP_ID",Context.MODE_PRIVATE);
                                            preferencesID.edit().putString("USERID",_id).apply();
                                        }
                                        public void onNothingSelected(AdapterView<?> parent) {
                                        }
                                    });

                        }
                        @Override
                        public void onFailure(Call<UploadUserTypeResponse> call, Throwable t) {
                            Toast.makeText(UploadActivity.this,"Failure",Toast.LENGTH_SHORT).show();
                        }
                    });
                }

            }
        });


        radioGroupMode.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                int selectedId = radioGroupMode.getCheckedRadioButtonId();
                radioButton = (RadioButton) findViewById(selectedId);
                String radioText =  radioButton.getText().toString();
                tv_result.setText(radioText);
                String mode = tv_result.getText().toString().trim();

                if(mode.equals("Cash"))
                {
                    llAmount.setVisibility(View.VISIBLE);
                    llDateofDeposit.setVisibility(View.VISIBLE);
                    llImageMode.setVisibility(View.VISIBLE);
                    ivUpload.setVisibility(View.VISIBLE);
                    btnUploadData.setVisibility(View.VISIBLE);
                    llBankNumber.setVisibility(View.GONE);
                    llChequeNumber.setVisibility(View.GONE);
                }
                if(mode.equals("Cheque"))
                {
                    llAmount.setVisibility(View.VISIBLE);
                    llDateofDeposit.setVisibility(View.VISIBLE);
                    llChequeNumber.setVisibility(View.VISIBLE);
                    llBankNumber.setVisibility(View.VISIBLE);
                    llImageMode.setVisibility(View.VISIBLE);
                    ivUpload.setVisibility(View.VISIBLE);
                    btnUploadData.setVisibility(View.VISIBLE);
                }

            }
        });


        ivAddUsers.setVisibility(View.GONE);
        tvTitle.setText("Upload");
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btnDatePicker=(ImageView)findViewById(R.id.btn_date);
        txtDate=(EditText)findViewById(R.id.in_date);

        if(ContextCompat.checkSelfPermission(this,Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED)
        {
            ivCamera.setEnabled(false);
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE} , 0);
        }
        else {
            ivCamera.setEnabled(true);
        }
        ivCamera.setOnClickListener(this);
        ivGallery.setOnClickListener(this);
        btnUploadData.setOnClickListener(this);
        btnDatePicker.setOnClickListener(this);

        initDialog();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 0) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                ivCamera.setEnabled(true);
            }
        }
    }

    @Override
    public void onClick(View v) {

        if (v == btnDatePicker) {

            // Get Current Date
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                            txtDate.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
            datePickerDialog.show();
        }

        if (v == ivCamera)
        {
            new MaterialDialog.Builder(this)
                    .title("Set Your Image")
                    .items(R.array.uploadImages)
                    .itemsIds(R.array.itemIds)
                    .itemsCallback(new MaterialDialog.ListCallback() {
                        @Override
                        public void onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                            switch (which) {
                                case 0:
                                    captureImage();
                                    break;
                                case 1:
                                    ivUpload.setImageResource(R.drawable.asset_normel_name_gallery_icon);
                                    break;
                            }
                        }
                    })
                    .show();
        }

        if(v == ivGallery)
        {
            new MaterialDialog.Builder(this)
                    .title("Set Your Image")
                    .items(R.array.uploadGalleryImages)
                    .itemsIds(R.array.galleryitemIds)
                    .itemsCallback(new MaterialDialog.ListCallback() {
                        @Override
                        public void onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                            switch (which) {
                                case 0:
                                    Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                                            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                                    startActivityForResult(galleryIntent, REQUEST_PICK_PHOTO);
                                    break;
                                case 1:
                                    ivUpload.setImageResource(R.drawable.asset_normel_name_gallery_icon);
                                    break;
                            }
                        }
                    })
                    .show();

        }

        if (v == btnUploadData)
        {
            uploadFile();
        }
    }


    private boolean isExternalStorageAvailable() {
        String state = Environment.getExternalStorageState();
        if(Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        else {
            return false;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_TAKE_PHOTO || requestCode == REQUEST_PICK_PHOTO) {
                if (data != null) {
                    // Get the Image from data
                    Uri selectedImage = data.getData();
                    String[] filePathColumn = {MediaStore.Images.Media.DATA};

                    Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                    assert cursor != null;
                    cursor.moveToFirst();

                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    mediaPath = cursor.getString(columnIndex);
                    // Set the Image in ImageView for Previewing the Media
                    ivUpload.setImageBitmap(BitmapFactory.decodeFile(mediaPath));
                    cursor.close();


                    postPath = mediaPath;
                }


            }else if (requestCode == CAMERA_PIC_REQUEST){
                if (Build.VERSION.SDK_INT > 21) {

                    Glide.with(this).load(mImageFileLocation).into(ivUpload);
                    postPath = mImageFileLocation;

                }else{
                    Glide.with(this).load(fileUri).into(ivUpload);
                    postPath = fileUri.getPath();

                }

            }

        }
        else if (resultCode != RESULT_CANCELED) {
            Toast.makeText(this, "Sorry, there was an error!", Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Checking device has camera hardware or not
     * */
    private boolean isDeviceSupportCamera() {
        if (getApplicationContext().getPackageManager().hasSystemFeature(
                PackageManager.FEATURE_CAMERA)) {
            // this device has a camera
            return true;
        } else {
            // no camera on this device
            return false;
        }
    }

    protected void initDialog() {

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading");
        pDialog.setCancelable(true);
    }


    protected void showpDialog() {

        if (!pDialog.isShowing()) pDialog.show();
    }

    protected void hidepDialog() {

        if (pDialog.isShowing()) pDialog.dismiss();
    }

    /**
     * Launching camera app to capture image
     */
    private void captureImage() {
        if (Build.VERSION.SDK_INT > 21) { //use this if Lollipop_Mr1 (API 22) or above
            Intent callCameraApplicationIntent = new Intent();
            callCameraApplicationIntent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);

            File photoFile = null;

            try {
                photoFile = createImageFile();
            } catch (IOException e) {
                Logger.getAnonymousLogger().info("Exception error in generating the file");
                e.printStackTrace();
            }
            Uri outputUri = FileProvider.getUriForFile(
                    this,
                    BuildConfig.APPLICATION_ID + ".provider",
                    photoFile);
            callCameraApplicationIntent.putExtra(MediaStore.EXTRA_OUTPUT, outputUri);
            callCameraApplicationIntent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);

            Logger.getAnonymousLogger().info("Calling the camera App by intent");
            startActivityForResult(callCameraApplicationIntent, CAMERA_PIC_REQUEST);
        } else {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            fileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
            startActivityForResult(intent, CAMERA_PIC_REQUEST);
        }


    }

    File createImageFile() throws IOException {
        Logger.getAnonymousLogger().info("Generating the image - method started");

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmSS").format(new Date());
        String imageFileName = "IMAGE_" + timeStamp;
        File storageDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES + "/photo_saving_app");
        Logger.getAnonymousLogger().info("Storage directory set");
        //  create the storage directory if does not exists
        if (!storageDirectory.exists()) storageDirectory.mkdir();
        File image = new File(storageDirectory, imageFileName + ".jpg");
        // File image = File.createTempFile(imageFileName, ".jpg", storageDirectory);
        Logger.getAnonymousLogger().info("File name and path set");

        mImageFileLocation = image.getAbsolutePath();
        // fileUri = Uri.parse(mImageFileLocation);
        return image;
    }


    /**
     * Here we store the file url as it will be null after returning from camera
     * app
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // save file url in bundle as it will be null on screen orientation
        outState.putParcelable("file_uri", fileUri);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        // get the file url
        fileUri = savedInstanceState.getParcelable("file_uri");
    }



    /**
     * Creating file uri to store image/video
     */
    public Uri getOutputMediaFileUri(int type) {
        return Uri.fromFile(getOutputMediaFile(type));
    }

    /**
     * returning image / video
     */
    private static File getOutputMediaFile(int type) {
        File mediaStorageDir = new File(
                Environment
                        .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                IMAGE_DIRECTORY_NAME);

        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.d(TAG, "Oops! Failed create "
                        + IMAGE_DIRECTORY_NAME + " directory");
                return null;
            }
        }

        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss",
                Locale.getDefault()).format(new Date());
        File mediaFile;
        if (type == MEDIA_TYPE_IMAGE) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator
                    + "IMG_" + ".jpg");
        }  else {
            return null;
        }

        return mediaFile;
    }

    // Uploading Image/Video
    private void uploadFile() {
        if (postPath == null || postPath.equals("")) {
            Toast.makeText(this, "please select an image ", Toast.LENGTH_LONG).show();
            return;
        } else {
            showpDialog();


            //      Map<String, RequestBody> map = new HashMap<>();
            File file = new File(postPath);
            // RequestBody filePart = RequestBody.create(MediaType.parse("image/*"),file);
            // MultipartBody.Part image = MultipartBody.Part.createFormData("photo",file.getName(),filePart);



            //     RequestBody requestBody = RequestBody.create(MediaType.parse("*/*"),file);
            //     map.put("file\"; filename=\"" + file.getName() + "\"", requestBody);

            RequestBody requestFile = RequestBody.create(MediaType.parse("*/*"), file);
            MultipartBody.Part body = MultipartBody.Part.createFormData("photo", file.getName(), requestFile);


            //   Map<String, RequestBody> map = new HashMap<>();
            //  File file = new File(postPath);
            //  RequestBody requestBody = RequestBody.create(MediaType.parse("*/*"), file);
            //  map.put("file\"; filename=\"" + file.getName() + "\"", requestBody);


            int selectedId = radioGroupMode.getCheckedRadioButtonId();
            radioButton = (RadioButton) findViewById(selectedId);
            String radioText =  radioButton.getText().toString();
            tv_result.setText(radioText);
            String mode = tv_result.getText().toString().trim();
            RequestBody payment_mode = RequestBody.create(MediaType.parse("multipart/form-data"), mode);

            String amt = etAmount.getText().toString().trim();
            RequestBody amount = RequestBody.create(MediaType.parse("multipart/form-data"),amt);

            String deposit_date = in_date.getText().toString().trim();
            RequestBody depositdate = RequestBody.create(MediaType.parse("multipart/form-data"), deposit_date);

            String chequeno = etChequeNumber.getText().toString().trim();
            RequestBody cheque_no = RequestBody.create(MediaType.parse("multipart/form-data"), chequeno);

            String bank_name = etBankName.getText().toString().trim();
            RequestBody bankname = RequestBody.create(MediaType.parse("multipart/form-data"), bank_name);

            int selected = radioGroupUser.getCheckedRadioButtonId();
            radioButton = (RadioButton) findViewById(selected);
            String radioUserText =  radioButton.getText().toString();
            tvUserUpload.setText(radioUserText);
            String user = tvUserUpload.getText().toString().trim();
            if(user.equals("Me"))
            {
                String user_id = String.valueOf(SharedPrefManager.getInstance(UploadActivity.this).getUser().get_id());
                userID.setText(user_id);
                //  RequestBody userid = RequestBody.create(MediaType.parse("multipart/form-data"), user_id);


            }
            if(user.equals("User"))
            {
                SharedPreferences preferencesID = this.getSharedPreferences("MY_APP_ID",Context.MODE_PRIVATE);
                String user_id  = preferencesID.getString("USERID",null);
                userID.setText(user_id);
                //   RequestBody radio_user = RequestBody.create(MediaType.parse("multipart/form-data"), user_id_radiouser);
            }

            if(user.equals("Dealer"))
            {
                SharedPreferences preferencesDealerID = this.getSharedPreferences("MY_APP_ID",Context.MODE_PRIVATE);
                String dealerid  = preferencesDealerID.getString("USERID",null);
                userID.setText(dealerid);
                //   RequestBody radio_user = RequestBody.create(MediaType.parse("multipart/form-data"), user_id_radiouser);
            }


            String selected_userid = (String) userID.getText();
            RequestBody userid = RequestBody.create(MediaType.parse("multipart/form-data"), selected_userid);


            //     RequestBody requestBody = RequestBody.create(MediaType.parse("*/*"), file);
            //     MultipartBody.Part body = MultipartBody.Part.createFormData("file", file.getName(), requestBody);
            // map.put("file\"; filename=\"" + file.getName() + "\"", requestBody);

            SharedPreferences preferences = UploadActivity.this.getSharedPreferences("MY_APP",Context.MODE_PRIVATE);
            String retrivedToken  = preferences.getString("TOKEN",null);

            Call<WalletRequestResponse> call = RetrofitClient.getmInstance()
                    .getApi()
                    .addWallet(retrivedToken,payment_mode,cheque_no,amount,bankname,depositdate,userid,body);
            call.enqueue(new Callback<WalletRequestResponse>() {
                @Override
                public void onResponse(Call<WalletRequestResponse> call, Response<WalletRequestResponse> response) {
                    if (response.isSuccessful()){
                        if (response.body() != null){
                            hidepDialog();
                            WalletRequestResponse serverResponse = response.body();
                            Toast.makeText(getApplicationContext(), serverResponse.getMessage(), Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(UploadActivity.this, DashboardActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                            finish();
                        }
                    }else {
                        hidepDialog();
                        WalletRequestResponse serverResponse = response.body();
                        Toast.makeText(getApplicationContext(),serverResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<WalletRequestResponse> call, Throwable t) {
                    hidepDialog();
                    Toast.makeText(UploadActivity.this,"Error",Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

}
