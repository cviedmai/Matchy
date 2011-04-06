package es.viedma.matchy;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Cards extends Activity {

	ImageView brand;
	private CardsManager cardsManager;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cards);
		Resources res = getResources();
		brand = (ImageView) findViewById(R.id.brand);
		cardsManager = new CardsManager(res.getStringArray(R.array.products), brand, getResources());
	}
	
	public void clickHandler(View v){
		String answer = (String) v.getTag();
		MatchyDb.instance.insertCard(cardsManager.currentCard(), answer);

		if  (cardsManager.moreCards()) {
			cardsManager.nextCard();
		}
		
		else {
			Intent myIntent = new Intent();
			myIntent.setClassName("es.viedma.matchy", "es.viedma.matchy.GoodWork");
			startActivity(myIntent);
		}						
	}
}
