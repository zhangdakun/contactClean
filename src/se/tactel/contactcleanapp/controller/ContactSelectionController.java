// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package se.tactel.contactcleanapp.controller;

import java.util.*;
import se.tactel.contactcleanapp.model.*;

public class ContactSelectionController
{

    public ContactSelectionController()
    {
        mSelectedContacts = new ArrayList();
        mDeselectedContacts = new ArrayList();
        mSuggestedNumbers = new ArrayList();
        mSuggestedEmails = new HashMap();
    }

    private void updateSuggestions()
    {
        Iterator iterator;
        mSuggestedFirstName = "";
        mSuggestedLastName = "";
        mSuggestedMiddleName = "";
        mSuggestedNumbers.clear();
        mSuggestedEmails.clear();
        iterator = mSelectedContacts.iterator();
//_L2:
//        Iterator iterator1;
//        if(!iterator.hasNext())
//            return;
//        iterator1 = ((AggregatedContact)iterator.next()).getContacts().iterator();
//_L3:
//        if(!iterator1.hasNext()) goto _L2; else goto _L1
//_L1:
//        ArrayList arraylist1;
//        Iterator iterator2;
//        Contact contact = (Contact)iterator1.next();
//        String s = contact.getData().getName().getGivenName();
//        String s1 = contact.getData().getName().getFamilyName();
//        String s2 = contact.getData().getName().getMiddleName();
//        ArrayList arraylist = contact.getData().getPhoneNumbers();
//        arraylist1 = contact.getData().getEmailAddresses();
//        if(s.length() > mSuggestedFirstName.length())
//            mSuggestedFirstName = s;
//        if(s1.length() > mSuggestedLastName.length())
//            mSuggestedLastName = s1;
//        if(s2.length() > mSuggestedMiddleName.length())
//            mSuggestedMiddleName = s2;
//        iterator2 = arraylist.iterator();
//_L4:
//label0:
//        {
//            if(iterator2.hasNext())
//                break label0;
//            Iterator iterator3 = arraylist1.iterator();
//            while(iterator3.hasNext()) 
//            {
//                EmailAddress emailaddress = (EmailAddress)iterator3.next();
//                if(!mSuggestedEmails.containsKey(emailaddress.getEmailAddress()))
//                    mSuggestedEmails.put(emailaddress.getEmailAddress(), emailaddress);
//            }
//        }
//          goto _L3
//          goto _L2
//        PhoneNumber phonenumber = (PhoneNumber)iterator2.next();
//        if(mSuggestedNumbers.contains(phonenumber))
//        {
//            int i = mSuggestedNumbers.indexOf(phonenumber);
//            PhoneNumber phonenumber1 = (PhoneNumber)mSuggestedNumbers.get(i);
//            if(phonenumber.getNumber().length() > phonenumber1.getNumber().length())
//            {
//                mSuggestedNumbers.remove(phonenumber1);
//                mSuggestedNumbers.add(phonenumber);
//            }
//        } else
//        {
//            mSuggestedNumbers.add(phonenumber);
//        }
//          goto _L4
		Iterator iterator1;
		while (iterator.hasNext()) {
			iterator1 = ((AggregatedContact) iterator.next()).getContacts()
					.iterator();

			while (iterator1.hasNext()) {
				ArrayList arraylist1;
				Iterator iterator2;
				Contact contact = (Contact) iterator1.next();
				String s = contact.getData().getName().getGivenName();
				String s1 = contact.getData().getName().getFamilyName();
				String s2 = contact.getData().getName().getMiddleName();
				ArrayList arraylist = contact.getData().getPhoneNumbers();
				arraylist1 = contact.getData().getEmailAddresses();
				if (s.length() > mSuggestedFirstName.length())
					mSuggestedFirstName = s;
				if (s1.length() > mSuggestedLastName.length())
					mSuggestedLastName = s1;
				if (s2.length() > mSuggestedMiddleName.length())
					mSuggestedMiddleName = s2;
				iterator2 = arraylist.iterator();
				while (iterator2.hasNext()) {
					PhoneNumber phonenumber = (PhoneNumber) iterator2.next();
					if (mSuggestedNumbers.contains(phonenumber)) {
						int i = mSuggestedNumbers.indexOf(phonenumber);
						PhoneNumber phonenumber1 = (PhoneNumber) mSuggestedNumbers
								.get(i);
						if (phonenumber.getNumber().length() > phonenumber1
								.getNumber().length()) {
							mSuggestedNumbers.remove(phonenumber1);
							mSuggestedNumbers.add(phonenumber);
						}
					} else {
						mSuggestedNumbers.add(phonenumber);
					}
				}

				Iterator iterator3 = arraylist1.iterator();

				while (iterator3.hasNext()) {
					EmailAddress emailaddress = (EmailAddress) iterator3.next();
					if (!mSuggestedEmails.containsKey(emailaddress
							.getEmailAddress()))
						mSuggestedEmails.put(emailaddress.getEmailAddress(),
								emailaddress);
				}
			}
		}
	}

    public void deselectContact(AggregatedContact aggregatedcontact)
    {
        if(mSelectedContacts.contains(aggregatedcontact))
        {
            int i = mSelectedContacts.indexOf(aggregatedcontact);
            mDeselectedContacts.add((AggregatedContact)mSelectedContacts.remove(i));
            updateSuggestions();
        }
    }

    public ArrayList getAllAggregatedContacts()
    {
        ArrayList arraylist = new ArrayList(mSelectedContacts);
        arraylist.addAll(mDeselectedContacts);
        return arraylist;
    }

    public ArrayList getAllContacts()
    {
        ArrayList arraylist = new ArrayList(getSelectedContacts());
        arraylist.addAll(getDeselectedContacts());
        return arraylist;
    }

    public ArrayList getDeselectedContacts()
    {
        ArrayList arraylist = new ArrayList();
        Iterator iterator = mDeselectedContacts.iterator();
        do
        {
            if(!iterator.hasNext())
                return arraylist;
            Iterator iterator1 = ((AggregatedContact)iterator.next()).getContacts().iterator();
            while(iterator1.hasNext()) 
                arraylist.add((Contact)iterator1.next());
        } while(true);
    }

    public ArrayList getSelectedAggregatedContacts()
    {
        return mSelectedContacts;
    }

    public ArrayList getSelectedContacts()
    {
        ArrayList arraylist = new ArrayList();
        Iterator iterator = mSelectedContacts.iterator();
        do
        {
            if(!iterator.hasNext())
                return arraylist;
            Iterator iterator1 = ((AggregatedContact)iterator.next()).getContacts().iterator();
            while(iterator1.hasNext()) 
                arraylist.add((Contact)iterator1.next());
        } while(true);
    }

    public ArrayList getSelectedContactsWithPhoto()
    {
        ArrayList arraylist = new ArrayList();
        Iterator iterator = getSelectedContacts().iterator();
        do
        {
            Contact contact;
            do
            {
                if(!iterator.hasNext())
                    return arraylist;
                contact = (Contact)iterator.next();
            } while(contact.getData().getPhoto() == null);
            arraylist.add(Long.valueOf(contact.getRawId()));
        } while(true);
    }

    public Collection getSuggestedEmailAddresses()
    {
        return mSuggestedEmails.values();
    }

    public String getSuggestedFirstName()
    {
        return mSuggestedFirstName;
    }

    public String getSuggestedLastName()
    {
        return mSuggestedLastName;
    }

    public String getSuggestedMiddleName()
    {
        return mSuggestedMiddleName;
    }

    public ArrayList getSuggestedPhoneNumbers()
    {
        return mSuggestedNumbers;
    }

    public void selectContact(AggregatedContact aggregatedcontact)
    {
        if(mDeselectedContacts.contains(aggregatedcontact))
        {
            int i = mDeselectedContacts.indexOf(aggregatedcontact);
            mSelectedContacts.add((AggregatedContact)mDeselectedContacts.remove(i));
            updateSuggestions();
        }
    }

    public void setContacts(List list)
    {
        mSelectedContacts.clear();
        mDeselectedContacts.clear();
        mSuggestedNumbers.clear();
        mSuggestedEmails.clear();
        mSelectedContacts.addAll(list);
        updateSuggestions();
    }

    private ArrayList mDeselectedContacts;
    private ArrayList mSelectedContacts;
    private HashMap mSuggestedEmails;
    private String mSuggestedFirstName;
    private String mSuggestedLastName;
    private String mSuggestedMiddleName;
    private ArrayList mSuggestedNumbers;
}
