import java.util.Calendar;

class Restaurant {

    private String restaurantNavn;
    private int etablert;
    private int antallBord;
    private Bord bord;

    public Restaurant(String restaurantNavn, int etablert, int antallBord){
        this.restaurantNavn = restaurantNavn;
        this.etablert = etablert;
        this.antallBord = antallBord;
        bord = new Bord(antallBord);
    }

    public int getLedigBord(){
        return bord.getLedigBord();
    }

    public int getOpptattBord(){ return bord.getOpptattBord(); }

    public int getEtablertingsaar(){ return etablert; }

    public String getRestaurantNavn(){
        return restaurantNavn;
    }

    // Setter restaurantnavnet til parameteren. Sammenligner da restaurantnavnet og parameter.
    // Hvis de er like returneres true. Hvis de ikke er like returneres false.
    public boolean setRestaurantNavn(String navn){
        this.restaurantNavn = navn.trim();
        if(this.restaurantNavn.equals(navn)){
            return true;
        } else return false;
    }

    // Returnerer alderen paa restauranten
    public int restaurantAlder(){
        int year = Calendar.getInstance().get(Calendar.YEAR);
        return year - etablert;
    }

    // Sjekker om det er nok plasser til antall reservasjoner. Returnerer false hvis det er for mange/fullt. Reserverer antall bord hvis det er nok plass og returnerer true når registrering er ferdig eller false hvis registrering feilet.
    public boolean reserverBord(String navn, int antall) {
        if (antall <= 0 && this.getOpptattBord() != antallBord) return false;
        for(int x = 0; x < antall; x++){
            boolean reservert = bord.reserverBord(navn);
            if(!reservert) return false;
        }
        return true;
    }

    // Returnerer en tabell med reservasjonene registrert til navnet i parameteren
    public int[] getReservasjon(String navn){
        return bord.getReservasjon(navn);
    }

    // Sender inn en int tabell med bordnummer som skal ryddes. Returnerer false hvis et av bordnummerene ikke blir ryddet. Hvis alle bord blir ryddet returneres true
    public boolean ryddBord(int[] bordNr){
        for(int x = 0; x < bordNr.length; x++){
            if(!bord.ryddBord(bordNr[x])) return false;
        }
        return true;
    }

    public String toString(){
        String message = "Restaurant: " + this.getRestaurantNavn() + "\nEtablert: " + this.getEtablertingsaar() + "\nRestaurant alder: " + this.restaurantAlder() + "\nReserverte bord: " + bord.toString();
        return message;
    }
}

class Bord {

    private String[] tabell;

    public Bord(int antallBord){
        this.tabell = new String[antallBord];
    }

    // Inkrementerer ledigBord for hver indeks i tabellen som er tom og returnerer verdien.
    public int getLedigBord(){
        int ledigBord = 0;
        for(int x = 0; x < tabell.length; x++){
            if(tabell[x] == null){
                ledigBord++;
            }
        }
        return ledigBord;
    }

    // Returnerer lengden av tabellen (antall bord) minus finnLedigBord() som blir antall opptatt bord.
    public int getOpptattBord(){
        return tabell.length - this.getLedigBord();
    }

    // Sjekker om det finnes en plass i tabellen som er tom, hvis det er en ledig plass så legges inn String parameteren inn i den tomme indeksen og returnerer true.
    // Hvis tabellen er full så returneres false
    public boolean reserverBord(String navn){
        for(int x = 0; x < tabell.length; x++){
            if(tabell[x] == null){
                tabell[x] = navn.toUpperCase();
                if(tabell[x].equals(navn.toUpperCase())) return true;
            }
        }
        return false;
    }

    // Setter en indeks i tabellen til null. Returnerer true hvis indeksen ble satt til null. Returnerer false hvis den fortsatt har en verdi.
    public boolean ryddBord(int i){
        tabell[i] = null;
       return true;
    }

    public int[] getReservasjon(String navn){
        int i = 0;

        // For loop for å finne ut hvor mange reservasjoner personen har, slik at man kan deklarere en array med riktig storrelse
        for(int x = 0; x < tabell.length; x++){
            if(tabell[x] != null){
                if(tabell[x].equals(navn.toUpperCase())) i++;
            }
        }

        int[] tab = new int[i];

        // Finner hvilke indekser i tabell som er reservert og lagrer indeksen i tab
        i = 0;
        for(int x = 0; x < tabell.length; x++){
            if(tabell[x] != null){
                if(tabell[x].equals(navn.toUpperCase())) {
                    tab[i] = x;
                    i++;
                }
            }
        }
        return tab;
    }

    public String toString(){
        String message = "";
        for(int x = 0; x < tabell.length; x++){
            if(tabell[x] != null){
                message += x + ": " + (tabell[x] + ", ");
            }
        }
        return message;
    }
}

class Test{
    public static void main(String[]args){

        Restaurant maccern = new Restaurant("MacDonald's", 1900, 20);


        boolean bool = maccern.reserverBord("Arne", 2); // Skal reserveres 2 bord under navnet ARNE
        boolean bool2 = maccern.reserverBord("Trym",3); // Skal reserveres 3 bord under navnet TRYM. Totalt reservert 5.

        if(bool && maccern.getLedigBord() == 15 && maccern.getOpptattBord() == 5){ // Printer vellykket! hvis metoden returnerer true og det er 15 ledige bord og 5 reserverte bord. Printer Feilet! ellers
            System.out.println("Vellykket! Bord reservert"); //
        } else {
            System.out.println("Feilet!");
        }

        if(bool2 && maccern.getLedigBord() == 15 && maccern.getOpptattBord() == 5){ // Printer vellykket hvis metoden returnerer true og det er 15 ledige bord og 5 reserverte bord. Printer Feilet! ellers
            System.out.println("Vellykket! Bord reservert");
        } else {System.out.println("Feilet!");
        }

        if(maccern.getLedigBord() == 15){
            System.out.println(maccern.getLedigBord() + " Ledige bord"); // Printer antall ledige bord. Forventet resultat 15
        }

        if(maccern.getOpptattBord() == 5){
            System.out.println(maccern.getOpptattBord() + " Opptatt bord"); // Printer antall reserverte bord. Forventet reslutat 5
        }

        System.out.println(maccern.toString()); // Printer toString metoden. Viser Restaurant navn, etablerings aar, restaurant alder og hvilke bor som er reservert av hvem


        maccern.setRestaurantNavn("Burger King"); // Endrer navn til Burger King,
        if(maccern.getRestaurantNavn().equals("Burger King")){
            System.out.println("Vellykket! Navn endret");
        }

        System.out.println(maccern.toString()); // toString med endret navn til Burger King

       int[] tab = maccern.getReservasjon("Trym"); // Henter en tabell over hvilke bord Trym har reservert. Forventet resultat 3 bord. #2,3,4. Printer resultat.
       String message = "Trym har reservert bord #: ";
       for(int x = 0; x < tab.length; x++){
           message += tab[x] + ", ";
       }
       System.out.println(message);

       int[] rydd = {1,2}; //
        if(maccern.ryddBord(rydd)){
           System.out.println("Vellykket! Bord ryddet"); // Fjerner reservasjonen tilsvarende innholdet i integer tabellen. Fjerner reservasjon for bord 1 og 2
        }

       System.out.println(maccern.toString()); // Printer toString uten reservasjonene til bord 1 og 2. Resterende reservasjoner skal være 0, 3, og 4.
    }
}
