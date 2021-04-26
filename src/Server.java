import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

    public class Server {
        static final int PORT = 7777;
        private ArrayList<Handler> clients = new ArrayList<Handler>();

        public Server() {
            Socket clientSocket = null;
            ServerSocket serverSocket = null;
            try {
                serverSocket = new ServerSocket(PORT);
                System.out.println("Server work!!!!!!!!");
                while (true) {

                    clientSocket = serverSocket.accept();
                    Handler client = new Handler(clientSocket, this);
                    clients.add(client);
                    new Thread(client).start();
                }
            }
            catch (IOException ex) {
                ex.printStackTrace();
            }
            finally {
                try {
                    clientSocket.close();
                    System.out.println("stop server");
                    serverSocket.close();
                }
                catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }

        public void sendMessageToAllClients(String msg) {
            for (Handler o : clients) {
                o.sendMsg(msg);
            }

        }

        public void removeClient(Handler client) {
            clients.remove(client);
        }

    }


