package es.viedma.matchy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class main extends Activity {
	
	private Button start_button, training_button;
	private TextView nextKidId;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		MatchyDb.setup(this);
		setContentView(R.layout.main);
		
		nextKidId = (TextView) findViewById(R.id.nextKidId);
		nextKidId.setText("Next kid ID: " + MatchyDb.instance.nextKidId());
		
		start_button = (Button) findViewById(R.id.newSample);
		start_button.setOnClickListener(new OnClickListener(){
			public void onClick(View v){
				MatchyDb.instance.createNewKid();
				Intent myIntent = new Intent();
				myIntent.setClassName("es.viedma.matchy", "es.viedma.matchy.Cards");
				startActivity(myIntent);
			}
		});
		
		training_button = (Button) findViewById(R.id.training);
		training_button.setOnClickListener(new OnClickListener(){
			public void onClick(View v){
				Intent myIntent = new Intent();
				myIntent.setClassName("es.viedma.matchy", "es.viedma.matchy.Training");
				startActivity(myIntent);
			}
		});
		
//		questions_button = (Button) findViewById(R.id.questions);
//		questions_button.setOnClickListener(new OnClickListener(){
//			public void onClick(View v){
//				Intent myIntent = new Intent();
//				myIntent.setClassName("es.viedma.matchy", "es.viedma.matchy.Questions");
//				startActivity(myIntent);
//			}
//		});
	}
}