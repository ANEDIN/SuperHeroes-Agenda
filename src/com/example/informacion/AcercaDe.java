package com.example.informacion;

import com.example.informacion.R;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.MediaController;
import android.widget.VideoView;



public class AcercaDe extends Activity {

	VideoView intro;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_acerca_de);
		Uri video = Uri.parse("android.resource://com.pac.myapp/raw/master");
		intro= (VideoView) findViewById(R.id.videoView1);
		Uri url = Uri.parse("android.resource://com.example.informacion/" + R.raw.lego);
		intro.setVideoURI(url);
		intro.requestFocus();
		intro.start();

	}

}
