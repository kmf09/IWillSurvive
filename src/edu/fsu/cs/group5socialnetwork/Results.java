package edu.fsu.cs.group5socialnetwork;

import java.util.ArrayList;
import java.util.Random;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Results extends Activity {
	static int mProfileScore = 0, mArticHighScore = 0, mDesertHighScore = 0, mForrestHighScore = 0, mMountainHighScore = 0, mSwampHighScore = 0;
	String mCurrentQuiz;
	Random mRand = new Random(); 
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_results);

		mCurrentQuiz = getIntent().getStringExtra("quizType");
		ArrayList<Question> mQuestion = Profile.mQuestion;
		int currentScore = 0;
		mProfileScore = Profile.mProfileScore;

		TextView currentScoreItem = (TextView) findViewById(R.id.currentScoreTV);
		TextView highScoreItem    = (TextView) findViewById(R.id.highScoreTV);
		TextView WinOrLose        = (TextView) findViewById(R.id.winloseTV);
		TextView factsTV          = (TextView) findViewById(R.id.factsTV);

		// Calculate total points awarded
		for (int i = 0; i < mQuestion.size(); i++)
			currentScore += mQuestion.get(i).mPoints_awarded;

		currentScore += mProfileScore - 20;

		if (mCurrentQuiz.equals("articQuiz") && currentScore > mArticHighScore)
		{
				mArticHighScore = currentScore;
				highScoreItem.setText("" + mArticHighScore);
		}
		else if (mCurrentQuiz.equals("desertQuiz") && currentScore > mDesertHighScore)
		{
				mDesertHighScore = currentScore;
				highScoreItem.setText("" + mDesertHighScore);
		}
		else if (mCurrentQuiz.equals("forestQuiz") && currentScore > mForrestHighScore)
		{
				mForrestHighScore = currentScore;
				highScoreItem.setText("" + mForrestHighScore);
		}
		else if (mCurrentQuiz.equals("mountainQuiz") && currentScore > mMountainHighScore)
		{
				mMountainHighScore = currentScore;
				highScoreItem.setText("" + mMountainHighScore);
		}
		else if (mCurrentQuiz.equals("swampQuiz") && currentScore > mSwampHighScore)
		{
				mSwampHighScore = currentScore;
				highScoreItem.setText("" + mSwampHighScore);
		}
		
		currentScoreItem.setText("" + currentScore);
		
		if (currentScore >= 90)
		{
			WinOrLose.setText("You survived!");
			WinOrLose.setTextColor(Color.GREEN);
		}
		else if (currentScore >= 70)
		{
			WinOrLose.setText("Barely made it");
			WinOrLose.setTextColor(Color.YELLOW);
		}
		else
		{
			WinOrLose.setText("Better luck next time");
			WinOrLose.setTextColor(Color.RED);
		}
		
		String[] facts = { "A human being can survive an average of three to five days without the intake of water.", 
				"High ground is warmer but a thermometer can't account for wind chill factors which often make high ground more dangerous.", 
				"If water reaches boiling point it's safe to drink regardless of how long it's been boiling.",
				"ALL fur bearing mammals are safe to eat and will provide you with nutrients and calories.",
				"All six legged insects are safe to eat and will provide you with nutrients and calories.",
				"Almost all freshwater fish and birds are safe to eat and will provide you with nutrients and calories.",
				"Most plants will harm you, make you sick, or poison you.",
				"A tourniquet should be the last resort and is only a viable choice if someone has lost a limb or a limb has been partially rendered from the body by a horrible accident.",
				"The pressure that a tourniquet applies will severely damage blood vessels and can often result in tissue death, making it possible that a limb will need to be amputated.",
				"A rattlesnake does not always warn someone of an impending attack by shaking it’s rattle.",
				"Sucking the venom out of a snake bite has never been proven effective.",
				"The proper way to treat snakebite is to clean the wound thoroughly with soap and water and to keep the bitten area below heart level.",
				"Heat will increase swelling on a sprain.",
				"Just because an animal ( or Bear Grylls ) eats it, doesn't mean you should. For example, deer and squirrels eat poisonous mushrooms and berries.",
				"The diverse and plentiful pathogens that cause human illness can be in any water, anywhere.  Boil, filter or chemically treat your water every time to be safe."};
		factsTV.setText(facts[mRand.nextInt(facts.length)]);
	}

	// When play again is clicked
	public void playAgainClick(View v) {
		Intent myIntent = new Intent(Results.this, FirstCategories.class);
		startActivity(myIntent);
	}
}
