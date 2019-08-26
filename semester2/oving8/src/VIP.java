import java.util.ArrayList;

class VIP extends Tribune{

    private String[][] tilskuer;
    ArrayList<SitteplassBillett> alleBilletter;

    public VIP(String tribunenavn, int kapasitet, int pris, int antallRader){
        super(tribunenavn, kapasitet, pris);
        tilskuer = new String[antallRader][kapasitet/antallRader];
       this.alleBilletter = new ArrayList<>(kapasitet);
    }

    @Override
    public int finnAntallSolgteBilletter(){
        int teller = 0;
        for(int i = 0; i < tilskuer.length; i++){
           for(int x = 0; x < tilskuer[i].length; x++){
               if(tilskuer[i][x]!= null){
                   teller++;
               }
           }
        }
        return teller;
    }

    @Override
    public int finnInntekt(){
        return finnAntallSolgteBilletter() * super.getPris();
    }

    @Override
    public Billett[] kjopBilletter(int antallBilleter){
        return null;
    }

    @Override
    public Billett[] kjopBilletter(String[] navn){
        Billett[] billetter = new Billett[navn.length];
        if(navn.length >= (getKapasitet() - finnAntallSolgteBilletter())) return null;
        for(int i = 0; i < navn.length; i++){
            int[] pos = finnLedigPlass(navn.length);
            tilskuer[pos[0]][pos[1]] = navn[i];
            SitteplassBillett stp = new SitteplassBillett(getTribunenavn(), getPris(), pos[0], pos[1]);
            billetter[i] = stp;
            alleBilletter.add(stp);
        }
        return billetter;
    }

    private int[] finnLedigPlass(int antallBilletter){
        int[] pos = new int[2];
        int radStr = getKapasitet()/tilskuer.length;
        if(radStr < antallBilletter) {
            pos[0] = -1;
            pos[1] = -1;
            return pos;
        }
        for(int i = 0; i < tilskuer.length; i++){
            pos[0] = i;
            for(int x = 0; x < tilskuer[i].length; x++){
                pos[1] = x;
                if(tilskuer[x][i] == null) return pos;
            }
        }
        return pos;
    }

    @Override
    public String toString(){
        String message = "VIP biletter: "+ getTribunenavn() +"\n";
        for(int i = 0; i < alleBilletter.size(); i++){
            message += alleBilletter.get(i) + "\n";
        }
        return message;
    }
}
