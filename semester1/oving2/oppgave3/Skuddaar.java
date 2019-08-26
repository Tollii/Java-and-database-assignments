import static javax.swing.JOptionPane.*;
class Skuddaar {
	public static void main(String[]args){
		// Henter og konverterer årstall
		String aarLest = showInputDialog("Årstall: ");
		int aar = Integer.parseInt(aarLest);
		boolean skuddaar;

		if (aar % 400 != 0){
			skuddaar = false;
		}
		else if (aar % 4 != 0){
			skuddaar = false;
		} else{
			skuddaar = true;
		}

		if (skuddaar == true){
			showMessageDialog(null,"År " + aar + " er et skuddår");
		} else {
			showMessageDialog(null,"År " + aar + " er ikke et skuddår");
		}
}
}
