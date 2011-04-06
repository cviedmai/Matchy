package es.viedma.matchy;

import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

public class QuestionsManager {
	private String[] questions;
	private String current_question;
	private TextView question;
	private Integer next_index = 0;
	private Integer amount;
	private EditText answer;
	
	public QuestionsManager (String[] questions, TextView question, EditText answer){
		this.questions = questions;
		this.question = question;
		this.answer = answer;
		this.current_question = questions[0];
		if (next_index == 0) nextQuestion();
		this.amount = questions.length;
	}
	
	private void setQuestion(){
		this.question.setText(current_question);
		this.answer.setText("");
		Log.i("QuestionsManager", "Setting up next question");
	}

	public String currentQuestion(){
		return this.current_question;
	}
	
	public void nextQuestion(){
		current_question = questions[next_index];		
		this.next_index += 1;
		Log.i("QuestionsManager", "showing next question: " + current_question);
		setQuestion();
	}
	
	public Boolean moreQuestions(){
		Boolean more = next_index != amount;
		Log.i("QuestionsManager", "Are there more questions?: " + more.toString());
		return more;
	}
}
