import static javax.swing.JOptionPane.*;

class tommercm {
	public static void main(String[]args) {

		//Henter string verdi fra dialog boks og gjør den om til double
		String tommerString = showInputDialog("Tommer: ");
		double tommerTall = Double.parseDouble(tommerString);

		//Definerer forholdstall
		double ratio = 2.54;

		//Kalkulerer og viser resultatet i en dialogboks
		showMessageDialog(null, tommerTall * ratio + " centimeter");
	}
}
	

