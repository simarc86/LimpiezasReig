package com.simarc86.limpiezasreig;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

/**
 * Created by marctamaritromero on 6/12/16.
 */
public class SignActivity extends Activity {
    /** Called when the activity is first created. */

    private SignView signView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signature);

        signView = (SignView) findViewById(R.id.signature_canvas);
    }

    public void clearCanvas(View v) {
        signView.clearCanvas();
    }

}


