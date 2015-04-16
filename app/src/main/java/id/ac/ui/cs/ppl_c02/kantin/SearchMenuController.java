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
public class SearchMenuController {
    public ArrayList<HashMap<String,String>> getMenuByHarga(String harga, ContentResolver contentResolver) {
        //Variabel penampung output
        ArrayList<HashMap<String, String>> listMenu = new ArrayList<>();

        //Select Kolom yang ingin di ambil
        String[] mProjection =
                {
                        MenuKios.KioskMenus.KEY_MENU,
                        Kiosk.Kiosks.KEY_NAME
                };

        //Where KEY_KIOSK = id
        String mSelectionClause = MenuKios.KioskMenus.KEY_COST+ " <= ?";
        String[] mSelectionArgs = {harga};

        //execute query
        //MenuKios.KioskMenus.CONTENT_URI = tabel menu kios
        Cursor c = contentResolver.query(Uri.parse("content://" + DBContentProvider.AUTHORITY + "/KiosMenuKios"),mProjection,mSelectionClause,mSelectionArgs,null);

        //iterasi cursor untuk memproses semua hasil query
        if (c.moveToFirst()) {
            do {
                //temporary variabel
                HashMap<String, String> temp = new HashMap<>();
                temp.put(MenuKios.KioskMenus.KEY_MENU, c.getString(0));
                temp.put(Kiosk.Kiosks.KEY_NAME, c.getString(1));
                //Log.e("Iterasi Menu", temp.toString());

                //Masukan temporary varibel ke output akhir setiap iterasi
                listMenu.add(temp);
                //Log.e("add ke list", listMenu.toString());
            } while (c.moveToNext());
        }

        //tutup cursor
        c.close();
        Log.e("getMenu", "Hasil  = " + listMenu.toString() + " = " + listMenu.size());

        //return statement
        return listMenu;
    }

    public ArrayList<HashMap<String,String>> getMenuByJenis(String jenis, ContentResolver contentResolver) {
        //Variabel penampung output
        ArrayList<HashMap<String, String>> listMenu = new ArrayList<>();

        //Select Kolom yang ingin di ambil
        String[] mProjection =
                {
                        Menu.Menus.KEY_NAME
                };

        //Where KEY_KIOSK = id
        String mSelectionClause = Menu.Menus.KEY_VARIETY+ " = ?";
        String[] mSelectionArgs = {jenis};

        //execute query
        //MenuKios.KioskMenus.CONTENT_URI = tabel menu kios
        Cursor c = contentResolver.query(Menu.Menus.CONTENT_URI,mProjection,mSelectionClause,mSelectionArgs,null);

        //iterasi cursor untuk memproses semua hasil query
        if (c.moveToFirst()) {
            do {
                //temporary variabel
                HashMap<String, String> temp = new HashMap<>();
                temp.put(Menu.Menus.KEY_NAME, c.getString(0));
                //Log.e("Iterasi Menu", temp.toString());

                //Masukan temporary varibel ke output akhir setiap iterasi
                listMenu.add(temp);
                //Log.e("add ke list", listMenu.toString());
            } while (c.moveToNext());
        }

        //tutup cursor
        c.close();
        Log.e("getMenu", "Hasil  = " + listMenu.toString() + " = " + listMenu.size());

        //return statement
        return listMenu;
    }
}
