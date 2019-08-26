import java.util.Random;
class Temperatur{
	private final int[][] temp;


	public Temperatur(int time, int dag){
		this.temp = new int[time][dag];
	} // Konstruktor

	// Fyller tabellen med tilfeldige tall mellom min og max
	public void fyllTabell(int time, int dag){
		java.util.Random random = new java.util.Random();
		int max = 15;
		int min = -15;
		for(int i = 0; i < dag; i++){
			for( int j = 0; j < time; j++){
				this.temp[j][i] = random.nextInt(max - min + 1) + min; 
			} // for slutt
		} // for slutt
	} // fyllTabell			OK

	public int hentVerdi(int time, int dag){
		return this.temp[time][dag];
	} // hentVerdi			OK

	// Finner middeltemperaturen for hver dag
	public double middelTempDag(int time, int dag){
		double verdi = 0.0;
		for(int i = 0; i < time; i++){
			verdi += this.temp[i][dag];
		} // for slutt

		return verdi/time;
	} // middelTempDag 		OK

	// Finner middeltemperaturen for hver timenummer
	public double middelTempTime(int time, int dag){
		double verdi = 0.0;
		for(int i = 0; i < dag; i++){
			verdi += this.temp[time][i];
		} // for slutt
		return verdi/dag;
	} // middelTempTime		OK

	// Finner middeltemperaturen for hele maaneden
	public double middelTempMaaned(int time, int dag){
		double verdi = 0.0;
		int i = 0;
		int j = 0;
		for(i = 0; i < dag; i++){
			for(j = 0; j < time; j++){
				 verdi += this.temp[j][i];
			} // for slutt
		} // for slutt
		return verdi / (i * j);
	} // middelTempMaaned	OK

	public int[] middelTempGruppe(int time, int dag){
		int[] verdi = new int[5];

		// Setter verdiene i arrayen til 0
		for(int x = 0; x < 4; x++){
			verdi[x] = 0;
		} // for slutt

		// Finner middelverdien for hver dag
		double middel = 0.0;
		for(int a = 0; a < dag; a++){
			middel = this.middelTempDag(time,a);

			// Sjekker om middelverdiene passer i noen av gruppene, og adderer 1 til den gruppen som passer
			if(middel < -5){
				verdi[0]++;
			} // under 5
			if(middel >= -5 && middel <= 0){
				verdi[1]++;
			} // fra -5 til 0

			if(middel >= 0 && middel <= 5){
				verdi[2]++;
			} // fra 0 til 5

			if(middel  >= 5 && middel <= 10){
				verdi[3]++;
			} // fra 5 til 10

			if(middel > 10){
				verdi[4]++;
			} // over 10
		} // for slutt
		return verdi;
	} // middelTempGruppe OK





} // class slutt

