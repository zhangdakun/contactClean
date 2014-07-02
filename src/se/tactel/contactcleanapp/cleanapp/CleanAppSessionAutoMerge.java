// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package se.tactel.contactcleanapp.cleanapp;

import android.util.Log;
import java.util.*;
import se.tactel.contactcleanapp.cleanapp.match.ContactMatch;
import se.tactel.contactcleanapp.cleanapp.match.ContactMatcher;
import se.tactel.contactcleanapp.cleanapp.merge.ContactMerger;
import se.tactel.contactcleanapp.cleanapp.purge.Purger;
import se.tactel.contactcleanapp.model.*;

// Referenced classes of package se.tactel.contactcleanapp.cleanapp:
//            CleanAppSession, CleanAppSessionListener

public class CleanAppSessionAutoMerge
    implements CleanAppSession
{

    public CleanAppSessionAutoMerge(ContactModelAPI contactmodelapi, ContactMatcher contactmatcher, ContactMerger contactmerger, Purger purger, CleanAppSessionListener cleanappsessionlistener)
    {
        mContactModel = contactmodelapi;
        mContactMatcher = contactmatcher;
        mContactMerger = contactmerger;
        mContactPurger = purger;
        mListener = cleanappsessionlistener;
    }

    private void clearPhotos(List list)
    {
        Iterator iterator = list.iterator();
        do
        {
            if(!iterator.hasNext())
                return;
            ((Contact)iterator.next()).getData().setPhoto(null);
        } while(true);
    }

    private void deleteDuplicates(final int stepNbr)
    {
        if(!mSessionAborted)
        {
            mContactPurger.setPurgeListener(new se.tactel.contactcleanapp.cleanapp.purge.Purger.PurgeListener() {

                public void onError()
                {
                    abort();
                }

                public void onFinished()
                {
                    mSteps[stepNbr] = 100;
                    if(mListener != null)
                        mListener.onProgress(getTotalProgress());
                }

                public void onProgress(int i)
                {
                    mSteps[stepNbr] = i;
                    if(mListener != null)
                        mListener.onProgress(getTotalProgress());
                }

                public void onPurged()
                {
                    if(mListener != null)
                        mListener.onPurged();
                }

                public void onStarted()
                {
                    if(mListener != null)
                        mListener.onProgress(getTotalProgress());
                }

//                final CleanAppSessionAutoMerge this$0;
//                private final int val$stepNbr;
//
//            
//            {
//                this$0 = CleanAppSessionAutoMerge.this;
//                stepNbr = i;
//                super();
//            }
            }
);
            mContactPurger.purgeAll();
        }
    }

    private void findDuplicates(final int stepNbr)
    {
        if(!mSessionAborted)
        {
            mContactMatcher.setContactMatcherListener(new se.tactel.contactcleanapp.cleanapp.match.ContactMatcher.ContactMatcherListener() {

                public void onFinished()
                {
                    mSteps[stepNbr] = 100;
                    if(mListener != null)
                        mListener.onProgress(getTotalProgress());
                }

                public void onProgress(int i)
                {
                    mSteps[stepNbr] = i;
                    if(mListener != null)
                        mListener.onProgress(getTotalProgress());
                }

                public void onSearchStarted()
                {
                    if(mListener != null)
                        mListener.onProgress(getTotalProgress());
                }

//                final CleanAppSessionAutoMerge this$0;
//                private final int val$stepNbr;
//
//            
//            {
//                this$0 = CleanAppSessionAutoMerge.this;
//                stepNbr = i;
//                super();
//            }
            }
);
            mDuplicateCandidates = mContactMatcher.getContactMatches(mContactModel.getContactsWithMatchInfo());
        }
    }

    private int getTotalProgress()
    {
        int i = 0;
        int ai[] = mSteps;
        int j = ai.length;
        int k = 0;
        do
        {
            if(k >= j)
                return i / mSteps.length;
            i += ai[k];
            k++;
        } while(true);
    }

    private void mergeHardMatches(int i)
    {
//        if(mSessionAborted) goto _L2; else goto _L1
//_L1:
//        ArrayList arraylist;
//        Iterator iterator;
//        arraylist = new ArrayList();
//        iterator = mDuplicateCandidates.iterator();
//_L5:
//        if(iterator.hasNext()) goto _L4; else goto _L3
//_L3:
//        int j;
//        int k;
//        Iterator iterator1;
//        j = arraylist.size();
//        k = 0;
//        iterator1 = arraylist.iterator();
//_L6:
//        if(iterator1.hasNext())
//            break MISSING_BLOCK_LABEL_152;
//        mSteps[i] = 100;
//        if(mListener != null)
//            mListener.onProgress(getTotalProgress());
//_L2:
//        return;
//_L4:
//        ContactMatch contactmatch = (ContactMatch)iterator.next();
//        mContactModel.getPhotosForContacts(contactmatch.getContacts());
//        if(contactmatch.isHardMatch())
//        {
//            Log.d(TAG, "adding a hardmatch");
//            arraylist.add(contactmatch);
//        } else
//        {
//            clearPhotos(contactmatch.getContacts());
//        }
//          goto _L5
//        ContactMatch contactmatch1 = (ContactMatch)iterator1.next();
//        if(mDuplicateCandidates.remove(contactmatch1))
//            Log.d("CleanappSession", "removed a hardmatch");
//        else
//            Log.d("CleanappSession", "could not remove hardmatch");
//        mContactMerger.merge(contactmatch1.getContacts());
//        clearPhotos(contactmatch1.getContacts());
//        k++;
//        mSteps[i] = (k * 100) / j;
//        if(mListener != null)
//        {
//            mListener.onProgress(getTotalProgress());
//            mListener.onMerged();
//        }
//          goto _L6
    	
		if (mSessionAborted) {
			return;
		}

		ArrayList arraylist;
		Iterator iterator;
		arraylist = new ArrayList();
		iterator = mDuplicateCandidates.iterator();

		while (iterator.hasNext()) {
			ContactMatch contactmatch = (ContactMatch) iterator.next();
			mContactModel.getPhotosForContacts(contactmatch.getContacts());
			if (contactmatch.isHardMatch()) {
				Log.d(TAG, "adding a hardmatch");
				arraylist.add(contactmatch);
			} else {
				clearPhotos(contactmatch.getContacts());
			}
		}

		int j;
		int k;
		Iterator iterator1;
		j = arraylist.size();
		k = 0;
		iterator1 = arraylist.iterator();

		while (iterator1.hasNext()) {
			ContactMatch contactmatch1 = (ContactMatch) iterator1.next();
			if (mDuplicateCandidates.remove(contactmatch1))
				Log.d("CleanappSession", "removed a hardmatch");
			else
				Log.d("CleanappSession", "could not remove hardmatch");
			mContactMerger.merge(contactmatch1.getContacts());
			clearPhotos(contactmatch1.getContacts());
			k++;
			mSteps[i] = (k * 100) / j;
			if (mListener != null) {
				mListener.onProgress(getTotalProgress());
				mListener.onMerged();
			}
		}

	}

    private void sessionCompleted()
    {
        if(mListener != null)
            if(mSessionAborted)
                mListener.onSessionAborted();
            else
                mListener.onFinished(mDuplicateCandidates);
    }

    private void setNbrSteps(int i)
    {
        mSteps = new int[i];
        int j = 0;
        do
        {
            if(j >= i)
                return;
            mSteps[j] = 0;
            j++;
        } while(true);
    }

    public void abort()
    {
        Log.d(TAG, "session aborted");
        mSessionAborted = true;
    }

    public void start()
    {
        if(mListener != null)
            mListener.onStarted();
        mSessionAborted = false;
        setNbrSteps(5);
        deleteDuplicates(0);
        
     // leb for eben not use   
//        findDuplicates(1);
//        mergeHardMatches(2);
//        deleteDuplicates(3);
//        findDuplicates(4);
//        sessionCompleted();
        
        
    }

    private static final String TAG = CleanAppSessionAutoMerge.class.getSimpleName();
    private ContactMatcher mContactMatcher;
    private ContactMerger mContactMerger;
    private ContactModelAPI mContactModel;
    private Purger mContactPurger;
    private List mDuplicateCandidates;
    private CleanAppSessionListener mListener;
    private boolean mSessionAborted;
    private int mSteps[];




}
