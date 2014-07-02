// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package se.tactel.contactcleanapp.storage;

import android.content.Context;
import java.io.*;
import java.util.*;

// Referenced classes of package se.tactel.contactcleanapp.storage:
//            Storage

public class FileStorage
    implements Storage
{

    public FileStorage(Context context)
    {
        mContext = context;
    }

    private String getStringToStore(List list)
    {
        StringBuilder stringbuilder;
        Iterator iterator;
        stringbuilder = new StringBuilder();
        iterator = list.iterator();
//_L2:
//        if(!iterator.hasNext())
//            return stringbuilder.toString();
//        Iterator iterator1 = ((List)iterator.next()).iterator();
//        do
//        {
//label0:
//            {
//                if(iterator1.hasNext())
//                    break label0;
//                stringbuilder.append("\n");
//            }
//            if(true)
//                continue;
//            stringbuilder.append(Long.toString(((Long)iterator1.next()).longValue()));
//            stringbuilder.append(":");
//        } while(true);
//        if(true) goto _L2; else goto _L1
//_L1:
        
        while(iterator.hasNext()) {
        	Iterator iterator1 = ((List)iterator.next()).iterator();
        	
        	while(iterator1.hasNext()) {
                stringbuilder.append(Long.toString(((Long)iterator1.next()).longValue()));
                stringbuilder.append(":");
        	}
        	stringbuilder.append("\n");
        	
        }
        
        return stringbuilder.toString();
    }

    private List parseStoredResult()
    {
        ArrayList arraylist;
        FileInputStream fileinputstream;
        ArrayList arraylist1;
        arraylist = new ArrayList();
        fileinputstream = null;
        arraylist1 = new ArrayList();
        BufferedReader bufferedreader;
        String s;
        try {
        fileinputstream = mContext.openFileInput("storage.txt");
        bufferedreader = new BufferedReader(new InputStreamReader(fileinputstream));
//        s = bufferedreader.readLine();
//        String s1 = s;
//_L3:
//        if(s1 != null) goto _L2; else goto _L1
//_L1:
//        Iterator iterator;
//        if(fileinputstream != null)
//            try
//            {
//                fileinputstream.close();
//            }
//            catch(IOException ioexception4)
//            {
//                ioexception4.printStackTrace();
//            }
//        iterator = arraylist1.iterator();
//        break MISSING_BLOCK_LABEL_77;
//_L2:
//        arraylist1.add(s1);
//        s2 = bufferedreader.readLine();
//        s1 = s2;
//          goto _L3
//        filenotfoundexception;
//        filenotfoundexception.printStackTrace();
//        if(fileinputstream != null)
//            try
//            {
//                fileinputstream.close();
//            }
//            catch(IOException ioexception3)
//            {
//                ioexception3.printStackTrace();
//            }
//        break MISSING_BLOCK_LABEL_69;
//        ioexception1;
//        ioexception1.printStackTrace();
//        if(fileinputstream != null)
//            try
//            {
//                fileinputstream.close();
//            }
//            catch(IOException ioexception2)
//            {
//                ioexception2.printStackTrace();
//            }
//        break MISSING_BLOCK_LABEL_69;
//        exception;
//        if(fileinputstream != null)
//            try
//            {
//                fileinputstream.close();
//            }
//            catch(IOException ioexception)
//            {
//                ioexception.printStackTrace();
//            }
//        throw exception;
//_L5:
//        String as[];
//        ArrayList arraylist2;
//        int i;
//        int j;
//        Exception exception;
//        IOException ioexception1;
//        FileNotFoundException filenotfoundexception;
//        String s2;
//        if(!iterator.hasNext())
//            return arraylist;
//        as = ((String)iterator.next()).split(":");
//        arraylist2 = new ArrayList();
//        i = as.length;
//        j = 0;
//_L6:
//label0:
//        {
//            if(j < i)
//                break label0;
//            arraylist.add(arraylist2);
//        }
//        if(true) goto _L5; else goto _L4
//_L4:
//        arraylist2.add(Long.valueOf(Long.parseLong(as[j])));
//        j++;
//          goto _L6
        
        
			while ((s = bufferedreader.readLine()) != null)  {
				arraylist1.add(s);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
      Iterator iterator;
      if(fileinputstream != null)
          try
          {
              fileinputstream.close();
          }
          catch(IOException ioexception4)
          {
              ioexception4.printStackTrace();
          }
      iterator = arraylist1.iterator();
      
    String as[];
    ArrayList arraylist2;
    int i;
    int j;
    Exception exception;
    IOException ioexception1;
    FileNotFoundException filenotfoundexception;
    String s2;
    
    while(iterator.hasNext()) {
    	
      as = ((String)iterator.next()).split(":");
      arraylist2 = new ArrayList();
      i = as.length;
      j = 0;
      
      for(j=0;j<i;j++) {
    	  arraylist2.add(Long.valueOf(Long.parseLong(as[j])));
      }
      arraylist.add(arraylist2);

      
    }
        
    return arraylist;
    
    }

    private void saveToFile(String s)
    {
        FileOutputStream fileoutputstream = null;
        try {
			fileoutputstream = mContext.openFileOutput("storage.txt", 0);
			fileoutputstream.write(s.getBytes());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        if(fileoutputstream == null) {
			try {
				fileoutputstream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        
//        if(fileoutputstream == null)
//            break MISSING_BLOCK_LABEL_29;
//        fileoutputstream.close();
//_L1:
//        return;
//        IOException ioexception1;
//        ioexception1;
//        ioexception1.printStackTrace();
//        if(fileoutputstream != null)
//            try
//            {
//                fileoutputstream.close();
//            }
//            catch(IOException ioexception2)
//            {
//                ioexception2.printStackTrace();
//            }
//          goto _L1
//        Exception exception;
//        exception;
//        if(fileoutputstream != null)
//            try
//            {
//                fileoutputstream.close();
//            }
//            catch(IOException ioexception)
//            {
//                ioexception.printStackTrace();
//            }
//        throw exception;
//        IOException ioexception3;
//        ioexception3;
//        ioexception3.printStackTrace();
//          goto _L1
    }

    public List getSkippedMatches()
    {
        return parseStoredResult();
    }

    public void storeSkippedMatches(List list)
    {
        saveToFile(getStringToStore(list));
    }

    private static final String DELIMITER = ":";
    private static final String FILENAME = "storage.txt";
    private static final String TAG = FileStorage.class.getSimpleName();
    private Context mContext;

}
