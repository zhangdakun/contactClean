// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package se.tactel.contactcleanapp.view;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.widget.*;

public class DisplayableItem extends LinearLayout
{
    public static interface ItemListener
    {

        public abstract void onDelete(DisplayableItem displayableitem);

        public abstract void onEdit();

        public abstract void onTypePressed(DisplayableItem displayableitem);
    }


    public DisplayableItem(Context context)
    {
        super(context);
        mType = -1;
        mCategory = -1;
    }

    public DisplayableItem(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        mType = -1;
        mCategory = -1;
    }

    private void delete()
    {
        if(mListener != null)
            mListener.onDelete(this);
    }

    private void onEdit()
    {
        if(mListener != null)
            mListener.onEdit();
    }

    private void typePressed()
    {
        if(mListener != null)
            mListener.onTypePressed(this);
    }

    public int getCategory()
    {
        return mCategory;
    }

    public String getFieldValue()
    {
        return mFieldValue.getEditableText().toString();
    }

    public int getType()
    {
        return mType;
    }

    protected void onFinishInflate()
    {
        mFieldType = (TextView)findViewById(0x7f070035);
        mFieldType.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                typePressed();
            }

//            final DisplayableItem this$0;
//
//            
//            {
//                this$0 = DisplayableItem.this;
//                super();
//            }
        }
);
        mFieldValue = (EditText)findViewById(0x7f070036);
        mFieldValue.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable editable)
            {
                onEdit();
            }

            public void beforeTextChanged(CharSequence charsequence, int i, int j, int k)
            {
            }

            public void onTextChanged(CharSequence charsequence, int i, int j, int k)
            {
            }

//            final DisplayableItem this$0;
//
//            
//            {
//                this$0 = DisplayableItem.this;
//                super();
//            }
        }
);
        mDeleteButton = (Button)findViewById(0x7f070037);
        mDeleteButton.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                onEdit();
                delete();
            }

//            final DisplayableItem this$0;
//
//            
//            {
//                this$0 = DisplayableItem.this;
//                super();
//            }
        }
);
    }

    public void setCategory(int i)
    {
        mCategory = i;
//        if(i != 3) goto _L2; else goto _L1
//_L1:
//        mFieldValue.setInputType(32);
//_L4:
//        return;
//_L2:
//        if(i == 2)
//            mFieldValue.setInputType(3);
//        if(true) goto _L4; else goto _L3
//_L3:
        
        if(i != 3) {
          if(i == 2)
        	  mFieldValue.setInputType(3);
        } else {
        	mFieldValue.setInputType(32);
        }
    }

    public void setFieldType(String s)
    {
        mFieldType.setText(s);
    }

    public void setFieldValue(String s)
    {
        mFieldValue.setText(s);
    }

    public void setListener(ItemListener itemlistener)
    {
        mListener = itemlistener;
    }

    public void setType(int i)
    {
        mType = i;
    }

    public static final int CATEGORY_EMAIL_ADDRESS = 3;
    public static final int CATEGORY_MIDDLE_NAME = 1;
    public static final int CATEGORY_PHONE_NUMBER = 2;
    public static final String KEY_CATEGORY = "category";
    public static final String KEY_INDEX = "index";
    private static final String TAG = DisplayableItem.class.getSimpleName();
    private int mCategory;
    private Button mDeleteButton;
    private TextView mFieldType;
    private EditText mFieldValue;
    private ItemListener mListener;
    private int mType;




}
