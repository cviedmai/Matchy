package es.viedma.matchy;

import android.content.res.Resources;
import android.util.Log;
import android.widget.ImageView;

public class CardsManager {
	private String[] cards;
	private ImageView questionCard;
	private String currentCard;
	private Integer next_index = 0;
	private Integer amount;
	private Resources resources;
	
	public CardsManager (String[] cards, ImageView questionCard, Resources resources){
		this.cards = cards;
		this.questionCard = questionCard;
		this.resources = resources;
		
		this.amount = cards.length;
		//If is the first time we load the card manager
		if (next_index == 0) nextCard();
	}
	
	private void setQuestionCard(){
		int id = resources.getIdentifier(currentCard(), "drawable", "es.viedma.matchy");
		questionCard.setImageResource(id);
		Log.i("CardsManager", "Setting up new question card");
	}
	
	public String currentCard(){
		return currentCard;
	}
	
	public void nextCard(){
		currentCard = cards[next_index];
		//this.brand_view.setText(current_question);
		this.next_index += 1;
		Log.i("CardsManager", "showing next card");
		setQuestionCard();
	}
	
	public Boolean moreCards(){
		Boolean more = next_index != amount;
		Log.i("CardsManager", "Are there more cards?: " + more.toString());
		return more;
		
	}

}
