import static javax.swing.JOptionPane.*;
import java.util.Random;
class RandomKlient{
	public static void main(String[]args){
		while(true){

			// Henter inndata
			String nedreLestInt = showInputDialog("Nedre heltall grense");
			int inedre = Integer.parseInt(nedreLestInt);

			String ovreLestInt = showInputDialog("Ovre heltall grense");
			int iovre = Integer.parseInt(ovreLestInt);

			String nedreLestDouble = showInputDialog("Nedre desimal grense");
			double dnedre = Double.parseDouble(nedreLestDouble);

			String ovreLestDouble = showInputDialog("Ovre desimal grense");	
			double dovre = Double.parseDouble(ovreLestDouble);

			// Printer resultat i dialog boks og terminal, eller feilmelding.
			if (inedre < iovre || dnedre < dovre || dnedre == dovre || iovre == inedre){

				showMessageDialog(null, "Heltall: " + nesteHeltall(inedre, iovre) + "\nDesimal tall: " + nesteDesimaltall(dnedre, dovre));
				for(int x = 0; x < 10;x++){
					System.out.println("Heltall: " + nesteHeltall(inedre, iovre) + "\nDesimal tall: " + nesteDesimaltall(dnedre, dovre));
				} // for slutt
			} else {
				showMessageDialog(null, "Ovre verdi er lavere eller lik nedre verdi, tast inn paa nytt");
			} // else slutt




		} // while slutt
	} // main slutt
} // class slutt