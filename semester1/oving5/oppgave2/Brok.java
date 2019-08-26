import static javax.swing.JOptionPane.*;
class Brok{
	private int teller;
	private int nevner;

	public Brok(int teller, int nevner){
		this.teller = teller;
		if(nevner == 0){
			 throw new IllegalArgumentException("Nevner kan ikke vaere 0");
		} else {
			this.nevner = nevner;
		} // else slutt
	} // Brok konstruktor 1 slutt

	public Brok(int teller){
		this.teller = teller;
		this.nevner = 1;
	} // Brok konstruktor 2 slutt

	public int getTeller(){
		return teller;
	} // getTeller slutt

	public int getNevner(){
		return nevner;
	} // getNevner slutt

	public String getSvar(){
		if(nevner == teller){
			return "er lik 1";
		} else if(teller == 0){
			return "er lik 0";
		}else {
			return teller + " over " + nevner;
		} // else slutt
	} // getSvar slutt


	// Addisjon
	public void addere(Brok b1){
		teller = (teller * b1.getNevner()) + (b1.getTeller() * nevner);
		nevner = nevner * b1.getNevner();
 	} // addere slutt

 	// Subtraksjon
	public void subtrahere(Brok b1){
		teller = (teller * b1.getNevner()) - (b1.getTeller() * nevner);
		nevner = nevner * b1.getNevner();
 	} // subtrahere slutt

 	// Multiplikasjon
	public void multiplikasjon(Brok b1){
		teller = teller * b1.getTeller();
		nevner = nevner * b1.getNevner();
 	} // multiplikasjon slutt

 	// Divisjon
	public void dividere(Brok b1){
		teller = b1.getTeller() * nevner;
		nevner = teller * b1.getNevner();
 	} // dividere slutt


 	public String toString(){
 		return "Teller: " + teller + "\nNevner: " + nevner;
 	} // toString slutt
} // class slutt