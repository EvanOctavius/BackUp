package id.ac.ui.cs.ppl_c02.kantin;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Evan Octavius S on 4/12/2015.
 */
public class SearchKioskController {
    public SearchKioskController(){

    }

    public ArrayList<HashMap<String,String>> getKioskByMenu(String menu, ContentResolver contentResolver) {
        //Variabel penampung output
        ArrayList<HashMap<String, String>> listMenu = new ArrayList<>();

        //Select Kolom yang ingin di ambil
        String[] mProjection =
                {
                        MenuKios.KioskMenus.KEY_KIOSK,
                        Kiosk.Kiosks.KEY_NAME,
                        MenuKios.KioskMenus.KEY_COST,
                        MenuKios.KioskMenus.KEY_TOTAL
                };

        //Where KEY_KIOSK = id
        String mSelectionClause = MenuKios.KioskMenus.KEY_MENU + " = ?";
        String[] mSelectionArgs = {menu};

        //execute query
        //MenuKios.KioskMenus.CONTENT_URI = tabel menu kios
        Cursor c = contentResolver.query(Uri.parse("content://" + DBContentProvider.AUTHORITY + "/KiosMenuKios"),mProjection,mSelectionClause,mSelectionArgs,null);

        //iterasi cursor untuk memproses semua hasil query
        if (c.moveToFirst()) {
            do {
                //temporary variabel
                HashMap<String, String> temp = new HashMap<>();
                temp.put(MenuKios.KioskMenus.KEY_KIOSK, c.getString(0));
                temp.put(Kiosk.Kiosks.KEY_NAME, c.getString(1));
                temp.put(MenuKios.KioskMenus.KEY_COST, c.getString(2));
                temp.put(MenuKios.KioskMenus.KEY_TOTAL, c.getString(3));
                //Log.e("Iterasi Menu", menu.toString());

                //Masukan temporary varibel ke output akhir setiap iterasi
                listMenu.add(temp);
                //Log.e("add ke list", listMenu.toString());
            } while (c.moveToNext());
        }

        //tutup cursor
        c.close();
        Log.e("getKiosk", "Hasil Ayam Goreng = " + listMenu.toString()+" = "+listMenu.size());

        //return statement
        return listMenu;
    }
}
