package Dyr;

public class Hannindivid extends Individ {

    public Hannindivid(String norskNavn, String latnavn, String latFamilie, int ankommeDato, String adresse, String navn, int fDato, boolean hanndyr, boolean farlig) {
        super(norskNavn, latnavn, latFamilie, ankommeDato, adresse, navn, fDato, hanndyr, farlig);
    }

    @Override
    public int getAntKull() {
        return 0;
    }

    @Override
    public void leggTilKull(int antall) {

    }

    @Override
    public void leggTilNyttKull() {

    }
}
