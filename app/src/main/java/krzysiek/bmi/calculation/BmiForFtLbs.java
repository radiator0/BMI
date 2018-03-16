package krzysiek.bmi.calculation;

/**
 * Created by Krzysztof Pilarczyk on 08.03.2018.
 */

public class BmiForFtLbs extends BMI {
    private static final double MIN_MASS = 88;
    private static final double MAX_MASS = 661;

    private static final double MIN_HEIGHT = 1.64;
    private static final double MAX_HEIGHT = 9.84;

    private static final double MULTIPLER = 4.88;

    public BmiForFtLbs(double mass, double height) {
        super(mass, height);
    }

    protected boolean checkMass(){
        return (getMass()>=MIN_MASS && getMass()<=MAX_MASS);
    }

    protected boolean checkHeight(){
        return (getHeight()>=MIN_HEIGHT && getHeight()<=MAX_HEIGHT);
    }

    protected double calculate(){
        return (getMass()*MULTIPLER)/ (getHeight()*getHeight());
    }
}
