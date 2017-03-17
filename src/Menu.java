import java.util.Scanner;

/**
 * Created by emilbonnekristiansen on 16/03/2017.
 */
public class Menu {
    private static Scanner sc;

    public Menu(Scanner sc) {
        this.sc = sc;
    }

    // Lad brugeren lave en parameter til en kommando der skal bruge en double
    private String selectParameterDouble(String question){
        double paramDouble = 0;

        while (true) {
            System.out.println(question);
            try {
                paramDouble = Double.parseDouble(sc.nextLine());
                return String.valueOf(paramDouble);
            } catch (NumberFormatException e) {
                System.err.println("Forkert input");
            }
        }

    }

    // Lad brugeren lave en parameter til en kommando der skal bruge en integer
    private String selectParameterInteger(String question){
        int paramInt;

        do {
            System.out.println(question);
            try {
                paramInt = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.err.println("Forkert input");
                paramInt = 0;
            }
        } while (1 > paramInt || paramInt > 4);

        return String.valueOf(paramInt);
    }

    // Lad brugeren lave en parameter til en kommando der skal bruge en string
    private String selectParameterString(String question){
        String param;

        do {
            System.out.println(question);
            param = sc.next().split(" ")[0];
        } while (param.length() >= 30);

        return param;
    }

    // Lad brugeren konstruere en kommando med et Menu system
    public String constructCommand(){
        int choice = 0;
        String command = "Q";
        String[] commands = {"S   ","T   ", "D   ", "DW  ", "P111", "RM20", "K   ", "B   ", "Q   "};
        String[] effects = {"Send stabil afvejning", "Tarér vægten", "Display", "Slet vægtens display", "Skriv i sekundært display", "Skriv i display, afvent indtasting", "Skifter vægtens knap-tilstand", "ny bruttovægt", "Luk"};

        System.out.println("--HOVEDMENU--");
        for (int i = 0; i < commands.length; i++) {
            System.out.println((i+1)+" - "+effects[i]);
        }

        do {
            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.err.println("Forkert input");
                choice = 0;
            }
        } while (1 > choice || choice > 9);

        switch (choice) {
            case 1:
                command = "S";
                break;
            case 2:
                command = "T";
                break;
            case 3:
                command = "D "+ selectParameterString("Hvad vil du skrive i displayet? (string uden mellemrum)");
                break;
            case 4:
                command = "DW";
                break;
            case 5:
                command = "P111 "+ selectParameterString("Hvad vil du skrive i sekundært display? (string uden mellemrum)");
                break;
            case 6:
                command = "RM208 "+ selectParameterString("Hvad vil du skrive i displayet? (string uden mellemrum)");
                break;
            case 7:
                command = "K "+selectParameterInteger("Hvilken knap-tilstand vil du sætte vægten i? (1-4)");
                break;
            case 8:
                command = "B "+selectParameterDouble("Hvad skal den nye brutto vægt være? (double)");
                break;
            case 9:
                command = "Q";
                break;
        }

        return command;

    }
}
