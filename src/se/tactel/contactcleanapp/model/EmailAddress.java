// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package se.tactel.contactcleanapp.model;

import android.content.Context;

public class EmailAddress
{

    public EmailAddress(String s, String s1, int i)
    {
        if(s == null)
            s = "";
        mDisplayName = s;
        if(s1 == null)
            s1 = "";
        mEmailAddress = s1;
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
                flag = ((EmailAddress)obj).getEmailAddress().equals(mEmailAddress);
        return flag;
    }

    public String getDisplayName()
    {
        return mDisplayName;
    }

    public String getEmailAddress()
    {
        return mEmailAddress;
    }

    public int getType()
    {
        return mType;
    }

    public String getTypeAsString(Context context)
    {
        return (String)android.provider.ContactsContract.CommonDataKinds.Email.getTypeLabel(context.getResources(), mType, "");
    }

    public int hashCode()
    {
        int i;
        if(mEmailAddress == null)
            i = 0;
        else
            i = mEmailAddress.hashCode();
        return i + 527;
    }

    public void setDisplayName(String s)
    {
        if(s == null)
            s = "";
        mDisplayName = s;
    }

    public void setEmailAddress(String s)
    {
        if(s == null)
            s = "";
        mEmailAddress = s;
    }

    public void setType(int i)
    {
        mType = i;
    }

    private String mDisplayName;
    private String mEmailAddress;
    private int mType;
}
