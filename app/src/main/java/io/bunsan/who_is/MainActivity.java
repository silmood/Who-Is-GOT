package io.bunsan.who_is;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

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

        generateNewIndex();
        updateIndex();
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
        boolean correctAnswer = currentImageIndex == currentNameIndex;

        return answer == correctAnswer;
    }
}
