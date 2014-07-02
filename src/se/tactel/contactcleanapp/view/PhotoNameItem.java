// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package se.tactel.contactcleanapp.view;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.widget.*;
import se.tactel.contactcleanapp.utils.DataUtils;

public class PhotoNameItem extends LinearLayout
{
    public static interface PhotoPickListener
    {

        public abstract void addPhoto(PhotoNameItem photonameitem);

        public abstract void onEdit();

        public abstract void photoPicked(PhotoNameItem photonameitem);
    }


    public PhotoNameItem(Context context)
    {
        super(context);
    }

    public PhotoNameItem(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
    }

    private void addPhoto()
    {
        mListener.addPhoto(this);
    }

    private void pickPhoto()
    {
        mListener.photoPicked(this);
    }

    public String getFamilyName()
    {
        return mFamilyName.getEditableText().toString();
    }

    public String getGivenName()
    {
        return mGivenName.getEditableText().toString();
    }

    public byte[] getPhoto()
    {
        return mPhoto;
    }

    protected void onFinishInflate()
    {
        mImageView = (ImageView)findViewById(0x7f07003b);
        mImageView.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                if(mListener != null)
                {
                    if(mNbrPhotos.getText().length() > 0)
                        pickPhoto();
                    else
                        addPhoto();
                    mListener.onEdit();
                }
            }

//            final PhotoNameItem this$0;
//
//            
//            {
//                this$0 = PhotoNameItem.this;
//                super();
//            }
        }
);
        mGivenName = (EditText)findViewById(0x7f07003e);
        mGivenName.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable editable)
            {
                if(mListener != null)
                    mListener.onEdit();
            }

            public void beforeTextChanged(CharSequence charsequence, int i, int j, int k)
            {
            }

            public void onTextChanged(CharSequence charsequence, int i, int j, int k)
            {
            }

//            final PhotoNameItem this$0;
//
//            
//            {
//                this$0 = PhotoNameItem.this;
//                super();
//            }
        }
);
        mFamilyName = (EditText)findViewById(0x7f07003f);
        mFamilyName.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable editable)
            {
                if(mListener != null)
                    mListener.onEdit();
            }

            public void beforeTextChanged(CharSequence charsequence, int i, int j, int k)
            {
            }

            public void onTextChanged(CharSequence charsequence, int i, int j, int k)
            {
            }

//            final PhotoNameItem this$0;
//
//            
//            {
//                this$0 = PhotoNameItem.this;
//                super();
//            }
        }
);
        mNbrPhotos = (TextView)findViewById(0x7f07003d);
        mPhotoCountBackground = (ImageView)findViewById(0x7f07003c);
        mPhotoCountBackground.setVisibility(4);
        mImageView.setImageBitmap(DataUtils.getRoundedCornerBitmap(BitmapFactory.decodeResource(getResources(), 0x7f02001d)));
    }

    public void setFamilyName(String s)
    {
        mFamilyName.setText(s);
    }

    public void setGivenName(String s)
    {
        mGivenName.setText(s);
    }

    public void setListener(PhotoPickListener photopicklistener)
    {
        mListener = photopicklistener;
    }

    public void setPhoto(byte abyte0[])
    {
        mPhoto = abyte0;
        android.graphics.Bitmap bitmap = null;
        if(abyte0 != null && abyte0.length > 0)
            bitmap = DataUtils.getRoundedCornerBitmap(BitmapFactory.decodeByteArray(abyte0, 0, abyte0.length));
        if(bitmap == null)
            bitmap = DataUtils.getRoundedCornerBitmap(BitmapFactory.decodeResource(getResources(), 0x7f02001d));
        mImageView.setImageBitmap(bitmap);
    }

    public void setPhoto(byte abyte0[], int i)
    {
        if(i > 0)
        {
            mPhotoCountBackground.setVisibility(0);
            mNbrPhotos.setText((new StringBuilder(String.valueOf(i))).toString());
        } else
        {
            mPhotoCountBackground.setVisibility(4);
        }
        setPhoto(abyte0);
    }

    private EditText mFamilyName;
    private EditText mGivenName;
    private ImageView mImageView;
    private PhotoPickListener mListener;
    private TextView mNbrPhotos;
    private byte mPhoto[];
    private ImageView mPhotoCountBackground;




}
