import static javax.swing.JOptionPane.*;
class sekunder {
	public static void main(String[]args){
		//Henter time verdi fra dialgboks og gjør om til double
		String timerLest = showInputDialog("Timer: ");
		double timer = Double.parseDouble(timerLest);

		//Henter minutt verdi fra dialogboks og gjør om til double
		String minutterLest = showInputDialog("Minutter: ");
		double minutter = Double.parseDouble(minutterLest);

		//Henter sekund verdi fra dialogboks og gjør om til double
		String sekunderLest = showInputDialog("Sekunder: ");
		double sekunder = Double.parseDouble(sekunderLest);

		//Kalkulerer totalen i sekunder og viser resultat i dialogboks

		double total = (timer * 3600) + (minutter * 60) + sekunder;
		showMessageDialog(null, "totalt " + total + " sekunder");

	}
}