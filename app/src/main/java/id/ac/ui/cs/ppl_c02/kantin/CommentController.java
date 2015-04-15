package id.ac.ui.cs.ppl_c02.kantin;

import android.content.ContentResolver;
import android.content.ContentValues;

import java.util.Calendar;

/**
 * Created by Evan Octavius S on 4/13/2015.
 */
public class CommentController {
    public CommentController(){

    }

    public void createCommentMenu(String uname, String menu,String kios ,String cmnt, ContentResolver contentResolver){
        Calendar time = Calendar.getInstance();
        int day = time.get(Calendar.DATE);
        int month = time.get(Calendar.MONTH);
        int year = time.get(Calendar.YEAR);
        String date = day+"-"+month+"-"+year;
        ContentValues values = new ContentValues();
        values.put(KomentarMenu.MenuComments.KEY_KIOSK,kios);
        values.put(KomentarMenu.MenuComments.KEY_MENU,menu);
        values.put(KomentarMenu.MenuComments.KEY_USER,uname);
        values.put(KomentarMenu.MenuComments.KEY_DATE,date);
        values.put(KomentarMenu.MenuComments.KEY_COMMENT,cmnt);
        contentResolver.insert(KomentarMenu.MenuComments.CONTENT_URI,values);
    }

    public void updateCommentMenu(String id, String menu, String cmnt, String uname, String kios, ContentResolver contentResolver){
        Calendar time = Calendar.getInstance();
        int day = time.get(Calendar.DATE);
        int month = time.get(Calendar.MONTH);
        int year = time.get(Calendar.YEAR);
        String date = day+"-"+month+"-"+year;
        ContentValues values = new ContentValues();
        values.put(KomentarMenu.MenuComments.KEY_KIOSK,kios);
        values.put(KomentarMenu.MenuComments.KEY_MENU,menu);
        values.put(KomentarMenu.MenuComments.KEY_USER,uname);
        values.put(KomentarMenu.MenuComments.KEY_DATE,date);
        values.put(KomentarMenu.MenuComments.KEY_COMMENT,cmnt);
        //Where KEY_KIOSK = id
        String mSelectionClause = MenuKios.KioskMenus.KEY_KIOSK + " = ?";
        String[] mSelectionArgs = {id};
        contentResolver.update(KomentarMenu.MenuComments.CONTENT_URI,values,mSelectionClause,mSelectionArgs);
    }

    public void deleteCommentMenu(String id, ContentResolver contentResolver){
        //Where KEY_KIOSK = id
        String mSelectionClause = KomentarMenu.MenuComments.KEY_ID+ " = ?";
        String[] mSelectionArgs = {id};
        contentResolver.delete(KomentarMenu.MenuComments.CONTENT_URI,mSelectionClause,mSelectionArgs);
    }

    public void createCommentKios(String uname, String kios ,String cmnt, ContentResolver contentResolver){
        Calendar time = Calendar.getInstance();
        int day = time.get(Calendar.DATE);
        int month = time.get(Calendar.MONTH);
        int year = time.get(Calendar.YEAR);
        String date = day+"-"+month+"-"+year;
        ContentValues values = new ContentValues();
        values.put(KomentarMenu.MenuComments.KEY_KIOSK,kios);
        values.put(KomentarKios.KioskComments.KEY_USER,uname);
        values.put(KomentarKios.KioskComments.KEY_DATE,date);
        values.put(KomentarKios.KioskComments.KEY_COMMENT,cmnt);
        contentResolver.insert(KomentarMenu.MenuComments.CONTENT_URI,values);
    }

    public void updateCommentKiosk(String id, String menu, String cmnt, String uname, String kios, ContentResolver contentResolver){
        Calendar time = Calendar.getInstance();
        int day = time.get(Calendar.DATE);
        int month = time.get(Calendar.MONTH);
        int year = time.get(Calendar.YEAR);
        String date = day+"-"+month+"-"+year;
        ContentValues values = new ContentValues();
        values.put(KomentarMenu.MenuComments.KEY_KIOSK,kios);
        values.put(KomentarKios.KioskComments.KEY_USER,uname);
        values.put(KomentarKios.KioskComments.KEY_DATE,date);
        values.put(KomentarKios.KioskComments.KEY_COMMENT,cmnt);
        //Where KEY_KIOSK = id
        String mSelectionClause = MenuKios.KioskMenus.KEY_KIOSK + " = ?";
        String[] mSelectionArgs = {id};
        contentResolver.update(KomentarKios.KioskComments.CONTENT_URI,values,mSelectionClause,mSelectionArgs);
    }

    public void deleteCommentKiosk(String id, ContentResolver contentResolver){
        //Where KEY_KIOSK = id
        String mSelectionClause = KomentarKios.KioskComments.KEY_ID + " = ?";
        String[] mSelectionArgs = {id};
        contentResolver.delete(KomentarKios.KioskComments.CONTENT_URI,mSelectionClause,mSelectionArgs);
    }
}
