import java.io.*;
import java.util.Scanner;

class Saldo{
	public static void main(String[]args) throws IOException{
		String filnavn = "saldo.txt";
		double saldo = 0.0;
		Scanner sc = new Scanner(System.in);
		FileReader lesfil = new FileReader(filnavn);
		BufferedReader leser = new BufferedReader(lesfil);

		try{
			// Hvis den første linjen er tom så legges det til I 0.00
			String prep1 = leser.readLine();
			if(prep1 == null){
				FileWriter skriveforbTilFil = new FileWriter(filnavn, true);
				PrintWriter skriver = new PrintWriter(new BufferedWriter(skriveforbTilFil));
				skriver.printf("I %.2f", saldo);
				skriver.close();
			} // if end

			// Leser det som står på saldo.txt og legger til/tar ut penger fra kontoen
			String prep = leser.readLine();
			while(prep != null){
				if(prep.charAt(0) == 'I'){
					saldo += Double.parseDouble(prep.substring(2));
				} else if(prep.charAt(0) == 'U'){ 
					saldo -= Double.parseDouble(prep.substring(2));
				} // else if end
				prep = leser.readLine();
			} // while end
		} catch(IOException e){
			System.out.printf("\nHallo");
		} // catch end
		leser.close();

		// Lukker programmet hvis transaksjonsloggen saldo.txt gjør at kontoen har en negativ verdi.
		if(saldo < 0){
			System.out.printf("\nKontoen går i minus, avslutter programmet.\nEndre på saldo.txt for å fikse problemet\nTrykk enter for å gå videre.");
			System.in.read();
			System.exit(0);
		} // if end

		/*
		Loop med et valgtre
		1. Legg til penger
		2. Ta ut penger
		3. Avslutt
		Skriver inn hva som ble gjort inn på saldo.txt
		*/
		FileWriter lesfil1 = new FileWriter(filnavn, true);
		PrintWriter skriver1 = new PrintWriter(new BufferedWriter(lesfil1));
		try{
			boolean run = true;
			while(run){
				FileWriter skriveforbTilFil = new FileWriter(filnavn, true);
				PrintWriter skriver = new PrintWriter(new BufferedWriter(skriveforbTilFil));

				System.out.printf("\nNåværende saldo er %.2f NOK\n", saldo);
				System.out.printf("\n %d: Legg til penger\n %d: Ta ut penger\n %d: Avslutt\n",1,2,3);

				// Valgtre
				String inputLest = sc.nextLine();
				int input = Integer.parseInt(inputLest);
				String readLest;
				double read = 0.0;
				switch(input){
					case 1 :
						System.out.printf("\nSkriv inn hvor mye du vil legge inn: ");
						readLest = sc.nextLine();
						read += Double.parseDouble(readLest);
						skriver.printf("\nI %.2f",read);
						saldo += read;
						System.out.printf("\nDet ble lagt inn %.2f NOK\n", read);
						break;

					case 2 : 
						System.out.printf("\nSkriv inn hvor mye du vil ta ut: ");
						readLest = sc.nextLine();
						read += Double.parseDouble(readLest);
						if(saldo - read < 0){
							System.out.printf("\nTransaksjon utføres ikke, kontoen går i minus\n");
							break;
						} // if end
						skriver.printf("\nU %.2f", read);
						saldo -= read;
						System.out.printf("\nDet ble tatt ut %.2f NOK\n", read);
						break;

					case 3 :
						run = false;
						break;	

					default : 
						System.out.printf("\nUgyldig valg, tast inn 1, 2 eller 3.\n");
						break;

				} // switch end
				skriver.close();
			} // while end
			skriver1.printf("\nSaldo: %.2f", saldo);
		} catch(IOException e){
			System.out.printf("\nHallo");
		} // catch end
		skriver1.close();
	} // main end
} // class end