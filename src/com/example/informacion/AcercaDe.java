package com.example.informacion;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.widget.VideoView;



public class AcercaDe extends Activity {

	VideoView intro;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_acerca_de);
		
		intro = (VideoView) findViewById(R.id.videoView1);

		intro.setVideoURI(Uri.parse("android.resource://com.example.informacion/" + R.raw.lego));
		intro.requestFocus();
		intro.start();

	}

}
