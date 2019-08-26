/**
* DP8 - Arv og polymorfi
*
* Arv: Bruker strukturen og oppførselen til en suoerclass i en subclass
* class B extends A{...}
* Klasse B får mange av de samme medlemmene som A, og A er en direkte superklasse for B, og B er en subklassef for A.
* For å teste om to klasser har et arv-forhold, spør deg selv om objekter av den ene klassen også er objekter av den andre
* For eksempel Jarlsberg ER ost, Brunost ER ost. I motsetning til aggregering hvor Ost HAR fett, ost HAR proteiner.t
*
* Polymorfi: Endrer oppførselen til en super class i subclassen
*
*/


/*
abstract class Person {
	private final String navn;
	
	public String getNavn() {
		return navn;
	}

	public Person(String navn) {
		this.navn = navn;
	}

	@Override
	public String toString() {
		return "[Person: navn=" + getNavn()+ "]";
	}
	
	public abstract String getRolle();
}


class Ansatt extends Person {
	private double lønn;

	public Ansatt(){
		super(navn);
	}

	public double getLønn(){
		return lønn;
	}

	public void setLønn(double nyLønn){
		this.lønn = nyLønn;
	}

	@Override
	public String getRolle(){
		return "Min rolle";
	}
}
*/

