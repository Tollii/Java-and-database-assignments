import java.util.ArrayList;

class Sitte extends Tribune{

    private int[] antOpptatt;
    private ArrayList<SitteplassBillett> alleBilletter;

    public Sitte(String tribunenavn, int kapasitet, int pris, int antRader){
        super(tribunenavn, kapasitet, pris);
        this.antOpptatt = new int[antRader];
        this.alleBilletter = new ArrayList<>(kapasitet);
    }

    @Override
    public int finnAntallSolgteBilletter(){
        int sum = 0;
        for(int i = 0; i < antOpptatt.length; i++){
            sum += antOpptatt[i];
        }
        return sum;
    }

    @Override
    public int finnInntekt(){
        return super.getPris() * finnAntallSolgteBilletter();
    }

    private int finnLedigPlass(int antallBilletter){
        int radStr = getKapasitet()/antOpptatt.length;
        if(radStr < antallBilletter) return -1;
        for(int i = 0; i < antOpptatt.length; i++){
            if(antOpptatt[i] < radStr && (radStr - antOpptatt[i]) >= antallBilletter) return i;
        }
        return -1;
    }

    @Override
    public Billett[] kjopBilletter(int antallBilletter){
        SitteplassBillett[] billetter = new SitteplassBillett[antallBilletter];
        int ledig = finnLedigPlass(antallBilletter);
        if(ledig != -1 && (getKapasitet() - finnAntallSolgteBilletter()) >= antallBilletter){
            for(int i = 0; i < antallBilletter; i++){
                int ledigRad = finnLedigPlass(antallBilletter);
                antOpptatt[ledigRad]++;
                SitteplassBillett spb = new SitteplassBillett(getTribunenavn(), getPris(), antOpptatt[ledigRad], ledig);
                billetter[i] = spb;
                alleBilletter.add(spb);
            }
        }
        return billetter;
    }

    @Override
    public Billett[] kjopBilletter(String[] navn){
        return kjopBilletter(navn.length);
    }

    @Override
    public String toString(){
        String message = "Sitteplasser: " + getTribunenavn() + "\n";
        for(SitteplassBillett sok : alleBilletter){
            message += sok.toString() + "\n";
        }
        return message;
    }
}