package id.ac.ui.cs.ppl_c02.kantin;
 
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.Toast;
 
public class MenuActivity extends Activity {
 
    MenuAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);
 
        // get the listview
        expListView = (ExpandableListView) findViewById(R.id.menu);

        //get parameter
        Bundle b = getIntent().getExtras();
        String idK = b.getString("id");

        // preparing list data
        prepareListData(idK);
 
        listAdapter = new MenuAdapter(this, listDataHeader, listDataChild);
 
        // setting list adapter
        expListView.setAdapter(listAdapter);
    
 
    // Listview Group click listener
    expListView.setOnGroupClickListener(new OnGroupClickListener() {

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
    expListView.setOnGroupExpandListener(new OnGroupExpandListener() {

        @Override
        public void onGroupExpand(int groupPosition) {
            Toast.makeText(getApplicationContext(),
                    listDataHeader.get(groupPosition) + " Expanded",
                    Toast.LENGTH_SHORT).show();
        }
    });

    // Listview Group collasped listener
    expListView.setOnGroupCollapseListener(new OnGroupCollapseListener() {

        @Override
        public void onGroupCollapse(int groupPosition) {
            Toast.makeText(getApplicationContext(),
                    listDataHeader.get(groupPosition) + " Collapsed",
                    Toast.LENGTH_SHORT).show();

        }
    });

    }
    
    /*
     * Preparing the list data
     */
    private void prepareListData(String id) {
        //get data from database
        HomeController homeController = new HomeController();
        ArrayList<HashMap<String,String>> tempdata = homeController.getMenu(id, getContentResolver());

        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        for (int i = 0; i < tempdata.size(); i++) {
            listDataHeader.add(tempdata.get(i).get("menu"));
        }

        // Adding child data
        for (int i = 0 ; i< tempdata.size();i++){
            List<String> temp = new ArrayList<>();
            temp.add("Harga      : "+tempdata.get(i).get(MenuKios.KioskMenus.KEY_COST));
            temp.add("Kolesterol : "+tempdata.get(i).get(Menu.Menus.KEY_CHOLERSTEROL));
            temp.add("Kalori     : "+tempdata.get(i).get(Menu.Menus.KEY_CALORIE));
            temp.add("Lemak      : "+tempdata.get(i).get(Menu.Menus.KEY_FAT));
            temp.add("Karbohidrat: "+tempdata.get(i).get(Menu.Menus.KEY_CARBO));
            temp.add("Total      : "+tempdata.get(i).get(MenuKios.KioskMenus.KEY_TOTAL));
            listDataChild.put(listDataHeader.get(i), temp);
        }
    }
}