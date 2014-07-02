// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package se.tactel.contactcleanapp.utils;

import java.util.ArrayList;
import java.util.Iterator;

public class StringUtils
{

    public StringUtils()
    {
    }

    public static String getLongestStringFromArray(ArrayList arraylist)
    {
        String s;
        if(arraylist == null || arraylist.size() <= 0)
        {
            s = null;
        } else
        {
            s = "";
            Iterator iterator = arraylist.iterator();
            while(iterator.hasNext()) 
            {
                String s1 = (String)iterator.next();
                if(s1.length() > s.length())
                    s = s1;
            }
        }
        return s;
    }
}
