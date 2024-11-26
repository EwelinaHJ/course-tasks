public class ZadanieDomowe {

    public static void main(String args[]) {


        int liczba = 76;

        if (liczba > 0 && liczba < 59) {
            System.out.println("Otrzymałeś ocenę F");
        } else if (liczba > 60 && liczba < 69) {
            System.out.println("Otrzymałeś ocenę D");
        } else if (liczba > 70 && liczba < 79) {
            System.out.println("Otrzymałeś ocenę C ");
        } else if (liczba > 80 && liczba < 89) {
            System.out.println("Otrzymałeś ocenę B");
        } else if (liczba > 90 && liczba < 100) {
            System.out.println("Otrzymałeś ocenę A");
        }


        var temperatura = 68;
        var fah = ((temperatura * 9 / 5) + 32);
        System.out.println("Stopnie Fahrenheita wynoszą:" + fah);


        int cyferka = 275;
        if (cyferka % 2 == 0) {
            System.out.println(cyferka + "jest parzysta");
        } else {
            System.out.println((cyferka + "jest nieparzysta"));

        if ((((cyferka % 2) == 0) && ((cyferka % 5) == 0))) {
                System.out.println(cyferka + "jest parzysta i dzieli się przez 5");
            } else {
                System.out.println(cyferka + "jest nieparzysta i nie dzieli się przez 5");
            }
        }



    }


}








