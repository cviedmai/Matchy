package es.viedma.matchy;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Training extends Activity {

	ImageView brand;
	private CardsManager cardsManager;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.training);
		Resources res = getResources(); 
		brand = (ImageView) findViewById(R.id.brand);
		
		cardsManager = new CardsManager(res.getStringArray(R.array.trainingCards), brand, getResources());
	}
	
	public void clickHandler(View v){
		//ImageButton b = (ImageButton) v;
		//Integer lastAnswer = (Integer) b.getTag();
		//MatchyDb.instance.insertQuestion(cardsManager.currentQuestion(), categories[lastAnswer]);
		if  (cardsManager.moreCards()) {
			cardsManager.nextCard();				
			//lastInput.setText(lastAnswer);	
		}
		else {
			Intent myIntent = new Intent();
			myIntent.setClassName("es.viedma.matchy", "es.viedma.matchy.GoodWork");
			startActivity(myIntent);
		}						
	}
}