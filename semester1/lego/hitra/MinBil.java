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
import lejos.hardware.lcd.TextLCD;


public class MinBil {
    public static void main (String[] args)  throws Exception {
        Brick brick = BrickFinder.getDefault();
        Port s1 = brick.getPort("S1"); // fargesensor
        Port s2 = brick.getPort("S2"); // lydsensor
        Port s3 = brick.getPort("S3"); // trykksensor

        // Definerer fargesensor
        EV3ColorSensor fargesensor = new EV3ColorSensor(s1); // ev3-fargesensor
        SampleProvider fargeLeser = fargesensor.getMode("RGB");  // svart = 0.01..
        float[] fargeSample = new float[fargeLeser.sampleSize()];

        // Definerer en lydsensor
        NXTSoundSensor lydsensor = new NXTSoundSensor(s2);
        SampleProvider lydLeser = lydsensor.getDBMode();
        float[] lyd = new float[30];

        /* Definerer en trykksensor */
        SampleProvider trykksensor = new EV3TouchSensor(s3);
        float[] trykkSample = new float[trykksensor.sampleSize()]; // tabell som inneholder avlest verdi


        // Beregn verdi for svart
        int svart = 0;
        for (int i = 0; i<100; i++){
            fargeLeser.fetchSample(fargeSample, 0);
            svart += fargeSample[0]* 100;
        }
        svart = svart / 100 + 5;
        System.out.println("Svart: " + svart);

        boolean start =true;

        //while(!trykkSample[0] > 0){
        while (start) {
            trykksensor.fetchSample(trykkSample, 0);
            if (trykkSample[0] > 0){
                System.out.println("Starter vaskingen");
                start = false;
            }//Slutt if
        }// Slutt while
        Thread.sleep(500);


        // Setter motor hastighet (toppfart = 900)
        Motor.A.setSpeed(450);
        Motor.B.setSpeed(450);

        // Kjor framover
        Motor.A.forward();  // Start motor B - kjor framover
        Motor.B.forward();  // Start motor B - kjor framover

        boolean fortsett = true;

        while (fortsett){   // Fortsett saa lenge roboten ikke treffer noe
                fargeLeser.fetchSample(fargeSample, 0);
            if (fargeSample[0]*100 > svart){   // sjekk sort linje
                    Motor.A.forward();
                    Motor.B.forward();        // viftearm
                    System.out.println("hvit");
            } else {
                     // Kjor framover
                    System.out.println("svart");
                    Motor.A.stop();
                    Motor.B.stop();
                    Motor.A.rotate(450);
                    Motor.A.forward();
                    Motor.B.forward();
                    Thread.sleep(500);
                    Motor.A.stop();
                    Motor.B.stop();
                    Motor.A.rotate(450);
                    Motor.A.forward();
                    Motor.B.forward();
                    Thread.sleep(2000);

            }// Else slutt

            trykksensor.fetchSample(trykkSample, 0);
            if (trykkSample[0] > 0){
                System.out.println("Avslutter");
                fortsett = false;
            }// If slutt

            lydsensor.fetchSample(lyd,0);
            if(lyd[0] > 0.95){
            Motor.A.stop();
            Motor.B.stop();
            System.out.println("Trafikk");
            Thread.sleep(3000);
            }// If slutt
        }// While slutt
    }// Main slutt
}// Class slutt

// jar cvfm MinBil.jar manifest.txt *.class






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