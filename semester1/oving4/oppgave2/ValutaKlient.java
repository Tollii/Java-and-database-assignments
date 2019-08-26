import static javax.swing.JOptionPane.*;
class ValutaKlient{
	public static void main(String[]args){
		// Lager nye objekter
		Valuta euro = new Valuta("EUR", 9.70);
		Valuta dollar = new Valuta("USD", 8.42);
		Valuta hkd = new Valuta("HKD", 1.07);

		// Loop som starter programmet paa nytt
		while(true){ 

			// Velger enten 1, fra NOK eller 2. til NOK
			String fraOgTilLest = showInputDialog("1. Fra NOK" + "\n2. Til NOK");
			int fraOgTil = Integer.parseInt(fraOgTilLest);

			// Velger hvilken valuta. 1.EUR 2.USD 3.HKD
			String tellerLest = showInputDialog("1. " + euro.getNavn() + "\n2. " + dollar.getNavn() + "\n3. " + hkd.getNavn());
			int teller = Integer.parseInt(tellerLest);

			// Switch som kjorer den riktige koden styrt av variabelen teller
			switch(teller){

				// NOK til EUR, EUR til NOK
				case 1 :

					if(fraOgTil == 1){
						String nokLest = showInputDialog("NOK");
						double nok = Double.parseDouble(nokLest);
						showMessageDialog(null, euro.fraNOK(nok) + " EUR");

					} else {
						String valutaLest = showInputDialog("EUR");
						double valuta = Double.parseDouble(valutaLest);
						showMessageDialog(null, euro.tilNOK(valuta) + " NOK");
					}
					break;

				// NOK til USD, USD til NOK
				case 2: 

					if(fraOgTil == 1){
						String nokLest = showInputDialog("NOK");
						double nok = Double.parseDouble(nokLest);
						showMessageDialog(null, dollar.fraNOK(nok) + " USD");

					} else {
						String valutaLest = showInputDialog("USD");
						double valuta = Double.parseDouble(valutaLest);
						showMessageDialog(null, dollar.tilNOK(valuta) + " NOK");
					}
					break;

				// NOK til HKD, USD til NOK
				case 3: 

					if(fraOgTil == 1){
						String nokLest = showInputDialog("NOK");
						double nok = Double.parseDouble(nokLest);
						showMessageDialog(null, hkd.fraNOK(nok) + " HKD");
					} else {
						String valutaLest = showInputDialog("HKD");
						double valuta = Double.parseDouble(valutaLest);
						showMessageDialog(null, hkd.tilNOK(valuta) + " NOK");
					}
					break;

				default :
					showMessageDialog(null, "Ugyldig verdi");
			}//Switch slutt
		}//While slutt
	}//main slutt
}//class slutt
