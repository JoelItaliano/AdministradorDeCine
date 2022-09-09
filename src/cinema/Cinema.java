package cinema;
import java.util.Scanner;
public class Cinema {


    public static void main(String[] args) {
        int contBuyVolet = 0;
        int priceBolet = 0;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        int cantRows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int numSeats = scanner.nextInt();

        String[][] asientos = new String[cantRows][numSeats];
        cargarAsientos(asientos);
        menu(asientos, cantRows, numSeats, contBuyVolet, priceBolet, scanner );
    }

    /*
        funcion que contiene menu
     */
    static void menu(String[][] asientos, int catRows, int numSeats, int contBuyVolet, int priceBolet, Scanner scanner){
        boolean exit = true;
        do {
            System.out.println();
            System.out.println("1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("3. Statistics");
            System.out.println("0. Exit");
            int respuesta = scanner.nextInt();

            switch (respuesta){
                case 1:
                    System.out.println();
                    imprimirAsientos(asientos);
                    break;
                case 2:
                    priceBolet = buyAsientos(catRows, numSeats, asientos, priceBolet, scanner);
                    contBuyVolet += 1;
                    break;
                case 3:
                    estadisticas(contBuyVolet, catRows, numSeats, priceBolet);
                    break;
                case 0:
                    exit = false;
                    break;
                default:
                    System.out.println("Respuesta incorrecta, intente de nuevo");
            }
        }while (exit);
    }

    /*
     * Pregunta por teclado la cantidad de asientos y filas que tiene el cine
     * luego llama a la funcion que muestra en consola esta cantidad
     */
    static int buyAsientos(int cantRows, int numSeats, String[][] asientos, int priceBolet, Scanner scanner) {
        boolean validador = true;
        while (validador){
            System.out.println("Enter a row number:");
            int posRow = scanner.nextInt();
            System.out.println("Enter a seat number in that row:");
            int posSeat = scanner.nextInt();

            if (posRow > cantRows || posSeat > numSeats){
                System.out.println("Wrong input!");
            }else if (asientos[posRow - 1][posSeat - 1] == "B") {
                System.out.println("That ticket has already been purchased!");
            }else {
                asientos[posRow - 1][posSeat - 1] = "B";
                priceBolet += calcPrecio(cantRows, numSeats, posRow, priceBolet);
                validador = false;
            }
        }
        return priceBolet;
    }

    /*
     * funcion para llenar array
     */
    static String[][] cargarAsientos(String[][] asientos) {
        for (int i = 0; i < asientos.length; i++) {
            for (int j = 0; j < asientos[i].length; j++) {
                asientos[i][j] = "S";
            }
        }
        return asientos;
    }

    /*
     * muestra en consola la cantidad de asientos que tiene el cine
     */
    public static void imprimirAsientos(String[][] asientos) {

        System.out.println("Cinema:");
        System.out.print(" ");

        for (int i = 0; i < asientos[0].length; i++) {
            System.out.print(" " + (i + 1));
        }
        System.out.println();

        for (int i = 0; i < asientos.length; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < asientos[i].length; j++) {
                System.out.print(asientos[i][j] + " ");
            }
            System.out.println("");
        }
    }


    /*
     *calcula el precio del boleto
     * */
    static int calcPrecio(int cantRows, int numSeats, int posRow, int priceBolet) {

        int filaDelantera = cantRows /2;
        int filaTrasera = cantRows / 2;
        int cantAsientos = cantRows * numSeats;

        if (cantAsientos <= 60) {
            priceBolet = 10;
        } else {
            if (cantRows % 2 == 0) {
                priceBolet = (posRow <= filaDelantera) ? 10 : 8;
            } else {
                filaDelantera = ((cantRows - 1) / 2);
                filaTrasera = ((cantRows + 1) / 2);
                priceBolet = (posRow <= filaDelantera) ? 10 : 8;
                }
        }
        System.out.printf("%nTicket price: $%d %n", priceBolet);
        return priceBolet;
    }

    /*
    funcion que muestra las estadisticas
  */
    public static void estadisticas (int contBuyVolet, int cantRows, int numSeats, int priceBolet){
        float numAsientos = cantRows * numSeats;
        float contadorVolteComprados = contBuyVolet;
        float porcentaje = contBuyVolet != 0  ? (contBuyVolet * 100) / numAsientos  : 0.00f;
        int total = priceTotal(cantRows, numSeats);
        System.out.printf("Number of purchased tickets: %d %nPercentage: %.2f%% %nCurrent income: $%d %nTotal income: $%d %n", contBuyVolet, porcentaje, priceBolet, total);
    }
    static int priceTotal(int cantRows, int numSeats){
        int cantAsientos = cantRows * numSeats;
        int total = 0;

        if (cantAsientos <= 60){
            total = 10 * cantAsientos;
        } else{
            if (cantRows % 2 == 0){
                total = ((cantAsientos /2) * 10) + (cantAsientos/2) * 8;
            }else {
                int precioFilaDelantera = (((cantRows - 1) / 2) * numSeats) * 10;
                int pretcioFilaTrasera = (((cantRows + 1) / 2) * numSeats) * 8;
                total = precioFilaDelantera + pretcioFilaTrasera;
            }
        }
        return total;
    }
}







