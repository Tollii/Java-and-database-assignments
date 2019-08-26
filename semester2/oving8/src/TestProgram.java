class TestProgram{
    public static void main(String[]args){
        Staa staatrib1 = new Staa("Staaplass 1", 30, 100);
        Staa staatrib2 = new Staa("Staaplass 2", 30, 100);
        Sitte sittetrib = new Sitte("Sitteplass 1", 20, 150, 2);
        VIP vip = new VIP("BIG T (Tribune)", 10, 500, 2);

        staatrib1.kjopBilletter(10);
        staatrib2.kjopBilletter(5);
        sittetrib.kjopBilletter(5);

        String[] navn = {"BIG T (ikke tribune)", "Hans K", "Mr. Younger", "Atle Antonsen"};

        vip.kjopBilletter(navn);

        System.out.println(staatrib1.toString());
        System.out.println(staatrib2.toString());
        System.out.println(sittetrib.toString());
        System.out.println(vip.toString());
    }
}