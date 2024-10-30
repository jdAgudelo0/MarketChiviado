package com.proyecto.market.Controller;

import com.proyecto.market.Exceptions.*;
import com.proyecto.market.Model.*;
import com.proyecto.market.Utils.Persistencia;

import java.io.IOException;
import java.util.ArrayList;
import static com.proyecto.market.Utils.Persistencia.*;

public class ModelFactory {

    Market market;

    Vendedor vendedor;
    Administrador administrador;

    private Thread actualizadorDatosThread;

    public static class SingletonHolder {
        private final static ModelFactory INSTANCE;
        static {
            try{
                INSTANCE = new ModelFactory();
            }catch(Exception e){
                throw new RuntimeException(e);
            }
        }
    }

    public static ModelFactory getInstance() {
        return SingletonHolder.INSTANCE;
    }


    public Market getMarket() {
        return market;
    }

    public ModelFactory() throws IOException {

        System.out.printf("te invoco");

        cargarDatos();
        guardarLog("Inicio De Sesion",1,"Ah Iniciado Sesion");
        guardarBinario();
        guardarArchivoXml();
        iniciarActualizacionDatos();

    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

    public Administrador getAdministrador() {
        return administrador;
    }

    public void setAdministrador(Administrador administrador) {
        this.administrador = administrador;
    }

    /*
          - Metodos Generales
        */
    public int loggin(String username, String password) {
        if (getMarket().loggin(username,password)==1){
            vendedor = getMarket().hallarVendedorUsuario(username,password);
            return 1;
        }else if(getMarket().loggin(username,password) == 2){
            administrador = getMarket().hallarAdminUsuario(username,password);
            return 2;
        }else {
            return 0;
        }}


    private void iniciarActualizacionDatos() {
        actualizadorDatosThread = new Thread(() -> {
            while (true) {
                try {
                    cargarDatos(); // Llamar al método cargarDatos
                    System.out.println("Cargando los datos");
                    Thread.sleep(15000); // Esperar 10 segundos
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt(); // Restaurar el estado de interrupción
                } catch (Exception e) {
                    System.err.println("Error al cargar datos: " + e.getMessage());
                }}});
        actualizadorDatosThread.start();}


//________________________________________________________________________________________

    /*
       - Metodos Producto
     */
    public int addProducto(Producto producto) throws ProductoException {
        if (!getMarket().productExist(producto.getCodigo())) {
            getMarket().addProduct(producto);
            salvarDatos();
            guardarLog("se esta agregando un producto", 1, "registro");
            return 1;
        }else{
            return 0;}}

    public int deleteProducto(Producto producto) throws ProductoException {
        if (getMarket().productExist(producto.getCodigo())) {
            getMarket().removeProduct(producto);
            salvarDatos();
            guardarLog("se esta eliminando un producto", 3, "eliminar");
            return 1;
        }else{
        return 0;}}

    public boolean updateProducto(Producto producto,String codigo) throws ProductoException {
        getMarket().updateProduct(producto, codigo);
        salvarDatos();
        guardarLog("se esta actualizando el producto", 2,"actualizar");
        return true;}

    public ArrayList<Producto> obtenerProductos() {
        return getMarket().obtenerProductos();}

    public ArrayList<Producto> obtenerProductosVendedor(String id){
        return getMarket().obtenerProductoVendedor(id);
    }

    public Producto hallarProducto(String codigo) throws ProductoException {
        return getMarket().buscarProducto(codigo);
    }

    public boolean cambiarEstadoProducto(Producto producto) throws ProductoException {
        if (getMarket().productExist(producto.getCodigo())) {
            getMarket().cambiarEstadoProducto(producto);
            salvarDatos();
            guardarLog("Se cambio el estado del Producto "+producto.getCodigo(),2,"actualizacion");
            return true;
        }else{
            return false;
        }
    }

    /*
      - Metodos Comentario Producto
    */

    public int addComentarioProducto(Producto producto,Comentario comentario) throws ComentarioException {
        if (getMarket().productExist(producto.getCodigo())) {
            getMarket().addComentarioProducto(producto,comentario);
            salvarDatos();
            guardarLog("se agrego un comentario al Producto "+producto.getCodigo(), 1, "registro");
            return 1;
        }else{
            return 0;}}

    public int deleteComentarioProducto(Producto producto,Comentario comentario) throws ComentarioException {
        if (getMarket().productExist(producto.getCodigo())){
            getMarket().removerComentarioProducto(producto,comentario);
            salvarDatos();
            guardarLog("se eliminp un comentario al Producto "+producto.getCodigo(), 3, "eliminar");
            return 1;
        }else{
            return 0;}}

    public ArrayList<Comentario> obtenerComentarioProductos(Producto producto) throws ComentarioException {
        return getMarket().obtenerComentariosProducto(producto);}


//________________________________________________________________________________________

    /*
       - Metodos Vendedor
     */
    public int addVendedor(Vendedor vendedor) throws VendedorException {
        if (!getMarket().vendedorExist(vendedor.getCedula())) {
            if (!getMarket().usuarioExiste(vendedor.getUsuario(),vendedor.getContrasenia())
                && !getMarket().cedulaExiste(vendedor.getCedula())) {
                getMarket().addVendedor(vendedor);
                salvarDatos();
                guardarLog("se esta agregando un vendedor", 1, "registro");
                return 1;
            }else{
                return 0;}
        }else{
            return 0;}}


    public int deleteVendedor(Vendedor vendedor) throws VendedorException {
        if (getMarket().vendedorExist(vendedor.getCedula())) {
            getMarket().removeVendedor(vendedor);
            salvarDatos();
            guardarLog("se esta eliminando un vendedor", 3, "eliminar");
            return 1;
        }else{
            return 0;}}

    public boolean updateVendedor(Vendedor vendedor,String cedula) throws VendedorException {
        getMarket().updateVendedor(vendedor, cedula);
        salvarDatos();
        guardarLog("se esta actualizando el vendedor", 2,"actualizar");
        return true;}

    public ArrayList<Vendedor> obtenerVendedores() throws VendedorException {
        return getMarket().obtenerVendedores();}

    public Vendedor hallarVendedor(String cedula) throws VendedorException{
        return getMarket().hallarVendedor(cedula);
    }

//________________________________________________________________________________________

    /*
       - Metodos Admins
     */
    public int addAdmin(Administrador admin) throws AdminException {
        if (!getMarket().AdminExist(admin.getCedula())) {
            if (!getMarket().usuarioExiste(admin.getUsuario(),admin.getPassword())
                    && !getMarket().cedulaExiste(admin.getCedula())) {
                getMarket().addAdmin(admin);
                salvarDatos();
                guardarLog("se agrega el admin", 1, "registro");
                return 1;
            }else{
                return 0;
            }
        }else{
            return 0;}}

    public int deleteAdmin(Administrador admin) throws AdminException {
        if (getMarket().AdminExist(admin.getCedula())) {
            getMarket().removeAdmin(admin);
            salvarDatos();
            guardarLog("se esta eliminando un admin", 3, "eliminar");
            return 1;
        }else{
            return 0;}}

    public boolean updateAdmin(Administrador admin,String cedula) throws AdminException {
        getMarket().updateAdmin(admin, cedula);
        salvarDatos();
        guardarLog("se esta actualizando el admin", 2,"actualizar");
        return true;}

    public ArrayList<Administrador> obtenerAdmin(){
        return getMarket().obtenerAdmins();}

    public Administrador hallarAdmin(String cedula) throws AdminException{
        return getMarket().hallarAdmin(cedula);
    }

//________________________________________________________________________________________________

    /*
      -- Metodos Muros
    */
    public int addMuro(Muro muro,Vendedor vendedor) throws MuroException {
        if (!getMarket().MuroExist(muro.getId())) {
            getMarket().addMuro(muro,vendedor);
            salvarDatos();
            guardarLog("se agrego un Muro al Vendedor"+vendedor.getCedula(), 1, "registro");
            return 1;
        }else{
            return 0;}}

    public int deleteMuro(Muro muro,Vendedor vendedor) throws MuroException {
        if (getMarket().MuroExist(muro.getId())) {
            getMarket().removeMuro(muro,vendedor);
            salvarDatos();
            guardarLog("se elimino el Muro del Vendedor "+vendedor.getCedula(), 3, "eliminar");
            return 1;
        }else{
            return 0;}}

    public boolean updateMuro(Muro muro,Vendedor vendedor,String id) throws MuroException {
        getMarket().updateMuro(muro,vendedor,id);
        salvarDatos();
        guardarLog("se esta actualizando el admin", 2,"actualizar");
        return true;}


    public ArrayList<Muro> obtenerMuro(){
        return getMarket().obtenerMuros();}

    public Muro hallarMuro(String id){
        return getMarket().hallarMuro(id);
    }

     /*
      - Metodos Comentario Producto
    */

    public int addComentarioMuro(Muro muro,Comentario comentario) throws ComentarioException {
        if (getMarket().MuroExist(muro.getId())) {
            getMarket().addComentarioMuro(muro,comentario);
            salvarDatos();
            guardarLog("se agrego un comentario al Muro "+muro.getId(), 1, "registro");
            return 1;
        }else{
            return 0;}}

    public int deleteComentarioMuro(Muro muro,Comentario comentario) throws ComentarioException {
        if (getMarket().MuroExist(muro.getId())){
            getMarket().removerComentarioMuro(muro,comentario);
            salvarDatos();
            guardarLog("se eliminp un comentario al Muro "+muro.getId(), 3, "eliminar");
            return 1;
        }else{
            return 0;}}

    public ArrayList<Comentario> obtenerComentarioMuro(Muro muro) throws ComentarioException {
        return getMarket().obtenerComentariosMuro(muro);}


//________________________________________________________________________________________

    /*
      - Metodos Ventas
    */
    public int addVenta(Venta venta) throws VentaException {
        if (!getMarket().VentaExist(venta.getCodigo())) {
                getMarket().addVenta(venta);
                salvarDatos();
                guardarLog("se agrega la venta", 1, "registro");
                return 1;
        }else{
            return 0;}}

    public int deleteVenta(Venta venta) throws VentaException {
        if (getMarket().VentaExist(venta.getCodigo())) {
            getMarket().removeVenta(venta);
            salvarDatos();
            guardarLog("se esta eliminando una Venta", 3, "eliminar");
            return 1;
        }else{
            return 0;}}

    public ArrayList<Venta> obtenerVentas() {
        return getMarket().obtenerVentas();}

    public Venta hallarVenta(String codigo)  {
        return getMarket().hallarVenta(codigo);}

    /*
      - Metodos Productos Ventas
    */
    public boolean addProductoVenta(Venta venta,Producto producto) throws VentaException {
        if (getMarket().VentaExist(venta.getCodigo()) && getMarket().productExist(producto.getCodigo())) {
            getMarket().addProductoVenta(venta,producto);
            salvarDatos();
            return true;
        }else{
            return false;
        }
    }

    public boolean removeProductoVenta(Venta venta,Producto producto) throws VentaException {
        if (getMarket().VentaExist(venta.getCodigo()) && getMarket().productExist(producto.getCodigo())) {
            getMarket().deleteProductoVenta(venta,producto);
            salvarDatos();
            return true;
        }else{
            return false;
        }
    }



//_______________________________________________________________________________________________

    private void salvarDatos() {
        try{
            Persistencia.guardarProductos(getMarket().obtenerProductos());
            Persistencia.guardarVendedores(getMarket().obtenerVendedores());
            Persistencia.guardarAdministradores(getMarket().obtenerAdmins());
            Persistencia.guardarProductosPorEstado(getMarket().obtenerProductos());

        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public void cargarDatos(){

        market = new Market();
        try{
            getMarket().obtenerProductos().addAll(Persistencia.cargarProductos());
            getMarket().obtenerVendedores().addAll(Persistencia.cargarVendedores());
            getMarket().obtenerAdmins().addAll(Persistencia.cargarAdministradores());
            getMarket().asignarProductosAVendedores();

        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    private void guardarBinario() throws IOException {
        Persistencia.guardarBinario(market);
    }

    private void cargarBinario() throws IOException {
        market = Persistencia.cargarBinario();
    }

    public void guardarArchivoXml() throws IOException {
        Persistencia.guardarArchivoXml(market);
    }

    public void cargarArchivoXml() throws IOException {
        market= Persistencia.cargarArchivoXml();
    }







}
