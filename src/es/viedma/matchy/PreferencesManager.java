package es.viedma.matchy;

import android.content.res.Resources;
import android.util.Log;
import android.widget.ImageView;

public class PreferencesManager {
	private String[] preferences1, preferences2;
	private ImageView preferenceImage1, preferenceImage2;
	private String currentPreference1, currentPreference2;
	private Integer next_index = 0;
	private Integer amount;
	private Resources resources;
	
	public PreferencesManager (String[] preferences1, String[] preferences2, 
											   ImageView preferenceImage1, ImageView preferenceImage2, 
											   Resources resources){
		this.preferences1 = preferences1;
		this.preferences2 = preferences2;
		this.preferenceImage1 = preferenceImage1;
		this.preferenceImage2 = preferenceImage2;
		
		this.resources = resources;
		this.amount = preferences1.length;
		//If is the first time we load the preferences manager
		if (next_index == 0) nextPreference();
	}
	
	private void setPreferences(){
		int id1 = resources.getIdentifier(getPreference1(), "drawable", "es.viedma.matchy");
		preferenceImage1.setImageResource(id1);
		int id2 = resources.getIdentifier(getPreference2(), "drawable", "es.viedma.matchy");
		preferenceImage2.setImageResource(id2);
		Log.i("PreferencesManager", "Setting up new preference question");
	}
	
	public String getPreference1(){
		return currentPreference1;
	}
	
	public String getPreference2(){
		return currentPreference2;
	}
	
	public void nextPreference(){
		currentPreference1 = preferences1[next_index];
		currentPreference2 = preferences2[next_index];

		this.next_index += 1;
		Log.i("PreferencesManager", "showing next preference question");
		setPreferences();
	}
	
	public Boolean morePreferences(){
		Boolean more = next_index != amount;
		Log.i("PreferencesManager", "Are there more preferences?: " + more.toString());
		return more;		
	}

}
