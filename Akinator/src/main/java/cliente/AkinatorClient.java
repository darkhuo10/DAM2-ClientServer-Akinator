package cliente;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class AkinatorClient {

    static Scanner sc = new Scanner(System.in);


    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 4567);

        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);


        System.out.println("Piensa un número entre 0 y 100");

        while (true) {
            int numero = Integer.parseInt((br.readLine()));
            System.out.println("¿Es el numero " + numero + " ?");
            System.out.println("mayor [>] / menor [<] / correcto [ok]");
            String respuesta = sc.nextLine();
            out.println(respuesta);
            if (respuesta.equalsIgnoreCase("ok"))
                System.exit(0);
        }


    }

}
