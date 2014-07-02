// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package se.tactel.contactcleanapp.controller;

import android.app.*;
import android.content.*;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.*;
import android.view.inputmethod.InputMethodManager;
import android.widget.*;
import java.io.ByteArrayOutputStream;
import java.util.*;
import se.tactel.contactcleanapp.cleanapp.Application;
import se.tactel.contactcleanapp.cleanapp.CleanAppManager;
import se.tactel.contactcleanapp.cleanapp.match.ContactMatch;
import se.tactel.contactcleanapp.model.*;
import se.tactel.contactcleanapp.utils.DataUtils;
import se.tactel.contactcleanapp.view.CheckboxView;
import se.tactel.contactcleanapp.view.FieldsView;

// Referenced classes of package se.tactel.contactcleanapp.controller:
//            ContactSelectionController, PhotoPickingActivity

public class MergeActivity extends Activity
{
    private class ListTypeAdapter extends ArrayAdapter
    {

        public View getView(int i, View view, ViewGroup viewgroup)
        {
            TextView textview;
            if(view == null)
                textview = (TextView)mInflater.inflate(0x1090003, null);
            else
                textview = (TextView)view;
            textview.setText(mList[i].getLabel());
            textview.setTextColor(0xff000000);
            return textview;
        }

        private static final int LIST_LAYOUT = 0x1090003;
        private LayoutInflater mInflater;
        private TypeItem mList[];
//        final MergeActivity this$0;

        public ListTypeAdapter(Context context, TypeItem atypeitem[])
        {
//            this$0 = MergeActivity.this;
            super(context, 0x1090003, atypeitem);
            mList = atypeitem;
            mInflater = LayoutInflater.from(context);
        }
    }

    private class MergeTask extends AsyncTask
    {

        protected Boolean doInBackground(Integer ainteger[])
        {
            ContactData contactdata = saveData();
            mManager.merge(getApplicationContext(), mContacts, contactdata);
            return Boolean.valueOf(true);
        }

        protected Object doInBackground(Object aobj[])
        {
            return doInBackground((Integer[])aobj);
        }

        protected void onPostExecute(Boolean boolean1)
        {
            if(mDialog != null)
                mDialog.dismiss();
            next();
        }

        protected void onPostExecute(Object obj)
        {
            onPostExecute((Boolean)obj);
        }

        protected void onPreExecute()
        {
            mDialog = new ProgressDialog(MergeActivity.this);
            mDialog.setMessage(getString(0x7f05000e));
            mDialog.setCancelable(false);
            mDialog.show();
        }

        private List mContacts;
        private ProgressDialog mDialog;
//        final MergeActivity this$0;

        public MergeTask(List list)
        {
//            this$0 = MergeActivity.this;
            super();
            mContacts = list;
        }
    }

    private class PhotoLoader extends AsyncTask
    {

        protected Boolean doInBackground(Integer ainteger[])
        {
            (new ContactModelImpl(mContext)).getPhotosForContacts(mContacts);
            byte abyte0[] = (byte[])null;
            int i = 0;
            Iterator iterator = mContacts.iterator();
            do
            {
                do
                {
                    if(!iterator.hasNext())
                    {
//                        byte abyte1[];
                        Boolean boolean1;
                        if(abyte0 != null)
                        {
                            updatePhoto(abyte0, i);
                            boolean1 = Boolean.valueOf(true);
                        } else
                        {
                            boolean1 = Boolean.valueOf(false);
                        }
                        return boolean1;
                    }
//                    abyte1 = ((Contact)iterator.next()).getData().getPhoto();
//                } while(abyte1 == null || abyte1.length <= 0);
                    abyte0 = ((Contact)iterator.next()).getData().getPhoto();
                } while(abyte0 == null || abyte0.length <= 0);                    
//                if(abyte0 == null)
//                    abyte0 = abyte1;
                i++;
            } while(true);
        }

        protected Object doInBackground(Object aobj[])
        {
            return doInBackground((Integer[])aobj);
        }

        private List mContacts;
        private Context mContext;
//        final MergeActivity this$0;

        public PhotoLoader(Context context, List list)
        {
//            this$0 = MergeActivity.this;
            super();
            mContext = context;
            mContacts = list;
        }
    }

    private class RefreshTask extends AsyncTask
    {

        protected Object doInBackground(Object aobj[])
        {
            return doInBackground((Void[])aobj);
        }

        protected ContactMatch doInBackground(Void avoid[])
        {
            return mManager.refreshData(getApplicationContext());
        }

        protected void onPostExecute(Object obj)
        {
            onPostExecute((ContactMatch)obj);
        }

        protected void onPostExecute(ContactMatch contactmatch)
        {
            if(mDialog != null)
                mDialog.dismiss();
            if(contactmatch == null)
                next();
            else
                showDuplicateCandidate(mManager.getCurrentMatch());
        }

        protected void onPreExecute()
        {
            mDialog = new ProgressDialog(MergeActivity.this);
            mDialog.setMessage(getString(0x7f05000f));
            mDialog.setCancelable(false);
            mDialog.show();
        }

        private ProgressDialog mDialog;
//        final MergeActivity this$0;

        public RefreshTask()
        {
//            this$0 = MergeActivity.this;
            super();
        }
    }

    private class TypeItem
    {

        public String getLabel()
        {
            return label;
        }

        public int getType()
        {
            return type;
        }

        private String label;
//        final MergeActivity this$0;
        private int type;

        public TypeItem(int i, String s)
        {
//            this$0 = MergeActivity.this;
            super();
            type = i;
            label = s;
        }
    }


    public MergeActivity()
    {
    }

    private void addPhoto()
    {
        startActivityForResult(getPhotoPickIntent(), 1);
//_L1:
//        return;
//        ActivityNotFoundException activitynotfoundexception;
//        activitynotfoundexception;
//        Toast.makeText(this, "No photo picker found on phone", 1).show();
//          goto _L1
    }

    private Intent getPhotoPickIntent()
    {
        Intent intent = new Intent("android.intent.action.GET_CONTENT", null);
        intent.setType("image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 96);
        intent.putExtra("outputY", 96);
        intent.putExtra("return-data", true);
        return intent;
    }

    private void hideKeyboard()
    {
        View view = getCurrentFocus();
        if(view != null)
            ((InputMethodManager)getSystemService("input_method")).hideSoftInputFromWindow(view.getWindowToken(), 2);
    }

    private void loadPhoto(List list)
    {
        (new PhotoLoader(this, list)).execute(new Integer[0]);
    }

    private TypeItem[] loadTypes(int i)
    {
        ArrayList arraylist;
        int k;
        arraylist = new ArrayList();
//        if(i != 3)
//            break MISSING_BLOCK_LABEL_72;
//        k = 0;
//_L3:
//        if(k <= 4) goto _L2; else goto _L1
//_L1:
//        return (TypeItem[])arraylist.toArray(new TypeItem[arraylist.size()]);
//_L2:
//        arraylist.add(new TypeItem(k, (String)android.provider.ContactsContract.CommonDataKinds.Email.getTypeLabel(getResources(), k, "")));
//        k++;
//          goto _L3
//        if(i == 2)
//        {
//            int j = 0;
//            while(j <= 19) 
//            {
//                arraylist.add(new TypeItem(j, (String)android.provider.ContactsContract.CommonDataKinds.Phone.getTypeLabel(getResources(), j, "")));
//                j++;
//            }
//        }
//          goto _L1
        
        if(i != 3) {
          if(i == 2)
          {
              int j = 0;
              while(j <= 19) 
              {
                  arraylist.add(new TypeItem(j, (String)android.provider.ContactsContract.CommonDataKinds.Phone.getTypeLabel(getResources(), j, "")));
                  j++;
              }
          }
        }else {
        	for(k=0;k<=4;k++) {
        		arraylist.add(new TypeItem(k, (String)android.provider.ContactsContract.CommonDataKinds.Email.getTypeLabel(getResources(), k, "")));
        	}
        }
        
        return (TypeItem[])arraylist.toArray(new TypeItem[arraylist.size()]);
        
    }

    private void next()
    {
        Iterator iterator = mSelectionController.getAllContacts().iterator();
        do
        {
            if(!iterator.hasNext())
            {
                mResetButton.setEnabled(false);
                mMergeButton.setEnabled(true);
                ContactMatch contactmatch = mManager.getNextMatch();
                if(contactmatch != null && mManager.isMerged(contactmatch.getContacts()))
                    (new RefreshTask()).execute(new Void[0]);
                else
                    showDuplicateCandidate(contactmatch);
                return;
            }
            ((Contact)iterator.next()).getData().setPhoto(null);
        } while(true);
    }

    private void photoPicked()
    {
        Intent intent = new Intent(this, PhotoPickingActivity.class);
        Bundle bundle = new Bundle();
        ArrayList arraylist = mSelectionController.getSelectedContactsWithPhoto();
        long al[] = new long[arraylist.size()];
        int i = 0;
        do
        {
            if(i >= al.length)
            {
                bundle.putLongArray("CONTACT_LIST_KEY", al);
                intent.putExtras(bundle);
                startActivityForResult(intent, 0);
                return;
            }
            al[i] = ((Long)arraylist.get(i)).longValue();
            i++;
        } while(true);
    }

    private ContactData saveData()
    {
        return mContactFields.getDisplayedData();
    }

    private void showDuplicateCandidate(ContactMatch contactmatch)
    {
        hideKeyboard();
        if(contactmatch == null)
        {
            setResult(-1);
            finish();
        } else
        {
            List list = contactmatch.getContacts();
            mSelectionController.setContacts(mManager.getAggregatedContacts(list));
            mCurrentMatchTextView.setText((new StringBuilder(String.valueOf(1 + mManager.getCurrentPos()))).append("/").append(mManager.getTotalNbrMatches()).toString());
            mAccountIcons = DataUtils.getAccountIcons(list, this, mAccountIcons);
            mContactNames.setContacts(mSelectionController.getAllAggregatedContacts(), mAccountIcons);
            updateFields();
        }
    }

    private void updateFields()
    {
        mContactFields.updateFieldsView();
        loadPhoto(mSelectionController.getSelectedContacts());
    }

    private void updateItem(final int index, final int type, final String label)
    {
        runOnUiThread(new Runnable() {

            public void run()
            {
                mContactFields.updateItem(index, type, label);
            }

//            final MergeActivity this$0;
//            private final int val$index;
//            private final String val$label;
//            private final int val$type;

            
            {
//                this$0 = MergeActivity.this;
//                index = i;
//                type = j;
//                label = s;
//                super();
            }
        }
);
    }

    private void updatePhoto(final byte photo[])
    {
        runOnUiThread(new Runnable() {

            public void run()
            {
                mContactFields.setPhoto(photo);
            }

//            final MergeActivity this$0;
//            private final byte val$photo[];
//
//            
//            {
//                this$0 = MergeActivity.this;
//                photo = abyte0;
//                super();
//            }
        }
);
    }

    private void updatePhoto(final byte photo[], final int nbrPhotos)
    {
        runOnUiThread(new Runnable() {

            public void run()
            {
                mContactFields.setPhoto(photo, nbrPhotos);
            }

//            final MergeActivity this$0;
//            private final int val$nbrPhotos;
//            private final byte val$photo[];
//
//            
//            {
//                this$0 = MergeActivity.this;
//                photo = abyte0;
//                nbrPhotos = i;
//                super();
//            }
        }
);
    }

    protected void onActivityResult(int i, int j, Intent intent)
    {
//        if(i != 0 || j != -1)
//            break MISSING_BLOCK_LABEL_129;
//        if(intent == null || intent.getLongExtra("CONTACT_PHOTO_SELECTED", -1L) <= -1L) goto _L2; else goto _L1
//_L1:
//        long l;
//        Contact contact;
//        Iterator iterator;
//        l = intent.getLongExtra("CONTACT_PHOTO_SELECTED", -1L);
//        contact = null;
//        iterator = mManager.getCurrentMatch().getContacts().iterator();
//_L6:
//        if(iterator.hasNext()) goto _L4; else goto _L3
//_L3:
//        if(contact != null)
//            updatePhoto(contact.getData().getPhoto());
//_L7:
//        return;
//_L4:
//        Contact contact1 = (Contact)iterator.next();
//        if(contact1.getRawId() != l) goto _L6; else goto _L5
//_L5:
//        contact = contact1;
//          goto _L3
//_L2:
//        updatePhoto(null);
//          goto _L7
//        if(i == 1 && j == -1 && intent != null)
//        {
//            Bitmap bitmap = (Bitmap)intent.getParcelableExtra("data");
//            if(bitmap != null)
//            {
//                ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
//                bitmap.compress(android.graphics.Bitmap.CompressFormat.PNG, 100, bytearrayoutputstream);
//                updatePhoto(bytearrayoutputstream.toByteArray());
//            }
//        }
//          goto _L7
    	
    	 if(i != 0 || j != -1) {
//    		 return;
    	       if(i == 1 && j == -1 && intent != null)
    	       {
    	           Bitmap bitmap = (Bitmap)intent.getParcelableExtra("data");
    	           if(bitmap != null)
    	           {
    	               ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
    	               bitmap.compress(android.graphics.Bitmap.CompressFormat.PNG, 100, bytearrayoutputstream);
    	               updatePhoto(bytearrayoutputstream.toByteArray());
    	           }
    	       }
    	 }
    	 
    	 else if(intent == null || intent.getLongExtra("CONTACT_PHOTO_SELECTED", -1L) <= -1L) {//goto _L2; else goto _L1
    		 updatePhoto(null);
    	 } else {
           long l;
           Contact contact;
           Iterator iterator;
           l = intent.getLongExtra("CONTACT_PHOTO_SELECTED", -1L);
           contact = null;
           iterator = mManager.getCurrentMatch().getContacts().iterator();
           
           while(iterator.hasNext()) {
             Contact contact1 = (Contact)iterator.next();
             if(contact1.getRawId() != l) {//goto _L6; else goto _L5
            	 
             } else {
            	 contact = contact1;
               if(contact != null)
            	   updatePhoto(contact.getData().getPhoto());
             }
           }
           
    	 }
    	 

    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        Application.get().setRunning(TAG, true);
        getWindow().setSoftInputMode(3);
        setContentView(0x7f030002);
        mManager = CleanAppManager.get(this);
        mAccountIcons = new HashMap();
        mSelectionController = new ContactSelectionController();
        mCurrentMatchTextView = (TextView)findViewById(0x7f07001c);
        mContactNames = (CheckboxView)findViewById(0x7f070028);
        mContactFields = (FieldsView)findViewById(0x7f07002b);
        mContactFields.setController(mSelectionController);
        mContactFields.setListener(new se.tactel.contactcleanapp.view.FieldsView.FieldsViewListener() {

            public void onEdit()
            {
                mResetButton.setEnabled(true);
            }

            public void onPhotoAdd()
            {
                addPhoto();
            }

            public void onPhotoPicked()
            {
                photoPicked();
            }

            public void onTypePressed(int i, int j)
            {
                Bundle bundle1 = new Bundle();
                bundle1.putInt("index", i);
                bundle1.putInt("category", j);
                showDialog(1, bundle1);
            }

//            final MergeActivity this$0;
//
//            
//            {
//                this$0 = MergeActivity.this;
//                super();
//            }
        }
);
        mMergeButton = (Button)findViewById(0x7f07001e);
        mMergeButton.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                (new MergeTask(mSelectionController.getSelectedContacts())).execute(new Integer[0]);
            }

//            final MergeActivity this$0;
//
//            
//            {
//                this$0 = MergeActivity.this;
//                super();
//            }
        }
);
        ((Button)findViewById(0x7f070021)).setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                mManager.storeSkippedMatch(mManager.getCurrentMatch().getContacts());
                next();
            }

//            final MergeActivity this$0;
//
//            
//            {
//                this$0 = MergeActivity.this;
//                super();
//            }
        }
);
        mResetButton = (Button)findViewById(0x7f070024);
        mResetButton.setEnabled(false);
        mResetButton.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                boolean flag = true;
                showDuplicateCandidate(mManager.getCurrentMatch());
                mResetButton.setEnabled(false);
                Button button = mMergeButton;
//                if(mSelectionController.getSelectedAggregatedContacts().size() <= flag)
//                    flag = false;
                if(mSelectionController.getSelectedAggregatedContacts().size() <= 1)
                    flag = false;                
                button.setEnabled(flag);
            }

//            final MergeActivity this$0;
//
//            
//            {
//                this$0 = MergeActivity.this;
//                super();
//            }
        }
);
        mContactNames.setCheckboxViewListener(new se.tactel.contactcleanapp.view.CheckboxView.CheckboxViewListener() {

            public void onCheckedChange(AggregatedContact aggregatedcontact, boolean flag)
            {
                boolean flag1 = true;
                Button button;
                if(flag)
                    mSelectionController.selectContact(aggregatedcontact);
                else
                    mSelectionController.deselectContact(aggregatedcontact);
                button = mMergeButton;
//                if(mSelectionController.getSelectedAggregatedContacts().size() <= flag1)
//                    flag1 = false;
                if(mSelectionController.getSelectedAggregatedContacts().size() <= 1)
                    flag1 = false;                
                button.setEnabled(flag1);
                updateFields();
            }

            public void onEdit()
            {
                mResetButton.setEnabled(true);
            }

//            final MergeActivity this$0;
//
//            
//            {
//                this$0 = MergeActivity.this;
//                super();
//            }
        }
);
        showDuplicateCandidate(mManager.getCurrentMatch());
    }

    protected Dialog onCreateDialog(int i, Bundle bundle)
    {
        Object obj;
        if(i == 1 && bundle != null)
        {
            final int index = bundle.getInt("index");
            final ListTypeAdapter adapter = new ListTypeAdapter(this, loadTypes(bundle.getInt("category")));
            android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
            builder.setTitle(0x7f05000d);
            builder.setAdapter(adapter, new android.content.DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialoginterface, int j)
                {
                    updateItem(index, ((TypeItem)adapter.getItem(j)).getType(), ((TypeItem)adapter.getItem(j)).getLabel());
                    removeDialog(1);
                }

//                final MergeActivity this$0;
//                private final ListAdapter val$adapter;
//                private final int val$index;
//
//            
//            {
//                this$0 = MergeActivity.this;
//                index = i;
//                adapter = listadapter;
//                super();
//            }
            }
);
            builder.setOnCancelListener(new android.content.DialogInterface.OnCancelListener() {

                public void onCancel(DialogInterface dialoginterface)
                {
                    removeDialog(1);
                }

//                final MergeActivity this$0;
//
//            
//            {
//                this$0 = MergeActivity.this;
//                super();
//            }
            }
);
            obj = builder.create();
        } else
        {
            obj = super.onCreateDialog(i, bundle);
        }
        return ((Dialog) (obj));
    }

    protected void onDestroy()
    {
        Application.get().setRunning(TAG, false);
        super.onDestroy();
    }

    private static final int DIALOG_LIST = 1;
    private static final int REQUEST_PHOTO_PICK = 0;
    private static final int REQUEST_PHOTO_PICK_GALLERY = 1;
    private static final String TAG = MergeActivity.class.getSimpleName();
    private HashMap mAccountIcons;
    private FieldsView mContactFields;
    private CheckboxView mContactNames;
    private TextView mCurrentMatchTextView;
    private CleanAppManager mManager;
    private Button mMergeButton;
    private Button mResetButton;
    private ContactSelectionController mSelectionController;














}
