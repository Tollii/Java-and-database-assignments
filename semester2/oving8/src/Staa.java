import java.util.ArrayList;

class Staa extends Tribune{

    private int antSolgteBilletter;
    private ArrayList<StaaplassBillett> alleBilletter;

    public Staa(String tribunenavn, int kapasitet, int pris){
        super(tribunenavn, kapasitet, pris);
        this.alleBilletter = new ArrayList<>(kapasitet);
    }

    @Override
    public int finnAntallSolgteBilletter(){
        return antSolgteBilletter;
    }

    @Override
    public int finnInntekt(){
        return antSolgteBilletter * super.getPris();
    }

    @Override
    public Billett[] kjopBilletter(int antallBilletter){
        StaaplassBillett[] billettListe = new StaaplassBillett[antallBilletter];
        if((getKapasitet()-antallBilletter) >= antallBilletter){
            for(int i = 0; i < antallBilletter; i++){
                StaaplassBillett spb = new StaaplassBillett(getTribunenavn(), getPris());
                billettListe[i] = spb;
                alleBilletter.add(spb);
            }
            antSolgteBilletter += antallBilletter;
        }
        return billettListe;
    }

    @Override
    public Billett[] kjopBilletter(String[] navn){
        return kjopBilletter(navn.length);
    }

    @Override
    public String toString(){
        String message = "Staaplassbilletter "+ getTribunenavn() +":\n";
        int teller = 0;
        for(StaaplassBillett sok : alleBilletter){
            message += sok.toString() + "\n";
            teller++;
        }
        return message + "Antall staaplasser solgt: " + teller + "\n";
    }

}