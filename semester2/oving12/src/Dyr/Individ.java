package Dyr;

abstract public class Individ extends Dyr implements SkandinaviskeRovdyr{

    private final String navn;
    private final int fDato;
    private final boolean hanndyr;
    private final boolean farlig;

    public Individ(String norskNavn, String latnavn, String latFamilie, int ankommeDato, String adresse,
                   String navn, int fDato, boolean hanndyr, boolean farlig) {
        super(norskNavn, latnavn, latFamilie, ankommeDato, adresse);
        this.navn = navn;
        this.fDato = fDato;
        this.hanndyr = hanndyr;
        this.farlig = farlig;
    }

    public String getNavn() {
        return navn;
    }

    public boolean isHanndyr() {
        return hanndyr;
    }

    public boolean isFarlig() {
        return farlig;
    }

    @Override
    public int getFdato(){
        return fDato;
    }

    @Override
    public int getAlder(){
        return 2019 - fDato;
    }

    @Override
    public String getAdresse(){
        return super.getAdresse();
    }

    @Override
    public void flytt(String nyAdresse){
        super.setAdresse(nyAdresse);
    }

    @Override
    public String skrivUtInfo(){
        String message = super.toString() + "\nNavn: " + navn + "\nFødselsdato: " +getFdato() + "\nKjønn: ";
        if(hanndyr) message += "gutt \nFarlig: ";
        else message += "jente \nFarlig: ";
        if(farlig) message += "ja\n";
        else message += "nei\n";
        return message;
    }
}
