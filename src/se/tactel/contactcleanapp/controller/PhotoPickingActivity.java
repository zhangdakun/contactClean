// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package se.tactel.contactcleanapp.controller;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import java.util.*;
import se.tactel.contactcleanapp.cleanapp.CleanAppManager;
import se.tactel.contactcleanapp.cleanapp.match.ContactMatch;
import se.tactel.contactcleanapp.model.Contact;
import se.tactel.contactcleanapp.model.ContactData;

public class PhotoPickingActivity extends Activity
{
    private class ImageAdapter extends BaseAdapter
    {

        public int getCount()
        {
            return mContacts.size();
        }

        public Object getItem(int i)
        {
            return mContacts.get(i);
        }

        public long getItemId(int i)
        {
            return 0L;
        }

        public View getView(int i, View view, ViewGroup viewgroup)
        {
            ImageView imageview;
            byte abyte0[];
            if(view == null)
            {
                imageview = new ImageView(mContext);
                int j = getResources().getDimensionPixelSize(0x7f040002);
                imageview.setLayoutParams(new android.widget.AbsListView.LayoutParams(j, j));
                imageview.setScaleType(android.widget.ImageView.ScaleType.CENTER_CROP);
            } else
            {
                imageview = (ImageView)view;
            }
            abyte0 = ((Contact)mContacts.get(i)).getData().getPhoto();
            if(abyte0 != null && abyte0.length > 0)
                imageview.setImageBitmap(BitmapFactory.decodeByteArray(abyte0, 0, abyte0.length));
            else
                imageview.setImageResource(0x7f02001d);
            return imageview;
        }

        private List mContacts;
        private Context mContext;
//        final PhotoPickingActivity this$0;

        public ImageAdapter(Context context, List list)
        {
//            this$0 = PhotoPickingActivity.this;
            super();
            mContext = context;
            mContacts = list;
        }
    }


    public PhotoPickingActivity()
    {
    }

    protected void onCreate(Bundle bundle)
    {
        Bundle bundle1;
        super.onCreate(bundle);
        setContentView(0x7f030004);
        bundle1 = getIntent().getExtras();
//        if(bundle1 == null || !bundle1.containsKey("CONTACT_LIST_KEY")) goto _L2; else goto _L1
//_L1:
//        long al[];
//        GridView gridview;
//        ArrayList arraylist;
//        Iterator iterator;
//        al = bundle1.getLongArray("CONTACT_LIST_KEY");
//        gridview = (GridView)findViewById(0x7f070031);
//        CleanAppManager cleanappmanager = CleanAppManager.get(this);
//        arraylist = new ArrayList();
//        iterator = cleanappmanager.getCurrentMatch().getContacts().iterator();
//_L6:
//        if(iterator.hasNext()) goto _L4; else goto _L3
//_L3:
//        final ImageAdapter adapter = new ImageAdapter(this, arraylist);
//        gridview.setAdapter(adapter);
//        gridview.setColumnWidth(getResources().getDimensionPixelSize(0x7f040002));
//        gridview.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {
//
//            public void onItemClick(AdapterView adapterview, View view, int k, long l1)
//            {
//                Intent intent = new Intent();
//                intent.putExtra("CONTACT_PHOTO_SELECTED", ((Contact)adapter.getItem(k)).getRawId());
//                setResult(-1, intent);
//                finish();
//            }
//
//            final PhotoPickingActivity this$0;
//            private final ImageAdapter val$adapter;
//
//            
//            {
//                this$0 = PhotoPickingActivity.this;
//                adapter = imageadapter;
//                super();
//            }
//        }
//);
//_L2:
//        ((Button)findViewById(0x7f070032)).setOnClickListener(new android.view.View.OnClickListener() {
//
//            public void onClick(View view)
//            {
//                setResult(0);
//                finish();
//            }
//
//            final PhotoPickingActivity this$0;
//
//            
//            {
//                this$0 = PhotoPickingActivity.this;
//                super();
//            }
//        }
//);
//        return;
//_L4:
//        Contact contact = (Contact)iterator.next();
//        int i = al.length;
//        int j = 0;
//        do
//        {
//            if(j < i)
//            {
//label0:
//                {
//                    long l = al[j];
//                    if(contact.getRawId() != l)
//                        break label0;
//                    arraylist.add(contact);
//                }
//            }
//            if(true)
//                continue;
//            j++;
//        } while(true);
//        if(true) goto _L6; else goto _L5
//_L5:
        
        if(bundle1 == null || !bundle1.containsKey("CONTACT_LIST_KEY")) {//goto _L2; else goto _L1 
          ((Button)findViewById(0x7f070032)).setOnClickListener(new android.view.View.OnClickListener() {
        	
        	            public void onClick(View view)
        	            {
        	                setResult(0);
        	                finish();
        	            }
        	        }
        	);
        } else {
          long al[];
          GridView gridview;
          ArrayList arraylist;
          Iterator iterator;
          al = bundle1.getLongArray("CONTACT_LIST_KEY");
          gridview = (GridView)findViewById(0x7f070031);
          CleanAppManager cleanappmanager = CleanAppManager.get(this);
          arraylist = new ArrayList();
          iterator = cleanappmanager.getCurrentMatch().getContacts().iterator();
          
          while(iterator.hasNext()) {
            Contact contact = (Contact)iterator.next();
            int i = al.length;
            int j = 0;
            do
            {
                if(j < i)
                {
//    label0:
                    {
                        long l = al[j];
                        if(contact.getRawId() != l) {
//                            break label0;
                        	j++;
                        }else {
                        arraylist.add(contact);
                        break;
                        }
                    }
                }
//                if(true)
//                    continue;
//                j++;
            } while(true);
          }
        }
    }

    public static final String CONTACT_LIST_KEY = "CONTACT_LIST_KEY";
    public static final String CONTACT_PHOTO_SELECTED = "CONTACT_PHOTO_SELECTED";
}
