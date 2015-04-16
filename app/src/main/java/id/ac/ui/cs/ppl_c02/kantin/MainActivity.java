package id.ac.ui.cs.ppl_c02.kantin;

import android.annotation.TargetApi;
import android.content.ContentResolver;
import android.content.Intent;
import android.media.Image;
import android.os.Build;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.io.BufferedReader;
import java.io.InputStreamReader;


public class MainActivity extends ActionBarActivity {


    ImageButton cobaButton;
    ImageButton cobaButton1;
    ImageButton searchButton;
    ImageButton searchButton2;
    ImageButton kaloriButton;
    ImageButton kolesterolButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.coba);


        /*Button viewK = (Button)findViewById(R.id.viewKiosk1);

        final HomeController homeController = new HomeController();
        final SearchKioskController searchKioskController = new SearchKioskController();
        final SearchMenuController searchMenuController = new SearchMenuController();

        viewK.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                homeController.getKiosk("11",getContentResolver());
                homeController.getMenu("11",getContentResolver());
                searchKioskController.getKioskByMenu("Ayam Goreng",getContentResolver());
                searchMenuController.getMenuByHarga("14000",getContentResolver());
                searchMenuController.getMenuByJenis("Chicken",getContentResolver());

            }
        });

        Button butt = (Button)findViewById(R.id.butt1);

        butt.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                new JSONParser().execute("http://ppl-c02.cs.ui.ac.id/index.php/json/json_kios");
            }
        });*/

        ImageButton butt2 = (ImageButton)findViewById(R.id.imageButton16);

        butt2.setOnClickListener(new View.OnClickListener(){
            @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
            public void onClick(View v){
                Bundle bundle = new Bundle();
                bundle.putBoolean(ContentResolver.SYNC_EXTRAS_EXPEDITED,true);
                bundle.putBoolean(ContentResolver.SYNC_EXTRAS_FORCE,true);
                bundle.putBoolean(ContentResolver.SYNC_EXTRAS_MANUAL,true);
                ContentResolver.requestSync(null,DBContentProvider.AUTHORITY,bundle);
            }
        });

        cobaButton = (ImageButton) findViewById(R.id.imageButton1);
        cobaButton1 = (ImageButton) findViewById(R.id.imageButton2);
        searchButton = (ImageButton) findViewById(R.id.imageButton11);
        searchButton2 = (ImageButton) findViewById(R.id.imageButton12);
        kaloriButton = (ImageButton) findViewById(R.id.imageButton13);
        kolesterolButton = (ImageButton) findViewById(R.id.imageButton14);

        kaloriButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent cobaIntent = new Intent(MainActivity.this, InfoKaloriActivity.class);
                MainActivity.this.startActivity(cobaIntent);
            }
        });

        kolesterolButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent cobaIntent = new Intent(MainActivity.this, InfoKolesterolActivity.class);
                MainActivity.this.startActivity(cobaIntent);
            }
        });

        searchButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent cobaIntent = new Intent(MainActivity.this, SearchKioskActivity.class);
                MainActivity.this.startActivity(cobaIntent);
            }
        });

        searchButton2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent cobaIntent = new Intent(MainActivity.this, SearchMenuActivity.class);
                MainActivity.this.startActivity(cobaIntent);
            }
        });


        cobaButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent cobaIntent = new Intent(MainActivity.this, KiosActivity.class);
                cobaIntent.putExtra("id", "01");
                MainActivity.this.startActivity(cobaIntent);
            }
        });

        cobaButton1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent cobaIntent = new Intent(MainActivity.this, MenuActivity.class);
                cobaIntent.putExtra("id", "02");
                MainActivity.this.startActivity(cobaIntent);
            }
        });

    }

    private void addListenerButton(){

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public static String executeCmd(String cmd, boolean sudo){
        try {

            Process p;
            if(!sudo)
                p= Runtime.getRuntime().exec(cmd);
            else{
                p= Runtime.getRuntime().exec(new String[]{"su", "-c", cmd});
            }
            BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));

            String s;
            String res = "";
            while ((s = stdInput.readLine()) != null) {
                res += s + "\n";
            }
            p.destroy();
            return res;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";

    }
}
