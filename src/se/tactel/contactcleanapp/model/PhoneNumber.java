// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package se.tactel.contactcleanapp.model;

import android.content.Context;

public class PhoneNumber
{

    public PhoneNumber(String s, int i)
    {
        mNumber = s;
        mType = i;
    }

    public boolean equals(Object obj)
    {
        boolean flag;
        flag = false;
//        break MISSING_BLOCK_LABEL_2;
        if(obj != null && obj.getClass() == getClass())
            if(obj == this)
                flag = true;
            else
                flag = ((PhoneNumber)obj).getSuffix().equals(getSuffix());
        return flag;
    }

    public String getNumber()
    {
        return mNumber;
    }

    public String getSuffix()
    {
//        if(mNumber != null) goto _L2; else goto _L1
//_L1:
//        String s = "";
//_L4:
//        return s;
//_L2:
//        s = mNumber.replaceAll("\\D+", "");
//        if(s.length() >= 6)
//            s = s.substring(s.length() - 6);
//        if(true) goto _L4; else goto _L3
//_L3:
    	
    	 String s = "";
    	 
    	 if(mNumber != null) {
    		 s = mNumber.replaceAll("\\D+", "");
           if(s.length() >= 6)
           s = s.substring(s.length() - 6);
    	 }
    	 
    	 return s;
    }

    public int getType()
    {
        return mType;
    }

    public String getTypeAsString(Context context)
    {
        return (String)android.provider.ContactsContract.CommonDataKinds.Phone.getTypeLabel(context.getResources(), mType, "");
    }

    public int hashCode()
    {
        int i;
        if(getSuffix() == null)
            i = 0;
        else
            i = getSuffix().hashCode();
        return i + 527;
    }

    public void setNumber(String s)
    {
        mNumber = s;
    }

    public void setType(int i)
    {
        mType = i;
    }

    private String mNumber;
    private int mType;
}
