package lesson6;

public class DniWMiesiacu {


    public static int dniWMiesiacu(int numerMiesiaca, int rok) {

        boolean przestepny = (rok % 4 == 0 && rok % 100 != 0) || (rok % 400 == 0);


        switch (numerMiesiaca) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                return 31;

            case 4:
            case 6:
            case 9:
            case 11:
                return 30;

            case 2:

                return przestepny ? 29 : 28;

            default:

                System.out.println("Niepoprawny numer miesiąca");
                return 0;
        }
    }


    public static void main(String[] args) {
        int rok = 2024;
        int miesiac = 2;

        int dni = dniWMiesiacu(miesiac, rok);

        System.out.println("Liczba dni w miesiącu " + miesiac + " roku " + rok + " wynosi: " + dni);
    }

}
