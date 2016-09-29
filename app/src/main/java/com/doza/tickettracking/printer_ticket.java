package com.doza.tickettracking;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

public class printer_ticket extends AppCompatActivity {

    EditText  printerAsset,clientName,  errorDisc, resolution;
    ArrayList printerTicketList = new ArrayList();
    Utilities util = new Utilities();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_printer_ticket);
        loadEditTestBoxes();
    }

    private void loadEditTestBoxes(){
        printerAsset = (EditText) findViewById(R.id.printer_AssetTags);;
        clientName =  (EditText) findViewById(R.id.client_name);
        errorDisc = (EditText) findViewById(R.id.error_message);
        resolution = (EditText) findViewById(R.id.resolution);
    }

    public void clearBoxes(View view){
        printerAsset.setText("");
        clientName.setText("");
        errorDisc.setText("");
        resolution.setText("");
        clientName.requestFocus();
    }

    public void saveTicket(View view) {
        boolean result;
        printerTicketList.clear();
        printerTicketList.add("Client name: " + clientName.getText().toString());
        printerTicketList.add("Printer Asset: " + printerAsset.getText().toString());
        printerTicketList.add("Error message: " + errorDisc.getText().toString());
        printerTicketList.add("Resolution: " + resolution.getText().toString());
        String ticketFileName = Environment.getExternalStorageDirectory().getAbsolutePath() +
                File.separator +  "Tickets" +File.separator + Utilities.getDate(".doc");
        result = util.saveToFile(ticketFileName, printerTicketList);
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

}
