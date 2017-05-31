package com.simarc86.limpiezasreig;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by marctamaritromero on 6/12/16.
 */
public class AlbaranAdapter extends ArrayAdapter<Product> {
    public AlbaranAdapter(Context context, int resource, List<Product> objects) {
        super(context, resource, objects);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.product_albaran, null);
        }

        final Product product = getItem(position);

        if (product != null) {
            TextView textName = (TextView) v.findViewById(R.id.name_product);
            TextView textPrice = (TextView) v.findViewById(R.id.price_product);
            TextView textQuantity = (TextView) v.findViewById(R.id.quantity_text);
            TextView textValue = (TextView) v.findViewById(R.id.value_text);




            if (textName != null) {
                textName.setText(product.getName());
            }

            if (textPrice != null) {
                textPrice.setText(String.valueOf(product.getPrice()));
            }


            if (textQuantity != null) {
                textQuantity.setText(String.valueOf(product.getQuantity()));
            }

            if (textValue != null) {
                textValue.setText(String.valueOf(product.getQuantity()*product.getPrice())+" €");
            }
        }

        return v;
    }

}
