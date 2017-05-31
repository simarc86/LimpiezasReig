package com.simarc86.limpiezasreig;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by marctamaritromero on 6/12/16.
 */
public class AlbaranActivity extends Activity{

    private SignView signView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.albaran_view);

        //Variables locales
        String totalTxt;
        TextView importAlbaran;
        TextView dateText;
        TextView idAlbaran;

        //Set up variables
        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        totalTxt = bundle.getString("total");
        ArrayList<Product> products = (ArrayList<Product>) bundle.getSerializable("products");

        //Obtenemos views
        ListView listView = (ListView) findViewById(R.id.list_albaran);
        importAlbaran = (TextView) findViewById(R.id.import_albaran);
        dateText = (TextView) findViewById(R.id.date);
        idAlbaran = (TextView) findViewById(R.id.idAlbaran);
        signView = (SignView) findViewById(R.id.signature_canvas);

        //Modificamos views
        importAlbaran.setText(totalTxt);
        dateText.setText(getDate());
        idAlbaran.setText(getString(R.string.albaran_txt) + getAlbaranId());

        //Set up adapter
        AlbaranAdapter albaranAdapter = new AlbaranAdapter(this, android.R.layout.simple_list_item_1, products);
        listView.setAdapter(albaranAdapter);

    }





    /*
    *   Obtiene el id del Albaran
    * */
    private String getAlbaranId() {
        Context context = this;
        SharedPreferences sharedPref = context.getSharedPreferences(
                getString(R.string.preference_id_albaran), Context.MODE_PRIVATE);


        long storedId = sharedPref.getInt(getString(R.string.preference_id_albaran), 0);

        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(getString(R.string.preference_id_albaran),pumpUpAlbaran(storedId));
        editor.commit();

        return "A / " + String.valueOf(storedId);

    }

    /*
    *   Obtiene la fecha actual
    * */
    private String getDate(){
        DateFormat df = new SimpleDateFormat("dd MM yyyy, HH:mm");
        String date = df.format(Calendar.getInstance().getTime());
        return date;
    }


    /*
    *   Aumenta la version del albaran
    * */
    private int pumpUpAlbaran(long oldId){
        return   (int) (oldId+1);
    }
}
