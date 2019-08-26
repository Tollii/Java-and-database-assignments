package Dyr;

class Fiskestim extends Dyregruppe{

    private final int gjennomSnittligLengde;
    private final boolean kanDeleAkvarium;

    public Fiskestim(String gruppenavn, int antIndivider, String norskNavn, String latNavn, String latFamilie,
                     int ankommetDato, String adresse, int gjennomSnittligLengde, boolean kanDeleAkvarium) {
        super(gruppenavn, antIndivider, norskNavn, latNavn, latFamilie, ankommetDato, adresse);
        this.gjennomSnittligLengde = gjennomSnittligLengde;
        this.kanDeleAkvarium = kanDeleAkvarium;
    }

    public int getGjennomSnittligLengde() {
        return gjennomSnittligLengde;
    }

    public boolean isKanDeleAkvarium() {
        return kanDeleAkvarium;
    }
}
