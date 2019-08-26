
import java.time.LocalDate;

public class SoelvMedlem extends BonusMedlem {

    public SoelvMedlem(int medlNr, Personalia pers, LocalDate innmeldtDato, double poeng) {
        super(medlNr, pers, innmeldtDato, poeng);
    }

    @Override
    public void registrerPoeng(double antPoeng) {
        super.registrerPoeng(antPoeng * 1.2);
    }


}
