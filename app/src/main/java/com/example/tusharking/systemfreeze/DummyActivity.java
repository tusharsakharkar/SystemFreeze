package com.example.tusharking.systemfreeze;

import android.app.Activity;
import android.os.Bundle;

public class DummyActivity extends Activity {
    @Override
    public void onCreate( Bundle icicle ) {
        super.onCreate( icicle );
        finish();
    }
}