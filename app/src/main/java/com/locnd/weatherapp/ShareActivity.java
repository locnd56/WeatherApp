package com.locnd.weatherapp;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import java.io.File;


/**
 * Created by Oracle on 4/21/2015.
 */
public class ShareActivity extends Activity{
    private File imagePath;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.share_activity);
        shareImage();
    }
    public void shareImage() {
        // TODO Auto-generated method stub
        Intent share = new Intent(Intent.ACTION_SEND);
        share.setType("image/*");
        imagePath = new File(Environment.getExternalStorageDirectory() + "/screenshot.png");
        Uri uri = Uri.fromFile(imagePath);
        share.putExtra(Intent.EXTRA_STREAM, uri);
        startActivity(Intent.createChooser(share, "Share Image!"));
    }

}
