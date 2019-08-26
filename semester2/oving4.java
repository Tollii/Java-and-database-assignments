import java.security.PrivilegedActionException;
import java.util.ArrayList;
import static javax.swing.JOptionPane.*;

/**
 * Kunde.java
 * Inneholder kundedata.
 */
class Kunde {
    private final String navn;
    private final String tlf;

    /**
     * Konstruktør:
     * Både navn og telefon må oppgis, de kan ikke være verken null eller tomme strenger.
     */
    public Kunde(String navn, String tlf) {
        if (navn == null || navn.trim().equals("")) {
            throw new IllegalArgumentException("Navn må oppgis.");
        }
        if (tlf == null || tlf.trim().equals("")) {
            throw new IllegalArgumentException("Tlf må oppgis.");
        }
        this.navn = navn.trim();
        this.tlf = tlf.trim();
    }

    public String getNavn() {
        return navn;
    }

    public String getTlf() {
        return tlf;
    }

    public String toString() {
        return navn + ", tlf " + tlf;
    }
}



/**
 * Tidspunkt.java
 *
 * Klasse som håndterer tidspunkt gitt som et heltall av typen long.
 *
 * NB! Konstruktøren foretar ingen kontroll av om tidspunktet er gyldig!
 */

class Tidspunkt implements Comparable<Tidspunkt> {
    private final long tid; // format ååååmmddttmm

    public Tidspunkt(long tid) {
        this.tid = tid;
    }

    public long getTidspunkt() {
        return tid;
    }

    /**
     * Formaterer tidspunktet slik: dd-mm-åååå kl ttmm
     */
    public String toString() {
        /*
         * Foretar trygg omforming til mindre type,
         * dato og klokkeslett er hver for seg innenfor tallområdet til int.
         */
        int dato = (int) (tid / 10000);
        int klokkeslett = (int) (tid % 10000);
        int år = dato / 10000;
        int mndDag = dato % 10000;
        int mnd = mndDag / 100;
        int dag = mndDag % 100;

        String tid = "";
        if (dag < 10) {
            tid += "0";
        }
        tid += dag + "-";
        if (mnd < 10) {
            tid += "0";
        }
        tid += mnd + "-" + år + " kl ";
        if (klokkeslett < 1000) {
            tid += "0";
        }
        tid += klokkeslett;
        return tid;
    }

    public int compareTo(Tidspunkt detAndre) {
        if (tid < detAndre.tid) {
            return -1;
        } else if (tid > detAndre.tid) {
            return 1;
        } else {
            return 0;
        }
    }

    /* Tester klassen Tidspunkt */
    public static void main(String[] args) {
        System.out.println("Totalt antall tester: 2");
        Tidspunkt t1 = new Tidspunkt(200301201200L);
        Tidspunkt t2 = new Tidspunkt(200301070230L);
        Tidspunkt t3 = new Tidspunkt(200301070230L);
        if (t1.compareTo(t2) > 0 &&
                t2.compareTo(t1) < 0 &&
                t3.compareTo(t2) == 0 &&
                t2.compareTo(t3) == 0) {
            System.out.println("Tidspunkt: Test 1 vellykket.");
        }
        if (t1.toString().equals("20-01-2003 kl 1200") &&
                t2.toString().equals("07-01-2003 kl 0230") &&
                t3.toString().equals("07-01-2003 kl 0230")) {
            System.out.println("Tidspunkt: Test 2 vellykket.");
        }
    }
}



/**
 * Resevasjon.java
 *
 * Et objekt inneholder data om en reservasjon.
 * Operasjoner for å hente ut data, og for å sjekke om overlapp
 * med annen reservasjon.
 */

class Reservasjon {
    private final Tidspunkt fraTid;
    private final Tidspunkt tilTid;
    private final Kunde kunde;

    /**
     * Konstruktør:
     * fraTid må være før tilTid.
     * Ingen av argumentene kan være null.
     */
    public Reservasjon(Tidspunkt fraTid, Tidspunkt tilTid, Kunde kunde) {
        if (fraTid == null || tilTid == null) {
            throw new IllegalArgumentException("Fra-tid og/eller til-tid er null");
        }
        if (fraTid.compareTo(tilTid) >= 0) {
            throw new IllegalArgumentException("Fra-tid er lik eller etter til-tid");
        }
        if (kunde == null) {
            throw new IllegalArgumentException("Kunde er null");
        }
        this.fraTid = fraTid;
        this.tilTid = tilTid;
        this.kunde = kunde;
    }

    public Tidspunkt getFraTid() {
        return fraTid;
    }

    public Tidspunkt getTilTid() {
        return tilTid;
    }

    /**
     * Metoden returnerer true dersom tidsintervallet [sjekkFraTid, sjekkTilTid] overlapper
     * med det tidsintervallet som er i det reservasjonsobjektet vi er inne i [fraTid, tilTid].
     * Overlapp er ikke definert hvis sjekkFraTid eller sjekkTilTid er null.
     * Da kaster metoden NullPointerException.
     */
    public boolean overlapp(Tidspunkt sjekkFraTid, Tidspunkt sjekkTilTid) {
        return (sjekkTilTid.compareTo(fraTid) > 0 && sjekkFraTid.compareTo(tilTid) < 0);
    }

    public String toString() {
        return "Kunde: " + kunde.getNavn() + ", tlf: " + kunde.getTlf() + ", fra " +
                fraTid.toString() + ", til " + tilTid.toString();
    }

    /**
     * Metode som prøver klassen Reservasjon.
     */
    public static void main(String[] args) {
        Kunde k = new Kunde("Anne Hansen", "12345678");
        System.out.println("Totalt antall tester: ");
        Reservasjon r1 = new Reservasjon(new Tidspunkt(200302011000L), new Tidspunkt(200302011100L), k);
        Reservasjon r2 = new Reservasjon(new Tidspunkt(200301211000L), new Tidspunkt(200301211030L), k);
        Reservasjon r3 = new Reservasjon(new Tidspunkt(200302011130L), new Tidspunkt(200302011300L), k);
        Reservasjon r4 = new Reservasjon(new Tidspunkt(200302010900L), new Tidspunkt(200302011100L), k);
        if (r1.toString().equals("Kunde: Anne Hansen, tlf: 12345678, fra 01-02-2003 kl 1000, til 01-02-2003 kl 1100") &&
                r2.toString().equals("Kunde: Anne Hansen, tlf: 12345678, fra 21-01-2003 kl 1000, til 21-01-2003 kl 1030") &&
                r3.toString().equals("Kunde: Anne Hansen, tlf: 12345678, fra 01-02-2003 kl 1130, til 01-02-2003 kl 1300") &&
                r4.toString().equals("Kunde: Anne Hansen, tlf: 12345678, fra 01-02-2003 kl 0900, til 01-02-2003 kl 1100")) {
            System.out.println("Reservasjon: Test 1 vellykket.");
        }

        if (r1.overlapp(new Tidspunkt(200302011000L), new Tidspunkt(200302011100L)) &&
                !r1.overlapp(new Tidspunkt(200302021000L), new Tidspunkt(200302021100L)) &&
                r1.overlapp(new Tidspunkt(200302011030L), new Tidspunkt(200302011100L)) &&
                r1.overlapp(new Tidspunkt(200302010930L), new Tidspunkt(200302011030L))) {
            System.out.println("Reservasjon: Test 2 vellykket.");
        }
        // Flg. setning kaster exception (fra-tid lik til-tid)
        //Reservasjon r5 = new Reservasjon(new Tidspunkt(200302011100L), new Tidspunkt(200302011100L), k);
        // Flg. setning kaster exception (fra-tid > til-tid)
        //Reservasjon r5 = new Reservasjon(new Tidspunkt(200302011130L), new Tidspunkt(200302011100L), k);
    }
}


class Rom {
    private int romNr;
    private int romStr;
    private ArrayList<Reservasjon> liste = new ArrayList<>();

    public Rom(int romNr, int romStr) {
        this.romNr = romNr;
        this.romStr = romStr;
    }

    public int getRomNr() {
        return romNr;
    }

    public int getRomStorrelse() {
        return romStr;
    }

    public ArrayList<Reservasjon> getListe() {
        return liste;
    }

    public int getReservasjoner(){
        return liste.size();
    }

    // Returnerer false hvis en reservasjon overlapper med input tidspunkt og dato eller hvis loopen går gjennom listen uten å legg inn reservasjonen. Returnerer true når reservasjonen er registrert.
    public boolean reserverRom(Reservasjon res) {
        for (Reservasjon sok : liste) {
            if (sok.overlapp(res.getFraTid(), res.getTilTid())) return false;
        }
        liste.add(res);
        return true;
    }

    public String toString(){
        String message = "Rom nummer " + romNr + ", rom størrelse " + romStr + "\nReservasjoner for dette rommet:\n";
        for(Reservasjon sok : liste){
            message += sok.toString() + "\n";
        }
        return message;
    }

    // Prøver klassen Rom
    public static void main(String[]args){
        Rom rom = new Rom(1, 10);
        Kunde k = new Kunde("Anne Hansen", "12345678");
        System.out.println("Totalt antall tester: ");
        Reservasjon r1 = new Reservasjon(new Tidspunkt(200302011000L), new Tidspunkt(200302011100L), k);
        Reservasjon r2 = new Reservasjon(new Tidspunkt(200301211000L), new Tidspunkt(200301211030L), k);
        Reservasjon r3 = new Reservasjon(new Tidspunkt(200302011130L), new Tidspunkt(200302011300L), k);
        Reservasjon r4 = new Reservasjon(new Tidspunkt(200302010900L), new Tidspunkt(200302011100L), k); // Overlapper med r1, skal ikke registreres

        if(rom.reserverRom(r1)){
            System.out.println("Reservasjon vellykket");
        }
        if(rom.reserverRom(r2)){
            System.out.println("Reservasjon vellykket");
        }
        if(rom.reserverRom(r3)){
            System.out.println("Reservasjon vellykket");
        }
        if(rom.reserverRom(r4)){
            System.out.println("Reservasjon vellykket");
        }

        System.out.println(rom.toString());

        if(rom.toString().equals("Rom nummer 1, rom størrelse 10\nReservasjoner for dette rommet:" +
                "\nKunde: Anne Hansen, tlf: 12345678, fra 01-02-2003 kl 1000, til 01-02-2003 kl 1100" +
                "\nKunde: Anne Hansen, tlf: 12345678, fra 21-01-2003 kl 1000, til 21-01-2003 kl 1030" +
                "\nKunde: Anne Hansen, tlf: 12345678, fra 01-02-2003 kl 1130, til 01-02-2003 kl 1300\n")){
            System.out.println("Rom: Test 1 vellykket");
        }
    }
}

class Konferansesenter{
    private ArrayList<Rom> liste = new ArrayList<>();

    public int getRom(){
        return liste.size();
    }

    public String getRom(int romNr){
        for(Rom sok : liste){
            if(sok.getRomNr() == romNr) return sok.toString();
        }
        return "Rom nummer ikke funnet.";
    }

    public String getRomIndex(int index){
        if(index > liste.size()){
            throw new IllegalArgumentException("Index out of bounds");
        }
        Rom temp = liste.get(index);
        return temp.toString();
    }

    // Leter gjennom Rom listen og reservasjon listen til det rommet etter et tidspunkt som passer for den nye reservasjonen. Hvis tiden passer blir en ny reservasjon registrert og returnerer true.
    public boolean reserverRom(String navn, String tlf, int antallPers, long fraTid, long tilTid){
        if(Integer.parseInt(tlf) < 9999999 || antallPers < 0)
            throw new IllegalArgumentException("Ugyldige verdier");
        Tidspunkt fraTidObj = new Tidspunkt(fraTid);
        Tidspunkt tilTidObj = new Tidspunkt(tilTid);
        Kunde nyKunde = new Kunde(navn, tlf);
        Reservasjon res = new Reservasjon(fraTidObj, tilTidObj, nyKunde);
        for(Rom sok : liste){
            if(antallPers <= sok.getRomStorrelse()){
                if(sok.reserverRom(res)) return true;
            }
        }
        return false;
    }

    public boolean registrerRom(int romNr, int romStr){
        Rom nyttRom = new Rom(romNr, romStr);
        for(Rom sok : liste){
            if(sok.getRomNr() == romNr || romNr < 0 || romStr < 0) {
                throw new IllegalArgumentException("Ugyldige verdier");
            }
        }
        liste.add(nyttRom);
        return true;
    }

    public String toString(){
        String message = "Antall rom registrert: " + liste.size() + "\n";
        for(Rom sok : liste){
            message += sok.toString() + "\n";
        }
        return message;
    }

    // Prøver klassen Konferansesenter
    public static void main(String[]args){
        Konferansesenter senter = new Konferansesenter();

        if(senter.registrerRom(2,10)){
            System.out.println("Konferansesenter: Test 1. Rom registrert");
        }
        if(senter.reserverRom("Anne Hansen", "123455678", 5, 200302011000L,200302011100L)){
            System.out.println("Konferansesenter: Test 2 vellykket. Reservasjon registrert");
        }
        if(!senter.reserverRom("Anne Hansen", "123455678", 5, 200302010900L,200302011001L)){
            System.out.println("Konferansesenter: Test 3 vellykket. Reservasjon overlapper, ikke registrert");
        }

        if(senter.registrerRom(101,101) && senter.reserverRom("Ola Nordmann", "23456789", 50,200302011000L,200302011100L) && senter.reserverRom("Ola Nordmann", "23456789", 2,200402011000L,200402011100L)){
            System.out.println("Bord registrert og to nye reserversjoner registrert\n");
        }
        if(senter.toString().equals("Reeservasjoner for dette rommet:\nAnne Hansen, tlf: 12345678, fra 01-02-2003 kl 1000, til 01-02-2003 kl 1100\nOla Nordmann, tlf: 23456789, fra 01-02-2003 kl 1000, til 01-02-2003 kl 1100")){
            System.out.println("Konferansesenter: Test 4 vellykket");
        }

        System.out.println("\n" + senter.toString());
        System.out.println("\n" + senter.getRom(101));
    }
}

class KonfGUI{
    private final String REG_ROM = "Registrer nytt rom";
    private final String RESERVER = "Reserver rom";
    // private final String FINN_ROM_INDEKS = "Finn rom med indeks";
    private final String FINN_ROM_ROMNR = "Finn rom med rom nummer";
    private final String FINN_ALLE = "Finn informasjon til alle rom";
    private final String AVSLUTT = "Avslutt";


    private String[] muligeValg = {REG_ROM, RESERVER, FINN_ROM_ROMNR, FINN_ALLE, AVSLUTT};

    private Konferansesenter konf;

    public KonfGUI(Konferansesenter konf){ this.konf = konf; }

    //
    public void registrerRom(){
        int dialogResult, romNr, romStr;
        do {
            try {
                romNr = Integer.parseInt(showInputDialog("Rom Nummer: "));
                romStr = Integer.parseInt(showInputDialog("Rom størrelse: "));
                konf.registrerRom(romNr, romStr);
            } catch (IllegalArgumentException e){
                showMessageDialog(null, "Ugyldige verdier, skriv inn på nytt");
            }
            dialogResult = showConfirmDialog(null, "Vil du registrere et nytt rom?", "Registrer rom", YES_NO_OPTION);
        } while(dialogResult == YES_OPTION);
        showMessageDialog(null,"Antall rom registrert: " + konf.getRom());
    }

    // Leser valg fra brukeren
    public String lesValg(){
        String valg = (String) showInputDialog(null, "Handlinger:",  "Konferansesenter program",
                DEFAULT_OPTION, null, muligeValg, muligeValg[0]);
        if(AVSLUTT.equals(valg)){
            valg = null;
        }
        return valg;
    }

    private void finnRomNr(){
        int romNr = Integer.parseInt(showInputDialog("Skriv inn rom nummer"));
        showMessageDialog(null, konf.getRom(romNr));
    }

    private void finnAlleRom(){
        showMessageDialog(null, konf.toString());
    }

    private void reserverRom(){
        String navn, tlf, dato;
        int antallPers;
        long fraTid, tilTid;
        try {
            navn = showInputDialog("Navn: ");
            tlf = showInputDialog("Telefon nummer: ");
            antallPers = Integer.parseInt(showInputDialog("Antall personer: "));
            dato = showInputDialog("Dato: (YYYYMMDD");
            fraTid = Long.parseLong(dato + showInputDialog("Fra klokken: (HHMM)"));
            tilTid = Long.parseLong(dato + showInputDialog("Til klokken: (HHMM)"));
            if (konf.reserverRom(navn, tlf, antallPers, fraTid, tilTid)) {
                showMessageDialog(null, "Reservasjon vellykket");
            } else {
                showMessageDialog(null, "Reservasjon feilet");
            }
        } catch (IllegalArgumentException e){
            showMessageDialog(null,"Ugyldige verdier, prøv igjen");
        }
    }

    public void utforOppgave(String valg){
        if(valg != null){
            switch(valg){
                case REG_ROM :
                    this.registrerRom();
                    break;

                case RESERVER :
                    this.reserverRom();
                    break;
                /*
                case FINN_ROM_INDEKS :
                    break;
                */
                case FINN_ROM_ROMNR :
                    this.finnRomNr();
                    break;

                case FINN_ALLE :
                    this.finnAlleRom();
                    break;

                default :
                    break;

            }
        }
    }
}


class Klient{
    public static void main(String[]args){
        Konferansesenter konf = new Konferansesenter();
        KonfGUI bgs = new KonfGUI(konf);

        String valg = "Registrer nytt rom";
        while(valg != null){
            bgs.utforOppgave(valg);
            valg = bgs.lesValg();
        }
    }
}