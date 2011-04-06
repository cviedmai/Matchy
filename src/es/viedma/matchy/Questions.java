package es.viedma.matchy;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Questions extends Activity{

	private QuestionsManager questionsManager;
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.questions);

		Resources res = getResources(); 
		questionsManager = new QuestionsManager(
				res.getStringArray(R.array.questions), 
				(TextView) findViewById(R.id.question), 
				(EditText) findViewById(R.id.answer));

		OnClickListener listener = new OnClickListener(){
			public void onClick(View v){
				//MatchyDb.instance.insertQuestion(cardsManager.currentQuestion(), lastAnswer);
				if  (questionsManager.moreQuestions()) {
					questionsManager.nextQuestion();				
				}
				else {
					Intent myIntent = new Intent();
					myIntent.setClassName("es.viedma.matchy", "es.viedma.matchy.GoodWork");
					startActivity(myIntent);
				}
								
			}};
		
		Button done = (Button) findViewById(R.id.next_question);
		done.setOnClickListener(listener);
	}
}
