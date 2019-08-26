import Dyr.*;

import java.util.ArrayList;

import static Dyr.Rovdyrfabrikk.*;


public class klient {

    static ArrayList<SkandinaviskeRovdyr> dyr = new ArrayList();

    public static void main(String[]args){

        dyr.add(nyBinne(1800, "Bur 1", "Ikke Ole Brumm", 1900, 1));
        dyr.add(nyHannbjørn(1990, "Bur 2", "Ole Brumm", 1924));
        dyr.add(nyUlvetispe(2001, "Bur 3", "Julius", 2005, 2));
        dyr.add(nyUlveHann(2003, "Bur 4", "Ikke Julius", 20017));

        // Sjekker "skrivUtInfo" aka toString metoden
        for(int x = 0; x < dyr.size(); x++){
            System.out.println(dyr.get(x).skrivUtInfo());
        }

        // Bytter adressen til Ikke Ole Brumm
        System.out.println("Adresse: " + dyr.get(0).getAdresse());
        dyr.get(0).flytt("Bur 6969");
        System.out.println("Adresse: " + dyr.get(0).getAdresse()+ "\n");

        //Henter alder på Ole Brumm
        System.out.println("Alder:"+ dyr.get(1).getAlder() + "\n");

        // Henter navnet til Ikke Julius
        System.out.println(dyr.get(3).getNavn());

        // Henter antall kull fra binnen
        System.out.println("Binnekull: " + dyr.get(0).getAntKull());

        // Setter antall kull hos
        dyr.get(2).leggTilKull(2);
        System.out.println("Tispekull: " + dyr.get(2).getAntKull());

    }
}
