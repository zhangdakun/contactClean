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
import se.tactel.contactcleanapp.notification.CleanAppNotificationManager;

// Referenced classes of package se.tactel.contactcleanapp.controller:
//            StoreTask

public class NoDuplicatesActivity extends Activity
{

    public NoDuplicatesActivity()
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
        setContentView(0x7f030003);
        Application.get().setRunning(TAG, true);
        final SharedPreferences prefs = getSharedPreferences("prefs", 0);
        CheckBox checkbox = (CheckBox)findViewById(0x7f070010);
        checkbox.setChecked(prefs.getBoolean("periodic", false));
        checkbox.setOnCheckedChangeListener(new android.widget.CompoundButton.OnCheckedChangeListener() {

            public void onCheckedChanged(CompoundButton compoundbutton, boolean flag)
            {
                CleanAppNotificationManager cleanappnotificationmanager = new CleanAppNotificationManager(NoDuplicatesActivity.this);
                if(flag)
                    cleanappnotificationmanager.setWeeklyScan();
                else
                    cleanappnotificationmanager.cancelPeriodicScan();
                prefs.edit().putBoolean("periodic", flag).commit();
            }

//            final NoDuplicatesActivity this$0;
//            private final SharedPreferences val$prefs;
//
//            
//            {
//                this$0 = NoDuplicatesActivity.this;
//                prefs = sharedpreferences;
//                super();
//            }
        }
);
        ((LinearLayout)findViewById(0x7f070011)).setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                launchMarketIntent("Tactel");
            }

//            final NoDuplicatesActivity this$0;
//
//            
//            {
//                this$0 = NoDuplicatesActivity.this;
//                super();
//            }
        }
);
        ((ImageView)findViewById(0x7f07002c)).setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                finish();
            }

//            final NoDuplicatesActivity this$0;
//
//            
//            {
//                this$0 = NoDuplicatesActivity.this;
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

//            final NoDuplicatesActivity this$0;
//
//            
//            {
//                this$0 = NoDuplicatesActivity.this;
//                super();
//            }
        }
);
        (new StoreTask(this)).execute(new Void[0]);
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

    private static final String TAG = NoDuplicatesActivity.class.getSimpleName();


}
