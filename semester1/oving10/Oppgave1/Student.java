import java.util.Random.*;

class Student{

	private String navn;
	private int antOppg;

	public Student(String navn, int antOppg){
		this.navn = navn.trim();
		this. antOppg = antOppg;
	} // constructor end


	public String getNavn(){
		return navn;
	} // method end


	public int getAntOppg(){
		return antOppg;
	} // method end


	public void økAntOppg(int økning){
		this.antOppg += økning;
	} // method end


	public String toString(){
		return "Navn: " + this.navn + "\nAntall oppgaver: " + antOppg + "\n";
	} // method end


	// test
	public static void main(String[]args){
		// Test av klassen student
		Student stud = new Student("Atle Antonsen", 10);
		String msg = (stud.getAntOppg() == 10) ? "Test vellykket!" : "Test feilet";
		System.out.println("Test av klassen Student: \n" + msg + " Antall oppgaver test\n");

		stud.økAntOppg(10);
		msg = (stud.getAntOppg() == 20) ? "Test vellykket!" : "Test feilet";
		System.out.println(msg + "Set oppgaver test " + "\nToString: " + stud.toString() + "\n");
	} // main end
} // class end



class Oppgaveoversikt{

	private Student[] studenter;
	private int antStud = 0;

	public Oppgaveoversikt(){
		this.studenter = new Student[0];
	} // constructor end


	public String getNavn(int studentarray){
		return studenter[studentarray].getNavn();
	} // method end


	public int getAntOppg(int studentarray){
		return studenter[studentarray].getAntOppg();
	} // method end


	public int getAntallStudenter(){
		return antStud;
	} // method end


	public int getAntallOppgaverLøst(int studentarray){
		return studenter[studentarray].getAntOppg();
	} // method end


	public boolean regStudent(String navn, int antOppg){
		if(studenter.length == getAntallStudenter()){
			utvidTabell();
		} // if end

		studenter[antStud] = new Student(navn, antOppg);
		antStud++;
		return true;
	} // method end


	public boolean økOppgaver(int studentarray, int increment){
		int old = studenter[studentarray].getAntOppg();
		studenter[studentarray].økAntOppg(increment);
		if( studenter[studentarray].getAntOppg() == old + increment){
			return true;
		} else{
			return false;
		} // if end
	} // method end


	public String toString(){
		String resultat = "";
		for(int i = 0; i < antStud; i++){
			resultat += "\n" + i + ". " + studenter[i].toString();
		} // for end
		return resultat;
	} // method end


	private void utvidTabell(){
		Student[] nyTab = new Student[studenter.length + 2];
		for (int i = 0; i < studenter.length; i++){
			nyTab[i] = studenter[i];
		} // for end
		studenter = nyTab;
	} // method end


	// test
	public static void main(String[]args){
		Oppgaveoversikt test = new Oppgaveoversikt();

		// Test av klassen Oppgaveoversikt
		String msg = (test.getAntallStudenter() == 0) ? "Test vellykket!" : "Test feilet";
		System.out.println("Test av klassen Oppgaveoversikt: \n" + msg + " Antall studenter test");

		test.regStudent("Ikke Atle Antonsen", 20);
		msg = (test.getAntallStudenter() == 1) ? "Test vellykket!" : "Test feilet";
		System.out.println(msg + " Reg ny student test");

		msg = (test.getAntallOppgaverLøst(0) == 20) ? "Test vellykket!" : "Test feilet";
		System.out.println(msg + " Antall oppgaver test");

		test.økOppgaver(0,20);
		msg = (test.getAntallOppgaverLøst(0) == 40) ? "Test vellykket!" : "Test feilet";
		System.out.println(msg + " Increment oppgaver\n");

		test.regStudent("Ikke Antonsen", 202);
		test.regStudent("Ikke Atle ", 207);
		test.regStudent("Atle Antonsen", 2);
		System.out.println( "toString:\n" + test.toString() + "\n");
	} // main end
} // class end