// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package se.tactel.contactcleanapp.cleanapp.match;

import java.util.*;
import se.tactel.contactcleanapp.model.*;

public class ContactMatch
{

    public ContactMatch(List list)
    {
        mContacts = list;
    }

    public List getContacts()
    {
        return mContacts;
    }

    public List getIds()
    {
        ArrayList arraylist = new ArrayList();
        Iterator iterator = mContacts.iterator();
        do
        {
            if(!iterator.hasNext())
                return arraylist;
            arraylist.add(Long.valueOf(((Contact)iterator.next()).getRawId()));
        } while(true);
    }

    public boolean isHardMatch()
    {
        Name name;
        byte abyte0[];
        Iterator iterator;
        name = ((Contact)mContacts.get(0)).getData().getName();
        abyte0 = ((Contact)mContacts.get(0)).getData().getPhoto();
        iterator = mContacts.iterator();
//_L6:
//        if(iterator.hasNext()) goto _L2; else goto _L1
//_L1:
//        HashMap hashmap;
//        HashMap hashmap1;
//        Iterator iterator1;
//        hashmap = new HashMap();
//        hashmap1 = new HashMap();
//        iterator1 = mContacts.iterator();
//_L8:
//        if(iterator1.hasNext()) goto _L4; else goto _L3
//_L3:
//        Iterator iterator4 = hashmap.values().iterator();
//_L11:
//        Iterator iterator5;
//        if(iterator4.hasNext())
//            continue; /* Loop/switch isn't completed */
//        iterator5 = hashmap1.values().iterator();
//_L13:
//        boolean flag;
//        if(iterator5.hasNext())
//            continue; /* Loop/switch isn't completed */
//        flag = false;
//_L7:
//        return flag;
//_L2:
//        Contact contact1 = (Contact)iterator.next();
//        if(contact1.getData().getName().equals(name) && Arrays.equals(abyte0, contact1.getData().getPhoto())) goto _L6; else goto _L5
//_L5:
//        flag = false;
//          goto _L7
//_L4:
//        Contact contact;
//        Iterator iterator2;
//        contact = (Contact)iterator1.next();
//        iterator2 = contact.getData().getPhoneNumbers().iterator();
//_L9:
//label0:
//        {
//            if(iterator2.hasNext())
//                break label0;
//            Iterator iterator3 = contact.getData().getEmailAddresses().iterator();
//            while(iterator3.hasNext()) 
//            {
//                EmailAddress emailaddress = (EmailAddress)iterator3.next();
//                PhoneNumber phonenumber;
//                ArrayList arraylist;
//                if(hashmap1.containsKey(emailaddress))
//                {
//                    ((ArrayList)hashmap1.get(emailaddress)).add(contact);
//                } else
//                {
//                    ArrayList arraylist1 = new ArrayList();
//                    arraylist1.add(contact);
//                    hashmap1.put(emailaddress, arraylist1);
//                }
//            }
//        }
//          goto _L8
//        phonenumber = (PhoneNumber)iterator2.next();
//        if(hashmap.containsKey(phonenumber))
//        {
//            ((ArrayList)hashmap.get(phonenumber)).add(contact);
//        } else
//        {
//            arraylist = new ArrayList();
//            arraylist.add(contact);
//            hashmap.put(phonenumber, arraylist);
//        }
//          goto _L9
//        if(((ArrayList)iterator4.next()).size() < size()) goto _L11; else goto _L10
//_L10:
//        flag = true;
//          goto _L7
//        if(((ArrayList)iterator5.next()).size() < size()) goto _L13; else goto _L12
//_L12:
//        flag = true;
//          goto _L7
        boolean flag = false;
        while(iterator.hasNext()) {
          Contact contact1 = (Contact)iterator.next();
          if(contact1.getData().getName().equals(name) && Arrays.equals(abyte0, contact1.getData().getPhoto())) {//goto _L6; else goto _L5
        	  flag = false;
        	  return false;
          }
        }
        
      HashMap hashmap;
      HashMap hashmap1;
      Iterator iterator1;
      hashmap = new HashMap();
      hashmap1 = new HashMap();
      iterator1 = mContacts.iterator();
      
		while (iterator1.hasNext()) {
			Contact contact;
			Iterator iterator2;
			contact = (Contact) iterator1.next();
			iterator2 = contact.getData().getPhoneNumbers().iterator();

			while (iterator2.hasNext()) {
				PhoneNumber phonenumber = (PhoneNumber) iterator2.next();
				if (hashmap.containsKey(phonenumber)) {
					((ArrayList) hashmap.get(phonenumber)).add(contact);
				} else {
					ArrayList arraylist = new ArrayList();
					arraylist.add(contact);
					hashmap.put(phonenumber, arraylist);
				}
			}
			Iterator iterator3 = contact.getData().getEmailAddresses()
					.iterator();
			while (iterator3.hasNext()) {
				EmailAddress emailaddress = (EmailAddress) iterator3.next();
				// PhoneNumber phonenumber;
				ArrayList arraylist;
				if (hashmap1.containsKey(emailaddress)) {
					((ArrayList) hashmap1.get(emailaddress)).add(contact);
				} else {
					ArrayList arraylist1 = new ArrayList();
					arraylist1.add(contact);
					hashmap1.put(emailaddress, arraylist1);
				}
			}

		}
      
		 Iterator iterator4 = hashmap.values().iterator();
 		 
		 while(iterator4.hasNext()) {
			 if(((ArrayList)iterator4.next()).size() < size()){// goto _L11; else goto _L10
				 
			 } else {
				 flag = true;
				 return flag;
			 }
		 }
		 
		 Iterator iterator5 = hashmap1.values().iterator();
		 while(iterator5.hasNext()) {
			 if(((ArrayList)iterator5.next()).size() < size()) {
				 
			 }else {
				 flag = true;
				 return flag;
			 }
			 
		 }
		 
		 
        return false;
    }

    public int size()
    {
        return mContacts.size();
    }

    private static final String TAG = ContactMatch.class.getSimpleName();
    private List mContacts;

}
