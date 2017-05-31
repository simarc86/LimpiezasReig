package com.simarc86.limpiezasreig;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by marctamaritromero on 2/5/16.
 */
public class ClientsActivity extends Activity{

    ListView clientsListView;
    ArrayList clientsArrayList;
    ClientsAdapter clientsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clients);

        clientsListView = (ListView) findViewById(R.id.clientsList);
        clientsAdapter = new ClientsAdapter(this,android.R.layout.simple_list_item_1, clientsArrayList);

        clientsListView.setAdapter(clientsAdapter);


    }

}
