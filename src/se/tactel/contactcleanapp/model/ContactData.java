// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package se.tactel.contactcleanapp.model;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Iterator;

// Referenced classes of package se.tactel.contactcleanapp.model:
//            Name, EmailAddress, PhoneNumber

public class ContactData
{

    public ContactData()
    {
        mPhoneNumbers = new ArrayList();
        mEmailAddresses = new ArrayList();
        mIsGooglePlus = false;
        mAccountName = "";
        mAccountType = "";
        mName = new Name("", "", "", "");
    }

    public void addEmail(String s, String s1, int i)
    {
        if(!TextUtils.isEmpty(s1))
            mEmailAddresses.add(new EmailAddress(s, s1, i));
    }

    public void addPhoneNumber(String s, int i)
    {
        if(!TextUtils.isEmpty(s))
            mPhoneNumbers.add(new PhoneNumber(s, i));
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
//                ContactData contactdata = (ContactData)obj;
//                if(contactdata.getAccountName().equals(mAccountName) && contactdata.getAccountType().equals(mAccountType) && contactdata.getName().equals(mName) && contactdata.getPhoneNumbers().equals(mPhoneNumbers) && contactdata.getEmailAddresses().equals(mEmailAddresses) && contactdata.isGooglePlusContact() == mIsGooglePlus)
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
              ContactData contactdata = (ContactData)obj;
              if(contactdata.getAccountName().equals(mAccountName) && contactdata.getAccountType().equals(mAccountType) && contactdata.getName().equals(mName) && contactdata.getPhoneNumbers().equals(mPhoneNumbers) && contactdata.getEmailAddresses().equals(mEmailAddresses) && contactdata.isGooglePlusContact() == mIsGooglePlus)
                  flag = true;
          }        	
        }
        
        
        return flag;
    }

    public String getAccountName()
    {
        return mAccountName;
    }

    public String getAccountType()
    {
        return mAccountType;
    }

    public ArrayList getEmailAddresses()
    {
        return mEmailAddresses;
    }

    public Name getName()
    {
        return mName;
    }

    public ArrayList getPhoneNumbers()
    {
        return mPhoneNumbers;
    }

    public byte[] getPhoto()
    {
        return mPhoto;
    }

    public boolean hasEmailAddress(EmailAddress emailaddress)
    {
        return mEmailAddresses.contains(emailaddress);
    }

    public boolean hasPhoneNumber(PhoneNumber phonenumber)
    {
        return mPhoneNumbers.contains(phonenumber);
    }

    public int hashCode()
    {
        int i = 0;
        int j;
        int k;
        int l;
        int i1;
        int j1;
        int k1;
        if(mAccountName == null)
            j = 0;
        else
            j = mAccountName.hashCode();
        k = 31 * (j + 527);
        if(mAccountType == null)
            l = 0;
        else
            l = mAccountType.hashCode();
        i1 = 31 * (k + l);
        if(mName == null)
            j1 = 0;
        else
            j1 = mName.hashCode();
        k1 = 31 * (31 * (31 * (i1 + j1) + mPhoneNumbers.hashCode()) + mEmailAddresses.hashCode());
        if(mIsGooglePlus)
            i = 1;
        return k1 + i;
    }

    public boolean isGooglePlusContact()
    {
        return mIsGooglePlus;
    }

    public void mergeWith(ContactData contactdata)
    {
        Iterator iterator;
        if(mName == null || mName.getFullName().length() <= 0)
            mName = contactdata.getName();
        iterator = contactdata.getPhoneNumbers().iterator();
//_L3:
//        if(iterator.hasNext()) goto _L2; else goto _L1
//_L1:
//        Iterator iterator1 = contactdata.getEmailAddresses().iterator();
//_L4:
//        if(!iterator1.hasNext())
//        {
//            if(mPhoto == null && contactdata.getPhoto() != null)
//                mPhoto = contactdata.getPhoto();
//            return;
//        }
//        break MISSING_BLOCK_LABEL_117;
//_L2:
//        PhoneNumber phonenumber = (PhoneNumber)iterator.next();
//        if(!hasPhoneNumber(phonenumber))
//            mPhoneNumbers.add(phonenumber);
//          goto _L3
//        EmailAddress emailaddress = (EmailAddress)iterator1.next();
//        if(!hasEmailAddress(emailaddress))
//            mEmailAddresses.add(emailaddress);
//          goto _L4
        
        while(iterator.hasNext()) {
          PhoneNumber phonenumber = (PhoneNumber)iterator.next();
          if(!hasPhoneNumber(phonenumber))
              mPhoneNumbers.add(phonenumber);
        }
        
        Iterator iterator1 = contactdata.getEmailAddresses().iterator();
        while(iterator1.hasNext()) {
          EmailAddress emailaddress = (EmailAddress)iterator1.next();
          if(!hasEmailAddress(emailaddress))
              mEmailAddresses.add(emailaddress);
        }
        
      if(mPhoto == null && contactdata.getPhoto() != null)
      mPhoto = contactdata.getPhoto();
    }

    public void setAccountName(String s)
    {
        if(s == null)
            s = "";
        mAccountName = s;
    }

    public void setAccountType(String s)
    {
        if(s == null)
            s = "";
        mAccountType = s;
    }

    public void setGooglePlusContact(boolean flag)
    {
        mIsGooglePlus = flag;
    }

    public void setName(Name name)
    {
        mName = name;
    }

    public void setPhoto(byte abyte0[])
    {
        mPhoto = abyte0;
    }

    private String mAccountName;
    private String mAccountType;
    private ArrayList mEmailAddresses;
    private boolean mIsGooglePlus;
    private Name mName;
    private ArrayList mPhoneNumbers;
    private byte mPhoto[];
}
