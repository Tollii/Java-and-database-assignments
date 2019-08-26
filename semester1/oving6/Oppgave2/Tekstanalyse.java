import static javax.swing.JOptionPane.*;

class Tekstanalyse{

	public int[] antallTegn = new int[30];
	private String tekst;

	public Tekstanalyse(String tekst){
		this.tekst = tekst;
		sorterTabell();
	} // Konstruktor slutt


	// Sorterer teksten inn i bokstaver og tegn
	public void sorterTabell(){
		for(int x = 0; x < tekst.length(); x++){
			char tegn = tekst.charAt(x);
			int verdi = tegn;

			// Legger til 1 for a-z
			if(verdi >=97 && verdi <= 122){
				antallTegn[verdi - 97]++;
			}

			// Legger til 1 for A-Z
			else if(verdi >= 65 && verdi <= 90){
				antallTegn[verdi - 65]++;
			}

			// Legger til 1 for æ og Æ
			else if(verdi == 280 || verdi == 198){
				antallTegn[26]++;
			}

			// Legger til 1 for ø og Ø
			else if(verdi == 248 || verdi == 216){
				antallTegn[27]++;
			}

			// Legger til 1 for å og Å
			else if(verdi == 229 || verdi == 197){
				antallTegn[28]++;
			} else {
				antallTegn[29]++;
			} // Logikk tre slutt
		} // for slutt
	} // sorterTabell slutt


	// Teller hvor mange bokstaver det er i teksten
	public int antallBokstaver(){
		int sum = 0;
		for(int x = 0; x < antallTegn.length - 1; x++){
			sum += antallTegn[x];
		} // for slutt

		return sum;
	} // antallBokstaver slutt


	// Finner hvor mange forskjellige bokstaver det er i teksten
	public int forskjellig(){

		int forskjellig = 0;
		for(int x = 0; x < antallTegn.length - 1; x++){
			if(antallTegn[x] != 0){
				forskjellig++;
			} // if slutt
		} // for slutt
	return forskjellig;
	} // forskjellig slutt


	// Finner hvor mange prosent som ikke er bokstaver
	public double prosent(){
		
		double sum = antallBokstaver();
		double prosent = antallTegn[29] * 100 / (antallTegn[29] + sum);
		return prosent;
	}


	// Finner hvor mange ganger den innskrevede bokstaven forekommer
	public int forekomst(char input){
		int verdi = input;

		if(verdi >=97 && verdi <= 122){
			return antallTegn[verdi - 97];
		}
		else if(verdi >= 65 && verdi <= 90){
			return antallTegn[verdi - 65];
		} else {
			return 1;
		} // Logikktre slutt
	} // forekomst slutt






	
//		-------------------------------------------







	



	// Finner hvilke bokstav(er) som kommer opp mest i teksten
	public char max(){
		int max = 0;
		char uni = 'b';
		for(int x = 0; x < antallTegn.length - 1; x++){
			if(antallTegn[x] > max){
				max = x;
			} // if slutt
		} // for slutt
		max += 97;
		uni = (char) max;
		return uni;
	} // max slutt













} // class slutt
