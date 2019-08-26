import lejos.hardware.motor.*;
import lejos.hardware.lcd.*;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.NXTTouchSensor;
import lejos.hardware.sensor.NXTLightSensor;
import lejos.hardware.sensor.NXTColorSensor;
import lejos.hardware.sensor.NXTSoundSensor;
import lejos.hardware.port.Port;
import lejos.hardware.Brick;
import lejos.hardware.BrickFinder;
import lejos.hardware.ev3.EV3;
import lejos.hardware.Keys;
import lejos.hardware.sensor.SensorModes;
import lejos.robotics.SampleProvider;
import lejos.hardware.sensor.*;
import lejos.hardware.lcd.TextLCD;


public class hitratunnel{
	public static void main(String[] args) throws Exception{

		/* Definerer sensorer: */
		Brick brick = BrickFinder.getDefault();
		Port s1 = brick.getPort("S1"); // fargesensor
		Port s2 = brick.getPort("S2"); // lydsensor
		Port s3 = brick.getPort("S3"); // trykksensor




		/* Definerer en fargesensor */
		EV3ColorSensor fargesensor = new EV3ColorSensor(s1); // ev3-fargesensor
		SampleProvider fargeLeser = fargesensor.getMode("RGB");  // svart = 0.01..
		float[] fargeSample = new float[fargeLeser.sampleSize()];  // tabell som innholder avlest verdi

		/* Definerer en trykksensor */
		SampleProvider trykksensor = new EV3TouchSensor(s3);
		float[] trykkSample = new float[trykksensor.sampleSize()]; // tabell som inneholder avlest verdi

		/* Definerer en lydsensor */
		NXTSoundSensor sound = new NXTSoundSensor(s2);
		SampleProvider lydleser = sound.getDBMode();
		float[] lyd = new float[30];



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
	 		}
		}
		Thread.sleep(500);


		/* Setter hastighet på roboten */
		Motor.A.setSpeed(360);// 2 RPM
		Motor.B.setSpeed(360);// 2 RPM
		Motor.C.setSpeed(600);// 2 RPM

		boolean fortsett = true;

		while (fortsett){ 	// Fortsett s� lenge roboten ikke treffer noe
			fargeLeser.fetchSample(fargeSample, 0);
			if (fargeSample[0]*100 > svart){   // sjekk sort linje
				Motor.A.forward();
				Motor.B.forward();        // viftearm
				System.out.println("hvit");
			} else {
			 /* Kjør framover */
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
				System.out.println("svart");
			}

			sound.fetchSample(lyd,0);
			if(lyd[0] > 0.84){
			 	Motor.A.stop();
        		Motor.B.stop();
	     		System.out.println("Trafikk");
			  	Thread.sleep(4000);
			}

			trykksensor.fetchSample(trykkSample, 0);
			if (trykkSample[0] > 0){
			 	System.out.println("Avslutter");
			 	fortsett = false;
			}




		}
	}
}
