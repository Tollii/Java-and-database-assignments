class Word{
	
	private final String tekst;

	public Word(String tekst){
	 	this.tekst = tekst;
	} // Konstruktor

	// Finner antall ord i teksten
	public int finnOrd(){
		String[] tabell = tekst.split("[ ,.!?:]");
		int remove = 0;
		for(int x = 0; x < tekst.length(); x++){
			char y = tekst.charAt(x);
			if(y == '?' || y == '.' || y == ',' || y == '!' || y == ':'){
				remove++;
			} // if slutt
		} // for slutt
		int verdi = remove == 0 ? tabell.length : tabell.length - remove + 1;
		return verdi;
	} // finnOrd slutt	OK

	// Bytter ut en del av strengen med noe annet
	public String replace(String bytte, String bytte1){
		String nyTekst = tekst.replace(bytte, bytte1);
		return nyTekst;
	} // replace slutt	OK

	// Henter den originale tekten
	public String getTekst(){
		return tekst;
	} //getTekst slutt	OK

	// Gjor teksten om til store bokstaver
	public String getStorBokstav(){
		String store = tekst.toUpperCase();
		return store;
	} // getStorBokstav	OK

	// Henter gjennomsnittslengde paa orda i teksten
	public double gjennomsnittOrdLengde(){
		String noTegn = tekst.replaceAll("[ .,?!:]", "");
		int value = noTegn.length();
		double resultat = value/this.finnOrd();
		return resultat;
	} // gjennomsnittOrdLengde OK

	// Gjennomsnitt ord per periode
	public double gjennomsnittPeriode(){
		String[] tabell = tekst.split("[.!?:]");
		return this.finnOrd()/tabell.length;
	} // gjennomsnittPeriode	OK



} // class slutt