package com.codebelow.keynet;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class PassWord extends AppCompatActivity {

    TextView expire_tv;
    EditText pass_et;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pass_word);

        AdView mAdViewTop = findViewById(R.id.bnr_top);
        mAdViewTop.loadAd(new AdRequest.Builder().build());

        AdView mAdViewBot = findViewById(R.id.bnr_bot);
        mAdViewBot.loadAd(new AdRequest.Builder().build());

        pass_et=findViewById(R.id.pass_et);
        expire_tv=findViewById(R.id.expire_tv);

        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("hh/dd/MM/yyyy");
        String strDate= formatter.format(date);

        Calendar calendar=Calendar.getInstance();
        calendar.setTime(date);


        pass_et.setText(encryptThisString(strDate).substring(0,4));
        expire_tv.setText("Expires in "+(60-calendar.get(Calendar.MINUTE))+" minutes");

    }

    public void copy(View view) {
        ClipboardManager clipboardManager= (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        ClipData clip=ClipData.newPlainText("Password",pass_et.getText());
        clipboardManager.setPrimaryClip(clip);
        Toast.makeText(this, "Password Copied", Toast.LENGTH_SHORT).show();
    }


    public static String encryptThisString(String input)
    {
        try {
            // getInstance() method is called with algorithm SHA-1
            MessageDigest md = MessageDigest.getInstance("SHA-1");

            // digest() method is called
            // to calculate message digest of the input string
            // returned as array of byte
            byte[] messageDigest = md.digest(input.getBytes());

            // Convert byte array into signum representation
            BigInteger no = new BigInteger(1, messageDigest);

            // Convert message digest into hex value
            String hashtext = no.toString(16);

            // Add preceding 0s to make it 32 bit
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }

            // return the HashText
            return hashtext;
        }

        // For specifying wrong message digest algorithms
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public void openMenu(View view) {
        startActivity(new Intent(this,Menu.class));
    }
}
