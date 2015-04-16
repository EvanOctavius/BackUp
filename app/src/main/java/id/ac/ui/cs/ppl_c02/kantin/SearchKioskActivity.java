package id.ac.ui.cs.ppl_c02.kantin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Evan Octavius S on 4/16/2015.
 */
public class SearchKioskActivity extends Activity {
    EditText text;
    Button cobaButton;

    String menu ="";

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_kiosk);


        addListenerButton();


    }

    private void addListenerButton(){
        cobaButton = (Button) findViewById(R.id.confirm_search);
        if(cobaButton!= null) {
            cobaButton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    Intent cobaIntent = new Intent(SearchKioskActivity.this,SearchKioskView.class);
                    text = (EditText) findViewById(R.id.searchText);
                    menu = text.getText().toString();
                    cobaIntent.putExtra("menu",menu);
                    SearchKioskActivity.this.startActivity(cobaIntent);
                }
            });
        }
    }
}
