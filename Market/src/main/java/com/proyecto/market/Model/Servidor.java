package com.proyecto.market.Model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import com.proyecto.market.Model.Vendedor;


public class Servidor {

    private static Map<String, String> users = new HashMap();
    private static ArrayList<Producto> productosPublicados = new ArrayList<>();
    private static Market vendedores = new Market();

    public static void main(String[] args){
        vendedores.getVendedores();

        System.out.println("Servidor iniciado con exito :)");

        try(ServerSocket serverSocket = new ServerSocket(12345)){
            while(true){
                Socket clientSocket = serverSocket.accept();
                System.out.println("Nuevo Cliente conectado");
                new Thread(new ClientHandler(clientSocket)).start();
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private static class ClientHandler implements Runnable{

        private Socket socket;

        public ClientHandler(Socket socket){
            this.socket = socket;
        }

        @Override
        public void run() {

            try(ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream())){

                String action =(String) in.readObject();
                if(action.equals("AUTH")){
                    handleAuth(in, out);

                } else if (action.equals("GET_REPORTE")) {
                    handleReport(in, out);
                }
            } catch (IOException | ClassNotFoundException e){
                e.printStackTrace();
            }

        }

        private void handleAuth(ObjectInputStream in, ObjectOutputStream out) throws IOException, ClassNotFoundException {

            try {
                // Leer los objetos desde el flujo de entrada
                String user = (String) in.readObject();
                String password = (String) in.readObject();

                boolean authenticated = false;
                // Verificar las credenciales
                for (Map.Entry<String, String> entry : users.entrySet()) {
                    if (entry.getKey().equals(user) && entry.getValue().equals(password)) {
                        authenticated = true;
                        out.writeObject("SUCESS");
                        break;
                    }
                }

                if (!authenticated) {
                    out.writeObject("FAIL");
                }

            } catch (SocketException e) {
                System.err.println("Error de conexión: " + e.getMessage());
                e.printStackTrace();
            } catch (IOException | ClassNotFoundException e) {
                System.err.println("Error al procesar la solicitud: " + e.getMessage());
                e.printStackTrace();
            }
        }

        private void handleReport(ObjectInputStream in, ObjectOutputStream out) throws IOException, ClassNotFoundException {

            String date = (String) in.readObject();

            ArrayList<Producto> result = productosPublicados;
            out.writeObject(result);

        }
    }
}
