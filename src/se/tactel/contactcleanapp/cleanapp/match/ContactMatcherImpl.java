// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package se.tactel.contactcleanapp.cleanapp.match;

import android.text.TextUtils;
import java.util.*;
import se.tactel.contactcleanapp.model.*;

// Referenced classes of package se.tactel.contactcleanapp.cleanapp.match:
//            ContactMatcher, ContactMatch

public class ContactMatcherImpl
    implements ContactMatcher
{
    private class ContactsComparator
        implements Comparator
    {

        public int compare(Object obj, Object obj1)
        {
            return compare((Contact)obj, (Contact)obj1);
        }

        public int compare(Contact contact, Contact contact1)
        {
            return (int)(contact.getRawId() - contact1.getRawId());
        }

//        final ContactMatcherImpl this$0;
//
//        private ContactsComparator()
//        {
//            this$0 = ContactMatcherImpl.this;
//            super();
//        }

        ContactsComparator(ContactsComparator contactscomparator)
        {
//            this();
        }
    }


    public ContactMatcherImpl(ContactModelAPI contactmodelapi)
    {
        mContactModel = contactmodelapi;
    }

    private static boolean computeDistance(CharSequence charsequence, CharSequence charsequence1, int i)
    {
        boolean flag = false;
        if(charsequence.length() >= i && charsequence1.length() >= i)
        {
            int j = charsequence.length();
            int k = charsequence1.length();
            int l;
            int i1;
            int j1;
            if(j < k)
                l = j;
            else
                l = k;
            i1 = i;
            j1 = i + 1;
//            while(false) 
//            {
                while(j1 <= l && charsequence.charAt(j - j1) == charsequence1.charAt(k - j1)) 
                {
                    i1++;
                    j1++;
                }
                if((i1 * 20) / l >= 17)
                    flag = true;
//            }
        }
        return flag;
    }

    private void computeOnEmailAdresses(Contact contact, Map map)
    {
        Iterator iterator = contact.getData().getEmailAddresses().iterator();
        do
        {
            if(!iterator.hasNext())
                return;
            String s = ((EmailAddress)iterator.next()).getEmailAddress();
            if(map.containsKey(s))
            {
                ((Set)map.get(s)).add(contact);
            } else
            {
                HashSet hashset = new HashSet();
                hashset.add(contact);
                map.put(s, hashset);
            }
        } while(true);
    }

    private void computeOnFullName(Contact contact, Map map)
    {
        String s = contact.getData().getName().getFullName().toLowerCase();
        if(!TextUtils.isEmpty(s))
            if(map.containsKey(s))
            {
                ((Set)map.get(s)).add(contact);
            } else
            {
                HashSet hashset = new HashSet();
                hashset.add(contact);
                map.put(s, hashset);
            }
    }

    private void computeOnTelephoneNumbers(Contact contact, Map map)
    {
        Iterator iterator = contact.getData().getPhoneNumbers().iterator();
//label0:
        do
        {
            String s;
            do
            {
                if(!iterator.hasNext())
                    return;
                s = ((PhoneNumber)iterator.next()).getNumber().replaceAll("\\D+", "");
            } while(s.length() < 5);
            String s1 = s.substring(s.length() - 5);
            if(map.containsKey(s1))
            {
                Map map1 = (Map)map.get(s1);
                if(map1.containsKey(s))
                {
                    Set set = (Set)map1.get(s);
                    set.add(contact);
                    map1.put(s, set);
                    continue;
                }
                boolean flag = false;
                HashSet hashset1 = new HashSet();
                Iterator iterator1 = map1.keySet().iterator();
//                do
//                {
//                    if(!iterator1.hasNext())
//                    {
//                        if(flag)
//                        {
//                            hashset1.add(contact);
//                            map1.put(s, hashset1);
//                        }
//                        continue label0;
//                    }
//                    if(computeDistance(s, ((String)iterator1.next()).replaceAll("\\D+", ""), 5))
//                        flag = true;
//                } while(true);
                while(iterator1.hasNext()) {
                  if(computeDistance(s, ((String)iterator1.next()).replaceAll("\\D+", ""), 5)) {
                	  flag = true;
                  }
                }
	              if(flag)
	              {
	                  hashset1.add(contact);
	                  map1.put(s, hashset1);
	              }
	              
	              continue;
                //up to up
            }
            HashMap hashmap = new HashMap();
            HashSet hashset = new HashSet();
            hashset.add(contact);
            hashmap.put(s, hashset);
            map.put(s1, hashmap);
        } while(true);
    }

    private boolean isAdded(List list, List list1)
    {
        Iterator iterator = list.iterator();
//_L2:
//        boolean flag;
//        if(iterator.hasNext())
//            continue; /* Loop/switch isn't completed */
//        flag = false;
//_L3:
//        return flag;
//        if(!((ContactMatch)iterator.next()).getContacts().equals(list1)) goto _L2; else goto _L1
//_L1:
//        flag = true;
//          goto _L3
//        if(true) goto _L2; else goto _L4
//_L4:
        
        boolean flag = false;
        
        while(iterator.hasNext()) {
        	if(!((ContactMatch)iterator.next()).getContacts().equals(list1)) {
        		
        	}else {
        		flag = true;
        		break;
        	}
        }
        
        return flag;
    }

    private boolean isAggregated(List list)
    {
        boolean flag;
        if(!mContactModel.isSameSuperId(list) && !mContactModel.aggregationExists(list))
            flag = false;
        else
            flag = true;
        return flag;
    }

    public List getContactMatches(List list)
    {
		ArrayList arraylist;
		HashMap hashmap;
		HashMap hashmap1;
		HashMap hashmap2;
		int i;
		int j;
		Iterator iterator;
		if (mListener != null)
			mListener.onSearchStarted();
		arraylist = new ArrayList();
		hashmap = new HashMap();
		hashmap1 = new HashMap();
		hashmap2 = new HashMap();
		i = list.size();
		j = 0;
		iterator = list.iterator();
		// _L8:
		// if(iterator.hasNext()) goto _L2; else goto _L1
		// _L1:
		// int k;
		// int l;
		// Iterator iterator1;
		// k = hashmap1.size();
		// l = 0;
		// iterator1 = hashmap1.entrySet().iterator();
		// _L9:
		// if(iterator1.hasNext()) goto _L4; else goto _L3
		// _L3:
		// int i1;
		// int j1;
		// Iterator iterator3;
		// i1 = hashmap.size();
		// j1 = 0;
		// iterator3 = hashmap.entrySet().iterator();
		// _L10:
		// if(iterator3.hasNext()) goto _L6; else goto _L5
		// _L5:
		// int k1;
		// int l1;
		// Iterator iterator5;
		// k1 = hashmap2.size();
		// l1 = 0;
		// iterator5 = hashmap2.values().iterator();
		// _L13:
		// Map map;
		// Contact contact;
		// java.util.Map.Entry entry;
		// Set set;
		// ArrayList arraylist1;
		// Iterator iterator2;
		// ContactsComparator contactscomparator;
		// ContactMatch contactmatch;
		// java.util.Map.Entry entry1;
		// Set set1;
		// ArrayList arraylist2;
		// Iterator iterator4;
		// ContactsComparator contactscomparator1;
		// ContactMatch contactmatch1;
		// if(!iterator5.hasNext())
		// {
		// if(mListener != null)
		// mListener.onFinished();
		// return arraylist;
		// }
		// map = (Map)iterator5.next();
		// goto _L7
		// _L2:
		// contact = (Contact)iterator.next();
		// computeOnTelephoneNumbers(contact, hashmap2);
		// computeOnEmailAdresses(contact, hashmap);
		// computeOnFullName(contact, hashmap1);
		// j++;
		// if(mListener != null)
		// mListener.onProgress((j * 70) / i);
		// goto _L8
		// _L4:
		// label0:
		// {
		// entry = (java.util.Map.Entry)iterator1.next();
		// set = (Set)entry.getValue();
		// (String)entry.getKey();
		// if(set.size() < 10 && set.size() > 1)
		// {
		// arraylist1 = new ArrayList();
		// for(iterator2 = set.iterator(); iterator2.hasNext();
		// arraylist1.add((Contact)iterator2.next()))
		// break label0;
		//
		// contactscomparator = new ContactsComparator(null);
		// Collections.sort(arraylist1, contactscomparator);
		// if(!isAggregated(arraylist1) && !isAdded(arraylist, arraylist1))
		// {
		// contactmatch = new ContactMatch(arraylist1);
		// arraylist.add(contactmatch);
		// }
		// }
		// l++;
		// if(mListener != null)
		// mListener.onProgress(70 + (l * 10) / k);
		// }
		// goto _L9
		// _L6:
		// label1:
		// {
		// entry1 = (java.util.Map.Entry)iterator3.next();
		// set1 = (Set)entry1.getValue();
		// (String)entry1.getKey();
		// if(set1.size() < 10 && set1.size() > 1)
		// {
		// arraylist2 = new ArrayList();
		// for(iterator4 = set1.iterator(); iterator4.hasNext();
		// arraylist2.add((Contact)iterator4.next()))
		// break label1;
		//
		// contactscomparator1 = new ContactsComparator(null);
		// Collections.sort(arraylist2, contactscomparator1);
		// if(!isAggregated(arraylist2) && !isAdded(arraylist, arraylist2))
		// {
		// contactmatch1 = new ContactMatch(arraylist2);
		// arraylist.add(contactmatch1);
		// }
		// }
		// j1++;
		// if(mListener != null)
		// mListener.onProgress(80 + (j1 * 10) / i1);
		// }
		// goto _L10
		// _L7:
		// if((map.size() >= 10 || map.size() <= 1) && (map.size() != 1 ||
		// ((Set)map.values().iterator().next()).size() <= 1)) goto _L12; else
		// goto _L11
		// _L11:
		// HashSet hashset;
		// Iterator iterator6;
		// hashset = new HashSet();
		// iterator6 = map.values().iterator();
		// _L14:
		// if(iterator6.hasNext())
		// break MISSING_BLOCK_LABEL_878;
		// if(hashset.size() > 1)
		// {
		// ArrayList arraylist3 = new ArrayList(hashset);
		// ContactsComparator contactscomparator2 = new
		// ContactsComparator(null);
		// Collections.sort(arraylist3, contactscomparator2);
		// if(!isAggregated(arraylist3) && !isAdded(arraylist, arraylist3))
		// {
		// ContactMatch contactmatch2 = new ContactMatch(arraylist3);
		// arraylist.add(contactmatch2);
		// }
		// }
		// _L12:
		// l1++;
		// if(mListener != null)
		// mListener.onProgress(90 + (l1 * 10) / k1);
		// goto _L13
		// Iterator iterator7 = ((Set)iterator6.next()).iterator();
		// while(iterator7.hasNext())
		// hashset.add((Contact)iterator7.next());
		// goto _L14

		while (iterator.hasNext()) {
			Contact contact = (Contact) iterator.next();
			computeOnTelephoneNumbers(contact, hashmap2);
			computeOnEmailAdresses(contact, hashmap);
			computeOnFullName(contact, hashmap1);
			j++;
			if (mListener != null)
				mListener.onProgress((j * 70) / i);
		}

		int k;
		int l;
		Iterator iterator1;
		k = hashmap1.size();
		l = 0;
		iterator1 = hashmap1.entrySet().iterator();

		while (iterator1.hasNext()) {
			// _L4:
			// label0:
			// {
			java.util.Map.Entry entry = (java.util.Map.Entry) iterator1.next();
			Set set = (Set) entry.getValue();
			// (String)entry.getKey();
			if (set.size() < 10 && set.size() > 1) {
				ArrayList arraylist1 = new ArrayList();
				// for(iterator2 = set.iterator(); iterator2.hasNext();
				// arraylist1.add((Contact)iterator2.next()))
				// break label0;

				Iterator iterator2 = set.iterator();
				while (iterator2.hasNext()) {
					arraylist1.add((Contact) iterator2.next());
				}
				// up to up
				ContactsComparator contactscomparator = new ContactsComparator(
						null);
				Collections.sort(arraylist1, contactscomparator);
				if (!isAggregated(arraylist1)
						&& !isAdded(arraylist, arraylist1)) {
					ContactMatch contactmatch = new ContactMatch(arraylist1);
					arraylist.add(contactmatch);
				}
			}
			l++;
			if (mListener != null)
				mListener.onProgress(70 + (l * 10) / k);
			// }
		}

		int i1;
		int j1;
		Iterator iterator3;
		i1 = hashmap.size();
		j1 = 0;
		iterator3 = hashmap.entrySet().iterator();

		while (iterator3.hasNext()) {
			// label1:
			// {
			java.util.Map.Entry entry1 = (java.util.Map.Entry) iterator3.next();
			Set set1 = (Set) entry1.getValue();
			// (String)entry1.getKey();
			if (set1.size() < 10 && set1.size() > 1) {
				ArrayList arraylist2 = new ArrayList();
				for (Iterator iterator4 = set1.iterator(); iterator4.hasNext(); arraylist2
						.add((Contact) iterator4.next())) {
					// break label1;
				}

				ContactsComparator contactscomparator1 = new ContactsComparator(
						null);
				Collections.sort(arraylist2, contactscomparator1);
				if (!isAggregated(arraylist2)
						&& !isAdded(arraylist, arraylist2)) {
					ContactMatch contactmatch1 = new ContactMatch(arraylist2);
					arraylist.add(contactmatch1);
				}
			}
			j1++;
			if (mListener != null)
				mListener.onProgress(80 + (j1 * 10) / i1);
			// }
			// goto _L10
		}

		int k1;
		int l1;
		Iterator iterator5;
		k1 = hashmap2.size();
		l1 = 0;
		iterator5 = hashmap2.values().iterator();

		Map map;
		Contact contact;
		java.util.Map.Entry entry;
		Set set;
		ArrayList arraylist1;
		Iterator iterator2;
		ContactsComparator contactscomparator;
		ContactMatch contactmatch;
		java.util.Map.Entry entry1;
		Set set1;
		ArrayList arraylist2;
		Iterator iterator4;
		ContactsComparator contactscomparator1;
		ContactMatch contactmatch1;
		// if(!iterator5.hasNext())
		// {
		// if(mListener != null)
		// mListener.onFinished();
		// return arraylist;
		// }
		// map = (Map)iterator5.next();
		// goto _L7

		while (iterator5.hasNext()) {
			map = (Map) iterator5.next();
			if ((map.size() >= 10 || map.size() <= 1)
					&& (map.size() != 1 || ((Set) map.values().iterator()
							.next()).size() <= 1)) {// goto _L12; else goto _L11
				// _L12:
				l1++;
				if (mListener != null)
					mListener.onProgress(90 + (l1 * 10) / k1);
			} else {
				// _L11:
				HashSet hashset;
				Iterator iterator6;
				hashset = new HashSet();
				iterator6 = map.values().iterator();
				// _L14:
				while (iterator6.hasNext()) {
					Iterator iterator7 = ((Set) iterator6.next()).iterator();
					while (iterator7.hasNext())
						hashset.add((Contact) iterator7.next());
					// goto _L14
				}
				
				 if(hashset.size() > 1)
				 {
					 ArrayList arraylist3 = new ArrayList(hashset);
					 ContactsComparator contactscomparator2 = new
					 ContactsComparator(null);
					 Collections.sort(arraylist3, contactscomparator2);
					 if(!isAggregated(arraylist3) && !isAdded(arraylist, arraylist3))
					 {
						 ContactMatch contactmatch2 = new ContactMatch(arraylist3);
						 arraylist.add(contactmatch2);
					 }
				 }
			}
		}
		if (mListener != null) {
			mListener.onFinished();
		}
		
//		return (List)arraylist;
//		return arraylist2;
		return arraylist;
    }

    public void setContactMatcherListener(ContactMatcher.ContactMatcherListener contactmatcherlistener)
    {
        mListener = contactmatcherlistener;
    }

    public static final String DIGITS_PATTERN = "\\D+";
    private static final String TAG = ContactMatcherImpl.class.getSimpleName();
    private ContactModelAPI mContactModel;
    private ContactMatcher.ContactMatcherListener mListener;

}
