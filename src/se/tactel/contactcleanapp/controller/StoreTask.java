// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package se.tactel.contactcleanapp.controller;

import android.content.Context;
import android.os.AsyncTask;
import se.tactel.contactcleanapp.cleanapp.CleanAppManager;

public class StoreTask extends AsyncTask
{

    public StoreTask(Context context)
    {
        mContext = context;
    }

//    protected Object doInBackground(Object aobj[])
//    {
//        return doInBackground((Void[])aobj);
//    }

    protected Void doInBackground(Void avoid[])
    {
        CleanAppManager.get(mContext).storeAllSkipped(mContext);
        return null;
    }

    private Context mContext;

	@Override
	protected Object doInBackground(Object... params) {
		// TODO Auto-generated method stub
		return null;
	}
}
