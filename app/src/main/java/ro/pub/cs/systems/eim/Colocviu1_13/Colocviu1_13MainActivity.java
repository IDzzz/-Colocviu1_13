package ro.pub.cs.systems.eim.Colocviu1_13;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Colocviu1_13MainActivity extends AppCompatActivity {
    private TextView cardinalTextView;
    private Button north_button, east_button, south_button, west_button, navigate_button;
    private int cardinals_count = 0;

    private ButtonClickListener buttonClickListener = new ButtonClickListener();

    private class ButtonClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.north_button:
                    if (cardinalTextView.length() != 0)
                        cardinalTextView.append(",");
                    cardinalTextView.append(north_button.getText());
                    cardinals_count++;
                    break;
                case R.id.east_button:
                    if (cardinalTextView.length() != 0)
                        cardinalTextView.append(",");
                    cardinalTextView.append(east_button.getText());
                    cardinals_count++;
                    break;
                case R.id.south_button:
                    if (cardinalTextView.length() != 0)
                        cardinalTextView.append(",");
                    cardinalTextView.append(south_button.getText());
                    cardinals_count++;
                    break;
                case R.id.west_button:
                    if (cardinalTextView.length() != 0)
                        cardinalTextView.append(",");
                    cardinalTextView.append(west_button.getText());
                    cardinals_count++;
                    break;
                case R.id.navigate_button:
                    Intent intent = new Intent(getApplicationContext(), Colocviul1_13SecondaryActivity.class);
                    intent.putExtra(Constants.CARDINALS_TEXT, cardinalTextView.getText().toString());
                    startActivityForResult(intent, Constants.SECONDARY_ACTIVITY_REQUEST_CODE);
                    cardinals_count = 0;
                    cardinalTextView.setText("");
                    break;
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_colocviu1_13_main);

        cardinalTextView = (TextView) findViewById(R.id.cardinals_text_view);

        north_button = (Button) findViewById(R.id.north_button);
        north_button.setOnClickListener(buttonClickListener);

        east_button = (Button) findViewById(R.id.east_button);
        east_button.setOnClickListener(buttonClickListener);

        south_button = (Button) findViewById(R.id.south_button);
        south_button.setOnClickListener(buttonClickListener);

        west_button = (Button) findViewById(R.id.west_button);
        west_button.setOnClickListener(buttonClickListener);

        navigate_button = (Button) findViewById(R.id.navigate_button);
        navigate_button.setOnClickListener(buttonClickListener);

        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey(Constants.CARDINALS_COUNT)) {
                Log.d("Saved number", "Count= " + cardinals_count);
                cardinals_count = savedInstanceState.getInt(Constants.CARDINALS_COUNT);
            }
        }
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt(Constants.CARDINALS_COUNT, cardinals_count);
        Log.d("Save number now", "Count= " + cardinals_count);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState.containsKey(Constants.CARDINALS_COUNT)) {
            Log.d("Saved number", "Count= " + cardinals_count);
            cardinals_count = savedInstanceState.getInt(Constants.CARDINALS_COUNT);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == Constants.SECONDARY_ACTIVITY_REQUEST_CODE) {
            if (resultCode == 1) {
                Toast.makeText(this, "Register", Toast.LENGTH_LONG).show();
            } else if (resultCode == -1) {
                Toast.makeText(this, "Cancel", Toast.LENGTH_LONG).show();
            }
        }
    }
}
