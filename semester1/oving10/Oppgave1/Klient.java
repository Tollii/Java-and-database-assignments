import java.util.Scanner;

class Klient{
	public static void main(String[]args){
		Scanner sc = new Scanner(System.in);
		Oppgaveoversikt t = new Oppgaveoversikt();

		System.out.printf("Registerer minst en ny student. (Navn, antall oppgaver)\n");
		String a = sc.nextLine();
		int b = Integer.parseInt(sc.nextLine());
		t.regStudent(a,b);
		boolean bol = true;
		while(bol){
			System.out.printf("\nVelg alternativ\n1.Hent antall studenter\n2.Registrer ny student\n3.Øk oppgaver på student\n4.Antall løste oppgaver\n5.Avslutt\n");
			int x = Integer.parseInt(sc.nextLine());

			/*
			1. Hvor mange studenter som er registrert
			2. Registrer ny student med navn og oppgaver som argument
			3. Øke antall oppgaver gjort for en enkel student
			4. Sjekke hvor mange oppgaver en student har gjort
			5. Avslutte programmet
			*/
			switch(x){
				case 1 :
					System.out.printf("\nDet er %d student(er) registrert i systemet\n", t.getAntallStudenter());
					break;

				case 2 :
					System.out.printf("\nRegisterer ny student. (Navn, antall oppgaver)\n");
					a = sc.nextLine();
					b = Integer.parseInt(sc.nextLine());
					t.regStudent(a,b);
					System.out.printf("\nNy student nå registrert\n");
					break;

				case 3 :
					System.out.printf("\nVelg student og hvor mye du vil øke med:\n");
					System.out.printf(t.toString());
					b = Integer.parseInt(sc.nextLine()); 
					int c = Integer.parseInt(sc.nextLine());
					t.økOppgaver(b,c);
					System.out.printf("\nStudenten %s har nå løst %d oppgaver\n", t.getNavn(b), t.getAntOppg(b));
					break;

				case 4 :
					System.out.printf("\nVelg student\n");
					System.out.printf(t.toString());
					b = Integer.parseInt(sc.nextLine());
					System.out.printf("\nStudenten %s har løst %d oppgaver\n", t.getNavn(b), t.getAntOppg(b));
					break;

				case 5 :
					bol = false;
					break;

				default : 
				break;
			} // switch end
		} // while end
		System.out.printf("\nEXIT\n");
	} // main end
} // class end


