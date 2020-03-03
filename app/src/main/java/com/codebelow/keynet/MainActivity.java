package com.codebelow.keynet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;

public class MainActivity extends AppCompatActivity {

    Button gp_btn;
    InterstitialAd mInterstitialAd;
    boolean showAfterLoad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MobileAds.initialize(this);
        gp_btn=findViewById(R.id.gp_btn);
        showAfterLoad = false;

        mInterstitialAd=new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-2735789015101856/7652632618");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
        mInterstitialAd.setAdListener(new AdListener(){
            @Override
            public void onAdClosed() {
                startActivity(new Intent(MainActivity.this,PassWord.class));
                showAfterLoad=false;
            }

            @Override
            public void onAdLoaded() {
                if (showAfterLoad){
                    {
                        mInterstitialAd.show();
                        showAfterLoad=false;
                        gp_btn.setText("Generate Password");
                    }
                }
            }

            @Override
            public void onAdFailedToLoad(int i) {
                if (showAfterLoad){
                    startActivity(new Intent(MainActivity.this,PassWord.class));
                    showAfterLoad=false;
                    gp_btn.setText("Generate Password");
                }
            }
        });

        AdView mAdViewTop = findViewById(R.id.bnr_top);
        mAdViewTop.loadAd(new AdRequest.Builder().build());

        AdView mAdViewBot = findViewById(R.id.bnr_bot);
        mAdViewBot.loadAd(new AdRequest.Builder().build());
    }

    public void generatePass(View view) {
        if (mInterstitialAd.isLoaded()){
            mInterstitialAd.show();
        }
        else{
            showAfterLoad=true;
            gp_btn.setText("Wait...");
        }
    }

    public void openMenu(View view) {
        startActivity(new Intent(this,Menu.class));
    }
}
