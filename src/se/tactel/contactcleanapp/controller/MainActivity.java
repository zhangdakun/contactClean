// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package se.tactel.contactcleanapp.controller;

import android.app.Activity;
import android.app.Dialog;
import android.content.*;
import android.os.*;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import se.tactel.contactcleanapp.cleanapp.*;
import se.tactel.contactcleanapp.notification.CleanAppNotificationManager;
import se.tactel.contactcleanapp.view.ProgressButtonView;

// Referenced classes of package se.tactel.contactcleanapp.controller:
//            MergeActivity, NoDuplicatesActivity, FinishActivity

public class MainActivity extends Activity
{
    private class FindMatchesTask extends AsyncTask
    {

        protected Object doInBackground(Object aobj[])
        {
            return doInBackground((Integer[])aobj);
        }

        protected Void doInBackground(Integer ainteger[])
        {
            CleanAppManager.get(getApplicationContext()).startSearch(getApplicationContext(), new CleanAppAdapter() {

                public void onProgress(int i)
                {
                    FindMatchesTask findmatchestask = FindMatchesTask.this;
                    Integer ainteger2[] = new Integer[1];
                    ainteger2[0] = Integer.valueOf(i);
                    findmatchestask.publishProgress(ainteger2);
                }

                public void onSessionAborted()
                {
                    mAborted = true;
                }

                public void onStarted()
                {
                    startAnimation();
                    FindMatchesTask findmatchestask = FindMatchesTask.this;
                    Integer ainteger2[] = new Integer[1];
                    ainteger2[0] = Integer.valueOf(0);
                    findmatchestask.publishProgress(ainteger2);
                }

//                final FindMatchesTask this$1;
//
//                
//                {
//                    this$1 = FindMatchesTask.this;
//                    super();
//                }
            }
);
            Integer ainteger1[] = new Integer[1];
            ainteger1[0] = Integer.valueOf(100);
            publishProgress(ainteger1);
            return null;
        }

        protected void onPostExecute(Object obj)
        {
            onPostExecute((Void)obj);
        }

        protected void onPostExecute(Void void1)
        {
            if(mAborted)
            {
                abort();
                init();
                showDialog(2);
            } else
            {
                searchCompleted();
            }
        }

        protected void onProgressUpdate(Integer ainteger[])
        {
            progressUpdated(ainteger[0].intValue());
        }

        protected void onProgressUpdate(Object aobj[])
        {
            onProgressUpdate((Integer[])aobj);
        }

        private boolean mAborted;
//        final MainActivity this$0;




        public FindMatchesTask()
        {
//            this$0 = MainActivity.this;
//            super();
            mAborted = false;
        }
    }

    private class ProgressController
        implements Runnable
    {

        public void run()
        {
            if(delayedProgress < 100)
            {
                if(delayedProgress < realProgress)
                {
                    delayedProgress = 1 + delayedProgress;
                    updateProgressView(delayedProgress);
                }
                mHandler.postDelayed(this, SPEED);
            } else
            {
                updateProgressView(100);
                animationCompleted();
            }
        }

        public void setRealProgress(int i)
        {
            realProgress = i;
        }

        private long SPEED;
        private int delayedProgress;
        private int realProgress;
//        final MainActivity this$0;

        public ProgressController()
        {
//            this$0 = MainActivity.this;
//            super();
            SPEED = 100L;
            realProgress = 0;
            delayedProgress = 0;
        }
    }


    public MainActivity()
    {
    }

    private void abort()
    {
        if(mFindMatchesTask != null)
            mFindMatchesTask.cancel(true);
        if(mHandler != null && mProgressController != null)
            mHandler.removeCallbacks(mProgressController);
    }

    private void animationCompleted()
    {
        incrementState();
        goToMerge();
    }

    private void findMatches()
    {
        incrementState();
        updateProgressView(0);
        mFindMatchesTask = new FindMatchesTask();
        mFindMatchesTask.execute(new Integer[0]);
    }

    /**
     * @deprecated Method getSearchState is deprecated
     */

    private int getSearchState()
    {
//        this;
//        JVM INSTR monitorenter ;
        int i = mState;
//        this;
//        JVM INSTR monitorexit ;
        return i;
//        Exception exception;
//        exception;
//        throw exception;
    }

    private void goToMerge()
    {
        if(getSearchState() == 3)
            startActivityForResult(new Intent(this, MergeActivity.class), 0);
    }

    /**
     * @deprecated Method incrementState is deprecated
     */

    private void incrementState()
    {
//        this;
//        JVM INSTR monitorenter ;
        mState = 1 + mState;
//        this;
//        JVM INSTR monitorexit ;
//        return;
//        Exception exception;
//        exception;
//        throw exception;
    }

    private void init()
    {
        setContentView(0x7f030001);
        mHandler = new Handler();
        initSearchState();
        mCleanupButton = (ProgressButtonView)findViewById(0x7f070019);
        mCleanupButton.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                if(getSearchState() == 0)
                    findMatches();
            }

//            final MainActivity this$0;
//
//            
//            {
//                this$0 = MainActivity.this;
//                super();
//            }
        }
);
        mPeriodicCheckbox = (CheckBox)findViewById(0x7f070010);
        mPeriodicCheckbox.setChecked(mPrefs.getBoolean("periodic", false));
        mPeriodicCheckbox.setOnCheckedChangeListener(new android.widget.CompoundButton.OnCheckedChangeListener() {

            public void onCheckedChanged(CompoundButton compoundbutton, boolean flag)
            {
                CleanAppNotificationManager cleanappnotificationmanager = new CleanAppNotificationManager(MainActivity.this);
                if(flag)
                    cleanappnotificationmanager.setWeeklyScan();
                else
                    cleanappnotificationmanager.cancelPeriodicScan();
                mPrefs.edit().putBoolean("periodic", flag).commit();
            }

//            final MainActivity this$0;
//
//            
//            {
//                this$0 = MainActivity.this;
//                super();
//            }
        }
);
        if(mPrefs.getBoolean("periodic", false))
            mPeriodicCheckbox.setVisibility(0);
        else
            mPeriodicCheckbox.setVisibility(8);
    }

    /**
     * @deprecated Method initSearchState is deprecated
     */

    private void initSearchState()
    {
//        this;
//        JVM INSTR monitorenter ;
        mState = 0;
//        this;
//        JVM INSTR monitorexit ;
//        return;
//        Exception exception;
//        exception;
//        throw exception;
    }

    private void progressUpdated(int i)
    {
        mProgressController.setRealProgress(i);
    }

    private void searchCompleted()
    {
        incrementState();
        goToMerge();
    }

    private void startAnimation()
    {
        mProgressController = new ProgressController();
        mHandler.post(mProgressController);
    }

    private void updateProgressView(int i)
    {
        mCleanupButton.setTextColor(0xaaffffff);
        mCleanupButton.setTextSize(1, 60F);
        mCleanupButton.setText((new StringBuilder(String.valueOf(i))).append("%").toString());
        mCleanupButton.setCurrentAngle(360F * (0.01F * (float)i));
    }

    protected void onActivityResult(int i, int j, Intent intent)
    {
        if(i == 0)
            if(j == -1)
            {
                if(CleanAppManager.get(this).getNbrTotalMerged() < 1)
                    startActivity(new Intent(this, NoDuplicatesActivity.class));
                else
                    startActivity(new Intent(this, FinishActivity.class));
                finish();
            } else
            if(CleanAppManager.get(this).getNbrTotalMerged() < 1)
            {
                init();
            } else
            {
                startActivity(new Intent(this, FinishActivity.class));
                finish();
            }
    }

    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        Application.get().setRunning(TAG, true);
        mPrefs = getSharedPreferences("prefs", 0);
//        if(!getIntent().getBooleanExtra("notification_start", false)) goto _L2; else goto _L1
//_L1:
//        startActivityForResult(new Intent(this, se/tactel/contactcleanapp/controller/MergeActivity), 0);
//_L4:
//        return;
//_L2:
//        init();
//        if(!mPrefs.getBoolean("terms", false))
//            showDialog(1);
//        if(true) goto _L4; else goto _L3
//_L3:
        
        if(!getIntent().getBooleanExtra("notification_start", false)) {
          init();
          if(!mPrefs.getBoolean("terms", false))
              showDialog(1);
        } else {
        	startActivityForResult(new Intent(this, MergeActivity.class), 0);
        }
    }

    protected Dialog onCreateDialog(int i)
    {
        Object obj;
        if(i == 1)
            obj = (new android.app.AlertDialog.Builder(this)).setTitle(0x7f050001).setMessage(0x7f050002).setCancelable(false).setPositiveButton(0x104000a, new android.content.DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialoginterface, int j)
                {
                    mPrefs.edit().putBoolean("terms", true).commit();
                }

//                final MainActivity this$0;
//
//            
//            {
//                this$0 = MainActivity.this;
//                super();
//            }
            }
).setNegativeButton(0x1040000, new android.content.DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialoginterface, int j)
                {
                    finish();
                }

//                final MainActivity this$0;
//
//            
//            {
//                this$0 = MainActivity.this;
//                super();
//            }
            }
).create();
        else
        if(i == 2)
            obj = (new android.app.AlertDialog.Builder(this)).setTitle(0x7f050006).setMessage(0x7f050007).setNeutralButton(0x104000a, new android.content.DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialoginterface, int j)
                {
                }

//                final MainActivity this$0;
//
//            
//            {
//                this$0 = MainActivity.this;
//                super();
//            }
            }
).create();
        else
            obj = super.onCreateDialog(i);
        return ((Dialog) (obj));
    }

    protected void onDestroy()
    {
        abort();
        Application.get().setRunning(TAG, false);
        super.onDestroy();
    }

    private static final int DIALOG_ERROR = 2;
    private static final int DIALOG_TERMS = 1;
    public static final String NOTIFICATION_START = "notification_start";
    private static final int REQUEST_MERGE = 0;
    private static final int STATE_ANIMATION_COMPLETED = 3;
    private static final int STATE_NOT_STARTED = 0;
    private static final int STATE_SEARCH_COMPLETED = 2;
    private static final int STATE_STARTED = 1;
    private static final String TAG =  MainActivity.class.getSimpleName();
    private ProgressButtonView mCleanupButton;
    private FindMatchesTask mFindMatchesTask;
    private Handler mHandler;
    private CheckBox mPeriodicCheckbox;
    private SharedPreferences mPrefs;
    private ProgressController mProgressController;
    private int mState;












}
