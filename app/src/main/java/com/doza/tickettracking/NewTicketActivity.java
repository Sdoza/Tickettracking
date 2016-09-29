package com.doza.tickettracking;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

public class NewTicketActivity extends AppCompatActivity {
    EditText assetTag, printerAsset,clientName, monitorAsset, errorDisc, resolution, barcode;
    ArrayList ticketList = new ArrayList();
    ArrayList assetUpdateList = new ArrayList();
    Utilities util = new Utilities();
    boolean isAssetTag, isMonitorTag, isPrinterTag;
    String barCodeResult = " barcode was too fast";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.activityName_NewTicket);
        setContentView(R.layout.activity_new_ticket);
        loadEditTestBoxes();
        changeBoolean(false);

    }

    public void saveTicket(View view){
        boolean result;
        ticketList.clear();
        Log.e("ticketList ", "ticketList is now empty");
        ticketList.add("Client name: " + clientName.getText().toString());
        ticketList.add("Asset Tag: " + assetTag.getText().toString());
        ticketList.add("Monitor Asset Tag: " + monitorAsset.getText().toString());
        ticketList.add("Printer Asset: " + printerAsset.getText().toString());
        ticketList.add("Error message: " + errorDisc.getText().toString());
        ticketList.add("Resolution: " + resolution.getText().toString());
        Log.e("ticketList ", "ticketList finished loading data");
        if(!assetTag.getText().toString().isEmpty()){
            Log.e("assetTag ", "assetTag is now comparing");
            assetUpdateList.clear();
            Log.e("assetUpdateList ", "assetUpdateList is now empty");
            assetUpdateList.add("Client name: " + clientName.getText().toString());
            assetUpdateList.add("Asset Tag: " + assetTag.getText().toString());
            assetUpdateList.add("Monitor Asset Tag: " + monitorAsset.getText().toString());
            Log.e("assetUpdateList ", "assetUpdateList is now loaded");
            String assetFileName = Environment.getExternalStorageDirectory().getAbsolutePath() +
                    File.separator +  "Tickets" +File.separator + "Asset Update,  "+Utilities.getDate(".doc");
            Log.e("assetFileName ", "assetFileName is now created : " + assetFileName);
            util.saveToFile(assetFileName, assetUpdateList);
            Log.e("assetUpdateList ", "assetUpdateList is now saved");
        }
        String ticketFileName = Environment.getExternalStorageDirectory().getAbsolutePath() +
                File.separator +  "Tickets" +File.separator + Utilities.getDate(".doc");
        result = util.saveToFile(ticketFileName, ticketList);

        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        if (result){
            clearBoxes(null) ;
            Toast toast = Toast.makeText(context, "New Ticket Saved", duration);
            toast.show();
        }
        else{
            Toast toast = Toast.makeText(context, "Something went wrong! Please reopen the application and try again!!!", duration);
            toast.show();
        }
    }
    public void clearBoxes(View view){
        assetTag.setText("");
        printerAsset.setText("");
        monitorAsset.setText("");
        clientName.setText("");
        errorDisc.setText("");
        resolution.setText("");
        clientName.requestFocus();
    }
    private void loadEditTestBoxes(){
        assetTag = (EditText)findViewById(R.id.asset_tag);
        printerAsset = (EditText) findViewById(R.id.printer_AssetTags);;
        clientName =  (EditText) findViewById(R.id.client_name);
        monitorAsset = (EditText) findViewById(R.id.monitor_AssetTags);
        errorDisc = (EditText) findViewById(R.id.error_message);
        resolution = (EditText) findViewById(R.id.resolution);
    }
    public void scanBarcode(View view){
        Log.e("Switch", "inside barcode method");
       switch (view.getId()){
           case R.id.assetScan:
               isAssetTag = true;
               //barcodeReader();
               break;
           case R.id.monitorScan:
               isMonitorTag = true;
               //barcodeReader();
              // monitorAsset.setText(util.barCodeResult);
               break;
           case R.id.printerScan:
               isPrinterTag = true;
              // barcodeReader();
               //printerAsset.setText(util.barCodeResult);
               break;
           default:
               Log.e("Switch", "default");
       }
    }

    /*@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if(result.getContents() == null) {
                //barcode.setText("Cancelled");
                barCodeResult = "CANCELLED";
                Log.e("Barcode","Cancelled");
                //Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
            } else {
                //Toast.makeText(this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
               // barcode.setText(result.getContents());
                barCodeResult = result.getContents();
                Log.e("Barcode",result.getContents());
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }

        if(isAssetTag){
            Log.e("Barcode","Inside the asset tag code");
            assetTag.setText(barCodeResult);
            changeBoolean(false);
        }
        if(isPrinterTag){
            printerAsset.setText(barCodeResult);
            changeBoolean(false);
        }
        if (isMonitorTag){
            monitorAsset.setText(barCodeResult);
            changeBoolean(false);
        }
    }*/
    private void changeBoolean(boolean trueOrFalse){
        isAssetTag = trueOrFalse;
        isMonitorTag = trueOrFalse;
        isPrinterTag = trueOrFalse;
    }
    /*public void  barcodeReader(){
        Intent intent = new Intent(this, Intents.class);
        //IntentIntegrator integrator = new IntentIntegrator(this);
        //integrator.setOrientationLocked(false);
        //integrator.initiateScan();
        startActivityForResult(intent, 0);

    }*/
}

