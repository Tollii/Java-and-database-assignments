import static javax.swing.JOptionPane.*;
import java.util.Random;
class TerningKlient{
	public static void main(String[]args){

		// Lager ny objekter
		Terning sp1 = new Terning("Spiller 1");
		Terning sp2 = new Terning("Spiller 2");

		// Looper spillet
		while(true){

			// Henter hvilke spiller som skal rulle
			String spillerLest = showInputDialog("1. " +  sp1.getNavn() + "\n2. " + sp2.getNavn());
			int spiller = Integer.parseInt(spillerLest);

			// Kaster terning etter valgt tall i variabelen spiller.
			switch(spiller){

				// Kaster terning for spiller 1
				case 1 :

					sp1.kastTerning();
					showMessageDialog(null,sp1.getNavn() + " kastet: " + sp1.getTerningkast());
					break;

				// Kaster terning for spiller 2
				case 2 :

					sp2.kastTerning();
					showMessageDialog(null,sp2.getNavn() + " kastet: " + sp2.getTerningkast());
					break;

				default :
				showMessageDialog(null, "Ugyldig verdi");
			} // switch slutt

			// Viser poengsum etter rulling og sjekker om noen har vunnet
			showMessageDialog(null, sp1.getNavn() + " poengsum: " + sp1.getSumPoeng() + "\n" + sp2.getNavn() + " poengsum: " + sp2.getSumPoeng());
			sp1.erFerdig();
			sp2.erFerdig();
		} // while slutt
	} // main slutt
} // class slutt