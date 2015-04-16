package id.ac.ui.cs.ppl_c02.kantin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

/**
 * Created by Evan Octavius S on 4/16/2015.
 */
public class SearchMenuActivity extends Activity {

    EditText text;
    Button cobaButton;

    CheckBox jenis;
    CheckBox harga;

    String input ="";

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_menu);

        addListenerButton();

    }

    private void addListenerButton(){
        cobaButton = (Button) findViewById(R.id.confirm_search_menu);
        if(cobaButton!= null) {
            cobaButton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    Intent cobaIntent = new Intent(SearchMenuActivity.this,SearchMenuView.class);
                    text = (EditText) findViewById(R.id.searchtext_menu);
                    input = text.getText().toString();
                    cobaIntent.putExtra("input",input);
                    jenis = (CheckBox) findViewById(R.id.jenisbox);
                    harga = (CheckBox) findViewById(R.id.hargabox);
                    if(jenis.isChecked()){
                        cobaIntent.putExtra("checkbox","jenis");
                    }
                    if(harga.isChecked()){
                        cobaIntent.putExtra("checkbox","harga");
                    }
                    SearchMenuActivity.this.startActivity(cobaIntent);
                }
            });
        }
    }
}
