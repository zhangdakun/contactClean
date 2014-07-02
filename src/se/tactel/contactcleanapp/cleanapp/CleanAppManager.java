// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package se.tactel.contactcleanapp.cleanapp;

import android.content.Context;
import android.util.Log;
import java.util.*;
import se.tactel.contactcleanapp.cleanapp.match.ContactMatch;
import se.tactel.contactcleanapp.cleanapp.match.ContactMatcher;
import se.tactel.contactcleanapp.cleanapp.match.ContactMatcherImpl;
import se.tactel.contactcleanapp.cleanapp.merge.ContactMerger;
import se.tactel.contactcleanapp.cleanapp.merge.ContactMergerImpl;
import se.tactel.contactcleanapp.cleanapp.purge.Purger;
import se.tactel.contactcleanapp.cleanapp.purge.PurgerImpl;
import se.tactel.contactcleanapp.model.*;
import se.tactel.contactcleanapp.storage.FileStorage;
import se.tactel.contactcleanapp.storage.Storage;

// Referenced classes of package se.tactel.contactcleanapp.cleanapp:
//            CleanAppSessionAutoMerge, CleanAppSession, CleanAppSessionListener

public class CleanAppManager
{

    private CleanAppManager(Context context)
    {
        mContactModel = new ContactModelImpl(context);
    }

    public static CleanAppManager get(Context context)
    {
        if(sInstance == null)
            sInstance = new CleanAppManager(context);
        return sInstance;
    }

    private void purge(Context context, List list)
    {
        (new PurgerImpl(context, mContactModel)).purge(list);
        updateTotal();
    }

    private void reset()
    {
        mNbrAutoMerged = 0;
        mNbrUserMerged = 0;
        mContactMatches = null;
        mContactMatchPos = 0;
        mMergedIds = new HashSet();
        mSkippedIds = new ArrayList();
    }

    private void saveMergedIds(List list)
    {
        Iterator iterator = list.iterator();
        do
        {
            if(!iterator.hasNext())
                return;
            Contact contact = (Contact)iterator.next();
            mMergedIds.add(Long.valueOf(contact.getRawId()));
        } while(true);
    }

    private void updateTotal()
    {
        mNbrInContactBook = mContactModel.getAggregatedNbrContacts();
    }

    public List getAggregatedContacts(List list)
    {
        return mContactModel.getAggregatedContacts(list);
    }

    public ContactMatch getCurrentMatch()
    {
        ContactMatch contactmatch = null;
        if(mContactMatches != null && mContactMatchPos > -1 && mContactMatchPos < mContactMatches.size())
            contactmatch = (ContactMatch)mContactMatches.get(mContactMatchPos);
        return contactmatch;
    }

    public int getCurrentPos()
    {
        return mContactMatchPos;
    }

    public List getLastStoredSkipped(Context context)
    {
        return (new FileStorage(context)).getSkippedMatches();
    }

    public int getNbrAutoMerged()
    {
        return mNbrAutoMerged;
    }

    public int getNbrInContactBook()
    {
        return mNbrInContactBook;
    }

    public int getNbrTotalMerged()
    {
        return mNbrAutoMerged + mNbrUserMerged;
    }

    public int getNbrUserMerged()
    {
        return mNbrUserMerged;
    }

    public ContactMatch getNextMatch()
    {
        mContactMatchPos = 1 + mContactMatchPos;
        return getCurrentMatch();
    }

    public List getSearchResult()
    {
        return mContactMatches;
    }

    public int getTotalNbrMatches()
    {
        return mContactMatches.size();
    }

    public void goToFirst()
    {
        mContactMatchPos = 0;
    }

    public boolean isMerged(List list)
    {
        Iterator iterator = list.iterator();
//_L2:
//        boolean flag;
//        if(!iterator.hasNext())
//        {
//            flag = false;
//        } else
//        {
//            Contact contact = (Contact)iterator.next();
//            if(!mMergedIds.contains(Long.valueOf(contact.getRawId())))
//                continue; /* Loop/switch isn't completed */
//            flag = true;
//        }
//        return flag;
//        if(true) goto _L2; else goto _L1
//_L1:
        boolean flag = false;
        while(iterator.hasNext()) {
          Contact contact = (Contact)iterator.next();
          if(!mMergedIds.contains(Long.valueOf(contact.getRawId()))) {
//              continue; /* Loop/switch isn't completed */
	          flag = true;
	          break;
          }
        }
        
        return flag;
    }

    public void merge(Context context, List list, ContactData contactdata)
    {
        (new ContactMergerImpl(mContactModel)).merge(list, contactdata);
        saveMergedIds(list);
        mNbrUserMerged = 1 + mNbrUserMerged;
        purge(context, mContactModel.refreshData(list));
    }

    public ContactMatch refreshData(Context context)
    {
        ArrayList arraylist = mContactModel.refreshData(getCurrentMatch().getContacts());
        PurgerImpl purgerimpl = new PurgerImpl(context, mContactModel);
        purgerimpl.setPurgeListener(new se.tactel.contactcleanapp.cleanapp.purge.Purger.PurgeListener() {

            public void onError()
            {
            }

            public void onFinished()
            {
            }

            public void onProgress(int i)
            {
            }

            public void onPurged()
            {
                CleanAppManager cleanappmanager = CleanAppManager.this;
                cleanappmanager.mNbrAutoMerged = 1 + cleanappmanager.mNbrAutoMerged;
            }

            public void onStarted()
            {
            }

//            final CleanAppManager this$0;
//
//            
//            {
//                this$0 = CleanAppManager.this;
//                super();
//            }
        }
);
        purgerimpl.purge(arraylist);
        List list = (new ContactMatcherImpl(mContactModel)).getContactMatches(arraylist);
        ContactMatch contactmatch = null;
        Iterator iterator = list.iterator();
        do
        {
            ContactMatch contactmatch1;
            do
            {
                if(!iterator.hasNext())
                {
                    if(contactmatch != null)
                    {
                        mContactMatches.remove(mContactMatchPos);
                        mContactMatches.add(mContactMatchPos, contactmatch);
                    }
                    return contactmatch;
                }
                contactmatch1 = (ContactMatch)iterator.next();
            } while(contactmatch != null && contactmatch1.size() <= contactmatch.size());
            contactmatch = contactmatch1;
        } while(true);
    }

    public void startSearch(Context context, final CleanAppSessionListener listener)
    {
        reset();
        (new CleanAppSessionAutoMerge(mContactModel, new ContactMatcherImpl(mContactModel), new ContactMergerImpl(mContactModel), new PurgerImpl(context, mContactModel), new CleanAppSessionListener() {

            public void onFinished(List list)
            {
                updateTotal();
                mContactMatches = list;
                listener.onFinished(list);
            }

            public void onMerged()
            {
                CleanAppManager cleanappmanager = CleanAppManager.this;
                cleanappmanager.mNbrAutoMerged = 1 + cleanappmanager.mNbrAutoMerged;
            }

            public void onProgress(int i)
            {
                listener.onProgress(i);
            }

            public void onPurged()
            {
                CleanAppManager cleanappmanager = CleanAppManager.this;
                cleanappmanager.mNbrAutoMerged = 1 + cleanappmanager.mNbrAutoMerged;
            }

            public void onSessionAborted()
            {
                listener.onSessionAborted();
            }

            public void onStarted()
            {
                Log.d(CleanAppManager.TAG, "on started");
                listener.onStarted();
            }

//            final CleanAppManager this$0;
//            private final CleanAppSessionListener val$listener;
//
//            
//            {
//                this$0 = CleanAppManager.this;
//                listener = cleanappsessionlistener;
//                super();
//            }
        }
)).start();
    }

    public void storeAllSkipped(Context context)
    {
        if(mSkippedIds != null)
            (new FileStorage(context)).storeSkippedMatches(mSkippedIds);
    }

    public void storeSkippedMatch(List list)
    {
        if(list != null)
        {
label0:
            {
                ArrayList arraylist = new ArrayList();
                for(Iterator iterator = list.iterator(); iterator.hasNext(); arraylist.add(Long.valueOf(((Contact)iterator.next()).getRawId())))
                    break label0;

                if(!mSkippedIds.contains(arraylist))
                    mSkippedIds.add(arraylist);
            }
        }
    }

    private static final String TAG = CleanAppManager.class.getSimpleName();
    private static CleanAppManager sInstance;
    private int mContactMatchPos;
    private List mContactMatches;
    private ContactModelAPI mContactModel;
    private HashSet mMergedIds;
    private int mNbrAutoMerged;
    private int mNbrInContactBook;
    private int mNbrUserMerged;
    private List mSkippedIds;






}
