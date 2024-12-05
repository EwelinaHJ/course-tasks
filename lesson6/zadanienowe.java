package lesson6;


public class zadanienowe {

    public static void main(String[] args) {

        char miesiac = 10;
        switch (miesiac) {
            case 1:
                System.out.println("Styczeń mamy zimę");
                break;
            case 2:
                System.out.println("Luty mamy zimę");
                break;
            case 3:
                System.out.println("Marzec mamy wiosnę");
                break;
            case 4:
                System.out.println("Kwiecień mamy wiosnę");
                break;
            case 5:
                System.out.println("Maj mamy wiosnę");
                break;
            case 6:
                System.out.println("Czerwiec mamy lato");
                break;
            case 7:
                System.out.println("Lipiec mamy lato");
                break;
            case 8:
                System.out.println("Sierpień mamy lato");
                break;
            case 9:
                System.out.println("Wrzesień mamy jesień");
                break;
            case 10:
                System.out.println("Październik mamy jesień");
                break;
            case 11:
                System.out.println("Listopad mamy jesień");
                break;
            case 12:
                System.out.println("Grudzień mamy zimę");
                break;

        }


    }


    public static class RokPrzestepny {
        public static void main(String[] args) {

            int rok = 2024;

            if (rok < 1 || rok > 9999) {
                System.out.println("Rok" + rok + " jest poza przyjętym zakresem");
            } else {
                if ((rok % 4 == 0 && rok % 100 != 0) || (rok % 400 == 0)) {
                    System.out.println("Rok " + rok + "jest rokiem przestępnym");
                } else {
                    System.out.println("Rok" + rok + "jest rokiem nieprzestepnym");
                }
            }
        }
    }

}









