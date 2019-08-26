class Person{

	private final String fornavn;
	private final String etternavn;
	private final int fødselsår;

	public Person(String fornavn, String etternavn, int fødselsår){
		this.fornavn = fornavn;
		this.etternavn = etternavn;
		this.fødselsår = fødselsår;
	} // Constructor end


	// Henter fornavn
	public String getFornavn(){
		return fornavn;
	} // Method end


	// Henter etternavn
	public String getEtternavn(){
		return etternavn;
	} // Method end


	// Henter fodselsår
	public int getFødselsår(){
		return fødselsår;
	} // Method end
}// class end