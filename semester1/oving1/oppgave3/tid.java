import static javax.swing.JOptionPane.*;
class tid {
	public static void main(String[]args){
		//Henter sekund verdi fra dialogboks og gjør om til integer
		String sekunderString = showInputDialog("Sekunder: ");
		int sekunder = Integer.parseInt(sekunderString);

		//Tar sekundende og deler de på 60 for å få minutt, deler minutt på 60 for å få timer, subtraherer 60 * timer for å få resterende minutter hvis verdien overstiger en time
		//Og subtraherer timer * 60 * 60 og minutter * 60 fra sekundende
		int minutter = sekunder / 60; 
		int timer = minutter / 60;
		minutter = minutter - 60 * timer;
		int sekundDialog = sekunder - (minutter * 60) - (timer * 3600);

		showMessageDialog(null,sekundDialog +" sekund(er) "+ minutter + " minutt(er) og " + timer + " time(r)");


	}
}