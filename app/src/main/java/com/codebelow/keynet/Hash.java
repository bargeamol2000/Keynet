package com.codebelow.keynet;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.TextureView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hash extends AppCompatActivity {

    TextView mAlgoName,mHashName;
    EditText mMessage,mHash;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hash);

        mAlgoName=findViewById(R.id.algo_name_tv);
        mHashName=findViewById(R.id.hash_name_tv);
        mMessage=findViewById(R.id.message_et);
        mHash=findViewById(R.id.hash_et);

        AdView mAdViewTop = findViewById(R.id.bnr_top);
        mAdViewTop.loadAd(new AdRequest.Builder().build());

        AdView mAdViewBot = findViewById(R.id.bnr_bot);
        mAdViewBot.loadAd(new AdRequest.Builder().build());

        switch (getIntent().getIntExtra("method",1)){
            case 1:
            {
                mAlgoName.setText("SHA-1 Hash Generator");
                mHashName.setText("SHA-1");

                mMessage.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        mHash.setText(getSha1(mMessage.getText().toString()));
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                break;
            }
            case 2:
            {
                mAlgoName.setText("SHA-224 Hash Generator");
                mHashName.setText("SHA-224");

                mMessage.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        mHash.setText(getSha224(mMessage.getText().toString()));
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                break;
            }

            case 3:
            {
                mAlgoName.setText("MD2 Hash Generator");
                mHashName.setText("MD2");

                mMessage.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        mHash.setText(getMD2(mMessage.getText().toString()));
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                break;
            }
            case 4:
            {
                mAlgoName.setText("MD5 Hash Generator");
                mHashName.setText("MD5");

                mMessage.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        mHash.setText(getMd5(mMessage.getText().toString()));
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                break;
            }
        }
    }

    public void openMenu(View view) {
        startActivity(new Intent(this, Menu.class));
    }

    public void copy(View view) {
        ClipboardManager clipboardManager= (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        ClipData clip=ClipData.newPlainText("hash",mHash.getText());
        clipboardManager.setPrimaryClip(clip);
        Toast.makeText(this, "Hash Copied", Toast.LENGTH_SHORT).show();
    }

    public static String getSha1(String input)
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

    public static String getSha224(String input)
    {
        try {
            // getInstance() method is called with algorithm SHA-224
            MessageDigest md = MessageDigest.getInstance("SHA-224");

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

    public static String getMD2(String input)
    {
        try {
            // getInstance() method is called with algorithm MD2
            MessageDigest md = MessageDigest.getInstance("MD2");

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

    public static String getMd5(String input)
    {
        try {

            // Static getInstance method is called with hashing MD5
            MessageDigest md = MessageDigest.getInstance("MD5");

            // digest() method is called to calculate message digest
            //  of an input digest() return array of byte
            byte[] messageDigest = md.digest(input.getBytes());

            // Convert byte array into signum representation
            BigInteger no = new BigInteger(1, messageDigest);

            // Convert message digest into hex value
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }

        // For specifying wrong message digest algorithms
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

}
