import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

class Medlemsarkiv{

    private ArrayList<BonusMedlem> liste = new ArrayList<>();

    // Finner en bruker med likt medlemsnummer og passord, og returnerer antall poeng registrert til brukeren.
    public double finnPoeng(int medlNr, String passord){
        for(BonusMedlem sok : liste){
            if(sok.getMedlnr() == medlNr && sok.okPassord(passord)) return sok.getPoeng();
        }
        return -1;
    }

    // Finner et objekt som matcher medlemsnummeret og øker poengverdien med parameter verdien.
    public boolean registrerPoeng(int medlNr, double antPoeng){
        for(BonusMedlem sok : liste){
            if(sok.getMedlnr() == medlNr) {
                sok.registrerPoeng(antPoeng);
                return true;
            }
        }
        return false;
    }

    // Registrerer et nytt medlem som Basic med 0 poeng.
    public int nyMedlem(Personalia pers, LocalDate inmeldt){
        int medlNr = this.finnLedigNr();
        liste.add(new BasicMedlem(medlNr, pers, inmeldt, 0));
        return medlNr;
    }

    // Finner et tilfeldig tall og sammenligner det med medlemsnummera i listen. Returnerer det tilfeldig tallet hvis det ikke finnes i listen.
    private int finnLedigNr(){
        Random tall = new Random();
        while(true){
            int random = tall.nextInt(100);
            if(liste.size() == 0) return random;
            for(int i = 0; i < liste.size(); i++){
                if(random != liste.get(i).getMedlnr()) return random;
            }
        }
    }


    // Sjekker om et medlem er Basic, og oppgraderer til gull eller sølv hvis medlemmet overtiger poenggrensene, gjør det samme med sølv medlemmer.
    public boolean sjekkMedlemmer(){
        boolean endret = false;
        for(int i = 0; i < liste.size(); i++){
            if(liste.get(i) instanceof BasicMedlem) {
                BasicMedlem b = (BasicMedlem) liste.get(i);
                if(b.getPoeng() >= 75000){
                    GullMedlem g = new GullMedlem(b.getMedlnr(), b.getPers(), b.getInnmeldtDato(), b.getPoeng());
                    liste.set(i, g);
                    endret = true;
                } else if(b.getPoeng() >= 25000){
                    SoelvMedlem s = new SoelvMedlem(b.getMedlnr(), b.getPers(), b.getInnmeldtDato(), b.getPoeng());
                    liste.set(i, s);
                    endret = true;
                }
                if(liste.get(i) instanceof SoelvMedlem){
                    SoelvMedlem s2 = (SoelvMedlem) liste.get(i);
                    if(s2.getPoeng() >= 75000){
                        GullMedlem g2 = new GullMedlem(s2.getMedlnr(), s2.getPers(), s2.getInnmeldtDato(), s2.getPoeng());
                        liste.set(i, g2);
                        endret = true;
                    }
                }
            }
        }
        return endret;
    }
}