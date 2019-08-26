import java.util.Random;

class TabellRandom{
		public static void main(String[]args){

			/* Deklarerer tabellen og grense variabelen */
			int[] antall = new int[10];
			int grense = 1000;

			for(int x = 0; x < grense; x++){

				/*Tilfeldig tallgenerator */
				java.util.Random random = new java.util.Random();
				int tall = random.nextInt(10);
				antall[tall]++;
			} // for slutt

			/* Printer i terminal hvor mange av hvert tall har blitt generert */
			String message = "Antall tall generert = " + grense + "\nAntall 0 = " + antall[0] + "\nAntall 1 = " + antall[1] + "\nAntall 2 = " + antall[2] + "\nAntall 3 = " + antall[3]
			+ "\nAntall 4 = " + antall[4] + "\nAntall 5 = " + antall[5] + "\nAntall 6 = " + antall[6] + "\nAntall 7 = " + antall[7] + "\nAntall 8 = " + antall[8]
			+ "\nAntall 9 = " + antall[9];
			System.out.println(message);

	} // main slutt
} // class slutt