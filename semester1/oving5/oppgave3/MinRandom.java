import static javax.swing.JOptionPane.*;
import java.util.Random;
class MinRandom{
	java.util.Random tilfeldigtall = new java.util.Random();


	// intervallet [nedre, ovre]
	public int nesteHeltall(int inedre, int iovre){
		int tilfeldigInt = tilfeldigtall.nextInt(iovre-inedre + 1) + inedre;
		return tilfeldigInt;
	} // nesteHeltall slutt

	 // intervallet [nedre, ovre]
	public double nesteDesimaltall(double dnedre, double dovre){
		double tilfeldigDouble = Math.abs(dovre - dnedre) * tilfeldigtall.nextDouble() + dnedre;
		return tilfeldigDouble;
	} // nesteDesimaltall slutt
} // class slutt
