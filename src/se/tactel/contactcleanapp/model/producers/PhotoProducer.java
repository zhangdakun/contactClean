// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package se.tactel.contactcleanapp.model.producers;

import android.content.ContentProviderOperation;
import android.content.ContentValues;
import java.util.ArrayList;

// Referenced classes of package se.tactel.contactcleanapp.model.producers:
//            DeletingDataRowProducer

public class PhotoProducer extends DeletingDataRowProducer
{

    public PhotoProducer(long l, byte abyte0[])
    {
        super("vnd.android.cursor.item/photo", l);
        mPhotoData = abyte0;
    }

    public void produce(ArrayList arraylist)
    {
        super.produce(arraylist);
        if(mPhotoData != null && mPhotoData.length > 0)
        {
            ContentValues contentvalues = new ContentValues();
            contentvalues.put("raw_contact_id", Long.valueOf(mId));
            contentvalues.put("mimetype", "vnd.android.cursor.item/photo");
            contentvalues.put("data15", mPhotoData);
            arraylist.add(ContentProviderOperation.newInsert(android.provider.ContactsContract.Data.CONTENT_URI).withValues(contentvalues).build());
        }
    }

    private byte mPhotoData[];
}
