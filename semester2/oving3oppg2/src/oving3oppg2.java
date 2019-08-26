import java.util.ArrayList;
import static javax.swing.JOptionPane.*;


class Student{

    private final String navn;
    private int antOppg;

    public Student(String navn){
        this.navn = navn;
    }

    public String getNavn(){
        return this.navn;
    }

    public int getAntOppg(){
        return this.antOppg;
    }

    public int setAntOppg(int antOppg){
        if(antOppg < 0){
            throw new IllegalArgumentException("Kan ikke være negativ.");
        }
        this.antOppg = antOppg;
        return antOppg;
    }

    public String toString(){
        return "Navn: " + this.getNavn() + ", antall oppgaver løst: " + this.getAntOppg();
    }
}

class OppgaveOversikt {

    private ArrayList<Student> studenter = new ArrayList<>();
    private int antStud = 0;

    public int finnAntStud(){
        return this.antStud;
    }

    // Registrerer en ny student. Hvis studenten er allerede registrert rueturneres false. Returnerer true hvis studenten har blitt registrert.
    public boolean regNyStudent(String navn){
        for(Student x : studenter){
            if(x.getNavn().toUpperCase().equals(navn.toUpperCase())) return false;
        }
        Student nyStudent = new Student(navn);
        studenter.add(nyStudent);
        antStud++;
        return true;
    }

    // Returnerer antall oppgaver løst for en gitt student, hvis studenten ikke finnes returneres -1.
    public int finnAntOppgaver(String navn){
        for(Student x : studenter){
            if(x.getNavn().toUpperCase().equals(navn.toUpperCase())) return x.getAntOppg();
        }
        return -1;
    }

    // Returnerer false hvis metoden økte feil antall oppgaver. Returnerer true hvis antall oppgaver ble økt riktig
    public boolean økAntOppg(String navn, int add){
        for(Student x : studenter){
            if(x.getNavn().toUpperCase().equals(navn.toUpperCase())){
                x.setAntOppg(x.getAntOppg() + add);
            }
        }
        return false;
    }

    // Legger alle navnene til Student objektet inn i en tabell og returnerer den.
    public String[] finnAlleNavn(){
        String[] tab = new String[antStud];
        int teller = 0;
        for(Student x : studenter){
            tab[teller] = x.getNavn();
            teller++;
        }
        return tab;
    }


    public String toString(){
        String message = "";
        for(Student x : studenter){
            message += x.toString() + "\n";
        }
        return message;
    }
}

/**
 *
 * Program som kan brukes til å prøve ut metodene laget i øvingen.
 *
 * Om det er vanskelig å lese, kan det kanskje være på sin plass å repetere litt:
 *
 * Brukergrensesnittet er lagt til en egen klasse, se kapittel 6.4, side 193.
 * For øvrig er et menystyrt program vist i kapittel 9.6, side 304.
 */

class GodkjenningBGS {
    public final String NY_STUDENT = "Ny student";
    public final String AVSLUTT = "Avslutt";
    private String[] muligeValg = {NY_STUDENT, AVSLUTT};  // første gang, ingen studenter registrert

    private OppgaveOversikt oversikt;
    public GodkjenningBGS(OppgaveOversikt oversikt) {
        this.oversikt = oversikt;
    }

    /**
     *
     * Metoden leser inn valget som en streng, og returnerer den.
     * Valget skal være argument til metoden utførValgtOppgave().
     * Hvis programmet skal avsluttes, returneres null.
     */
    public String lesValg() {
        int antStud = oversikt.finnAntStud();
        String valg = (String) showInputDialog(null, "Velg fra listen, " + antStud + " studenter:",  "Godkjente oppgaver",
                DEFAULT_OPTION, null, muligeValg, muligeValg[0]);
        if (AVSLUTT.equals(valg)) {
            valg = null;
        }
        return valg;
    }

    /**
     *
     * Metode som sørger for at ønsket valg blir utført.
     */
    public void utførValgtOppgave(String valg) {
        if (valg != null && !valg.equals(AVSLUTT)) {
            if (valg.equals(NY_STUDENT)) {
                registrerNyStudent();
            } else {
                registrerOppgaver(valg);  // valg er navnet til studenten
            }
        }
    }

    /**
     *
     * Metoden registrere ny student.
     * Hvis student med dette navnet allerede eksisterer, skjer ingen registrering.
     * Resultatet av operasjonen skrives ut til brukeren.
     */
    private void registrerNyStudent() {
        String navnNyStud = null;
        do {
            navnNyStud = showInputDialog("Oppgi navn: ");
        } while (navnNyStud == null);

        navnNyStud = navnNyStud.trim();
        if (oversikt.regNyStudent(navnNyStud)) {
            showMessageDialog(null, navnNyStud + " er registrert.");
            String[] alleNavn = oversikt.finnAlleNavn();
            String[] nyMuligeValg = new String[alleNavn.length + 2];
            for (int i = 0; i < alleNavn.length; i++) {
                nyMuligeValg[i] = alleNavn[i];
            }
            nyMuligeValg[alleNavn.length] = NY_STUDENT;
            nyMuligeValg[alleNavn.length + 1] = AVSLUTT;
            muligeValg = nyMuligeValg;
        } else  {
            showMessageDialog(null, navnNyStud + " er allerede registrert.");
        }
    }

    /**
     *
     * Metoden registrerer oppgaver for en navngitt student.
     * Brukerinput kontrolleres ved at det må kunne tolkes som et tall.
     * Registreringsmetoden (i klassen Student) kan kaste unntaksobjekt IllegalArgumentException.
     * Dette fanges også opp. I begge tilfeller må brukeren gjenta inntasting inntil ok data.
     * Endelig skrives det ut en melding om antall oppgaver studenten nå har registrert.
     */
    private void registrerOppgaver(String studNavn) {
        String melding = "Oppgi antall nye oppgaver som skal godkjennes for " + studNavn +": ";
        int antOppgØkning = 0;
        boolean registrert = false;
        do { // gjentar inntil registrering aksepteres av objektet oversikt
            try {
                antOppgØkning = lesHeltall(melding);
                oversikt.økAntOppg(studNavn, antOppgØkning);  // kan ikke returnere false, pga navn alltid gyldig
                registrert = true; // kommer hit bare dersom exception ikke blir kastet
            } catch (IllegalArgumentException e) {  // kommer hit hvis studenter får negativt antall oppgaver
                melding = "Du skrev " + antOppgØkning + ". \nIkke godkjent økning for " + studNavn + ". Prøv igjen: ";
            }
        } while (!registrert);

        melding = "Totalt antall oppgaver registrert på " + studNavn + " er " + oversikt.finnAntOppgaver(studNavn) + ".";
        showMessageDialog(null, melding);
    }

    /* Hjelpemetode som går i løkke inntil brukeren skriver et heltall. */
    private int lesHeltall(String melding) {
        int tall = 0;
        boolean ok = false;
        do {  // gjentar inntil brukerinput kan tolkes som tall
            String tallLest = showInputDialog(melding);
            try {
                tall = Integer.parseInt(tallLest);
                ok = true;
            } catch (Exception e) {
                showMessageDialog(null, "Kan ikke tolke det du skrev som tall. Prøv igjen. ");
            }
        } while (!ok);
        return tall;
    }
}

/**
 * Hovedprogrammet. Går i løkke og lar brukeren gjøre valg.
 */
class Oppgave1 {
    public static void main(String[] args) {

        OppgaveOversikt oversikt = new OppgaveOversikt();
        GodkjenningBGS bgs = new GodkjenningBGS(oversikt);

        String valg = bgs.lesValg();
        while (valg != null) {
            bgs.utførValgtOppgave(valg);
            valg = bgs.lesValg();
        }
        /* Prøver toString() */
        System.out.println("\nHer kommer informasjon om alle studentene: ");
        System.out.println(oversikt);
    }
}