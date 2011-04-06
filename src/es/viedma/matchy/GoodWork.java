package es.viedma.matchy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class GoodWork extends Activity {

	ImageButton button;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.goodwork);
		button = (ImageButton) findViewById(R.id.goodwork);
		button.setOnClickListener(new OnClickListener(){
			public void onClick(View v){
				Intent myIntent = new Intent();
				myIntent.setClassName("es.viedma.matchy", "es.viedma.matchy.main");
				startActivity(myIntent);
			}
		});
	}

}
