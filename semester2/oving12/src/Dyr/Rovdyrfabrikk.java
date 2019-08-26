package Dyr;

public class Rovdyrfabrikk {



    public static SkandinaviskeRovdyr nyBinne(int ankomstdato, String adresse, String navn, int fDato, int antKull){
        return new Hunnindivid("Brunbjørn", "Ursus arctos", "Ursidae", ankomstdato, adresse, navn, fDato, false, true, antKull);
    }

    public static SkandinaviskeRovdyr nyHannbjørn(int ankomstdato, String adresse, String navn, int fDato){
        return new Hannindivid("Brunbjørn", "Ursus arctos", "Ursidae", ankomstdato, adresse, navn, fDato, true, true);
    }

    public static SkandinaviskeRovdyr nyUlvetispe(int ankomstdato, String adresse, String navn, int fDato, int antKull){
        return new Hunnindivid("Norsk ulv", "Canis lupus", "Canidae", ankomstdato, adresse, navn, fDato, false, true, antKull);
    }

    public static SkandinaviskeRovdyr nyUlveHann(int ankomstdato, String adresse, String navn, int fDato){
        return new Hannindivid("Norsk ulv", "Canis lupus", "Canidae", ankomstdato, adresse, navn, fDato, true, true);
    }
}

