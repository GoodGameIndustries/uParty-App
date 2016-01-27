package com.GGI.uParty.android;

import com.GGI.uParty.uParty;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import android.app.Dialog;
import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.net.wifi.WifiManager.MulticastLock;
import android.os.Bundle;
import android.provider.Settings;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.Toast;

public class AndroidLauncher extends AndroidApplication implements com.GGI.uParty.Adapter{

  private static final String AD_UNIT_ID_BANNER = "ca-app-pub-3725510963686041/7593452210";
  private static final String AD_UNIT_ID_INTERSTITIAL = "ca-app-pub-3725510963686041/5655385018";
  private static final String GOOGLE_PLAY_URL = "https://play.google.com/store/apps/developer?id=Good%20Game%20Industries&hl=en";
  protected AdView adView;
  protected View gameView;
  private InterstitialAd interstitialAd;
  MulticastLock multicastLock = null;
  WifiManager wifi = null;
  Instrumentation inst = new Instrumentation();
  private boolean adShown = false;
  
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    wifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);
	multicastLock = wifi.createMulticastLock("multicastLock");
	multicastLock.setReferenceCounted(true);
	multicastLock.acquire();

    AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();
    cfg.useAccelerometer = false;
    cfg.useCompass = false;

    // Do the stuff that initialize() would do for you
    requestWindowFeature(Window.FEATURE_NO_TITLE);
    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);

    RelativeLayout layout = new RelativeLayout(this);
    RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
    layout.setLayoutParams(params);

    AdView admobView = createAdView();
    //layout.addView(admobView);
    View gameView = createGameView(cfg);
    layout.addView(gameView);

    
    
    setContentView(layout);
    //startAdvertising(admobView);
    
    interstitialAd = new InterstitialAd(this);
    interstitialAd.setAdUnitId(AD_UNIT_ID_INTERSTITIAL);
    interstitialAd.setAdListener(new AdListener() {
        @Override
        public void onAdLoaded() {
        	adShown=true;
      	  interstitialAd.show();
        	//Toast.makeText(getApplicationContext(), "Finished Loading Interstitial", Toast.LENGTH_SHORT).show();
        }
        @Override
        public void onAdClosed() {
          //Toast.makeText(getApplicationContext(), "Closed Interstitial", Toast.LENGTH_SHORT).show();
        }
      });
     
  }

  @Override
  public void showOrLoadInterstital() {
    try {
      runOnUiThread(new Runnable() {
        public void run() {
        	
          if (interstitialAd.isLoaded()) {
        	  adShown=true;
        	  interstitialAd.show();
           // Toast.makeText(getApplicationContext(), "Showing Interstitial", Toast.LENGTH_SHORT).show();
          }
          else {
            AdRequest interstitialRequest = new AdRequest.Builder().addTestDevice("1A9B9E06D85890AEBDAEC34616F502D2").addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build();
            interstitialAd.loadAd(interstitialRequest);
            
            //Toast.makeText(getApplicationContext(), "Loading Interstitial", Toast.LENGTH_SHORT).show();
          }
        	}
        
      });
    } catch (Exception e) {
    }
  }
  
  private AdView createAdView() {
	    adView = new AdView(this);
	    adView.setAdSize(AdSize.SMART_BANNER);
	    adView.setAdUnitId(AD_UNIT_ID_BANNER);
	    adView.setId(12345); // this is an arbitrary id, allows for relative positioning in createGameView()
	    RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
	    params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
	    params.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);
	    adView.setLayoutParams(params);
	    adView.setBackgroundColor(Color.BLACK);
	    return adView;
	  }

	  private View createGameView(AndroidApplicationConfiguration cfg) {
		  
	    gameView = initializeForView(new uParty(this), cfg);
	    RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
	    params.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
	    params.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);
	    params.addRule(RelativeLayout.ABOVE, adView.getId());
	    gameView.setLayoutParams(params);
	    return gameView;
	  }

  private void startAdvertising(AdView adView) {
    AdRequest adRequest = new AdRequest.Builder().build();
    adView.loadAd(adRequest);
  }

  

  @Override
  public void onResume() {
    super.onResume();
    if (adView != null) adView.resume();
  }

  @Override
  public void onPause() {
    if (adView != null) adView.pause();
    super.onPause();
  }

  @Override
  public void onDestroy() {
    if (adView != null) adView.destroy();
    super.onDestroy();
    multicastLock.release();
  }
  


  @Override
  public void onBackPressed() {
    final Dialog dialog = new Dialog(this);
    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

    LinearLayout ll = new LinearLayout(this);
    ll.setOrientation(LinearLayout.VERTICAL);

    Button b1 = new Button(this);
    b1.setText("Quit");
    b1.setOnClickListener(new OnClickListener() {
      public void onClick(View v) {
        finish();
      }
    });
    ll.addView(b1);

    Button b2 = new Button(this);
    b2.setText("GGI");
    b2.setOnClickListener(new OnClickListener() {
      public void onClick(View v) {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(GOOGLE_PLAY_URL)));
        dialog.dismiss();
      }
    });
    ll.addView(b2);


    dialog.setContentView(ll);
    dialog.show();
  }
  
  public void pressKey(){
	  
	  inst.sendKeyDownUpSync(KeyEvent.KEYCODE_ENTER);
  }
}  