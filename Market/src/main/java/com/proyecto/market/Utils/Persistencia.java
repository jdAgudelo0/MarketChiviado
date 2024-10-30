package com.proyecto.market.Utils;

import com.proyecto.market.Controller.ModelFactory;
import com.proyecto.market.Model.*;
import com.proyecto.market.Model.Enum.Categoria;
import com.proyecto.market.Model.Enum.Estado;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class Persistencia {

    public static final String rutaVendedoresTxt = "src/main/resources/DataBase/txt/Vendedor.txt";
    public static final String rutaAdministradoresTxt = "src/main/resources/DataBase/txt/Admins.txt";
    public static final String rutaProductoTxt = "src/main/resources/DataBase/txt/Producto.txt";

   public static final String rutaProductoVendido = "src/main/resources/DataBase/ProductoVendido.xml";
   public static final String rutaProductoPublicado = "src/main/resources/DataBase/ProductoPublicado.xml";
   public static final String rutaProductoCancelado = "src/main/resources/DataBase/ProductoCancelado.xml";

    public static final String rutaArchivoLog = "src/main/resources/LOG/Log.txt";
    private static final String rutaArchivoDat = "src/main/resources/DataBase/Market.dat";
    private static final String rutaMarketXml = "src/main/resources/DataBase/Market.xml";




    public static void guardarLog(String msg, int nivel, String accion){

        ArchivoUtil.guardarRegistroLOG(msg, nivel, accion, rutaArchivoLog);
    }

    public static void guardarVendedores(ArrayList<Vendedor> listaVendedores) throws IOException {
        StringBuilder contenido = new StringBuilder();

        for (Vendedor vendedor : listaVendedores) {
            contenido.append (vendedor.getCedula()).append(" %% ")
                    .append(vendedor.getNombre()).append(" %% ")
                    .append(vendedor.getApellido()).append(" %% ")
                    .append(vendedor.getUsuario()).append(" %% ")
                    .append(vendedor.getContrasenia()).append(" %% ");

            if (vendedor.getMuro() != null) {
                contenido .append(vendedor.getMuro().getId()).append(" %% ");
            }else{
                contenido.append("null %% ");
            }

            ArrayList<Vendedor> vendedores = vendedor.getCedulasAliados();
            if (vendedores != null) {
                for (Vendedor vendedor1 : vendedores) {
                    contenido.append(vendedor1.getCedula()).append(" %% ");
                }
                if (!vendedores.isEmpty()) {
                    contenido.setLength(contenido.length() - 4);
                }
            }

            ArrayList<Producto> productos = vendedor.getProductos();
            if (productos != null) {
                for (Producto producto1 : productos) {
                    contenido.append(producto1.getCodigo()).append(" %% ");
                }
                if (!productos.isEmpty()) {
                    contenido.setLength(contenido.length() - 4);
                }
            }
            contenido.append("\n");
        }

        ArchivoUtil.guardarArchivo(rutaVendedoresTxt, contenido.toString(), false);

    }

    public static ArrayList<Vendedor> cargarVendedores() throws IOException {
        ModelFactory modelFactory = ModelFactory.getInstance();
        ArrayList<Vendedor> listaVendedores = new ArrayList<>();
        ArrayList<String> contenido = ArchivoUtil.leerArchivos(rutaVendedoresTxt);

        for (String linea : contenido) {
            // Dividir la línea en partes usando " %% " como separador
            String[] partes = linea.split(" %% ");

            // Verificar si hay al menos cinco partes para inicializar los atributos principales
            if (partes.length < 5) {
                continue; // Si no hay suficientes partes, pasa a la siguiente línea
            }

            // Crear un nuevo vendedor y establecer sus atributos básicos
            Vendedor vendedor = new Vendedor();
            vendedor.setCedula(partes[0]);
            vendedor.setNombre(partes[1]);
            vendedor.setApellido(partes[2]);
            vendedor.setUsuario(partes[3]);
            vendedor.setContrasenia(partes[4]);

            // Asigna el muro si está disponible
            if (!"null".equals(partes[5])) {
                Muro muro = modelFactory.getMarket().hallarMuro(partes[5]);
                vendedor.setMuro(muro);
            }

            ArrayList<Vendedor> vendedoresAsociados = new ArrayList<>();
            int i = 6;
            while (i < partes.length && !esProducto(partes[i])) {
                Vendedor aliado = modelFactory.getMarket().hallarVendedor(partes[i]);
                if (aliado != null) vendedoresAsociados.add(aliado);
                i++;
            }
            vendedor.setCedulasAliados(vendedoresAsociados);

            // Solo asigna productos si el muro está disponible
            ArrayList<Producto> productos = new ArrayList<>();
            if (vendedor.getMuro() != null) {
                while (i < partes.length) {
                    Producto producto = modelFactory.getMarket().buscarProducto(partes[i]);
                    if (producto != null) productos.add(producto);
                    i++;
                }
            }
            vendedor.getProductos().addAll(productos);
            listaVendedores.add(vendedor);
        }

        return listaVendedores;
    }

    private static boolean esProducto(String parte) {
        // Puedes personalizar esta función dependiendo de cómo se estructura la cadena para productos
        // Aquí asumimos que los productos tienen un formato específico que los diferencia de otras partes
        try {
            Double.parseDouble(parte);
            return false; // Si parte es un número, no es un producto
        } catch (NumberFormatException e) {
            return true;}}


//______________________________________________________________________________________________________

    /*
        - Metodos Admin
    */

    public static void guardarAdministradores(ArrayList<Administrador> listaAdministradores) throws IOException {
        StringBuilder contenido = new StringBuilder();

        for (Administrador administrador : listaAdministradores) {
            contenido.append(administrador.getCedula()).append(" %% ")
                    .append(administrador.getNombre()).append(" %% ")
                    .append(administrador.getApellido()).append(" %% ")
                    .append(administrador.getUsuario()).append(" %% ")
                    .append(administrador.getPassword()).append(" %% ")
                    .append(administrador.getRutaImagen())
                    .append("\n");
        }

        ArchivoUtil.guardarArchivo(rutaAdministradoresTxt, contenido.toString(), false); // Cambia `rutaAdministradoresTxt` a la ruta que uses para guardar administradores
    }


    public static ArrayList<Administrador> cargarAdministradores() throws IOException {
        ArrayList<Administrador> listaAdministradores = new ArrayList<>();
        ArrayList<String> contenido = ArchivoUtil.leerArchivos(rutaAdministradoresTxt); // Cambia `rutaAdministradoresTxt` a la ruta que uses para cargar administradores

        for (String linea : contenido) {
            // Dividir la línea en partes usando " %% " como separador
            String[] partes = linea.split(" %% ");

            if (partes.length < 6) {
                continue; // Si no hay suficientes partes, pasa a la siguiente línea
            }

            // Crear un nuevo administrador y establecer sus atributos
            Administrador administrador = new Administrador();
            administrador.setCedula(partes[0]);
            administrador.setNombre(partes[1]);
            administrador.setApellido(partes[2]);
            administrador.setUsuario(partes[3]);
            administrador.setPassword(partes[4]);
            administrador.setRutaImagen(partes[5]);

            // Añadir el administrador a la lista
            listaAdministradores.add(administrador);
        }

        return listaAdministradores;
    }


//____________________________________________________________________________________________

    public static void guardarProductos(ArrayList<Producto> listaProductos) throws IOException {
    StringBuilder contenido = new StringBuilder();

    for (Producto producto : listaProductos) {
        contenido.append(producto.getCodigo()).append(" %% ")
                .append(producto.getVendedor().getCedula()).append(" %% ")
                .append(producto.getNombreProducto()).append(" %% ")
                .append(producto.getPrecio()).append(" %% ")
                .append(producto.getCategoria()).append(" %% ")
                .append(producto.getImagen()).append(" %% ")
                .append(producto.getEstado()).append(" %% ")
                .append(producto.getLikes()).append(" %% ");

        ArrayList<Comentario> comentarios = producto.getComentarios();
        if (comentarios != null) {
            for (Comentario comentario : comentarios) {
                contenido.append(comentario.getMensaje()).append(" %% ")
                        .append(comentario.getFechaPublicacion()).append(" %% ");
            }
            if (!comentarios.isEmpty()) {
                contenido.setLength(contenido.length() - 4);
            }
        }
        contenido.append("\n");
    }

    ArchivoUtil.guardarArchivo(rutaProductoTxt, contenido.toString(), false); // Cambia `rutaAdministradoresTxt` a la ruta que uses para guardar administradores
}


    public static ArrayList<Producto> cargarProductos() throws IOException {
        ArrayList<Producto> listaProductos = new ArrayList<>();
        ArrayList<String> contenido = ArchivoUtil.leerArchivos(rutaProductoTxt); // Usa la ruta adecuada

        for (String linea : contenido) {
            // Separar cada línea en campos usando " %% " como delimitador
            String[] partes = linea.split(" %% ");

            if (partes.length < 8) {
                continue; // Si la línea no tiene los campos básicos, saltarla
            }

            // Crear el producto y asignar los valores básicos
            Producto producto = new Producto();
            producto.setCodigo(partes[0]);

            // Configura el vendedor del producto
            Vendedor vendedor = new Vendedor();
            vendedor.setCedula(partes[1]);
            producto.setVendedor(vendedor);

            producto.setNombreProducto(partes[2]);
            producto.setPrecio(Float.parseFloat(partes[3]));
            producto.setCategoria(Categoria.valueOf(partes[4]));
            producto.setImagen(partes[5]);
            producto.setEstado(Estado.valueOf(partes[6]));
            producto.setLikes(Integer.parseInt(partes[7]));

            // Cargar comentarios (si existen)
            ArrayList<Comentario> comentarios = new ArrayList<>();
            int i = 8; // Comenzar desde la posición 8, donde empiezan los comentarios
            while (i < partes.length) {
                if (i + 1 >= partes.length) break; // Asegurarse de tener pares de datos para comentario

                Comentario comentario = new Comentario();
                comentario.setMensaje(partes[i]);
                comentario.setFechaPublicacion(LocalDate.parse(partes[i + 1]));

                comentarios.add(comentario);
                i += 2; // Avanzar al siguiente comentario
            }
            producto.setComentarios(comentarios);

            // Añadir el producto a la lista
            listaProductos.add(producto);
        }
        return listaProductos;
    }


    public static void guardarProductosPorEstado(ArrayList<Producto> listaProductos) throws IOException {
        ArrayList<Producto> productosCancelado = new ArrayList<>();
        ArrayList<Producto> productosPublicado = new ArrayList<>();
        ArrayList<Producto> productosVendidos = new ArrayList<>();

        for (Producto producto : listaProductos) {
            switch (producto.getEstado()) {
                case CANCELADO:
                    productosCancelado.add(producto);
                    break;
                case PUBLICADO:
                    productosPublicado.add(producto);
                    break;
                case VENDIDO:
                    productosVendidos.add(producto);
                    break;
            }
        }

        guardarProductosCancelado(productosCancelado);
        guardarProductosPublicado(productosPublicado);
        guardarProductosVendido(productosVendidos);
    }



    public static void guardarProductosCancelado(ArrayList<Producto> listaProductos) throws IOException {
        ArchivoUtil.salvarArchivoXml(rutaProductoCancelado, listaProductos);
    }

    public static void guardarProductosPublicado(ArrayList<Producto> listaProductos) throws IOException {
        ArchivoUtil.salvarArchivoXml(rutaProductoPublicado, listaProductos);
    }

    public static void guardarProductosVendido(ArrayList<Producto> listaProductos) throws IOException {
        ArchivoUtil.salvarArchivoXml(rutaProductoVendido, listaProductos);
    }



    public static void guardarBinario(Market market) throws IOException {
        try{
            ArchivoUtil.salvarSerializable(rutaArchivoDat, market);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static Market cargarBinario() throws IOException {

        Market market = null;
        try{
            market = (Market) ArchivoUtil.leerSerializable(rutaArchivoDat);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return market;
    }

    public static void guardarArchivoXml(Market market) {
        try{
            ArchivoUtil.salvarArchivoXml(rutaMarketXml, market);
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    public static Market cargarArchivoXml() {
        Market market = null;
        try{
            market = (Market) ArchivoUtil.cargarArchivoXml(rutaMarketXml);
        }catch(Exception e){
            e.printStackTrace();
        }
        return market;
    }



}
