package ro.pub.cs.systems.eim.Colocviu1_13;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Colocviul1_13SecondaryActivity extends AppCompatActivity {
    private TextView cardinals_text_view;
    private Button register_button, cancel_button;

    private ButtonClickListener buttonClickListener = new ButtonClickListener();

    private class ButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.register_button:
                    setResult(1, null);
                    break;
                case R.id.cancel_button:
                    setResult(-1, null);
                    break;
            }
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colocviul1_13_secondary);

        cardinals_text_view = (TextView) findViewById(R.id.cardinals_text_view);
        Intent intent = getIntent();
        if (intent != null && intent.getExtras().containsKey(Constants.CARDINALS_TEXT)) {
            cardinals_text_view.setText(intent.getStringExtra(Constants.CARDINALS_TEXT));
        }
        register_button = (Button) findViewById(R.id.register_button);
        register_button.setOnClickListener(buttonClickListener);
        cancel_button = (Button) findViewById(R.id.cancel_button);
        cancel_button.setOnClickListener(buttonClickListener);
    }
}
