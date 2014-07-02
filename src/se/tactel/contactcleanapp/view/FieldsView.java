// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package se.tactel.contactcleanapp.view;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import java.util.*;
import se.tactel.contactcleanapp.controller.ContactSelectionController;
import se.tactel.contactcleanapp.model.*;

// Referenced classes of package se.tactel.contactcleanapp.view:
//            PhotoNameItem, DisplayableItem

public class FieldsView extends LinearLayout
{
    public static interface FieldsViewListener
    {

        public abstract void onEdit();

        public abstract void onPhotoAdd();

        public abstract void onPhotoPicked();

        public abstract void onTypePressed(int i, int j);
    }


    public FieldsView(Context context)
    {
        super(context);
    }

    public FieldsView(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
    }

    private String getDisplayName(String s, String s1, String s2)
    {
        StringBuilder stringbuilder = new StringBuilder();
        if(!TextUtils.isEmpty(s))
            stringbuilder.append(s);
        if(!TextUtils.isEmpty(s1))
        {
            if(stringbuilder.length() > 0)
                stringbuilder.append(" ");
            stringbuilder.append(s1);
        }
        if(!TextUtils.isEmpty(s2))
        {
            if(stringbuilder.length() > 0)
                stringbuilder.append(" ");
            stringbuilder.append(s2);
        }
        return stringbuilder.toString();
    }

    public ContactData getDisplayedData()
    {
        ContactData contactdata;
        String s;
        String s1;
        String s2;
        int i;
        contactdata = new ContactData();
        s = "";
        s1 = "";
        s2 = "";
        i = 0;
//_L2:
//        if(i >= getChildCount())
//        {
//            contactdata.setName(new Name(getDisplayName(s, s2, s1), s, s1, s2));
//            return contactdata;
//        }
//        if(!(getChildAt(i) instanceof PhotoNameItem))
//            break; /* Loop/switch isn't completed */
//        PhotoNameItem photonameitem = (PhotoNameItem)getChildAt(i);
//        s = photonameitem.getGivenName();
//        s1 = photonameitem.getFamilyName();
//        if(photonameitem.getPhoto() != null)
//            contactdata.setPhoto(photonameitem.getPhoto());
//_L4:
//        i++;
//        if(true) goto _L2; else goto _L1
//_L1:
//        if(!(getChildAt(i) instanceof DisplayableItem)) goto _L4; else goto _L3
//_L3:
//        DisplayableItem displayableitem = (DisplayableItem)getChildAt(i);
//        switch(displayableitem.getCategory())
//        {
//        case 1: // '\001'
//            s2 = displayableitem.getFieldValue();
//            break;
//
//        case 2: // '\002'
//            contactdata.addPhoneNumber(displayableitem.getFieldValue(), displayableitem.getType());
//            break;
//
//        case 3: // '\003'
//            contactdata.addEmail(displayableitem.getFieldValue(), displayableitem.getFieldValue(), displayableitem.getType());
//            break;
//        }
//        if(true) goto _L4; else goto _L5
//_L5:
        
        for(i = 0;i<getChildCount();i++) {
        	 if((getChildAt(i) instanceof PhotoNameItem)) {
               PhotoNameItem photonameitem = (PhotoNameItem)getChildAt(i);
               s = photonameitem.getGivenName();
               s1 = photonameitem.getFamilyName();
               if(photonameitem.getPhoto() != null)
                   contactdata.setPhoto(photonameitem.getPhoto());
        	 } else if((getChildAt(i) instanceof DisplayableItem)) {
               DisplayableItem displayableitem = (DisplayableItem)getChildAt(i);
               switch(displayableitem.getCategory())
               {
               case 1: // '\001'
                   s2 = displayableitem.getFieldValue();
                   break;
       
               case 2: // '\002'
                   contactdata.addPhoneNumber(displayableitem.getFieldValue(), displayableitem.getType());
                   break;
       
               case 3: // '\003'
                   contactdata.addEmail(displayableitem.getFieldValue(), displayableitem.getFieldValue(), displayableitem.getType());
                   break;
               }
        	 }
        }
        
      contactdata.setName(new Name(getDisplayName(s, s2, s1), s, s1, s2));
      return contactdata;
        
    }

    public void setController(ContactSelectionController contactselectioncontroller)
    {
        mSelectionController = contactselectioncontroller;
    }

    public void setListener(FieldsViewListener fieldsviewlistener)
    {
        mListener = fieldsviewlistener;
    }

    public void setPhoto(byte abyte0[])
    {
        mPhotoNameItem.setPhoto(abyte0);
    }

    public void setPhoto(byte abyte0[], int i)
    {
        mPhotoNameItem.setPhoto(abyte0, i);
    }

    public void updateFieldsView()
    {
		if (mSelectionController != null && mListener != null) {
			// goto _L2; else goto _L1
			// _L1:
			// Log.d(TAG,
			// "must set controller and listener before calling updateFieldsView()");
			// _L4:
			// return;
			// _L2:
			removeAllViews();
			LayoutInflater layoutinflater = (LayoutInflater) getContext()
					.getSystemService("layout_inflater");
			mPhotoNameItem = (PhotoNameItem) layoutinflater.inflate(0x7f03000a,
					null);
			mPhotoNameItem.setGivenName(mSelectionController
					.getSuggestedFirstName());
			mPhotoNameItem.setFamilyName(mSelectionController
					.getSuggestedLastName());
			mPhotoNameItem.setListener(new PhotoNameItem.PhotoPickListener() {

				public void addPhoto(PhotoNameItem photonameitem) {
					mListener.onPhotoAdd();
				}

				public void onEdit() {
					mListener.onEdit();
				}

				public void photoPicked(PhotoNameItem photonameitem) {
					mListener.onPhotoPicked();
				}

				// final FieldsView this$0;
				//
				//
				// {
				// this$0 = FieldsView.this;
				// super();
				// }
			});
			addView(mPhotoNameItem);
			if (!TextUtils.isEmpty(mSelectionController
					.getSuggestedMiddleName())) {
				String s = mSelectionController.getSuggestedMiddleName();
				DisplayableItem displayableitem2 = (DisplayableItem) layoutinflater
						.inflate(0x7f030007, null);
				displayableitem2.setFieldType("Middle name");
				displayableitem2.setCategory(1);
				displayableitem2.setFieldValue(s);
				displayableitem2
						.setListener(new DisplayableItem.ItemListener() {

							public void onDelete(
									DisplayableItem displayableitem3) {
								removeView(displayableitem3);
							}

							public void onEdit() {
								mListener.onEdit();
							}

							public void onTypePressed(
									DisplayableItem displayableitem3) {
							}

							// final FieldsView this$0;
							//
							//
							// {
							// this$0 = FieldsView.this;
							// super();
							// }
						});
				addView(displayableitem2);
			}
			Iterator iterator = mSelectionController.getSuggestedPhoneNumbers()
					.iterator();
			// do
			// {
			// label0:
			// {
			// if(iterator.hasNext())
			// break label0;
			// Iterator iterator1 =
			// mSelectionController.getSuggestedEmailAddresses().iterator();
			// while(iterator1.hasNext())
			// {
			// EmailAddress emailaddress = (EmailAddress)iterator1.next();
			// DisplayableItem displayableitem1 =
			// (DisplayableItem)layoutinflater.inflate(0x7f030007, null);
			// displayableitem1.setFieldType(emailaddress.getTypeAsString(getContext()));
			// displayableitem1.setFieldValue(emailaddress.getEmailAddress());
			// displayableitem1.setType(emailaddress.getType());
			// displayableitem1.setCategory(3);
			// displayableitem1.setListener(new DisplayableItem.ItemListener() {
			//
			// public void onDelete(DisplayableItem displayableitem3)
			// {
			// removeView(displayableitem3);
			// }
			//
			// public void onEdit()
			// {
			// mListener.onEdit();
			// }
			//
			// public void onTypePressed(DisplayableItem displayableitem3)
			// {
			// mListener.onTypePressed(indexOfChild(displayableitem3),
			// displayableitem3.getCategory());
			// }
			//
			//
			// }
			// );
			// addView(displayableitem1);
			// }
			// }
			// if(true)
			// continue;
			// PhoneNumber phonenumber = (PhoneNumber)iterator.next();
			// DisplayableItem displayableitem =
			// (DisplayableItem)layoutinflater.inflate(0x7f030007, null);
			// displayableitem.setFieldType(phonenumber.getTypeAsString(getContext()));
			// displayableitem.setFieldValue(phonenumber.getNumber());
			// displayableitem.setType(phonenumber.getType());
			// displayableitem.setCategory(2);
			// displayableitem.setListener(new DisplayableItem.ItemListener() {
			//
			// public void onDelete(DisplayableItem displayableitem3)
			// {
			// removeView(displayableitem3);
			// }
			//
			// public void onEdit()
			// {
			// mListener.onEdit();
			// }
			//
			// public void onTypePressed(DisplayableItem displayableitem3)
			// {
			// mListener.onTypePressed(indexOfChild(displayableitem3),
			// displayableitem3.getCategory());
			// }
			//
			//
			// }
			// );
			// addView(displayableitem);
			// } while(true);
			// if(true) goto _L4; else goto _L3
			// _L3:
			while (iterator.hasNext()) {
				PhoneNumber phonenumber = (PhoneNumber) iterator.next();
				DisplayableItem displayableitem = (DisplayableItem) layoutinflater
						.inflate(0x7f030007, null);
				displayableitem.setFieldType(phonenumber
						.getTypeAsString(getContext()));
				displayableitem.setFieldValue(phonenumber.getNumber());
				displayableitem.setType(phonenumber.getType());
				displayableitem.setCategory(2);
				displayableitem.setListener(new DisplayableItem.ItemListener() {

					public void onDelete(DisplayableItem displayableitem3) {
						removeView(displayableitem3);
					}

					public void onEdit() {
						mListener.onEdit();
					}

					public void onTypePressed(DisplayableItem displayableitem3) {
						mListener.onTypePressed(indexOfChild(displayableitem3),
								displayableitem3.getCategory());
					}

				});
				addView(displayableitem);
			}

			Iterator iterator1 = mSelectionController
					.getSuggestedEmailAddresses().iterator();

			while (iterator1.hasNext()) {
				EmailAddress emailaddress = (EmailAddress) iterator1.next();
				DisplayableItem displayableitem1 = (DisplayableItem) layoutinflater
						.inflate(0x7f030007, null);
				displayableitem1.setFieldType(emailaddress
						.getTypeAsString(getContext()));
				displayableitem1.setFieldValue(emailaddress.getEmailAddress());
				displayableitem1.setType(emailaddress.getType());
				displayableitem1.setCategory(3);
				displayableitem1
						.setListener(new DisplayableItem.ItemListener() {

							public void onDelete(
									DisplayableItem displayableitem3) {
								removeView(displayableitem3);
							}

							public void onEdit() {
								mListener.onEdit();
							}

							public void onTypePressed(
									DisplayableItem displayableitem3) {
								mListener.onTypePressed(
										indexOfChild(displayableitem3),
										displayableitem3.getCategory());
							}

						});
				addView(displayableitem1);
			}

		}
	}

    public void updateItem(int i, int j, String s)
    {
        if(i > -1 && i < getChildCount() && (getChildAt(i) instanceof DisplayableItem))
        {
            DisplayableItem displayableitem = (DisplayableItem)getChildAt(i);
            displayableitem.setType(j);
            displayableitem.setFieldType(s);
        }
        mListener.onEdit();
    }

    private static final String TAG = FieldsView.class.getSimpleName();
    private FieldsViewListener mListener;
    private PhotoNameItem mPhotoNameItem;
    private ContactSelectionController mSelectionController;


}
