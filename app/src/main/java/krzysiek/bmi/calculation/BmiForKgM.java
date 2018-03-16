package krzysiek.bmi.calculation;

/**
 * Created by Krzysztof Pilarczyk on 08.03.2018.
 */

public class BmiForKgM extends BMI {
    private static final double MIN_MASS = 40;
    private static final double MAX_MASS = 300;

    private static final double MIN_HEIGHT = 0.5;
    private static final double MAX_HEIGHT = 3.0;

    public BmiForKgM(double mass, double height){
        super(mass,height);
    }

    protected boolean checkMass(){
        return (getMass()>=MIN_MASS && getMass()<=MAX_MASS);
    }

    protected boolean checkHeight(){
        return (getHeight()>=MIN_HEIGHT && getHeight()<=MAX_HEIGHT);
    }

    protected double calculate(){
        return getMass() / (getHeight()*getHeight());
    }
}
