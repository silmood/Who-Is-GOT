package io.bunsan.who_is;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

/**
 * Created by silmood on 9/14/17.
 */

public class CheatActivity extends AppCompatActivity {

    private TextView labelAnswer;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

        labelAnswer = (TextView) findViewById(R.id.label_answer);

    }

    private boolean getCorrectAnswer(){
        return getIntent().getBooleanExtra(MainActivity.KEY_ANSWER_EXTRA, true);
    }


    public void showAnswer(View button){
        int answerId = getCorrectAnswer() ? R.string.label_true : R.string.label_false;
        String answer = getString(answerId);
        labelAnswer.setText(answer);
    }
}
