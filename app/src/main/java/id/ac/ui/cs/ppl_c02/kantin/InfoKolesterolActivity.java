package id.ac.ui.cs.ppl_c02.kantin;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by Evan Octavius S on 4/16/2015.
 */
public class InfoKolesterolActivity extends Activity{
    TextView text;
    String idK;
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_kolesterol);

        idK = idK = getResources().getString(R.string.kolesterol_description);
        setText(idK);


    }
    private void setText(String id){
        text = (TextView) findViewById(R.id.kolesterol);
        text.setText(id);
    }
}
