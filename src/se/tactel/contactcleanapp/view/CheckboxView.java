// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package se.tactel.contactcleanapp.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.*;
import java.util.*;
import se.tactel.contactcleanapp.model.*;

public class CheckboxView extends LinearLayout
{
    public static interface CheckboxViewListener
    {

        public abstract void onCheckedChange(AggregatedContact aggregatedcontact, boolean flag);

        public abstract void onEdit();
    }


    public CheckboxView(Context context)
    {
        super(context);
    }

    public CheckboxView(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
    }

    public void setCheckboxViewListener(CheckboxViewListener checkboxviewlistener)
    {
        mListener = checkboxviewlistener;
    }

    public void setContacts(List list, HashMap hashmap)
    {
        LayoutInflater layoutinflater;
        Iterator iterator;
        removeAllViews();
        layoutinflater = (LayoutInflater)getContext().getSystemService("layout_inflater");
        iterator = list.iterator();
//_L2:
//        if(!iterator.hasNext())
//            return;
//        final AggregatedContact aggContact = (AggregatedContact)iterator.next();
//        View view = layoutinflater.inflate(0x7f030008, null);
//        CheckBox checkbox = (CheckBox)view.findViewById(0x7f070039);
//        checkbox.setOnCheckedChangeListener(new android.widget.CompoundButton.OnCheckedChangeListener() {
//
//            public void onCheckedChanged(CompoundButton compoundbutton, boolean flag)
//            {
//                mListener.onCheckedChange(aggContact, flag);
//                mListener.onEdit();
//            }
//
//            final CheckboxView this$0;
//            private final AggregatedContact val$aggContact;
//
//            
//            {
//                this$0 = CheckboxView.this;
//                aggContact = aggregatedcontact;
//                super();
//            }
//        }
//);
//        checkbox.setText(aggContact.getFirst().getData().getName().getFullName());
//        LinearLayout linearlayout = (LinearLayout)view.findViewById(0x7f070038);
//        Iterator iterator1 = aggContact.getContacts().iterator();
//        do
//        {
//label0:
//            {
//                if(iterator1.hasNext())
//                    break label0;
//                addView(view);
//            }
//            if(true)
//                continue;
//            final Contact contact = (Contact)iterator1.next();
//            ImageView imageview = (ImageView)layoutinflater.inflate(0x7f030009, linearlayout, false);
//            imageview.setImageDrawable((Drawable)hashmap.get(contact.getData().getAccountType()));
//            imageview.setOnClickListener(new android.view.View.OnClickListener() {
//
//                public void onClick(View view1)
//                {
//                    String s = "";
//                    if(contact.getData().isGooglePlusContact())
//                        s = "/plus";
//                    Toast.makeText(getContext(), (new StringBuilder(String.valueOf(contact.getData().getAccountName()))).append(s).toString(), 1).show();
//                }
//
//                final CheckboxView this$0;
//                private final Contact val$contact;
//
//            
//            {
//                this$0 = CheckboxView.this;
//                contact = contact1;
//                super();
//            }
//            }
//);
//            linearlayout.addView(imageview);
//        } while(true);
//        if(true) goto _L2; else goto _L1
//_L1:
        
        while(iterator.hasNext()) {
          final AggregatedContact aggContact = (AggregatedContact)iterator.next();
          View view = layoutinflater.inflate(0x7f030008, null);
          CheckBox checkbox = (CheckBox)view.findViewById(0x7f070039);
          checkbox.setOnCheckedChangeListener(new android.widget.CompoundButton.OnCheckedChangeListener() {
  
              public void onCheckedChanged(CompoundButton compoundbutton, boolean flag)
              {
                  mListener.onCheckedChange(aggContact, flag);
                  mListener.onEdit();
              }

          }
  );
          checkbox.setText(aggContact.getFirst().getData().getName().getFullName());
          LinearLayout linearlayout = (LinearLayout)view.findViewById(0x7f070038);
          Iterator iterator1 = aggContact.getContacts().iterator();
          
          while(iterator1.hasNext()) {
              final Contact contact = (Contact)iterator1.next();
              ImageView imageview = (ImageView)layoutinflater.inflate(0x7f030009, linearlayout, false);
              imageview.setImageDrawable((Drawable)hashmap.get(contact.getData().getAccountType()));
              imageview.setOnClickListener(new android.view.View.OnClickListener() {
  
                  public void onClick(View view1)
                  {
                      String s = "";
                      if(contact.getData().isGooglePlusContact())
                          s = "/plus";
                      Toast.makeText(getContext(), (new StringBuilder(String.valueOf(contact.getData().getAccountName()))).append(s).toString(), 1).show();
                  }

              });
              linearlayout.addView(imageview);
          }
          addView(view);
//          do
//          {
//  label0:
//              {
//                  if(iterator1.hasNext())
//                      break label0;
//                  addView(view);
//              }
//              if(true)
//                  continue;
//              final Contact contact = (Contact)iterator1.next();
//              ImageView imageview = (ImageView)layoutinflater.inflate(0x7f030009, linearlayout, false);
//              imageview.setImageDrawable((Drawable)hashmap.get(contact.getData().getAccountType()));
//              imageview.setOnClickListener(new android.view.View.OnClickListener() {
//  
//                  public void onClick(View view1)
//                  {
//                      String s = "";
//                      if(contact.getData().isGooglePlusContact())
//                          s = "/plus";
//                      Toast.makeText(getContext(), (new StringBuilder(String.valueOf(contact.getData().getAccountName()))).append(s).toString(), 1).show();
//                  }
//
//              });
//              linearlayout.addView(imageview);
//          } while(true);
        }
    }

    private static final String TAG = CheckboxView.class.getSimpleName();
    private CheckboxViewListener mListener;


}
