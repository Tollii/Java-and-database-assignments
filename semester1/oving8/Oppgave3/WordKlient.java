import static javax.swing.JOptionPane.*;

class WordKlient{
	public static void main(String[]args){
		String tekst = showInputDialog("Skriv en tekst:");
		Word ord = new Word(tekst);

		while(true){
			String tellerLest = showInputDialog("1. Finn antall ord \n2. Gjennomsnitt ordlengde \n3. Tekst i store bokstaver \n4. Hent tekst \n5. Bytt ut ord \n6. Gjennomsnitt ord per periode");
			int teller = Integer.parseInt(tellerLest);

			switch (teller){
				case 1 :
					showMessageDialog(null, ord.finnOrd());
					break;

				case 2 : 
					showMessageDialog(null, ord.gjennomsnittOrdLengde());
					break;

				case 3 : 
					showMessageDialog(null, ord.getStorBokstav());
					break;

				case 4 :
					showMessageDialog(null, ord.getTekst());
					break;

				case 5 : 
					String bytte = showInputDialog("Skriv inn det du vil bytte:");
					String bytte1 = showInputDialog("Skriv inn det du vil bytte med:");
					showMessageDialog(null, ord.replace(bytte, bytte1));
					break;

				case 6 : 
					showMessageDialog(null, ord.gjennomsnittPeriode());
					break;

				default : 
				showMessageDialog(null,"Tast inn et gyldig tall");
			} // switch slutt
		} // while slutt
	} // main slutt
} // class slutt