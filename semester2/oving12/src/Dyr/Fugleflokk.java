package Dyr;

class Fugleflokk extends Dyregruppe{

    private final int gjennomSnittligVekt;
    private final boolean svommer;

    public Fugleflokk(String gruppenavn, int antIndivider, String norskNavn, String latNavn, String latFamilie,
                      int ankommetDato, String adresse, int gjennomSnittligVekt, boolean svommer) {
        super(gruppenavn, antIndivider, norskNavn, latNavn, latFamilie, ankommetDato, adresse);
        this.gjennomSnittligVekt = gjennomSnittligVekt;
        this.svommer = svommer;
    }

    public int getGjennomSnittligVekt() {
        return gjennomSnittligVekt;
    }

    public boolean isSvommer() {
        return svommer;
    }
}
