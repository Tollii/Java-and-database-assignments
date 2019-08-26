class Arbtaker{

	private final Person personalia;
	private final int arbtakernr;
	private final int ansettelsesår;
	private int månedslønn;
	private final double skatteprosent;

	public Arbtaker(Person personalia, int arbtakernr, int ansettelsesår, int månedslønn){
		this.personalia = personalia;
		this.arbtakernr = arbtakernr;
		this.ansettelsesår = ansettelsesår;
		this.månedslønn = månedslønn;
		this.skatteprosent = 25;
	} // Constructor end


	// Henter klassen Person
	public Person getPersonalia(){
		return personalia;
	} // Method end


	// Henter arbeidstakerens nummer
	public int getArbtakernr(){
		return this.arbtakernr;
	} // Method end


	// Henter hvilket år arbeidstakeren ble ansatt
	public int getAnsettelsesår(){
		return this.ansettelsesår;
	} // Method end


	// Henter månedslønnen
	public int getMånedslønn(){
		return this.månedslønn;
	} // Method end


	// Setter ny månedslønn 
	public void setMånedslønn(int nyMåndedslønn){
		this.månedslønn = nyMåndedslønn;
	} // Method end


	// Henter skatteprosent
	public double getSkatteprosent(){
		return this.skatteprosent;
	} // Method end


	// Henter årlig bruttolønn
	public int getBruttoÅrslønn(){
		return getMånedslønn() * 12;
	} // Method end


	// Returnerer hvor mange år arbeidstakeren har vært ansatt
	public int getAnsiennitet(){
		java.util.GregorianCalendar kalender = new java.util.GregorianCalendar();
		return kalender.get(java.util.Calendar.YEAR) - getAnsettelsesår();
	} // Method end


	// Sjekker om den ansatta har vært ansatt før eller etter input 
	public boolean getAnsattMerEnnÅr(int år){
		boolean verdi = (getAnsettelsesår() > år) ? true : false; // True er ansatt mer enn input, false er ansatt mindre enn input
		return verdi;
	} // Method end


	// Henter hvor mye som er skattet for angitt månedslønn
	public double getSkattTrukketPerMåned(){
		return getMånedslønn() * (getSkatteprosent()/100);
	} // Method end


	// Henter etternavn og navn i denne rekkefølgen
	public String getForm(){
		return personalia.getEtternavn() + ", " + personalia.getFornavn();
	} // Method end


	// Henter skatten som blir trukket over et år
	public double getSkattTrukketPerÅr(){
		return getSkattTrukketPerMåned() * 10 + getSkattTrukketPerMåned() * 0.5;
	} // Method end


	// Henter alderen på den ansatte
	public int getAlder(){
		java.util.GregorianCalendar kalender = new java.util.GregorianCalendar();
		return kalender.get(java.util.Calendar.YEAR) - this.getFødselsår();
	} // Method end

	public String getFornavn(){
		return personalia.getFornavn();
	} // Method end


	public String getEtternavn(){
		return personalia.getEtternavn();
	}// Method end


	public int getFødselsår(){
		return personalia.getFødselsår();
	} // Method end
}// class end