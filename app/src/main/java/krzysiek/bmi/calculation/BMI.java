package krzysiek.bmi.calculation;

/**
 * Created by Krzysztof Pilarczyk on 08.03.2018.
 */

public abstract class BMI {
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
}
