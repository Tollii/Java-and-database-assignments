import static javax.swing.JOptionPane.*;

class TekstKlient{
	public static void main(String[]args){

		String tekstLest = showInputDialog("Skriv inn en tekst: ");
		Tekstanalyse tekst = new Tekstanalyse(tekstLest);
		System.out.println("Innskrevet tekst: " + tekstLest);
		System.out.println("Antall bokstaver " + tekst.antallBokstaver() + "\nProsent ikke bokstaver: " + tekst.prosent() + "\nForskjellig " + tekst.forskjellig() + "\nMax: " + tekst.max());


		String forekomstLest = showInputDialog("Skriv inn en bokstav: ");
		char charforekomst = forekomstLest.charAt(0);
		int forekomst = tekst.forekomst(charforekomst);
		System.out.println("Forekomst: " + forekomst);


	} // main slutt
} // class slutt