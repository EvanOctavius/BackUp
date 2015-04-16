package id.ac.ui.cs.ppl_c02.kantin;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.HashMap;

/**
 * Created by Evan Octavius S on 4/16/2015.
 */
public class InfoKaloriActivity extends Activity {
    TextView text;
    String idK;
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_kalori);

        idK = getResources().getString(R.string.kalori_description);

        setText(idK);


    }
    private void setText(String id){
        text = (TextView) findViewById(R.id.kalori);
        text.setText(id);
    }
}
