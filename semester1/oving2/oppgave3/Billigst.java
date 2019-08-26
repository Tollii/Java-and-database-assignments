import static javax.swing.JOptionPane.*;
class Billigst{
	public static void main(String[]args){
		// Henter og gjør om pris og vekt på kjøttdeig A
		String prisALest = showInputDialog("Pris på kjøttdeig A: ");
		double prisA = Double.parseDouble(prisALest);
		String vektALest = showInputDialog("Vekt på kjøttdeig A: ");
		double vektA = Double.parseDouble(vektALest);

		// Henter og gjør om pris og vekt på kjøttdeig B
		String prisBLest = showInputDialog("Pris på kjøttdeig B: ");
		double prisB = Double.parseDouble(prisBLest);
		String vektBLest = showInputDialog("Vekt på kjøttdeig B: ");
		double vektB = Double.parseDouble(vektBLest);

		//Regner pris per kilo
		double prisPerKiloA = (prisA / vektA) * 1000;
		double prisPerKiloB = (prisB / vektB) * 1000;

		// Viser den billigste kjøttdeigen
		if(prisPerKiloA > prisPerKiloB) { 
			showMessageDialog(null, "Kjøttdeig B er billigst");
		} else { 
			showMessageDialog(null, "Kjøttdeig A er billigst");
		}
	}
}

