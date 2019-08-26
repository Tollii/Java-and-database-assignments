package Dyr;

class Dyregruppe extends Dyr {

    private final String gruppenavn;
    private int antIndivider;

    public Dyregruppe(String gruppenavn, int antIndivider, String norskNavn, String latNavn, String latFamilie, int ankommetDato, String adresse) {
        super(norskNavn, latNavn, latFamilie, ankommetDato, adresse);
        this.gruppenavn = gruppenavn;
        this.antIndivider = antIndivider;
    }

    public String getGruppenavn() {
        return gruppenavn;
    }

    public int getAntIndivider() {
        return antIndivider;
    }

    public void setAntIndivider(int antIndivider) {
        this.antIndivider = antIndivider;
    }



}
