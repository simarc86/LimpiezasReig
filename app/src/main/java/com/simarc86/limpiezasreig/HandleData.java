package com.simarc86.limpiezasreig;

import android.os.Environment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by marctamaritromero on 26/11/15.
 */
public class HandleData {

    private String providers;
    private ArrayList products;
    private ArrayList clients;

    int countline;

    public HandleData(ArrayList products){
        this.products = products;
    }


    public void readCSV(){

        String baseDir = Environment.getExternalStorageDirectory().getAbsolutePath();
        String fileName = "productos.csv";

// Not sure if the / is on the path or not
        File file = new File(baseDir + File.separator + fileName);


        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";

        try {

            br = new BufferedReader(new FileReader(file));
            while ((line = br.readLine()) != null) {
                Product product = new Product();


                // use comma as separator
                String[] lines = line.split(cvsSplitBy);

                product.setName(lines[0]);

                if (lines.length>1)
                    if (lines[1].length()>0)
                        product.setPrice(Float.parseFloat(lines[1]));

                product.setStock(5);
                product.setQuantity(0);

                products.add(product);

                countline++;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public ArrayList<Product> getProducts(){
        return products;
    }

    public ArrayList<Client> getClients(){
        return clients;
    }

    public void setClients(ArrayList clients) {
        this.clients = clients;
    }
}
