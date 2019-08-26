import static javax.swing.JOptionPane.*;
class Valuta{
	private final String navn;
	private final double ratio;

	public Valuta(String navn, double ratio){
		this.navn = navn;
		this.ratio = ratio;
	}

	// Konverterer fra en annen valuta til NOK
	public double tilNOK(double valuta){
		return valuta * ratio;
	}

	// Konverterer fra NOK til en annen valuta
	public double fraNOK(double nok){
		return nok/ratio;
	}

	// Henter valutakursen
	public double getRatio() {
		return ratio;
	}

	// Henter navnet paa valutaen
	public String getNavn() {
		return navn;
	}
}
