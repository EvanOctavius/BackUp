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
public class HomeController{
    public HomeController(){

    }

    public HashMap<String,String> getKiosk(String id,ContentResolver contentResolver){
        //Varibel penampung output
        HashMap<String,String> kiosk = new HashMap<>();

        //Select Kolom yang ingin di ambil
        String[] mProjection =
                {
                        Kiosk.Kiosks.KEY_NO,
                        Kiosk.Kiosks.KEY_OWNER,
                        Kiosk.Kiosks.KEY_NAME,
                        Kiosk.Kiosks.KEY_DESC,
                        Kiosk.Kiosks.KEY_TELP
                };
        //Where KEY_NO = id
        String mSelectionClause = Kiosk.Kiosks.KEY_NO + " = ?";
        String[] mSelectionArgs = {id};

        //execute query
        //Kiosk.Kiosks.CONTENT_URI = tabel Kios
        Cursor c = contentResolver.query(Kiosk.Kiosks.CONTENT_URI, mProjection, mSelectionClause, mSelectionArgs, null);

        //check apakah outputny tidak kosong
        //Mengjalankan kursor ke hasil pertama
        if (c.moveToFirst()){
            //Memproses output menampung kedalam variabel sesuai kolom
            kiosk.put(Kiosk.Kiosks.KEY_NO,c.getString(0));
            kiosk.put(Kiosk.Kiosks.KEY_OWNER,c.getString(1));
            kiosk.put(Kiosk.Kiosks.KEY_NAME,c.getString(2));
            kiosk.put(Kiosk.Kiosks.KEY_DESC,c.getString(3));
            kiosk.put(Kiosk.Kiosks.KEY_TELP,c.getString(4));
        }

        //tutup cursor
        c.close();
        Log.e("getKiosk","Hasil = " + kiosk);

        //return statement
        return kiosk;
    }

    public ArrayList<HashMap<String,String>> getMenu(String id, ContentResolver contentResolver){
        //Variabel penampung output
        ArrayList<HashMap<String,String>> listMenu = new ArrayList<>();

        //Select Kolom yang ingin di ambil
        String[] mProjection =
                {
                        MenuKios.KioskMenus.KEY_MENU,
                        MenuKios.KioskMenus.KEY_COST,
                        MenuKios.KioskMenus.KEY_TOTAL,
                        Menu.Menus.KEY_CHOLERSTEROL,
                        Menu.Menus.KEY_CALORIE,
                        Menu.Menus.KEY_CARBO,
                        Menu.Menus.KEY_PROT
                };

        //Where KEY_KIOSK = id
        String mSelectionClause = MenuKios.KioskMenus.KEY_KIOSK + " = ?";
        String[] mSelectionArgs = {id};

        //execute query
        //MenuKios.KioskMenus.CONTENT_URI = tabel menu kios
        Cursor c = contentResolver.query(Uri.parse("content://" + DBContentProvider.AUTHORITY + "/MenuMenuKios"), mProjection,mSelectionClause,mSelectionArgs,null);

        //iterasi cursor untuk memproses semua hasil query
        if (c.moveToFirst()){
            do {
                //temporary variabel
                HashMap<String,String> menu = new HashMap<>();
                menu.put(MenuKios.KioskMenus.KEY_MENU, c.getString(0));
                menu.put(MenuKios.KioskMenus.KEY_COST, c.getString(1));
                menu.put(MenuKios.KioskMenus.KEY_TOTAL, c.getString(2));
                menu.put(Menu.Menus.KEY_CHOLERSTEROL, c.getString(3));
                menu.put(Menu.Menus.KEY_CALORIE, c.getString(4));
                menu.put(Menu.Menus.KEY_CARBO, c.getString(5));
                menu.put(Menu.Menus.KEY_PROT, c.getString(6));

                //Log.e("Iterasi Menu",menu.toString());

                //Masukan temporary varibel ke output akhir setiap iterasi
                listMenu.add(menu);
                //Log.e("add ke list", listMenu.toString());
            }while(c.moveToNext());
        }

        //tutup cursor
        c.close();
        Log.e("getMenu", "Hasil = " + listMenu.toString());

        //return statement
        return listMenu;
    }

    public String showCalorie(){
        String info ="";

        return info;
    }

    public String showCholesterol(){
        String info ="";

        return info;
    }
}