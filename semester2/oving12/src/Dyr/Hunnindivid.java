package Dyr;

public class Hunnindivid extends Individ {

    private int antKull;


    public Hunnindivid(String norskNavn, String latnavn, String latFamilie, int ankommeDato, String adresse, String navn, int fDato, boolean hanndyr, boolean farlig, int antKull) {
        super(norskNavn, latnavn, latFamilie, ankommeDato, adresse, navn, fDato, hanndyr, farlig);
        this.antKull = antKull;
    }

    public int getAntKull() {
        return antKull;
    }

    @Override
    public void leggTilKull(int antall) {
        antKull += antall;
    }

    @Override
    public void leggTilNyttKull() {
        antKull++;
    }
}
