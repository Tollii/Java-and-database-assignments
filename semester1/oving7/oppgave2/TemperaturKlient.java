import static javax.swing.JOptionPane.*;

class TemperaturKlient{
	public static void main(String[]args){

		java.util.Random random = new java.util.Random();
		String timeLest = showInputDialog("Skriv inn timer");
		String dagLest = showInputDialog("Skriv inn dager");
		int time = Integer.parseInt(timeLest);
		int dag = Integer.parseInt(dagLest);
		TemperaturOld temp = new TemperaturOld(time, dag); 

		// Fyller tabellen og printer den i terminalen	OK
		temp.fyllTabell(time, dag);
		System.out.println("Tabell: Vannrett er timer, loddrett er dager");
		for(int i = 0; i < dag; i++){
			for( int j = 0; j < time; j++){
				System.out.print(temp.hentVerdi(j, i));
				if(temp.hentVerdi(j, i) > 0){
					System.out.print("      ");
				} else{
					System.out.print("     ");
				}
			} // for slutt
			System.out.println("");
		} // for slutt

		// Middeltemp for hver dag i maaneden
		System.out.println("");
		for(int a = 0; a < dag; a++){
			System.out.printf("Middelverdien for dogn %.0f er %.2f grader.\n", a + 1.0, temp.middelTempDag(time, a));
		}

		// Middeltemp for hver time i dognet
		System.out.println("");
		for(int a = 0; a < time; a++){
			System.out.printf("Middelverdien for time nummer %.0f av hvert dogn, er %.2f grader. \n", a + 1.0, temp.middelTempTime(a, dag));
		}
		// Middeltemp for maaned
		System.out.printf("\nMiddelverdien denne maaneden er %.2f grader.\n", temp.middelTempMaaned(time, dag));

		// Gruppe for middeltemp grenser
		System.out.println("\nGruppe 1 er under -5 grader \nGruppe 2 er fra -5 til 0 grader \nGruppe 3 er fra 0 til 5 grader \nGruppe 4 er fra 5 til 10 grader \nGruppe 5 er over 10 grader \n ");
		for(int a = 0; a < temp.middelTempGruppe(time, dag).length; a++){
			System.out.println("Antall dager i gruppe " + (a + 1) + " er " + temp.middelTempGruppe(time, dag)[a]);
		} // for slutt
	} // main slutt
} // class slutt



// - < -5 < 0 < 5 < 10 < +

/*

Tempraturer temp = new ... (int[dager][time])

temp.getTempForHourInDay(1, 3);

temp.getAverageForDay(3);

temp.getAverageForHour(13);

temp.getAverageForMonth();



*/