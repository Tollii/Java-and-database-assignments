import java.awt.Graphics;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;

class Vindu extends JFrame {
	public Vindu(String tittel){
		setTitle(tittel); // setter tittel
		setSize(600,450); // Lengde bredde paa vindu
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Tegning tegningen = new Tegning();
		add(tegningen);
	}	

}

class Tegning extends JPanel{
	public void paintComponent(Graphics tegneflate){
		super.paintComponent(tegneflate); // husk denne
		setBackground(Color.WHITE);
		tegneflate.drawString("Hei, hei", 55,75);
		tegneflate.setColor(Color.RED);
		tegneflate.fillOval(40,30,500,380); // ansikt
		tegneflate.setColor(Color.BLACK);
		tegneflate.fillOval(190,135,70,70); // v oye
		tegneflate.fillOval(340,135,70,70); // h oye
		tegneflate.fillOval(220,250,150,100); // munn svart
		tegneflate.setColor(Color.RED);
		tegneflate.fillOval(220,230,150,100); // munn red
	}
}

class Grafikk{
	public static void main(String[]args){
		Vindu etVindu = new Vindu("Enkel grafikk");
		etVindu.setVisible(true);
	}
}