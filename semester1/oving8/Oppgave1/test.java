
import static javax.swing.JOptionPane.*;
class Test{
	public static void main(String[]args){

		char c = '\0';
		String test = "test";
		String result = test + c + test;
		showMessageDialog(null, result);
	}
}