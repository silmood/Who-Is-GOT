package io.bunsan.who_is;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String KEY_ANSWER_EXTRA = "key_answer";
    public static final String LOG_TAG = MainActivity.class.getCanonicalName();
    private static final String KEY_IMAGE_INDEX = "image_index";
    private static final String KEY_NAME_INDEX = "name_index";

    private Character[] characters = {new Character(R.string.arya, R.drawable.arya),
            new Character(R.string.bronn, R.drawable.bronn),
            new Character(R.string.podrick, R.drawable.podrick)
    };

    private int currentImageIndex;
    private int currentNameIndex;
    private Random random;

    private ImageView imgCharacter;
    private TextView labelName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        random = new Random();

        imgCharacter = (ImageView) this.findViewById(R.id.img);
        labelName = (TextView) this.findViewById(R.id.name);

        this.findViewById(R.id.btn_true).setOnClickListener(this);
        this.findViewById(R.id.btn_false).setOnClickListener(this);

        if (isFirstTime(savedInstanceState)) {
            generateNewIndex();
        } else {
            restoreIndexes(savedInstanceState);
        }
        updateIndex();

    }

    private void restoreIndexes(Bundle savedInstanceState) {
        currentImageIndex = savedInstanceState.getInt(KEY_IMAGE_INDEX);
        currentNameIndex = savedInstanceState.getInt(KEY_NAME_INDEX);
    }

    private boolean isFirstTime(Bundle savedInstanceState) {
        return savedInstanceState == null;
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(LOG_TAG, "On start");
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.d(LOG_TAG, "On resume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(LOG_TAG, "On pause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(LOG_TAG, "On stop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(LOG_TAG, "On destroy");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt(KEY_IMAGE_INDEX, currentImageIndex);
        outState.putInt(KEY_NAME_INDEX, currentNameIndex);
    }

    private void generateNewIndex() {
        currentImageIndex = random.nextInt(3);
        currentNameIndex = random.nextInt(3);
    }

    private void updateIndex() {
        Character imageCharacter = characters[currentImageIndex];
        Character nameCharacter = characters[currentNameIndex];

        imgCharacter.setImageResource(imageCharacter.getImageResId());
        labelName.setText(nameCharacter.getNameResId());
    }

    @Override
    public void onClick(View viewClicked) {
        boolean result = getResult(viewClicked);

        Toast.makeText(this,
                result ? R.string.label_correct : R.string.label_incorrect,
                Toast.LENGTH_SHORT).show();

        generateNewIndex();
        updateIndex();
    }

    private boolean getResult(View viewClicked) {
        boolean answer = viewClicked.getId() == R.id.btn_true;
        boolean correctAnswer = correctAnswer();

        return answer == correctAnswer;
    }

    private boolean correctAnswer() {
        return currentImageIndex == currentNameIndex;
    }

    public void launchAnswerActivity(View button){
        Intent answerIntent = new Intent(this, CheatActivity.class);
        answerIntent.putExtra(KEY_ANSWER_EXTRA, correctAnswer());

        startActivity(answerIntent);
    }
}
