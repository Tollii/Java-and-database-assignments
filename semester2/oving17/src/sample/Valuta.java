package sample;

public class Valuta {

    private String valutanavn;
    private double kurs_mot_norske;
    private int enhet;

    public Valuta(String valutanavn, double kurs_mot_norske, int enhet) {
        this.valutanavn = valutanavn;
        this.kurs_mot_norske = kurs_mot_norske;
        this.enhet = enhet;
    }

    public String getValutanavn() {
        return valutanavn;
    }

    public double getKurs_mot_norske() {
        return kurs_mot_norske;
    }

    public int getEnhet() {
        return enhet;
    }

    public static double konverterValuta(Valuta fra, Valuta til, double input){
        double omTilNorsk = input / til.getKurs_mot_norske() * til.getEnhet();
        return (omTilNorsk * fra.getKurs_mot_norske()) / fra.getEnhet();
    }

    public static void main(String[]args){
        Valuta SEK = new Valuta("Svenske kroner", 88.96, 100);
        Valuta USD = new Valuta("US Dollar", 6.23, 1);
        System.out.println(konverterValuta(SEK, USD, 200));

    }







}
