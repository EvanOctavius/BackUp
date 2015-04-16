package id.ac.ui.cs.ppl_c02.kantin;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Evan Octavius S on 4/16/2015.
 */
public class SearchKioskView extends Activity {

    KioskAdapter listAdapter;
    ExpandableListView expandableListView;
    List<String> listDataHeader;
    HashMap<String,List<String>> listDataChild;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_kios_view);

        expandableListView = (ExpandableListView) findViewById(R.id.kiosk_search);
        Bundle b = getIntent().getExtras();
        String menu = b.getString("menu");

        listKiosk(menu);

        listAdapter = new KioskAdapter(this, listDataHeader, listDataChild);

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

    private void listKiosk(String menu){
        SearchKioskController searchKioskController = new SearchKioskController();

        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        ArrayList<HashMap<String,String>> tempdata = searchKioskController.getKioskByMenu(menu, getContentResolver());
        if(tempdata != null) {
            for (int i = 0; i < tempdata.size(); i++) {
                listDataHeader.add(tempdata.get(i).get(Kiosk.Kiosks.KEY_NAME));
            }

            for (int i = 0; i < tempdata.size(); i++) {
                List<String> temp = new ArrayList<>();
                temp.add("Harga      : " + tempdata.get(i).get(MenuKios.KioskMenus.KEY_COST));
                temp.add("Total      : " + tempdata.get(i).get(MenuKios.KioskMenus.KEY_TOTAL));
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
}
