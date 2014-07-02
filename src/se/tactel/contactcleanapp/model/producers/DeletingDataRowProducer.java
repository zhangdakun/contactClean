// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package se.tactel.contactcleanapp.model.producers;

import android.content.ContentProviderOperation;
import java.util.ArrayList;

// Referenced classes of package se.tactel.contactcleanapp.model.producers:
//            DataRowProducer

public abstract class DeletingDataRowProducer
    implements DataRowProducer
{

    public DeletingDataRowProducer(String s, long l)
    {
        mMimeType = s;
        mId = l;
    }

    public void produce(ArrayList arraylist)
    {
        android.content.ContentProviderOperation.Builder builder = ContentProviderOperation.newDelete(android.provider.ContactsContract.Data.CONTENT_URI);
        String as[] = new String[2];
        as[0] = (new StringBuilder(String.valueOf(mId))).toString();
        as[1] = mMimeType;
        arraylist.add(builder.withSelection("raw_contact_id=? AND mimetype=?", as).build());
    }

    protected static final String WHERE_ID_AND_MIMETYPE = "raw_contact_id=? AND mimetype=?";
    protected long mId;
    private String mMimeType;
}
