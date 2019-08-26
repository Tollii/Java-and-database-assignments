class NyString{
	
	private final String tekst;

	public NyString(String tekst){
	 	this.tekst = tekst;
	} // Konstruktor

	// Returnerer den forste bokstaven i hvert ord.
	public String forkort(){
		String[] tabell = tekst.split("[ ,.]");
		String result = "";
		for(int x = 0; x < tabell.length; x++){
			char bokstav = tabell[x].charAt(0);
			result = result + bokstav;
		} // for slutt
		return result;
	} // forkort slutt

	public String replace(char c){
		String nyTekst = tekst.replace(c,'\0');
		return nyTekst;
	} // replace slutt













} // class slutt