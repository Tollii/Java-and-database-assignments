import static javax.swing.JOptionPane.*;
class BrokBeregning{
	public static void main(String[]args){

		while(true){


			// Henter teller fra bruker
			String tellerLest111 = showInputDialog("1. Skriv inn telleren: ");
			int tellerLest11 = Integer.parseInt(tellerLest111);

			// Henter nevner fra bruker
			String nevnerLest111 = showInputDialog("1. Skriv inn nevneren: ");
			int nevnerLest11 = Integer.parseInt(nevnerLest111);
			if(nevnerLest11 == 0){
				throw new IllegalArgumentException("Nevner kan ikke vaere 0");
			}

			Brok b1 = new Brok(tellerLest11, nevnerLest11);


			// Henter teller fra bruker
			String tellerLest1 = showInputDialog("2. Skriv inn telleren: ");
			int tellerLest = Integer.parseInt(tellerLest1);

			// Henter nevner fra bruker
			String nevnerLest1 = showInputDialog("2. Skriv inn nevneren: ");
			int nevnerLest = Integer.parseInt(nevnerLest1);
			if(nevnerLest == 0){
				throw new IllegalArgumentException("Nevner kan ikke vaere 0");
			}

			Brok b2 = new Brok(tellerLest, nevnerLest);

			// Henter regneart
			String artLest = showInputDialog("1. Addisjon: \n2. Subtraksjon \n3. Multiplikasjon \n4. Divisjon");
			int art = Integer.parseInt(artLest);

			// Velger regneart
			switch(art){
				case 1 :
					b1.addere(b2);
					break;

				case 2 :
					b1.subtrahere(b2);
					break;

				case 3 :
					b1.multiplikasjon(b2);
					break;

				case 4 : 
					b1.dividere(b2);
					break;

				default : 
					showMessageDialog(null, "Ugyldig verdi.");
					continue;
			} // switch slutt
		showMessageDialog(null, b1.getSvar());
		}// loop slutt
	} // main slutt
} // class slutt