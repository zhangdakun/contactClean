// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package se.tactel.contactcleanapp.controller;

import android.app.Activity;
import android.content.*;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;
import se.tactel.contactcleanapp.cleanapp.Application;
import se.tactel.contactcleanapp.cleanapp.CleanAppManager;
import se.tactel.contactcleanapp.notification.CleanAppNotificationManager;

// Referenced classes of package se.tactel.contactcleanapp.controller:
//            StoreTask

public class FinishActivity extends Activity
{

    public FinishActivity()
    {
    }

    private void launchAmazonIntent()
    {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse("http://www.amazon.com/gp/mas/dl/android?p=se.tactel.contactcleanapp&showAll=1"));
        startActivity(intent);
        finish();
//_L1:
//        return;
//        ActivityNotFoundException activitynotfoundexception;
//        activitynotfoundexception;
//        Log.d(TAG, "No market app on device");
//          goto _L1
    }

    private void launchMarketIntent(String s)
    {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse((new StringBuilder("market://search?q=")).append(s).toString()));
        startActivity(intent);
        finish();
//_L1:
//        return;
//        ActivityNotFoundException activitynotfoundexception;
//        activitynotfoundexception;
//        Log.d(TAG, "No market app on device");
//          goto _L1
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(0x7f030000);
        Application.get().setRunning(TAG, true);
        CleanAppManager cleanappmanager = CleanAppManager.get(this);
        ((TextView)findViewById(0x7f070007)).setText((new StringBuilder(String.valueOf(cleanappmanager.getNbrUserMerged()))).toString());
        ((TextView)findViewById(0x7f070009)).setText((new StringBuilder(String.valueOf(cleanappmanager.getNbrAutoMerged()))).toString());
        ((TextView)findViewById(0x7f07000b)).setText((new StringBuilder(String.valueOf(cleanappmanager.getNbrTotalMerged()))).toString());
        ((TextView)findViewById(0x7f07000d)).setText((new StringBuilder(String.valueOf(cleanappmanager.getNbrInContactBook()))).toString());
        ((LinearLayout)findViewById(0x7f070011)).setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                launchMarketIntent("Tactel");
            }

//            final FinishActivity this$0;
//
//            
//            {
//                this$0 = FinishActivity.this;
//                super();
//            }
        }
);
        ((ImageView)findViewById(0x7f070001)).setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                finish();
            }

//            final FinishActivity this$0;
//
//            
//            {
//                this$0 = FinishActivity.this;
//                super();
//            }
        }
);
        ((LinearLayout)findViewById(0x7f070013)).setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("http://www.tactel.se"));
                startActivity(intent);
                finish();
            }

//            final FinishActivity this$0;
//
//            
//            {
//                this$0 = FinishActivity.this;
//                super();
//            }
        }
);
        final SharedPreferences prefs = getSharedPreferences("prefs", 0);
        CheckBox checkbox = (CheckBox)findViewById(0x7f070010);
        checkbox.setChecked(prefs.getBoolean("periodic", false));
        checkbox.setOnCheckedChangeListener(new android.widget.CompoundButton.OnCheckedChangeListener() {

            public void onCheckedChanged(CompoundButton compoundbutton, boolean flag)
            {
                CleanAppNotificationManager cleanappnotificationmanager = new CleanAppNotificationManager(FinishActivity.this);
                if(flag)
                    cleanappnotificationmanager.setWeeklyScan();
                else
                    cleanappnotificationmanager.cancelPeriodicScan();
                prefs.edit().putBoolean("periodic", flag).commit();
            }

//            final FinishActivity this$0;
//            private final SharedPreferences val$prefs;
//
//            
//            {
//                this$0 = FinishActivity.this;
//                prefs = sharedpreferences;
//                super();
//            }
        }
);
        (new StoreTask(this)).execute(new Void[0]);
        if(!prefs.getBoolean("rate", false))
        {
            (new CleanAppNotificationManager(this)).setNotificationAlarm(0x927c0L);
            prefs.edit().putBoolean("rate", true).commit();
        }
    }

    protected void onDestroy()
    {
        Application.get().setRunning(TAG, false);
        super.onDestroy();
    }

    protected void onPause()
    {
        super.onPause();
        finish();
    }

    private static final String TAG = FinishActivity.class.getSimpleName();


}
