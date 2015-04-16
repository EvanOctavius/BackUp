package id.ac.ui.cs.ppl_c02.kantin;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Evan Octavius S on 4/16/2015.
 */
public class SearchMenuView extends Activity{
    MenuAdapter listAdapter;
    ExpandableListView expandableListView;
    List<String> listDataHeader;
    HashMap<String,List<String>> listDataChild;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_menu_view);

        expandableListView = (ExpandableListView) findViewById(R.id.menu_search);
        Bundle b = getIntent().getExtras();
        String checkbox = b.getString("checkbox");
        String input = b.getString("input");
        Log.e("Debug", checkbox + "   " + input);

        if(checkbox.equals("harga")){
            listMenuByHarga(input);
            Log.e("Debug", "harga");
        } else {
            listMenuByJenis(input);
        }

        listAdapter = new MenuAdapter(this, listDataHeader, listDataChild);

        expandableListView.setAdapter(listAdapter);


        // Listview Group click listener
        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                // Toast.makeText(getApplicationContext(),
                // "Group Clicked " + listDataHeader.get(groupPosition),
                // Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        // Listview Group expanded listener
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(getApplicationContext(),
                        listDataHeader.get(groupPosition) + " Expanded",
                        Toast.LENGTH_SHORT).show();
            }
        });

        // Listview Group collasped listener
        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
                Toast.makeText(getApplicationContext(),
                        listDataHeader.get(groupPosition) + " Collapsed",
                        Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void listMenuByHarga(String harga){
        SearchMenuController searchMenuController = new SearchMenuController();

        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        ArrayList<HashMap<String,String>> tempdata = searchMenuController.getMenuByHarga(harga, getContentResolver());
        if(tempdata != null) {
            for (int i = 0; i < tempdata.size(); i++) {
                listDataHeader.add(tempdata.get(i).get(MenuKios.KioskMenus.KEY_MENU));
            }

            for (int i = 0; i < tempdata.size(); i++) {
                List<String> temp = new ArrayList<>();
                temp.add("Nama Kios  : " + tempdata.get(i).get(Kiosk.Kiosks.KEY_NAME));
                listDataChild.put(listDataHeader.get(i), temp);
            }
        } else {
            listDataHeader.add("Kosong");
            List<String> temp = new ArrayList<>();
            temp.add("Harga      : -");
            temp.add("Total      : -");
            listDataChild.put(listDataHeader.get(0), temp);
        }
    }

    private void listMenuByJenis(String jenis){
        SearchMenuController searchMenuController = new SearchMenuController();

        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        ArrayList<HashMap<String,String>> tempdata = searchMenuController.getMenuByJenis(jenis, getContentResolver());
        if(tempdata != null) {
            for (int i = 0; i < tempdata.size(); i++) {
                listDataHeader.add(tempdata.get(i).get(Menu.Menus.KEY_NAME));
                Log.e("Coba vew",tempdata.toString());
            }
            for(int i = 0 ; i< tempdata.size();i++){
                List<String> temp = new ArrayList<>();
                listDataChild.put(listDataHeader.get(i), temp);
            }
        } else {
            listDataHeader.add("Kosong");
            List<String> temp = new ArrayList<>();
            listDataChild.put(listDataHeader.get(0), temp);
        }
    }
}
