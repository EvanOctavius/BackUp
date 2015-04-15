package id.ac.ui.cs.ppl_c02.kantin;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;

import java.util.HashMap;

/**
 * Created by Evan Octavius S on 4/12/2015.
 */
public class MenuController {
    public MenuController(){

    }

    public String addCounter(String menu, ContentResolver contentResolver){
        String count ="";
        ContentValues values = new ContentValues();
        String[] mProjection =
                {
                        Pengguna.Users.KEY_UNAME
                };

        //Where KEY_KIOSK = id
        String mSelectionClause = Pengguna.Users.KEY_UNAME + " = ?";
        String[] mSelectionArgs = {"0"};
        Cursor c = contentResolver.query(Pengguna.Users.CONTENT_URI,mProjection,mSelectionClause,mSelectionArgs,null);
        String pengguna = "";
        if(c.moveToFirst()){
            pengguna = c.getString(0);
        }

        String[] mProjection1 =
                {
                        MenuKios.KioskMenus.KEY_MENU,
                        Kiosk.Kiosks.KEY_NO,
                };

        //Where KEY_KIOSK = id
        String mSelectionClause1 = MenuKios.KioskMenus.KEY_MENU + " = ?";
        String[] mSelectionArgs1 = {menu};

        //execute query
        //MenuKios.KioskMenus.CONTENT_URI = tabel menu kios
        Cursor c1 = contentResolver.query(MenuKios.KioskMenus.CONTENT_URI,mProjection1,mSelectionClause1,mSelectionArgs1,null);
        String dataTemp[]={""};
        if(c.moveToFirst()){
            dataTemp[0] = c.getString(0);
            dataTemp[1] = c.getString(1);
        }

        values.put(Pengguna.Users.KEY_NAME, pengguna);
        values.put(Kiosk.Kiosks.KEY_NO,dataTemp[1]);
        values.put(MenuKios.KioskMenus.KEY_MENU,dataTemp[0]);
        contentResolver.insert(RiwayatLike.RLs.CONTENT_URI,values);
        return count;
    }
}
