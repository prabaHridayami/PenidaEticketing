package com.example.penidae_ticketing.ReceiptActivity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.penidae_ticketing.R;
import com.example.penidae_ticketing.api.ApiClient;
import com.example.penidae_ticketing.model.Upload;

import java.io.File;
import java.io.IOException;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class UploadActivity extends AppCompatActivity implements UploadView {

    ImageView iv_receipt;
    Button btn_gallery, btn_upload;
    private static final int PICK_IMAGE = 100;
    private static final int CAPTURE = 200;
    private UploadPresenter uploadPresenter;
    Uri imageUri;
    Bitmap bitmap;
    MultipartBody.Part body;
    int id_transaksi;
    String menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        iv_receipt = findViewById(R.id.iv_receipt);
        btn_gallery = findViewById(R.id.btn_gallery);
        btn_upload = findViewById(R.id.btn_upload);

        if (ContextCompat.checkSelfPermission(UploadActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(UploadActivity.this,
                    new String [] {
                            Manifest.permission.CAMERA
                    },
                    CAPTURE);
        }

        uploadPresenter =new UploadPresenter(this, ApiClient.getService());
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            menu = bundle.getString("menu");
            id_transaksi = bundle.getInt("id",0);
        }


        btn_gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (checkStoragePermission()) {
                        openGallery();
                    }
                }
                else {
                    openGallery();
                }

            }
        });

        btn_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!body.equals(null)){
                    RequestBody id = RequestBody.create(okhttp3.MultipartBody.FORM, Integer.toString(id_transaksi));
                    if (menu.equals("boat")){
                        uploadPresenter.uploadBoat(body,id);
                    }else if(menu.equals("hotel")){
                        uploadPresenter.uploadHotel(body,id);
                    }else if (menu.equals("vehicle")){
                        uploadPresenter.uploadVehicle(body,id);
                    }else if (menu.equals("watersport")){
                        uploadPresenter.uploadWatersport(body,id);
                    }else if (menu.equals("tour")){
                        uploadPresenter.uploadTour(body,id);
                    }

                }else {
                    Toast.makeText(getApplicationContext(),"Select a receipt",Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    private void openGallery(){
        Intent intent= new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,PICK_IMAGE);
    }

    public static final int MY_PERMISSIONS_REQUEST_STORAGE= 1;
    private boolean checkStoragePermission() {
        if (ContextCompat.checkSelfPermission(this,Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    android.Manifest.permission.READ_EXTERNAL_STORAGE)) {
                ActivityCompat.requestPermissions(this,
                        new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE},
                        MY_PERMISSIONS_REQUEST_STORAGE);
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE},
                        MY_PERMISSIONS_REQUEST_STORAGE);
            }
            return false;
        } else {
            return true;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE&&data!=null){
            Uri selectedImage=data.getData();
            try {
                bitmap= MediaStore.Images.Media.getBitmap(getContentResolver(),selectedImage);
                iv_receipt.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }

            String wholeID = DocumentsContract.getDocumentId(selectedImage);

            // Split at colon, use second item in the array
            String id = wholeID.split(":")[1];

            String[] column = { MediaStore.Images.Media.DATA };

            // where id is equal to
            String sel = MediaStore.Images.Media._ID + "=?";

            Cursor cursor = getContentResolver().
                    query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                            column, sel, new String[]{ id }, null);

            String filePath = "";

            int columnIndex = cursor.getColumnIndex(column[0]);

            if (cursor.moveToFirst()) {
                filePath = cursor.getString(columnIndex);
            }
            cursor.close();
            File file = new File(filePath);

            RequestBody reqFile = RequestBody.create(MediaType.parse("image/*"), file);

            body = MultipartBody.Part.createFormData("image", file.getName(), reqFile);

        }else{
            body = null;
        }

    }


    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onSuccess(Upload upload) {
        Toast.makeText(this,upload.getMessage()+" ID:"+id_transaksi,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onError() {
        Toast.makeText(this, "Failure", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFailure(Throwable t) {
        Log.d(TAG, "onFailure: "+t);
        Toast.makeText(this, "Error : "+t, Toast.LENGTH_SHORT).show();
    }
}
