import static javax.swing.JOptionPane.*;

class Klient{
	public static void main(String[]args){

		// Deklarerer personer
		Person andreasTolnes = new Person("Andreas", "Tolnes", 1998);
		Person torjeThorkildsen = new Person("Torje", "Thorkildsen", 1998);
		Person ericYounger = new Person("Eric", "Younger", 1990);

		// Deklarerer arbeidstakere
		Arbtaker andreasTolnesArbtaker = new Arbtaker(andreasTolnes, 123456, 2017, 40000);
		Arbtaker torjeThorkildsenArbtaker = new Arbtaker(torjeThorkildsen, 654321, 2014, 50000);
		Arbtaker ericYoungersArbtaker = new Arbtaker(ericYounger, 112233, 2010, 60000);

		// Deklarerer brukergrensesnitt
		KlientUI klientUI = new KlientUI(andreasTolnesArbtaker, torjeThorkildsenArbtaker, ericYoungersArbtaker);

		// UI loop
		int valg = klientUI.lesValgAnsatt();
		while (valg >= 0){
			int valg2 = klientUI.lesValg();
			while (valg2 >= 0){
				klientUI.utførValgtOppgave(valg2);
				valg2 = klientUI.lesValg();
			} // while end
			valg = klientUI.lesValgAnsatt();
		} // while end
	} // main end
} // class end


class KlientUI{

	private final String[] ALTERNATIVER = {"Vis ansattinformasjon", "Sett ny månedslønn", "Vis brutto årslønn", "Vis skatteinformasjon", "Sjekk ansatt før årstall", "Tilbake"};
	private final String[] ANSATTE = new String[4];
	private final int VIS_ANSATTINFO = 0;
	private final int SET_MÅNEDSLØNN = 1;
	private final int VIS_BRUTTOÅR = 2;
	private final int VIS_SKATTINFO = 3;
	private final int SJEKK_ANSIENNITET = 4;
	private final int TILBAKE = 5;

	private Arbtaker andreasTolnesArbtaker;
	private Arbtaker torjeThorkildsenArbtaker;
	private Arbtaker ericYoungersArbtaker;
	private Arbtaker valgAnsatt;

	public KlientUI(Arbtaker andreasTolnesArbtaker, Arbtaker torjeThorkildsenArbtaker, Arbtaker ericYoungersArbtaker){
		this.andreasTolnesArbtaker = andreasTolnesArbtaker;
		this.torjeThorkildsenArbtaker = torjeThorkildsenArbtaker;
		this.ericYoungersArbtaker = ericYoungersArbtaker;
		ANSATTE[0] = andreasTolnesArbtaker.getForm();
		ANSATTE[1] = torjeThorkildsenArbtaker.getForm();
		ANSATTE[2] = ericYoungersArbtaker.getForm();
	} // Constructor end


	// Henter ansatt valgt fra brukeren
	public int lesValgAnsatt(){
		int valg = showOptionDialog(null, "Velg ansatt", "Ansattprogram", 0, DEFAULT_OPTION, null, ANSATTE, ANSATTE[0]);
			switch (valg){
			case 0 : 
				this.valgAnsatt = andreasTolnesArbtaker;
				break;

			case 1: 
				this.valgAnsatt = torjeThorkildsenArbtaker;
				break;

			case 2 :
				this.valgAnsatt = ericYoungersArbtaker;
				break;

			default : 
				break;
		} // switch end
		return valg;
	} // Method end


	// Henter operasjon valgt fra brukeren
	public int lesValg(){
		int valg = showOptionDialog(null, "Velg operasjon", "Ansattprogram", 0, PLAIN_MESSAGE, null, ALTERNATIVER, ALTERNATIVER[0]);
		if(valg == TILBAKE){
			valg = -1;
		}
		return valg;
	} // Method end


	// Utfører valgt operasjon
	public void utførValgtOppgave(int valg2){
		switch (valg2){
			case VIS_ANSATTINFO : 
				showMessageDialog(null, "Navn: " + valgAnsatt.getForm() + "\nFødselsår: " + valgAnsatt.getFødselsår() + "\nAlder: " + valgAnsatt.getAlder() + "\nAnsattnummer: " + valgAnsatt.getArbtakernr() + "\nMånedslønn: " + valgAnsatt.getMånedslønn()  + " NOK\nAnsettelsesår: " + valgAnsatt.getAnsettelsesår() + "\nAnsiennitet: " + valgAnsatt.getAnsiennitet() + " år");
				break;

			case SET_MÅNEDSLØNN :
				String aLest = showInputDialog("Skriv inn ny månedslønn: ");
				int a = Integer.parseInt(aLest);
				if(a < 0){
					showMessageDialog(null,"Tast inn en positiv verdi:");
					break;
				} // if end
				valgAnsatt.setMånedslønn(a);
				showMessageDialog(null, "Ny månedslønn er nå " + valgAnsatt.getMånedslønn() + " NOK");
				break;

			case VIS_BRUTTOÅR :
				showMessageDialog(null, valgAnsatt.getBruttoÅrslønn() + " NOK");
				break;

			case VIS_SKATTINFO :
				showMessageDialog(null, "Årlig skattetrekk: " + valgAnsatt.getSkattTrukketPerÅr() + "\nMånedlig skattetrekk: " + valgAnsatt.getSkattTrukketPerMåned() + "\nSkatteprosent : " + valgAnsatt.getSkatteprosent() + "%");
				break;

			case SJEKK_ANSIENNITET :
				String bLest = showInputDialog(null, "Skriv inn årstall: ");
				int b = Integer.parseInt(bLest);
				boolean verdi = valgAnsatt.getAnsattMerEnnÅr(b);

				String melding = (verdi == true) ? valgAnsatt.getFornavn() + " var ikke en ansatt før " + b + "." : valgAnsatt.getFornavn() + " ble ansatt før " + b + ".";
				showMessageDialog(null, melding);
				break;

			default :
				break;
		} // switch end
	} // method end
} // class end


