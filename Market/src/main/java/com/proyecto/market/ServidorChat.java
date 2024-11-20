package com.proyecto.market;

import com.proyecto.market.Model.Market;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;

public class ServidorChat {

    private static ConcurrentHashMap<String, ObjectOutputStream> activeUsers = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        System.out.println("Servidor de Chat iniciado...");
        try (ServerSocket serverSocket = new ServerSocket(12346)) {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Cliente encontrado: " + clientSocket.getRemoteSocketAddress());
                new Thread(new ClientHandler(clientSocket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class ClientHandler implements Runnable {
        private Socket socket;
        private ObjectOutputStream out;
        private ObjectInputStream in;
        private String username;

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {

                while (true) {
                    String recipient = (String) in.readObject();
                    String message = (String) in.readObject();
                    sendMessage(recipient, username + ": " + message);
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
                try {
                    socket.close();
                } catch (IOException ex) {
                   ex.printStackTrace();
                }

            }

        }

        private static void sendMessage(String recipient, String message) {
            try {
                ObjectOutputStream recipientOut = activeUsers.get(recipient);
                if (recipientOut != null) {
                    recipientOut.writeObject(message);
                    recipientOut.flush();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
