// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package se.tactel.contactcleanapp.model;

import java.util.*;

public class AccountTypes
{

    public AccountTypes()
    {
    }

    public static HashSet all()
    {
        HashSet hashset = new HashSet();
        Iterator iterator = sTypeSets.iterator();
        do
        {
            if(!iterator.hasNext())
                return hashset;
            String as[] = (String[])iterator.next();
            int i = as.length;
            int j = 0;
            while(j < i) 
            {
                hashset.add(as[j]);
                j++;
            }
        } while(true);
    }

    public static HashSet simTypes()
    {
        HashSet hashset = new HashSet();
        String as[] = SIM;
        int i = as.length;
        int j = 0;
        do
        {
            if(j >= i)
                return hashset;
            hashset.add(as[j]);
            j++;
        } while(true);
    }

    private static final String CUSTOMERS[];
    private static final String EVERDROID[];
    private static final String EXCHANGE[];
    private static final String GOOGLE[];
    private static final String OTHER[];
    private static final String PHONE_LOCAL[];
    private static final String SIM[];
    public static final String TYPE_GOOGLE = "com.google";
    private static Vector sTypeSets;

    static 
    {
        String as[] = new String[1];
        as[0] = "com.everdroid.mobile";
        EVERDROID = as;
        String as1[] = new String[4];
        as1[0] = "ru.megafon.memorybank";
        as1[1] = "com.mobitel";
        as1[2] = "no.telenor.sync";
        as1[3] = "vnd.android.cursor.item/vnd.sonyericsson.syncml.account";
        CUSTOMERS = as1;
        String as2[] = new String[1];
        as2[0] = "com.google";
        GOOGLE = as2;
        String as3[] = new String[2];
        as3[0] = "com.android.exchange";
        as3[1] = "com.htc.android.mail.eas";
        EXCHANGE = as3;
        String as4[] = new String[8];
        as4[0] = "com.htc.android.pcsc";
        as4[1] = "com.sonyericsson.localcontacts";
        as4[2] = "com.mobileleader.sync";
        as4[3] = "vnd.sec.contact.phone";
        as4[4] = "com.motorola.blur.contacts.UNCONNECTED_ACCOUNT";
        as4[5] = "com.android.acersync";
        as4[6] = "com.android.huawei.phone";
        as4[7] = "com.lge.sync";
        PHONE_LOCAL = as4;
        String as5[] = new String[1];
        as5[0] = "com.funambol";
        OTHER = as5;
        String as6[] = new String[2];
        as6[0] = "com.anddroid.contacts.sim";
        as6[1] = "vnd.sec.contact.sim";
        SIM = as6;
        sTypeSets = new Vector();
        sTypeSets.add(EVERDROID);
        sTypeSets.add(CUSTOMERS);
        sTypeSets.add(GOOGLE);
        sTypeSets.add(EXCHANGE);
        sTypeSets.add(PHONE_LOCAL);
        sTypeSets.add(OTHER);
    }
}
