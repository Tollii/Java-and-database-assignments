import lejos.hardware.motor.*;
import lejos.hardware.lcd.*;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.hardware.sensor.EV3GyroSensor;
import lejos.hardware.sensor.NXTTouchSensor;
import lejos.hardware.sensor.NXTLightSensor;
import lejos.hardware.sensor.NXTColorSensor;
import lejos.hardware.sensor.NXTSoundSensor;
import lejos.hardware.sensor.NXTUltrasonicSensor;
import lejos.hardware.port.Port;
import lejos.hardware.Brick;
import lejos.hardware.BrickFinder;
import lejos.hardware.ev3.EV3;
import lejos.hardware.Keys;
import lejos.hardware.sensor.SensorModes;
import lejos.robotics.SampleProvider;
import lejos.hardware.sensor.*;

public class MinBil {
  public static void main (String[] args)  throws Exception {
    Brick brick = BrickFinder.getDefault();
    Port s1 = brick.getPort("S1"); // fargesensor
    Port s2 = brick.getPort("S2"); // trykksensor
    Port s3 = brick.getPort("S3"); // lydsensor






    // Definerer fargesensor
    EV3ColorSensor fargesensor = new EV3ColorSensor(s1); // ev3-fargesensor
    SampleProvider fargeLeser = fargesensor.getMode("RGB");  // svart = 0.01..


    /* Definerer en trykksensor */
    SampleProvider trykksensor = new EV3TouchSensor(s2);
    float[] trykkSample = new float[trykksensor.sampleSize()]; // tabell som inneholder avlest verdi

    // Definerer en lydsensor
    SampleProvider lydsensor = new NXTSoundSensor(s3);
    double soundLevel = 0;


    // Setter motor hastighet (toppfart = 900)
    Motor.A.setSpeed(450);
    Motor.C.setSpeed(450);

    // Kjor framover
    Motor.A.forward();  // Start motor A - kjor framover
    Motor.C.forward();  // Start motor C - kjor framover

    boolean fortsett = true;

    // Robot algoritme
    while(fortsett) {
        trykksensor.fetchSample(trykkSample, 0);
        if (trykkSample[0] < 1){
             // Kjor framover
            Motor.A.forward();  // Start motor A - kjor framover
            Motor.C.forward();  // Start motor C - kjor framover
        } else {
            Motor.A.backward();
            Motor.C.backward();
            Thread.sleep(500);
            Motor.A.rotate(230);  // roter 180 gr med motor A
            Motor.C.stop();
            while (Motor.A.isMoving()) Thread.yield();  // vent til rotasjon er ferdig
         }// Else slutt
    }// While slutt
  }// Main slutt
}// Class slutt
















/*
     // Rygg
     LCD.clear();        // Rens lcd-skjermen
     System.out.println("Bak 2000");
     Motor.A.backward();
     Motor.C.backward();
     Thread.sleep(2000);

     // Sving
     LCD.clear();
     System.out.println("Snu 180 grader");
     Motor.A.rotate(180);  // roter 180 gr med motor A
     Motor.C.stop();
     while (Motor.A.isMoving()) Thread.yield();  // vent til rotasjon er ferdig

     // Kjor framover
     LCD.clear();
     System.out.println("Fram 1000");
     Motor.A.forward();
     Motor.C.forward();

     Thread.sleep(1000);
     Motor.A.stop();
     Motor.C.stop();
     */