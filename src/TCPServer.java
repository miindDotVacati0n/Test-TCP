import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {
    public static void main(String[] args) throws IOException {
        Socket socket = null;
        ServerSocket serverSocket = null;
        InputStreamReader inputStreamReader = null;
        OutputStreamWriter outputStreamWriter = null;
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;

        serverSocket = new ServerSocket(8000);

        while (true) {
            try {
                socket = serverSocket.accept();

                inputStreamReader = new InputStreamReader(socket.getInputStream());
                outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());

                bufferedReader = new BufferedReader(inputStreamReader);
                bufferedWriter = new BufferedWriter(outputStreamWriter);

                while (true) {

                    String acpFromClient = bufferedReader.readLine();

                    System.out.println("Client: " + acpFromClient);

                    bufferedWriter.write("ACP Received.");
                    bufferedWriter.newLine();
                    bufferedWriter.flush();
                    if (acpFromClient.equalsIgnoreCase("END"))
                        break;
                }

                socket.close();
                inputStreamReader.close();
                outputStreamWriter.close();
                bufferedReader.close();
                bufferedWriter.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
