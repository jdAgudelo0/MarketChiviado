package com.proyecto.market.Model;

import com.proyecto.market.Exceptions.*;
import com.proyecto.market.Model.Enum.Estado;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Stream;

public class Market implements Serializable {

    private static final long serialVersionUID = 1L;

    private ArrayList<Vendedor> vendedores = new ArrayList<>();
    private ArrayList<Producto> productos = new ArrayList<>();
    private ArrayList<Administrador> administradores = new ArrayList<>();
    private ArrayList<Muro> muros = new ArrayList<>();
    private ArrayList<Venta> ventas = new ArrayList<>();

    public Market() {}

    // Getters And Setters
    public ArrayList<Vendedor> getVendedores() { return vendedores; }

    public void setVendedores(ArrayList<Vendedor> vendedores) { this.vendedores = vendedores; }

    public ArrayList<Producto> getProductos() { return productos; }

    public void setProductos(ArrayList<Producto> productos) { this.productos = productos; }

    public ArrayList<Muro> getMuros() { return muros; }

    public void setMuros(ArrayList<Muro> muros) { this.muros = muros; }

    public ArrayList<Administrador> getAdministradores() { return administradores; }

    public void setAdministradores(ArrayList<Administrador> administradores) { this.administradores = administradores; }

    public ArrayList<Venta> getVentas() { return ventas; }

    public void setVentas(ArrayList<Venta> ventas) { this.ventas = ventas; }


//_______________________________________________________________________________________

    /*
       - Metodos Generales
    */

    public boolean cedulaExisteVendedor(String cedula) {
        return vendedores.stream().anyMatch(v -> v.getCedula().equals(cedula));}

    public boolean cedulaExisteAdmin(String usuario) {
        return administradores.stream().anyMatch(a -> a.getCedula().equals(usuario));}

    public boolean cedulaExiste(String cedula) {
        return cedulaExisteVendedor(cedula) || cedulaExisteAdmin(cedula);}


    public boolean usuarioExiste(String usuario,String contrasena) {
        return vendedores.stream().anyMatch(v -> v.getUsuario().equals(usuario)
        && v.getContrasenia().equals(contrasena))  ||
                administradores.stream().anyMatch(a -> a.getPassword().equals(contrasena)
                && a.getUsuario().equals(usuario));
    }

    public boolean usuarioExisteVendedor(String usuario, String contrasena) {
        if (vendedores.stream().anyMatch(v -> v.getUsuario().equals(usuario)) ||
                vendedores.stream().anyMatch(v -> v.getCedula().equals(usuario))){
                    return vendedores.stream().anyMatch(v -> v.getContrasenia().equals(contrasena));
        }else{
            return false;
        }
    }

    public boolean usuarioExisteAdmin(String usuario, String contrasena) {
        if (administradores.stream().anyMatch(a -> a.getUsuario().equals(usuario)) ||
        administradores.stream().anyMatch(v -> v.getCedula().equals(usuario))){
            return administradores.stream().anyMatch(admin -> admin.getPassword().equals(contrasena));
        }else{
            return false;
        }
    }


    public int loggin(String usuario, String contrasena) {
        if (usuarioExisteVendedor(usuario, contrasena)) {
            return 1;
        }else if (usuarioExisteAdmin(usuario,contrasena)) {
            return 2;
        }else{
            return 0;
        }
    }

    public void asignarProductosAVendedores() {
        for (Vendedor vendedor : vendedores) {
            for (Producto producto : productos) {
                    if (producto.getVendedor().getCedula().equals(vendedor.getCedula())) {
                        vendedor.getProductos().add(producto);
                    }
                }
        }
    }

// ____________________________________________________________________________________ //

    /*
      -Metodos Producto
    */
    public void addProduct(Producto producto) throws ProductoException {
        if (producto == null) {
            throw new ProductoException("El producto no puede ser nulo");
        }
        productos.add(producto);
        Vendedor vendedor = hallarVendedor(producto.getVendedor().getCedula());
        vendedor.getProductos().add(producto);}

    public void removeProduct(Producto producto) throws ProductoException {
        if(producto==null){
            throw new ProductoException("El producto no puede ser nulo");
        }else if(producto.getEstado() != Estado.VENDIDO){
            Vendedor vendedor = hallarVendedor(producto.getVendedor().getCedula());
            ArrayList<Producto> productos1 = vendedor.getProductos();
            for (Producto producto2 : productos1) {
                if (producto2.getCodigo().equals(producto.getCodigo())) {
                    productos1.remove(producto2);}
            }
            vendedor.setProductos(productos1);
            productos.remove(producto);}}


    public void updateProduct(Producto productoActualizado, String codigo ) throws ProductoException {
        if(productoActualizado == null){
            throw new ProductoException("El producto no puede ser nulo");
        }for(Producto producto: productos){
            if(producto.getCodigo().equals(codigo)){
                Vendedor vendedor = hallarVendedor(producto.getVendedor().getCedula());
                 ArrayList<Producto> productos1 = vendedor.getProductos();
                 for (Producto producto2 : productos1) {
                     if (producto2.getCodigo().equals(producto.getCodigo())) {
                         productos1.set(productos1.indexOf(producto), productoActualizado);
                     }
                 }
                 vendedor.setProductos(productos1);
                 productos.set(productos.indexOf(producto), productoActualizado);
                break;
            }}}


    public boolean productExist(String codigo){
        return productos.stream().anyMatch(producto -> producto.getCodigo().equals(codigo));
    }

    public Producto buscarProducto(String codigo){
        return productos.stream().filter(producto -> producto.getCodigo().equals(codigo)).findFirst().get();
    }

    public ArrayList<Producto> obtenerProductos() {
        return productos;}

    /*
       - Metodos Comentario Producto
    */

    public void addComentarioProducto(Producto producto,Comentario comentario) throws ComentarioException {
        if (producto == null && comentario == null) {
            throw new ComentarioException("el producto/comentario no puede ser nulo");
        }else{
            producto.getComentarios().add(comentario);
        }
    }

    public void removerComentarioProducto(Producto producto,Comentario comentario) throws ComentarioException {
        if (producto == null && comentario == null) {
            throw new ComentarioException("el producto/comentario no puede ser nulo");
        }else{
            producto.getComentarios().remove(comentario);
        }
    }

    public ArrayList<Comentario> obtenerComentariosProducto(Producto producto) throws ComentarioException {
        if (producto == null) {
            throw new ComentarioException("el producto no puede ser nulo");
        } else {
            ArrayList<Comentario> comentarios = producto.getComentarios();
            return comentarios;
        }
    }

    public void cambiarEstadoProducto(Producto producto) throws ProductoException {
        if(producto == null){
            throw new ProductoException("la producto no puede ser nulo");
        }else{
            producto.setEstado(Estado.CANCELADO);
            productos.set(productos.indexOf(producto), producto);}}

// _______________________________________________________________________________________________

    /*
        - Metodos Administrador
     */
    public void addAdmin(Administrador admin) throws AdminException {
        if(admin == null){
            throw new AdminException("El admin no puede ser nulo");
        }else {
            administradores.add(admin);}}

    public void removeAdmin(Administrador admin) throws AdminException {
        if(admin == null){
            throw  new AdminException("El admin no puede ser nulo");
        }else {
            administradores.remove(admin);}}

    public void updateAdmin(Administrador adminActualizado, String cedula) throws AdminException {
        if(adminActualizado == null){
            throw new AdminException("El admin no puede ser nulo");
        }else{
            for(Administrador admin : administradores){
                if(admin.getCedula().equals(cedula)){
                    administradores.set(administradores.indexOf(admin), adminActualizado);
                    break;
                }}}}


    public ArrayList<Administrador> obtenerAdmins() {
        return administradores;}

    public boolean AdminExist(String cedula){
        return administradores.stream().anyMatch(v -> v.getCedula().equals(cedula));}

    public Administrador hallarAdmin(String cedula){
        return administradores.stream().filter(v -> v.getCedula().equals(cedula)).findFirst().get();}

    public Administrador hallarAdminUsuario(String usuario, String password){
        return administradores.stream().filter(v -> v.getUsuario().equals(usuario) &&
                v.getPassword().equals(password) ||
                v.getCedula().equals(usuario)).findFirst().get();}

//____________________________________________________________________________________________

    /*
      - Metodos Vendedor
    */
    public void addVendedor(Vendedor vendedor) throws VendedorException {
        if(vendedor == null){
            throw new VendedorException("El vendedor no puede ser nulo");
        }else {
            vendedores.add(vendedor);}}

    public void removeVendedor(Vendedor vendedor) throws VendedorException {
        if(vendedor == null){
            throw  new VendedorException("El personaje no puede ser nulo");
        }else {
            vendedores.remove(vendedor);}}

    public void updateVendedor(Vendedor vendedorActualizado, String cedula) throws VendedorException {
        if(vendedorActualizado == null){
            throw new VendedorException("El personaje no puede ser nulo");
        }else{
            for(Vendedor vendedor : vendedores){
                if(vendedor.getCedula().equals(cedula)){
                    vendedores.set(vendedores.indexOf(vendedor), vendedorActualizado);
                    break;}}}}

    public ArrayList<Vendedor> obtenerVendedores() {
        return vendedores; }

    public ArrayList<Producto> obtenerProductoVendedor(String id){
        return hallarVendedor(id).getProductos();
    }

    public boolean vendedorExist(String cedula){
        return vendedores.stream().anyMatch(v -> v.getCedula().equals(cedula)); }

    public Vendedor hallarVendedor(String cedula){
        return vendedores.stream().filter(v -> v.getCedula().equals(cedula)).findFirst().get(); }

    public Vendedor hallarVendedorUsuario(String usuario, String password){
        return vendedores.stream().filter(v -> v.getUsuario().equals(usuario) &&
                v.getContrasenia().equals(password) ||
                v.getCedula().equals(usuario)).findFirst().get();}

//____________________________________________________________________________________________

    /*
      - Metodos Muros
    */
    public void addMuro(Muro muro,Vendedor vendedor) throws MuroException {
        if(muro == null && vendedor == null){
            throw new MuroException("El muro no puede ser nulo");
        }else if (hallarVendedor(vendedor.getCedula()) != null){
            if (vendedor.getMuro() == null){
                muros.add(muro);
                vendedor.setMuro(muro);
            }else{
                throw new MuroException("El vendedor ya tiene muro");
            }
        }else{
            throw new MuroException("El vendedor no Existe");}}


    public void removeMuro(Muro muro,Vendedor vendedor) throws MuroException {
        if(muro == null && vendedor == null){
            throw  new MuroException("El personaje no puede ser nulo");
        }else if (vendedor.getMuro().equals(muro)){
            vendedor.setMuro(null);
            muros.remove(muro);}}


    public void updateMuro(Muro muroActualizado,Vendedor vendedor, String id) throws MuroException {
        if(muroActualizado == null && vendedor == null){
            throw new MuroException("El muro no puede ser nulo");
        }else{
            for(Muro muro : muros){
                if(muro.getId().equals(id)){
                    if (vendedor.getMuro().equals(muro)){
                    muros.set(muros.indexOf(muro), muroActualizado);
                    vendedor.setMuro(muro);
                    break;
                    }else{
                        throw new MuroException("El muro no coincide con el vendedor");}}}}}


    public ArrayList<Muro> obtenerMuros() {
        return muros; }


    public boolean MuroExist(String id){
        return muros.stream().anyMatch(m -> m.getId().equals(id)); }

    public Muro hallarMuro(String id){
        return muros.stream().filter(m -> m.getId().equals(id)).findFirst().get(); }

    /*
       - Metodos Comentarios Muro
    */
    public void addComentarioMuro(Muro muro,Comentario comentario) throws ComentarioException {
        if (muro == null && comentario == null) {
            throw new ComentarioException("el muro/comentario no puede ser nulo");
        }else{
            muro.getComentarios().add(comentario);}}


    public void removerComentarioMuro(Muro muro,Comentario comentario) throws ComentarioException {
        if (muro == null && comentario == null) {
            throw new ComentarioException("el muro/comentario no puede ser nulo");
        }else{
            muro.getComentarios().remove(comentario);}}


    public ArrayList<Comentario> obtenerComentariosMuro(Muro muro) throws ComentarioException {
        if (muro == null) {
            throw new ComentarioException("el muro no puede ser nulo");
        } else {
            ArrayList<Comentario> comentarios = muro.getComentarios();
            return comentarios;}}

//___________________________________________________________________________________________

    /*
      - Metodo Venta
    */
    public void addVenta(Venta venta) throws VentaException {
        if(venta == null){
            throw new VentaException("La venta no puede ser nulo");
        }else {
            ventas.add(venta);}}

    public void removeVenta(Venta venta) throws VentaException {
        if(venta == null){
            throw  new VentaException("La venta no puede ser nulo");
        }else if (!venta.getFechaVenta().isAfter(venta.getFechaVenta().plusDays(30).minusDays(1))){
            Vendedor vendedor = venta.getVendedor();
            ArrayList<Producto> productos = vendedor.getProductos();
            for (Producto producto : productos){
                vendedor.getProductos().add(producto);
            }
            ventas.remove(venta);
        }else{
            throw new VentaException("El venta no puede ser eliminado despues de 30");}}

    public ArrayList<Venta> obtenerVentas() {
        return ventas; }

    public boolean VentaExist(String id){
        return ventas.stream().anyMatch(m -> m.getCodigo().equals(id)); }

    public Venta hallarVenta(String id){
        return ventas.stream().filter(m -> m.getCodigo().equals(id)).findFirst().get(); }


    /*
      - Metodos Vendedor Ventas
    */
    public ArrayList<Venta> obtenerVentasVendedor(Vendedor vendedor){
        ArrayList<Venta> ventas1 = new ArrayList<>();
        for (Venta venta : ventas) {
            if (venta.getVendedor().getCedula().equals(vendedor.getCedula())) {
                ventas1.add(venta);
            }
        }
        return ventas1;
    }

    /*
      - Metodos Productos Ventas
    */
    public void addProductoVenta(Venta venta,Producto producto) throws VentaException {
        if (venta == null && producto == null) {
            throw new VentaException("la venta/producto no puede ser nulo");
        } else {
            venta.setTotal(+producto.getPrecio());
            Producto p = buscarProducto(producto.getCodigo());
            p.setEstado(Estado.VENDIDO);
            productos.set(productos.indexOf(producto), p);
            venta.getProductosVenta().add(producto);
            ventas.set(ventas.indexOf(venta), venta);}}


    public void deleteProductoVenta(Venta venta,Producto producto) throws VentaException {
        if (venta == null && producto == null) {
            throw new VentaException("la venta/producto no puede ser nulo");
        }else{
            venta.setTotal(-producto.getPrecio());
            Producto p = buscarProducto(producto.getCodigo());
            p.setEstado(Estado.PUBLICADO);
            productos.set(productos.indexOf(producto), p);
            venta.getProductosVenta().remove(producto);
            ventas.set(ventas.indexOf(venta), venta);}}


//_____________________________________________________________________________________________



    public void SugerirContacto(Vendedor vendedores){
        
    }

}
