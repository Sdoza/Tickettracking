package com.doza.tickettracking;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by User on 9/26/2016.
 */
public class Utilities extends AppCompatActivity {

    BufferedWriter bw = null;
    String barCodeResult = " barcode was too fast";
    boolean isBarcode = false;
    public  boolean saveToFile(String filePath , ArrayList data){
        Log.e("saveToFile ", "saveToFile is now started");
        boolean result = true;
        try{
            Log.e("saveToFile ", "inside try method");
            bw = new BufferedWriter(new FileWriter(filePath, true));
            Log.e("saveToFile ", "bw should be created");
            bw.write("=================================================");
            bw.newLine();
            Log.e("saveToFile ", "started to write");
            for (int i = 0; i < data.size(); i ++ ){
                bw.write(data.get(i).toString());
                bw.newLine();
            }
            bw.write("=================================================");
            Log.e("saveToFile ", "end of try method");
            bw.newLine();
            bw.flush();
        }
        catch (IOException e){
            result = false;
        }
        finally {
            if(bw !=null) try{
                bw.close();
            }
            catch (IOException e){

            }
        }
        Log.e("saveToFile ", "inside try method");
        return result;
    }
    public static String getDate(String type){
        DateFormat dateFormat = new SimpleDateFormat("MMM , d");
        Date date = new Date();
        return dateFormat.format(date)+ type;
    }

    /*public void  barcodeReader(Activity act){
        IntentIntegrator integrator = new IntentIntegrator(act);
        integrator.setOrientationLocked(true);
        integrator.initiateScan();

    }
    public void setBarcode (boolean bool){
        isBarcode = bool;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if(result.getContents() == null) {
                //barcode.setText("Cancelled");
                barCodeResult = "CANCELLED";
                Log.e("Barcode","Cancelled");
                isBarcode = true;
                //Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
            } else {
                //Toast.makeText(this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
                // barcode.setText(result.getContents());
                barCodeResult = result.getContents();
                Log.e("Barcode",result.getContents());
                isBarcode = true;
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }*/

}
