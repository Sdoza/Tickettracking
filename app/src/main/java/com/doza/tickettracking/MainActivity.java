package com.doza.tickettracking;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.io.File;

public class MainActivity extends AppCompatActivity implements ActivityCompat.OnRequestPermissionsResultCallback{
    public final static String EXTRA_MESSAGE = "com.doza.tickettracking.Message";
    private static String TAG = "Permission";
    private static final int REQUEST_WRITE_STORAGE = 112;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createFolder();

    }

    public void createFolder(){
        getPermission();
        File appDir = new File(Environment.getExternalStorageDirectory(), "/Tickets");

        if(!appDir.exists()){
            appDir.mkdir();
            Log.w("inside loop", "should have a folder now");
        }
    }

    public  void newTicket(View view) {
        Intent intent = new Intent(this, NewTicketActivity.class);
        startActivity(intent);
    }
    public void getPermission(){
        int permission = ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if(permission != PackageManager.PERMISSION_GRANTED){
            Log.i(TAG, "Permission to record denied");
            ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
            if(ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)){
                Log.i(TAG, "Trying to ask for permission");
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Permission to access the storage is required for this app to run correctly ")
                        .setTitle("Permission required");
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Log.i(TAG, "Clicked");
                        makeRequest();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
            else {
                makeRequest();
            }
        }
    }
    protected void makeRequest(){
        ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE},
                REQUEST_WRITE_STORAGE);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissios[], int[] grantResults){
        switch (requestCode){
            case REQUEST_WRITE_STORAGE:{
                if (grantResults.length ==0 || grantResults[0] != PackageManager.PERMISSION_GRANTED){
                    Log.i(TAG, "Permission has been denied by user");
                }
                else{
                    Log.i(TAG, "Permission has been granter by the user");
                }
                return;
            }
        }
    }

    public void newAcitivity(View view){
        Log.e("Switch", "inside barcode method");
        Intent intent;
        switch (view.getId()){
            case R.id.newTicket:
                intent = new Intent(this, NewTicketActivity.class);
                startActivity(intent);
                break;
            case R.id.printerTicket:
                intent = new Intent(this, printer_ticket.class);
                startActivity(intent);
                break;
            case R.id.assetRecord:
                intent = new Intent(this, AssetRecord.class);
                startActivity(intent);
                break;
            case R.id.aboutApp:
                intent = new Intent(this, About.class);
                startActivity(intent);
                break;
            default:
                Log.e("Error" , "Error ocurred");
        }
    }

}
