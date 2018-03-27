package krzysiek.bmi;

import org.junit.Test;

import krzysiek.bmi.calculation.BMI;
import krzysiek.bmi.calculation.BmiForFtLbs;
import krzysiek.bmi.calculation.BmiForKgM;

import static org.junit.Assert.*;

public class UnitTests {

    @Test
    public void testIsCorrectKgMCalculation(){
        BMI bmiCounter = new BmiForKgM(60,1.70);
        double bmi = bmiCounter.calculateBmi();
        assertEquals(20.761, bmi, 0.001);
    }

    @Test
    public void testIsCorrectFtLbsCalculation(){
        BMI bmiCounter = new BmiForFtLbs(100,5);
        double bmi = bmiCounter.calculateBmi();
        assertEquals(19.520,bmi,0.001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIsKgMException(){
        BMI bmiCounter = new BmiForKgM(0,0);
        bmiCounter.calculateBmi();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIsFtLbsException(){
        BMI bmiCounter = new BmiForFtLbs(0,0);
        bmiCounter.calculateBmi();
    }

    @Test
    public void testIsCorrectKgMMinValuesCalculation(){
        BMI bmiCounter = new BmiForKgM(40,0.5);
        double bmi = bmiCounter.calculateBmi();
        assertEquals(160,bmi,0.001);
    }

    @Test
    public void testIsCorrectFtLbsMinValuesCalculation(){
        BMI bmiCounter = new BmiForFtLbs(88,1.64);
        double bmi = bmiCounter.calculateBmi();
        assertEquals(159.666, bmi, 0.001);
    }

    @Test
    public void testIsCorrectKgMMaxValuesCalculation(){
        BMI bmiCounter = new BmiForKgM(300, 3.0);
        double bmi = bmiCounter.calculateBmi();
        assertEquals(33.333, bmi, 0.001);
    }

    @Test
    public void testIsCorrectFtLbsMaxValuesCalculation(){
        BMI bmiCounter = new BmiForFtLbs(661,9.84);
        double bmi = bmiCounter.calculateBmi();
        assertEquals(bmi,33.314,0.001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIsKgMTooSmallValuesException(){
        BMI bmiCounter = new BmiForKgM(39.99, 0.49);
        bmiCounter.calculateBmi();
    }

    @Test(expected =  IllegalArgumentException.class)
    public void testIsFtLbsTooSmallValuesException(){
        BMI bmiCounter = new BmiForFtLbs(87.9, 1.639);
        bmiCounter.calculateBmi();
    }


    @Test(expected = IllegalArgumentException.class)
    public void testIsKgMTooBigValuesException(){
        BMI bmiCounter = new BmiForKgM(300.01,3.01);
        bmiCounter.calculateBmi();
    }

    @Test(expected =  IllegalArgumentException.class)
    public void testIsFtLbsTooBigValuesException(){
        BMI bmiCounter = new BmiForFtLbs(661.01, 9.841);
        bmiCounter.calculateBmi();
    }

    @Test(expected =  IllegalArgumentException.class)
    public void testIsBadKgException(){
        BMI bmiCounter = new BmiForFtLbs(20, 1.70);
        bmiCounter.calculateBmi();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIsBadMException(){
        BMI bmiCounter = new BmiForFtLbs(88, -1.0);
        bmiCounter.calculateBmi();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIsBadLbsException(){
        BMI bmiCounter = new BmiForFtLbs(10,4);
        bmiCounter.calculateBmi();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIsBadFtException(){
        BMI bmiCounter = new BmiForFtLbs(300,10);
        bmiCounter.calculateBmi();
    }
}