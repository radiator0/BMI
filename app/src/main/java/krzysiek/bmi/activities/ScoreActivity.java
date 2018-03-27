package krzysiek.bmi.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import java.text.DecimalFormat;
import krzysiek.bmi.R;
import krzysiek.bmi.calculation.BMI;
import krzysiek.bmi.calculation.BmiClassification;

public class ScoreActivity extends AppCompatActivity {

    private static final String BMI_MESSAGE_KEY = "BMI_KEY";

    public static void start(Context context, Double score) {
        Intent starter = new Intent(context, ScoreActivity.class);
        starter.putExtra(BMI_MESSAGE_KEY,score.toString());
        context.startActivity(starter);
    }
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        double bmi = Double.parseDouble(getIntent().getStringExtra(BMI_MESSAGE_KEY));
        DecimalFormat df = new DecimalFormat(getString(R.string.bmi_pattern));
        TextView textView = findViewById(R.id.bmi_value);
        textView.setText(df.format(bmi));

        int color;
        BmiClassification classification = BMI.getClassification(bmi);
        if(classification.equals(BmiClassification.UNDERWEIGHT)){
            color = ContextCompat.getColor(getApplicationContext(), R.color.underweight);
        }else if(classification.equals(BmiClassification.NORMAL_WEIGHT)){
            color = ContextCompat.getColor(getApplicationContext(), R.color.healty_weight);
        }else if(classification.equals(BmiClassification.OVERWEIGHT)){
            color = ContextCompat.getColor(getApplicationContext(), R.color.overweight);
        }else{
            color = ContextCompat.getColor(getApplicationContext(), R.color.black_overlay);
        }
        textView.setTextColor(color);
    }
}