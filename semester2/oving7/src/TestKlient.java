import java.time.LocalDate;

class TestKlient{
    public static void main(String[]args){

        Personalia ole = new Personalia("Olsen", "Ole", "ole.olsen@dot.com", "ole");
        Personalia tove = new Personalia("Hansen", "Tove", "tove.hansen@dot.com", "tove");
        LocalDate testdato = LocalDate.of(2008, 2, 10);
        Medlemsarkiv arkiv = new Medlemsarkiv();

        System.out.println("Antall tester: 4");

        int medlnrole = arkiv.nyMedlem(ole, testdato);
        int medlnrtove = arkiv.nyMedlem(tove, testdato);
        System.out.println("Ole mdlnr : " + medlnrole + "\nTove mdlnr: " + medlnrtove);

        if(arkiv.registrerPoeng(medlnrole, 100000) && arkiv.finnPoeng(medlnrole, "ole") == 100000){
            System.out.println("Test 1 vellykket.\nOle poenge: " + arkiv.finnPoeng(medlnrole,"ole"));
        }

        if(arkiv.registrerPoeng(medlnrtove, 50000) && arkiv.finnPoeng(medlnrtove, "tove") == 50000){
            System.out.println("Test 2 vellykket.\nTove poeng: " + arkiv.finnPoeng(medlnrtove, "tove"));

        }

        if(arkiv.sjekkMedlemmer()){
            System.out.println("Test 3 vellykket. Brukere ble oppgradert.");
        }

        if(!arkiv.sjekkMedlemmer()){
            System.out.println("Test 4 vellykket. Brukere ble ikke oppgradert.");
        }
    }
}