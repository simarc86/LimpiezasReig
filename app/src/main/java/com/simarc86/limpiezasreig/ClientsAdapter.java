package com.simarc86.limpiezasreig;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by marctamaritromero on 2/5/16.
 */
public class ClientsAdapter extends ArrayAdapter<Client> {


    public ClientsAdapter(Context context, int resource, ArrayList clients) {
        super(context, resource, clients);
    }


    public View getView(int position, View convertView, ViewGroup parent) {


        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.client_view, null);
        }

        final Client client = getItem(position);

        if (client != null) {

            TextView textName = (TextView) v.findViewById(R.id.clientName);
            textName.setText(client.getName());


        }

        return v;
    }

}
