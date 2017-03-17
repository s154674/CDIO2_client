import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Main {
    private static Scanner sc = new Scanner(System.in);
    private static Menu menu = new Menu(sc);

    public static void main(String[] args) {
        try {
            // Konfigurér socket
            Socket clientSocket = new Socket("127.0.0.1", 8000); // Brug altid denne adresse
            BufferedReader inStream = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            DataOutputStream outStream = new DataOutputStream(clientSocket.getOutputStream());

            // send beskeder
            String message = "";
            while (message!="Q") {
                message = menu.constructCommand();
                outStream.writeBytes(message+'\n');
                String inLine = inStream.readLine();
                System.out.println("Modtaget: \""+inLine+"\"");
            }
            // Luk ned
            clientSocket.close();
            sc.close();
            System.out.println("Program lukket");

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}