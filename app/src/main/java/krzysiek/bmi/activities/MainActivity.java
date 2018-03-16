package krzysiek.bmi.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import krzysiek.bmi.calculation.BMI;
import krzysiek.bmi.calculation.BmiForFtLbs;
import krzysiek.bmi.calculation.BmiForKgM;
import krzysiek.bmi.R;


public class MainActivity extends AppCompatActivity {
    private static final boolean DEFAULT_SWITCH_POSITION = false;

    private EditText editMass;
    private EditText editHeight;
    private TextView viewMessage;
    private Switch switchUnit;

    private BMI bmi;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.author_option){
            Intent intent = new Intent(this,AuthorActivity.class);
            startActivity(intent);
        }
        if(item.getItemId() == R.id.save){
            SharedPreferences sharedPref = getPreferences(Activity.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString(getString(R.string.mass_key), editMass.getText().toString());
            editor.putString(getString(R.string.height_key), editHeight.getText().toString());
            editor.putBoolean(getString(R.string.switch_key), switchUnit.isChecked());
            editor.apply();
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonCount = findViewById(R.id.count);
        editMass = findViewById(R.id.mass);
        editHeight = findViewById(R.id.height);
        viewMessage = findViewById(R.id.message);
        switchUnit = findViewById(R.id.unit);

        SharedPreferences sharedPref = getPreferences(Activity.MODE_PRIVATE);
        String defaultMass = sharedPref.getString(getResources().getString(R.string.mass_key), getString(R.string.default_mass_val));
        String defaultHeight = sharedPref.getString(getResources().getString(R.string.height_key), getString(R.string.default_height_val));
        boolean defaultSwitchPosition = sharedPref.getBoolean(getResources().getString(R.string.switch_key),DEFAULT_SWITCH_POSITION);
        editMass.setText(defaultMass);
        editHeight.setText(defaultHeight);
        switchUnit.setChecked(defaultSwitchPosition);

        final Intent intent = new Intent(this,ScoreActivity.class);
        buttonCount.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {
                        String massString = editMass.getText().toString();
                        String heightString = editHeight.getText().toString();

                        String output;
                        if(massString.equals("") || heightString.equals("")){
                            output = getString(R.string.input_error_message);
                        }else{
                            double mass = Double.parseDouble(massString);
                            double height = Double.parseDouble(heightString);

                            if(switchUnit.isChecked()){
                                bmi = new BmiForFtLbs(mass,height);
                            }else{
                                bmi = new BmiForKgM(mass,height);
                            }

                            try{
                                bmi.calculateBmi();
                                Double score = bmi.calculateBmi();
                                output = getString(R.string.input_press_message);
                                intent.putExtra(getString(R.string.bmi_message_key),score.toString());
                                startActivity(intent);
                            }catch(IllegalArgumentException e){
                                output = getString(R.string.bad_values_message);
                            }
                        }
                        viewMessage.setText(output);
                    }
                }
        );

        editMass.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                setStartMessage();
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });

        editHeight.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                setStartMessage();
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });

        switchUnit.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                resetInput();
            }
        });
    }

    private void setStartMessage() {
        viewMessage.setText(getString(R.string.input_press_message));
    }

    private void resetInput(){
        editMass.setText(R.string.default_mass_val);
        editHeight.setText(R.string.default_height_val);
    }

}
