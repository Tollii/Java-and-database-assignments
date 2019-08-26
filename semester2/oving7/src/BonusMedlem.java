import java.time.LocalDate;
import java.time.Period;

class BonusMedlem{

    private final int medlNr;
    private final Personalia pers;
    private final LocalDate innmeldtDato;
    private double poeng = 0;

    public BonusMedlem(int medlNr, Personalia pers, LocalDate inmeldtDato, double poeng){
        this.medlNr = medlNr;
        this.pers = pers;
        this.innmeldtDato = inmeldtDato;
        this.poeng = poeng;
    }

    public int getMedlnr() {
        return medlNr;
    }

    public Personalia getPers() {
        return pers;
    }

    public LocalDate getInnmeldtDato() {
        return innmeldtDato;
    }

    public double getPoeng() {
        return poeng;
    }

    // Sjekker om det er mindre enn ett år fra parameterdatoen personen medlte seg inn. Returnerer antall poeng hvis det er mindre enn ett år, returnerer 0 hvis det overstiger ett år.
    public double finnKvalPoeng(LocalDate dato){
        int diff = Period.between(innmeldtDato, dato).getYears();
        if(diff < 1) return poeng;
        else return 0;
    }

    // Sammenligner passord argumentet med passord knyttet til brukeren
    public boolean okPassord(String passord){
        return pers.okPassord(passord);
    }

    public void registrerPoeng(double antPoeng){
        poeng += antPoeng;
    }



}
