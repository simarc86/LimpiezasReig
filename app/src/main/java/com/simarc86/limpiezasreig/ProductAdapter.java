package com.simarc86.limpiezasreig;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * Created by marctamaritromero on 24/11/15.
 */
public class ProductAdapter extends ArrayAdapter<Product> {

    TextView totalTxt;
    Float priceTotal;

    public ProductAdapter(Context context, int resource, ArrayList products, TextView totalTxt) {
        super(context, resource, products);
        this.totalTxt = totalTxt;
        priceTotal = Float.valueOf((String) this.totalTxt.getText());
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.product_view, null);
        }

        final Product product = getItem(position);

        if (product != null) {
            TextView textName = (TextView) v.findViewById(R.id.name_product);
            TextView textPrice = (TextView) v.findViewById(R.id.price_product);
            TextView textStock = (TextView) v.findViewById(R.id.stock_product);
            TextView textQuantity = (TextView) v.findViewById(R.id.quantity_text);
            Button removeBtn = (Button) v.findViewById(R.id.remove_btn);
            Button addBtn = (Button) v.findViewById(R.id.add_btn);





            if (textName != null) {
                textName.setText(product.getName());
            }

            if (textPrice != null) {
                textPrice.setText(String.valueOf(product.getPrice()));
            }

            if (textStock != null) {
                textStock.setText(String.valueOf(product.getStock()));
            }

            if (textQuantity != null) {
                textQuantity.setText(String.valueOf(product.getQuantity()));
            }



            if (product.getStock()<1)
                addBtn.setEnabled(false);
            else
                addBtn.setEnabled(true);

            if (product.getQuantity()<1)
                removeBtn.setEnabled(false);
            else
                removeBtn.setEnabled(true);


            if (removeBtn != null)
                removeBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        product.setQuantity(product.getQuantity()-1);
                        product.setStock(product.getStock()+1);
                        notifyDataSetChanged();

                    }
                });

            if (addBtn != null)
                addBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        product.setQuantity(product.getQuantity()+1);
                        product.setStock(product.getStock()-1);

                        totalTxt.setText(getPriceTotal(product.getPrice()));
                        notifyDataSetChanged();
                    }
                });


            if (product.getQuantity()>0){
                textName.setBackgroundColor(Color.CYAN);
                product.setAdded(true);
            }
            else{
                textName.setBackgroundColor(Color.WHITE);
                product.setAdded(false);
            }

            if (product.getStock()<1){
                textStock.setBackgroundColor(Color.RED);
            }else {
                textStock.setBackgroundColor(Color.WHITE);
            }

        }

        return v;
    }


    private String getPriceTotal(Float price){
        priceTotal += price;

        return String.format(String.valueOf(round(priceTotal,2)));
    }


    public static float round(float d, int decimalPlace) {
        return BigDecimal.valueOf(d).setScale(decimalPlace,BigDecimal.ROUND_HALF_UP).floatValue();
    }

}