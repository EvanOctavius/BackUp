package id.ac.ui.cs.ppl_c02.kantin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.HashMap;

public class KiosActivity extends Activity{
	
	Button cobaButton;
    String idK;
    TextView text;
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.kios);

        Bundle b = getIntent().getExtras();
        idK = b.getString("id");
        setText(idK);
				
		addListenerButton();
	}
	
	private void addListenerButton(){
		cobaButton = (Button) findViewById(R.id.button1);
		
		
		cobaButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent cobaIntent = new Intent(KiosActivity.this, MenuActivity.class);
                cobaIntent.putExtra("id", idK);
                KiosActivity.this.startActivity(cobaIntent);
            }
        });
		
	}

    private void setText(String id){
        HomeController homeController = new HomeController();

        HashMap<String,String> tempdata = homeController.getKiosk(id, getContentResolver());

        text = (TextView) findViewById(R.id.textView1);
        text.setText("Nama Kios    : "+ tempdata.get(Kiosk.Kiosks.KEY_NAME)+".\n"+
                     "Nama Pemilik : "+ tempdata.get(Kiosk.Kiosks.KEY_OWNER)+".\n"+
                     "Telepon      : "+ tempdata.get(Kiosk.Kiosks.KEY_TELP)+".\n"+
                     "Keterangan   : "+ tempdata.get(Kiosk.Kiosks.KEY_DESC)+".\n");
    }
}
