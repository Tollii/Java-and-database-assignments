import static javax.swing.JOptionPane.*;

class NyStringKlient{
	public static void main(String[]args){
		// Forkort
		String tekst = showInputDialog("Skriv tekst: ");
		NyString string = new NyString(tekst);
		while(true){
			String tellerLest = showInputDialog("1. Forkort" + "\n2. Fjern bokstav");
			int teller = Integer.parseInt(tellerLest);
			switch (teller){
			case 1 :
				showMessageDialog(null, string.forkort());
				break;

			case 2 :
				String cLest = showInputDialog("Skriv inn bokstav du vil fjerne: ");
				char c = cLest.charAt(0);
				showMessageDialog(null,string.replace(c));
				break;

			default :
				showMessageDialog(null, "Skriv inn et gyldig tall");
			} // switch slutt
		} // while slutt
	} // main slutt
} // class slutt