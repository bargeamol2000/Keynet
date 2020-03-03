package com.codebelow.keynet;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void backClicked(View view) {
        onBackPressed();
    }

    public void openPlaystorePage(View view) {
        try{
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id="+getPackageName())));
        }catch (ActivityNotFoundException e){
            startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("https://play.google.com/store/apps/details?id="+getPackageName())));
        }
    }

    public void showAvailableSoon(View view) {
        Toast.makeText(getApplicationContext(), "Available Soon", Toast.LENGTH_SHORT).show();
    }

    public void openHourlyPass(View view) {
        Intent i=new Intent(getApplicationContext(),MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }

    public void openSHA1(View view) {
        Intent i=new Intent(getApplicationContext(),Hash.class);
        i.putExtra("method",1);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }

    public void openSHA224(View view) {
        Intent i=new Intent(getApplicationContext(),Hash.class);
        i.putExtra("method",2);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }

    public void openMD2(View view) {
        Intent i=new Intent(getApplicationContext(),Hash.class);
        i.putExtra("method",3);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }

    public void openMD5(View view) {
        Intent i=new Intent(getApplicationContext(),Hash.class);
        i.putExtra("method",4);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }
}
