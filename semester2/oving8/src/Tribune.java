abstract class Tribune{

    private final String tribunenavn;
    private final int kapasitet;
    private final int pris;

    public Tribune(String tribunenavn, int kapasitet, int pris){
        this.tribunenavn = tribunenavn;
        this.kapasitet = kapasitet;
        this.pris = pris;
    }

    public String getTribunenavn(){
        return tribunenavn;
    }

    public int getKapasitet() {
        return kapasitet;
    }

    public int getPris(){
        return pris;
    }

    public abstract int finnAntallSolgteBilletter();

    public abstract int finnInntekt();

    public abstract Billett[] kjopBilletter(int antallBilletter);

    public abstract Billett[] kjopBilletter(String[] navn);

    public abstract String toString();
}


