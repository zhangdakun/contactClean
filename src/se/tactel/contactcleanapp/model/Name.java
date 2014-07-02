// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package se.tactel.contactcleanapp.model;

import android.text.TextUtils;

public class Name
{

    public Name(String s, String s1, String s2, String s3)
    {
        if(s == null)
            s = "";
        mDisplayName = s;
        if(s1 == null)
            s1 = "";
        mGivenName = s1;
        if(s2 == null)
            s2 = "";
        mFamilyName = s2;
        if(s3 == null)
            s3 = "";
        mMiddleName = s3;
        updateFullName();
    }

    private void updateFullName()
    {
        StringBuilder stringbuilder = new StringBuilder();
        if(!TextUtils.isEmpty(mGivenName))
            stringbuilder.append(mGivenName);
        if(!TextUtils.isEmpty(mMiddleName))
        {
            if(stringbuilder.length() != 0)
                stringbuilder.append(" ");
            stringbuilder.append(mMiddleName);
        }
        if(!TextUtils.isEmpty(mFamilyName))
        {
            if(stringbuilder.length() != 0)
                stringbuilder.append(" ");
            stringbuilder.append(mFamilyName);
        }
        mFullName = stringbuilder.toString();
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
                flag = ((Name)obj).getFullName().equalsIgnoreCase(mFullName);
        return flag;
    }

    public String getDisplayName()
    {
        return mDisplayName;
    }

    public String getFamilyName()
    {
        return mFamilyName;
    }

    public String getFullName()
    {
        return mFullName;
    }

    public String getGivenName()
    {
        return mGivenName;
    }

    public String getMiddleName()
    {
        return mMiddleName;
    }

    public int hashCode()
    {
        int i;
        if(getFullName() == null)
            i = 0;
        else
            i = getFullName().hashCode();
        return i + 527;
    }

    public void setDisplayName(String s)
    {
        if(s == null)
            s = "";
        mDisplayName = s;
    }

    public void setFamilyName(String s)
    {
        if(s == null)
            s = "";
        mFamilyName = s;
        updateFullName();
    }

    public void setGivenName(String s)
    {
        if(s == null)
            s = "";
        mGivenName = s;
        updateFullName();
    }

    public void setMiddleName(String s)
    {
        mMiddleName = s;
        updateFullName();
    }

    private String mDisplayName;
    private String mFamilyName;
    private String mFullName;
    private String mGivenName;
    private String mMiddleName;
}
