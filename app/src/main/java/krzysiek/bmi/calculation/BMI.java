package krzysiek.bmi.calculation;

/**
 * Created by Krzysztof Pilarczyk on 08.03.2018.
 */

public abstract class BMI {
    private static final double MIN_HEALTHY_BMI = 18.5;
    private static final double MAX_HEALTHY_BMI = 25.0;
    private static final String ERROR_MESSAGE = "Invalid data";

    private double mass;
    private double height;

    public BMI(double mass, double height){
        this.mass = mass;
        this.height = height;
    }

    public double getMass() {
        return mass;
    }
    public double getHeight() {
        return height;
    }

    protected abstract boolean checkMass();
    protected abstract boolean checkHeight();
    protected abstract double calculate();

    public double calculateBmi() {
        if(dataAreValid()){
            return calculate();
        }else{
            throw new IllegalArgumentException(ERROR_MESSAGE);
        }
    }

    private boolean dataAreValid() {
        return checkMass() && checkHeight();
    }

    public static BmiClassification getClassification(double bmi){
        if(bmi < MIN_HEALTHY_BMI){
            return BmiClassification.UNDERWEIGHT;
        }else if(bmi<=MAX_HEALTHY_BMI){
            return BmiClassification.NORMAL_WEIGHT;
        }else{
            return BmiClassification.OVERWEIGHT;
        }
    }

}
