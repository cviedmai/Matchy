package es.viedma.matchy;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Preference extends Activity {
	
	private ImageView preference1, preference2;
	private PreferencesManager preferencesManager;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.preference);
		Resources res = getResources();

		preference1 = (ImageView) findViewById(R.id.preference1);
		preference2 = (ImageView) findViewById(R.id.preference2);
		
		preferencesManager = new PreferencesManager(res.getStringArray(R.array.preferences1), 
																				  res.getStringArray(R.array.preferences2),
																				  preference1, preference2,  res);
	}
	
	public void clickHandler(View v){
		String answer = "";
		String button = (String) v.getTag();
		if ("preference1".equals(button)) answer = preferencesManager.getPreference1();
		if ("preference2".equals(button)) answer = preferencesManager.getPreference2();
		MatchyDb.instance.insertPreference(answer);

		if  (preferencesManager.morePreferences()) {
			preferencesManager.nextPreference();
		}
		
		else {
			Intent myIntent = new Intent();
			myIntent.setClassName("es.viedma.matchy", "es.viedma.matchy.GoodWork");
			startActivity(myIntent);
		}						
	}
	
}