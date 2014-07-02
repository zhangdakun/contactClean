// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package se.tactel.contactcleanapp.utils;

import android.accounts.*;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.*;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import java.util.*;
import se.tactel.contactcleanapp.model.Contact;
import se.tactel.contactcleanapp.model.ContactData;

public class DataUtils
{

    public DataUtils()
    {
    }

    public static HashMap getAccountIcons(List list, Context context, HashMap hashmap)
    {
//        if(list == null) goto _L2; else goto _L1
//_L1:
//        Iterator iterator = list.iterator();
//_L5:
//        if(iterator.hasNext()) goto _L4; else goto _L3
//_L3:
//        Iterator iterator1 = hashmap.entrySet().iterator();
//_L6:
//        if(iterator1.hasNext())
//            break MISSING_BLOCK_LABEL_124;
//_L2:
//        return hashmap;
//_L4:
//        Contact contact = (Contact)iterator.next();
//        String s = contact.getData().getAccountType();
//        String s1 = contact.getData().getAccountName();
//        if(!TextUtils.isEmpty(s1) && !TextUtils.isEmpty(s) && !hashmap.containsKey(s))
//            hashmap.put(s, getIconForAccount(new Account(s1, s), context));
//          goto _L5
//        if(((java.util.Map.Entry)iterator1.next()).getValue() == null)
//            iterator1.remove();
//          goto _L6
    	if(list == null) {
    		
    	} else {
    		Iterator iterator = list.iterator();
    		while(iterator.hasNext()) {
    	        Contact contact = (Contact)iterator.next();
    	        String s = contact.getData().getAccountType();
    	        String s1 = contact.getData().getAccountName();
    	        if(!TextUtils.isEmpty(s1) && !TextUtils.isEmpty(s) && !hashmap.containsKey(s))
    	            hashmap.put(s, getIconForAccount(new Account(s1, s), context));
    		}
    		
    		Iterator iterator1 = hashmap.entrySet().iterator();
    		
    		while(iterator1.hasNext()) {
    	        if(((java.util.Map.Entry)iterator1.next()).getValue() == null)
    	        	iterator1.remove();
    		}
    	} 
    	
    	return hashmap;
    }

    public static Drawable getIconForAccount(Account account, Context context)
    {
        AuthenticatorDescription aauthenticatordescription[];
        int i;
        int j;
        aauthenticatordescription = AccountManager.get(context).getAuthenticatorTypes();
        i = aauthenticatordescription.length;
        j = 0;
//_L6:
//        if(j < i) goto _L2; else goto _L1
//_L1:
//        Drawable drawable = context.getResources().getDrawable(0x7f02001b);
//_L4:
//        return drawable;
//_L2:
//        AuthenticatorDescription authenticatordescription = aauthenticatordescription[j];
//        if(!authenticatordescription.type.equals(account.type))
//            break; /* Loop/switch isn't completed */
//        drawable = context.getPackageManager().getDrawable(authenticatordescription.packageName, authenticatordescription.iconId, null);
//        if(true) goto _L4; else goto _L3
//_L3:
//        j++;
//        if(true) goto _L6; else goto _L5
//_L5:

        Drawable drawable = context.getResources().getDrawable(0x7f02001b);
        
        for(j=0;j<i;j++) {
        	AuthenticatorDescription authenticatordescription = aauthenticatordescription[j];
        	if(authenticatordescription.type.equals(account.type)) {
        		drawable = context.getPackageManager().getDrawable(authenticatordescription.packageName, authenticatordescription.iconId, null);
        		break;
        	}
        }
        return drawable;
        
    }

    public static Bitmap getRoundedCornerBitmap(Bitmap bitmap)
    {
        Bitmap bitmap1;
        if(bitmap == null)
        {
            bitmap1 = null;
        } else
        {
            bitmap1 = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), android.graphics.Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap1);
            Paint paint = new Paint();
            Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
            RectF rectf = new RectF(rect);
            paint.setAntiAlias(true);
            canvas.drawARGB(0, 0, 0, 0);
            paint.setColor(0xff424242);
            canvas.drawRoundRect(rectf, 10F, 10F, paint);
            paint.setXfermode(new PorterDuffXfermode(android.graphics.PorterDuff.Mode.SRC_IN));
            canvas.drawBitmap(bitmap, rect, rect, paint);
        }
        return bitmap1;
    }
}
