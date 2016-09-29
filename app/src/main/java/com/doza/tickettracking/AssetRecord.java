package com.doza.tickettracking;

import android.content.Context;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

public class AssetRecord extends AppCompatActivity {
    EditText assetTag,clientName, monitorAsset;
    ArrayList assetUpdateList = new ArrayList();
    Utilities util = new Utilities();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asset_record);
        loadEditTestBoxes();
    }

    private void loadEditTestBoxes(){
        assetTag = (EditText)findViewById(R.id.asset_tag);
        clientName =  (EditText) findViewById(R.id.client_name);
        monitorAsset = (EditText) findViewById(R.id.monitor_AssetTags);
    }
    public void clearBoxes(View view){
        assetTag.setText("");
        monitorAsset.setText("");
        clientName.setText("");
        clientName.requestFocus();
    }
    public void saveTofile(View view) {
        boolean result;
        assetUpdateList.add("Client name: " + clientName.getText().toString());
        assetUpdateList.add("Asset Tag: " + assetTag.getText().toString());
        assetUpdateList.add("Monitor Asset Tag: " + monitorAsset.getText().toString());
        String assetFileName = Environment.getExternalStorageDirectory().getAbsolutePath() +
                File.separator +  "Tickets" +File.separator + "Asset Update,  "+Utilities.getDate(".doc");
        result = util.saveToFile(assetFileName, assetUpdateList);
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        if (result){
            clearBoxes(null) ;
            Toast toast = Toast.makeText(context, "Asset record updated", duration);
            toast.show();
        }
        else{
            Toast toast = Toast.makeText(context, "Something went wrong! Please reopen the application and try again!!!", duration);
            toast.show();
        }
    }
}
