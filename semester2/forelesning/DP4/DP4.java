/*
 *
 * DP4 - 14 Januar 2019
 *
 * ArrayList, Interface, JavaAPI sortering
 *
 */





/**
 * INTERFACE
 *
 * Klassen vet ingenting. Klassen implementerer en interface klasse.
 * Interface klassen har bare statiske konstanter og abstrakt metode.
 * Java-klassen kan implementere flere Java-grensesnitt.
 * Alle metoder i et grensesnitt er impisert offentlige og abstrakte.
 * Definisjonene skal være i Java-klassen, ikke i interface.
 * For eksempel: Hvis du har en klasse Lyspære som implementerer interface-klassen bryter, kan også en annen klasse motor implementere interface-klassen bryter.
 * Interface funksjoner er implisitt static og public
 * 
 *
 *
 *
 *
 */
import java.util.*;

interface Animal {
	public abstract void eat();
	public abstract void travel();
	public abstract int noOfLegs();
}

class Cat implements Animal{

	public void eat(){
		System.out.println("cat eats");
	}

	public void travel(){
		System.out.println("cat travels");
	}

	public int noOfLegs(){
		return 4;
	}
}

class Dog implements Animal{

	public void eat(){
		System.out.println("dog eats");
	}

	public void travel(){
		System.out.println("dog travels");
	}

	public int noOfLegs(){
		return 4;
	}

}

class AnimalClient{

	public static void main(String[]args){

		Cat dog = new Cat();
		dog.travel();
		dog.eat();
		dog.noOfLegs();

		Animal cat = (Animal) new Dog();
		
		cat.travel();
		cat.eat();
		cat.noOfLegs();

		Animal[] an = {new Dog doge(), new Cat cate()}

	}

}




/**
* SORTERING
* 
*
*
*
*/

class TabellAvStreng{
	public static void main(String[]args){
		String[] navneliste = new String[2];

		navneliste[0] = "Britt";
		navneliste[1] = "Arne";

		Arrays.sort(navneliste);
		int i = Arrays.binarySearch(navneliste, "Britt");
		System.out.println(i);
	}
}










































