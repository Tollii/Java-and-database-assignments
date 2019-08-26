import static javax.swing.JOptionPane.*;
import java.util.Random;
class Terning{
	private final String navn;
	private int poeng;
	private int terningkast;

	public Terning(String navn){
		this.navn = navn;
	}


	// Kaster terning og legger til summen i poeng variabelen.
	public int kastTerning(){
		java.util.Random terning = new java.util.Random(); // Lager tilfeldig tallgenerator
		terningkast = terning.nextInt(6) + 1;

		// Adderer naar poeng er under hundre, subtraherer naar poeng er over 100
		if(poeng <= 100){
			poeng += terningkast;
		} else {
			poeng -= terningkast;
		} // Else slutt
		return terningkast;
	} // kastTerning slutt

	// Henter terningkast
	public int getTerningkast(){
		return terningkast;
	} // getTerningkast slutt

	// Henter poengsum
	public int getSumPoeng(){
	return poeng;
	} // getSumPoeng slutt

	// Henter navn til spiller
	public String getNavn(){
	return navn;
	} // getNavn slutt

	// Sjekker om spillet er ferdig
	public void erFerdig(){
		if(poeng == 100){
			showMessageDialog(null, navn + " har vunnet!");
			System.exit(0);
		} // if slutt
	} // erFerdig slutt

	public String toString(){
		return "Spiller navn: " + navn + "\nPoengsum: " + poeng + "\nSiste terningkast: " + terningkast;
	} // toString slutt
} // class slutt