import java.util.*;

public class PlaneManagement {
    //Array, objects of Ticket class
    public static Ticket[][] ticketArray = new Ticket[4][14];

    public static void main(String[] args) {

        int quitOp = 0;
        System.out.println("Welcome to the Plane Management application");
        // Array, seat availability
        int[][] seatArray = {{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};

        do {
            System.out.println("*************************************************");
            System.out.println("*                  MENU OPTIONS                 *");
            System.out.println("*************************************************");
            System.out.println("     1) Buy a seat");
            System.out.println("     2) Cancel a seat");
            System.out.println("     3) Find first available seat");
            System.out.println("     4) Show seating plan");
            System.out.println("     5) Print tickets information and total sales");
            System.out.println("     6) Search ticket");
            System.out.println("     0) Quit");
            System.out.println("*************************************************");

            try {
                Scanner input = new Scanner(System.in);
                System.out.print("Please select an option: ");
                int option = input.nextInt();

                switch (option) {
                    case 1:
                        System.out.println("-----------------------------------");
                        buySeat(seatArray);
                        break;
                    case 2:
                        System.out.println("-----------------------------------");
                        cancelSeat(seatArray);
                        break;
                    case 3:
                        System.out.println("-----------------------------------");
                        findFirstAvailable(seatArray);
                        break;
                    case 4:
                        System.out.println("-----------------------------------");
                        showSeatingPlan(seatArray);
                        break;
                    case 5:
                        System.out.println("-----------------------------------");
                        printTicketsInfo();
                        break;
                    case 6:
                        System.out.println("-----------------------------------");
                        searchTicket();
                        break;
                    case 0:
                        System.out.println("-----------------------------------");
                        System.out.println("QUIT");
                        quitOp--;
                        break;
                    default:
                        System.out.println("Please Enter a valid option !");
                }
            }
            catch (InputMismatchException e){
                System.out.println("Please Enter a valid option !");
            }
        } while (quitOp != -1) ;// repeat till quit option

    }


    /**
     * Book seats and save information
     * @param seatArray Array used to store availability of seats.
     */
    public static void buySeat(int[][] seatArray){
        double seatPrice;
        boolean repeatOp = true;

        while (repeatOp) {
            Scanner input = new Scanner(System.in);
            System.out.println("Enter the seat you want to Book");
            System.out.print("Seat Row(A,B,C,D): ");
            String seatRow = input.next();
            //convert string to int using seatRowInt method
            int rowI = seatRowInt(seatRow);
            if (rowI == -1){
                System.out.println("Enter a valid seat row");
                continue;
            }
            //seat number errors handling
            int seatNb = seatNbErrorHandle(rowI);
            /*Assign values to seats*/
            if (seatNb<6){seatPrice = 200;}
            else if(seatNb > 5 && seatNb < 10){seatPrice = 150;}
            else {seatPrice = 180;}

            if (seatArray[rowI][seatNb-1]==0){
                System.out.println("Seat is available");
                boolean repeatOp2 = true;
                while(repeatOp2){
                    System.out.println("Do you want to reserve the seat(Yes or NO): ");
                    String seatBookQ = input.next();
                    if(seatBookQ.equalsIgnoreCase("Yes")){
                        seatArray[rowI][seatNb-1] = 1;
                        System.out.println("Enter your First Name: ");
                        String fName = input.next();
                        validateName(fName);
                        System.out.println("Enter your Surname: ");
                        String sName = input.next();
                        validateName(sName);

                        String email = emailValidation();

                        Person person = new Person(fName,sName,email);
                        Ticket ticket = new Ticket(seatRow.toUpperCase(),seatNb,seatPrice,person);
                        ticketArray[rowI][seatNb-1] = ticket;
                        System.out.println("The seat is successfully booked!");
                        ticket.save();
                        repeatOp2 = false;
                    }
                    else if (seatBookQ.equalsIgnoreCase("No")) {
                        System.out.println("You cancelled the booking");
                        repeatOp2 = false;
                    }
                    else {
                        System.out.println("Please Enter Yes or No !");
                    }
                }
            }
            else {
                System.out.println("The seat is unavailable!");
            }
            repeatOp = menuOrCont();
        }
    }

    /**
     * Cancel reservations when user wants to and delete information
     * @param seatArray Array that used to store availability of seats
     */
    public static void cancelSeat(int[][] seatArray){
        boolean repeatOp = true;
        while (repeatOp){
            Scanner input = new Scanner(System.in);
            System.out.println("Enter the seat you want to cancel");
            System.out.print("Seat Row(A,B,C,D): ");
            String seatRow = input.next();

            int rowI = seatRowInt(seatRow);
            if (rowI == -1){
                System.out.println("Enter a valid seat row");
                continue;
            }
            int seatNb = seatNbErrorHandle(rowI);
            if (seatArray[rowI][seatNb-1]==1){
                System.out.println("The Seat is Reserved");
                System.out.println("Do you want to cancel the Reservation(Yes or No): ");
                String seatCancelQ = input.next();
                if(seatCancelQ.equalsIgnoreCase("Yes")){
                    seatArray[rowI][seatNb-1] = 0;
                    ticketArray[rowI][seatNb] = null;

                    System.out.println("The Reservation is successfully cancelled!");
                    break;

                }
                else if (seatCancelQ.equalsIgnoreCase("No")) {
                    System.out.println("You terminated the cancellation.");

                    break;
                }
                else {
                    System.out.println("Please Enter Yes or No !");
                }
            }
            else {
                System.out.println("The seat is not Reserved !");
                repeatOp = menuOrCont();
            }
        }
    }

    /**
     * Finds the first available seat and prints it.
     * @param seatArray Array that used to store availability of seats.
     */
    public static void findFirstAvailable(int[][] seatArray){
        int i = 0;
        int j;
        boolean firstFound = false;
        String iRow = "";

        while ((i < seatArray.length) && !firstFound) {
            j = 0;
            while ((j < seatArray[i].length) && !firstFound) {
                if (seatArray[i][j] == 0) {

                    switch (i) {
                        case (0):
                            iRow = "A";
                            break;
                        case (1):
                            iRow = "B";
                            break;
                        case (2):
                            iRow = "C";
                            break;
                        case (3):
                            iRow = "D";
                            break;
                    }
                    System.out.println(iRow + (j + 1) + " seat is available");
                    firstFound = true;
                }
                else {
                    System.out.println("All seats are Reserved !");
                }


                j++;
            }
            i++;
        }
    }

    /**
     * Display the seating plan(if seat is booked:X, if it's available:O)
     * @param seatArray Array that used to store availability of seats
     */
    public static void showSeatingPlan(int[][] seatArray){
        System.out.println("         Seating Plan");
        System.out.println("Available :- 0     Reserved:-X ");
        for (int i = 0;i<seatArray.length;i++){
            for (int j = 0; j<seatArray[i].length;j++){
                if (seatArray[i][j] == 0){
                    System.out.print("O ");
                }
                else {
                    System.out.print("X ");
                }
            }
            System.out.println();
        }
    }

    /**
     * Prints the information of all reserved seats.
     */
    public static void printTicketsInfo(){
        double totalPrice = 0;
        for (int i = 0; i < ticketArray.length; i++) {
                for (int j = 0; j < ticketArray[i].length; j++) {
                    if (ticketArray[i][j] != null) {
                        System.out.println(ticketArray[i][j]);
                        Ticket ticket = ticketArray[i][j];
                        totalPrice += ticket.getPrice();
                        System.out.println("*---------------------*");
                    }
                }
        }
        System.out.println("Total amount: " + totalPrice );
    }

    /**
     * Prompt user to enter a seat and check whether it's booked or not and if it's booked
     * print the information
     */
    public static void searchTicket() {
        Scanner input = new Scanner(System.in);
        boolean repeatOp = true;
        while (repeatOp) {
            System.out.println("Enter the seat you want to search");
            System.out.print("Seat Row(A,B,C,D): ");
            String seatRow = input.next();
            int rowI = seatRowInt(seatRow);
            if (rowI == -1) {
                System.out.println("Enter a valid seat row");
                continue;
            }

            int seatNb = seatNbErrorHandle(rowI);

            if (ticketArray[rowI][seatNb - 1] != null) {
                Ticket ticket = ticketArray[rowI][seatNb-1];
                System.out.println(ticket);
                repeatOp = false;
            }
            else {
                System.out.println("This seat is available");
                repeatOp = menuOrCont();
            }

        }
    }

    /**
     * Convert the entered seat row to int so, it can be used in arrays.
     * @param seatRow Entered seat row in String type
     * @return The integer for entered seat row
     */
    public static int seatRowInt(String seatRow){

        switch (seatRow.toUpperCase()) {
            case "A":
                return 0;
            case "B":
                return 1;
            case "C":
                return 2;
            case "D":
                return 3;
            default:
                return -1;
        }
    }

    /**
     * Prompts user to enter the seat number and check whether it's valid.
     * @param rowI The integer for entered row.
     * @return The seat number if it's valid.
     */
    public static int seatNbErrorHandle(int rowI){

        Scanner input = new Scanner(System.in);
        int seatNb;
        while (true){
            try {
                System.out.print("Seat Number(A/D:1-14,B/C:1-12): ");
                seatNb = input.nextInt();

                if ((rowI == 0 || rowI == 3) && seatNb > 14) {
                    System.out.println("Enter a valid seat Number for the Row!");
                } else if ((rowI == 1 || rowI == 2) && seatNb > 12) {
                    System.out.println("Enter a valid seat number for the Row");
                } else {
                    break;
                }
            }
            catch (InputMismatchException e){
                System.out.println("Enter a valid seat number for the Row");
                input.next();

            }

        }
        return seatNb;
    }

    /**
     *Prompts the user to continue the option or return to the menu.
     * @return true(to continue the option) or false(to go to the menu)
     */
    public static boolean menuOrCont(){
        Scanner input = new Scanner(System.in);

        while (true){
            System.out.print("Enter \"M\" to go to the main menu or \"C\" to continue: ");
            String contOp = input.next();
            if (contOp.equalsIgnoreCase("M")){
                return false;
            }
            else if (contOp.equalsIgnoreCase("C")){
                return true;
            }
            else {
                System.out.println("Please Enter M or C!");
            }
        }
    }

    /**
     * Check whether email is valid or not
     * @return The valid email
     * Refference: https://www.tutorialspoint.com/checking-for-valid-email-address-using-regular-expressions-in-java
     */
    public static String emailValidation(){
        Scanner input = new Scanner(System.in);
        while (true){
            System.out.println("Enter your Email: ");
            String email = input.next();
            String regex = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$";
            boolean result = email.matches(regex);
            if (result){
                return email;
            }
            else {
                System.out.println("Email is invalid please try again!");
            }
        }

    }

    /**
     * validate the name
     * @param name firstname or second name
     * @return Valid name
     * Referrence:https://www.tutorialspoint.com/checking-for-valid-email-address-using-regular-expressions-in-java
     */
    public static String validateName(String name) {
        Scanner input = new Scanner(System.in);
        while (true) {
            String regex = "^[a-zA-Z ]+$";
            boolean result = name.matches(regex);
            if (result) {
                return name;
            } else {
                System.out.println("Name is invalid");
                System.out.print("Enter a valid name: ");
                name = input.nextLine();
                continue;
            }
        }
    }
}



