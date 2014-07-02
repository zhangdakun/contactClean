// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package se.tactel.contactcleanapp.model;


// Referenced classes of package se.tactel.contactcleanapp.model:
//            ContactData

public class Contact
{

    public Contact(long l, long l1)
    {
        mRawId = l;
        mAggregatedId = l1;
        mData = new ContactData();
    }

    public boolean equals(Object obj)
    {
        boolean flag;
        flag = false;
//        break MISSING_BLOCK_LABEL_2;
//        while(true) 
//        {
//            do
//                return flag;
//            while(obj == null || obj.getClass() != getClass());
//            if(obj == this)
//            {
//                flag = true;
//            } else
//            {
//                Contact contact = (Contact)obj;
//                if(contact.getRawId() == mRawId && contact.getAggregatedId() == mAggregatedId && contact.getData().equals(mData))
//                    flag = true;
//            }
//        }
        
        if(obj == null || obj.getClass() != getClass()) {
        	
        } else {
          if(obj == this)
          {
              flag = true;
          } else
          {
              Contact contact = (Contact)obj;
              if(contact.getRawId() == mRawId && contact.getAggregatedId() == mAggregatedId && contact.getData().equals(mData))
                  flag = true;
          }
        }
        
        return flag;
    }

    public long getAggregatedId()
    {
        return mAggregatedId;
    }

    public ContactData getData()
    {
        return mData;
    }

    public long getRawId()
    {
        return mRawId;
    }

    public int hashCode()
    {
        int i = 31 * (31 * (527 + Long.valueOf(mRawId).hashCode()) + Long.valueOf(mAggregatedId).hashCode());
        int j;
        if(mData == null)
            j = 0;
        else
            j = mData.hashCode();
        return i + j;
    }

    public void setData(ContactData contactdata)
    {
        mData = contactdata;
    }

    private long mAggregatedId;
    private ContactData mData;
    private long mRawId;
}
