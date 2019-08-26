
import java.time.LocalDate;

public class GullMedlem extends BonusMedlem {

    public GullMedlem(int medlNr, Personalia pers, LocalDate innmeldtDato, double poeng) {
        super(medlNr, pers, innmeldtDato, poeng);
    }

    @Override
    public void registrerPoeng(double antPoeng) {
        super.registrerPoeng(antPoeng * 1.5);
    }


}
