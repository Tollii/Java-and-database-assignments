import static javax.swing.JOptionPane.*;
class Multiplikasjon{
	public static void main(String[]args){
		
		// Henter nedre og øvre grense fra dialogboks


		String fraLest = showInputDialog("Fra: ");
		int fra = Integer.parseInt(fraLest);
		String tilLest = showInputDialog("Til: ");
		int til = Integer.parseInt(tilLest);
		int result;

		//	While loop: Printer navn på hvilken gangetabell, looper for-loopen, fra++
		//	For loop: Lager gangetabellen frem til ti
		while(fra <= til){

			System.out.println("\n" + fra + "-gangen:");

			for(int x = 1; x <= 10; x++){
				result = fra * x;
				System.out.println(fra + " x " + x + " = " + result);
			}

			fra++;
		}

	}

}