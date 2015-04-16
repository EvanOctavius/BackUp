package id.ac.ui.cs.ppl_c02.kantin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class HomeActivity extends Activity{
	
	ImageButton cobaButton;
	ImageButton cobaButton1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.coba);
				
		addListenerButton();
	}
	
	private void addListenerButton(){
		cobaButton = (ImageButton) findViewById(R.id.imageButton1);
		cobaButton1 = (ImageButton) findViewById(R.id.imageButton2);
		
		
		cobaButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent cobaIntent = new Intent(HomeActivity.this, AppNavHomeActivity.class);
				//cobaIntent.putExtra("key", "nilainya");
				HomeActivity.this.startActivity(cobaIntent);
			}
		});
		
		cobaButton1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent cobaIntent = new Intent(HomeActivity.this, MenuActivity.class);
				//cobaIntent.putExtra("key", "nilainya");
				HomeActivity.this.startActivity(cobaIntent);
			}
		});
	}
}
