package servidor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class AkinatorServer {

    private static final int NUMERO_MINIMO = 0;
    private static final int NUMERO_MAXIMO = 100;

    private static int numero;
    private static int minimo;
    private static int maximo;

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(4567);
        while (true) {
            Socket socket = serverSocket.accept();

            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            String mensaje;

            minimo = NUMERO_MINIMO;
            maximo = NUMERO_MAXIMO;
            numero = numeroAleatorio(minimo, maximo);

            while (true) {
                out.println(numero);
                System.out.println("Número: " + numero);

                mensaje = br.readLine();
                boolean esMenor;
                if (mensaje.equalsIgnoreCase(">")) {
                    esMenor = false;
                    cambiarNumero(esMenor);
                } else if (mensaje.equalsIgnoreCase("<")) {
                    esMenor = true;
                    cambiarNumero(esMenor);
                } else if (mensaje.equalsIgnoreCase("ok")) {
                    System.out.println("Acertado. Fin del juego");
                    System.exit(0);
                } else
                    break;
            }
            socket.close();
            serverSocket.close();
            out.close();
            br.close();
        }
    }

    private static void cambiarNumero(boolean menor) {
        if (menor)
            maximo = numero - 1;
        else
            minimo = numero + 1;
        numero = numeroAleatorio(minimo, maximo);
    }

    private static int numeroAleatorio(int minimo, int maximo) {
        return (int) (Math.random() * (maximo - minimo) + minimo);
    }


}