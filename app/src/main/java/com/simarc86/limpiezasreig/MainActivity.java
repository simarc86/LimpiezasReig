package com.simarc86.limpiezasreig;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;





public class MainActivity extends Activity {

    public final static String EXTRA_TOTAL = "com.simarc86.limpiezasreig.TOTALPRICE";
    public final static String EXTRA_PRODUCTS = "com.simarc86.limpiezasreig.TOTALPRICE";

    ListView listView ;
    ArrayList products = new ArrayList();
    ProductAdapter productAdapter;
    ProductAdapter productTappedAdapter;

    private boolean cartTapped;

    Button cartBtn;
    Button sendBtn;
    TextView totalTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get ListView object from xml
        listView = (ListView) findViewById(R.id.listView);
        totalTxt = (TextView) findViewById(R.id.price_total);
        cartBtn = (Button) findViewById(R.id.cart_btn);
        sendBtn = (Button) findViewById(R.id.albaran_btn);

        HandleData handleData = new HandleData(products);
        handleData.readCSV();

        products = handleData.getProducts();
        productAdapter = new ProductAdapter(this,android.R.layout.simple_list_item_1,products,totalTxt);

        // Assign adapter to ListView
        listView.setAdapter(productAdapter);



        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //TODO: Generar albaran
                TextView priceText = (TextView) findViewById(R.id.price_total);
                String total = priceText.getText().toString();


                Intent intent = new Intent(MainActivity.this, AlbaranActivity.class);

                Bundle bundle = new Bundle();
                bundle.putSerializable("products", getProductsAdds());
                bundle.putString("total",total);
                intent.putExtras(bundle);

//                intent.putExtra(EXTRA_TOTAL, message);
                startActivity(intent);

                //composeMail();
                //sendMail();
            }
        });


        cartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isCartTapped()){
                    listView.setAdapter(productAdapter);
                    setTitle(getResources().getString(R.string.title_activity_main));
                    cartBtn.setText(getResources().getString(R.string.title_activity_cart));

                }else{
                    productTappedAdapter = new ProductAdapter(MainActivity.this,android.R.layout.simple_list_item_1,getProductsAdds(),totalTxt);
                    listView.setAdapter(productTappedAdapter);
                    setTitle(getResources().getString(R.string.title_activity_cart));
                    cartBtn.setText(getResources().getString(R.string.title_activity_main));
                }

                setCartTapped(!isCartTapped());
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_carrier) {
            if (isCartTapped()){
                listView.setAdapter(productAdapter);
                item.setTitle(getResources().getString(R.string.title_activity_cart));
                setTitle(getResources().getString(R.string.title_activity_main));
            }else{
                productTappedAdapter = new ProductAdapter(this,android.R.layout.simple_list_item_1,getProductsAdds(),totalTxt);
                listView.setAdapter(productTappedAdapter);
                item.setTitle(getResources().getString(R.string.title_activity_main));
                setTitle(getResources().getString(R.string.title_activity_cart));
            }

            setCartTapped(!isCartTapped());

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public ArrayList<Product> getProductsAdds(){
        ArrayList products_tapped = new ArrayList();

        for (Object prod_aux : products){
            Product product = (Product) prod_aux;
            if (product.isAdded())
                products_tapped.add(product);

        }

        return products_tapped;
    }


    public void composeMail(){

    }
    public void sendMail(){

        Bitmap bm = screenShot();
        File file = saveBitmap(bm, "albaran.png");
        Log.i("chase", "filepath: "+file.getAbsolutePath());
        Uri uri = Uri.fromFile(new File(file.getAbsolutePath()));
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_TEXT, "Albaran");
        shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
        shareIntent.putExtra(Intent.EXTRA_EMAIL  , new String[]{"simarc@gmail.com"});
        shareIntent.setType("image/*");
        shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        startActivity(Intent.createChooser(shareIntent, "share via"));


    /*send by mail
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("message/rfc822");
        i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"simarc@gmail.com"});
        i.putExtra(Intent.EXTRA_SUBJECT, "Albaran");
        i.putExtra(Intent.EXTRA_TEXT   , "body of email");
        try {
            startActivity(Intent.createChooser(i, "Send mail..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(MainActivity.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
        }
        */

    }

    public boolean isCartTapped() {
        return cartTapped;
    }

    public void setCartTapped(boolean cartTapped) {
        this.cartTapped = cartTapped;
    }

    public Bitmap screenShot() {
        View view = findViewById(android.R.id.content).getRootView();

        Bitmap bitmap = Bitmap.createBitmap(view.getWidth(),
                view.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        view.draw(canvas);
        return bitmap;
    }
    private static File saveBitmap(Bitmap bm, String fileName){
        final String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Screenshots";
        File dir = new File(path);
        if(!dir.exists())
            dir.mkdirs();
        File file = new File(dir, fileName);
        try {
            FileOutputStream fOut = new FileOutputStream(file);
            bm.compress(Bitmap.CompressFormat.PNG, 90, fOut);
            fOut.flush();
            fOut.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file;
    }


}
