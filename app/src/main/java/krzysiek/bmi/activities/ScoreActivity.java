package krzysiek.bmi.activities;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import java.text.DecimalFormat;
import krzysiek.bmi.R;

public class ScoreActivity extends AppCompatActivity {
    private static final int MIN_HEALTHY_BMI = 16;
    private static final int MAX_HEALTHY_BMI = 25;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        double bmi = Double.parseDouble(getIntent().getStringExtra(getString(R.string.bmi_message_key)));
        DecimalFormat df = new DecimalFormat(getString(R.string.bmi_pattern));
        TextView textView = findViewById(R.id.bmi_value);
        textView.setText(df.format(bmi));

        int color;
        if(bmi < MIN_HEALTHY_BMI){
            color = ContextCompat.getColor(getApplicationContext(), R.color.underweight);
        }else if(bmi<=MAX_HEALTHY_BMI){
            color = ContextCompat.getColor(getApplicationContext(), R.color.healty_weight);
        }else{
            color = ContextCompat.getColor(getApplicationContext(), R.color.overweight);
        }
        textView.setTextColor(color);
    }
}