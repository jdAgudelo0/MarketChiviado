package com.proyecto.market.Controller;

import com.proyecto.market.Exceptions.ComentarioException;
import com.proyecto.market.Exceptions.MuroException;
import com.proyecto.market.Exceptions.ProductoException;
import com.proyecto.market.Model.Comentario;
import com.proyecto.market.Model.Muro;
import com.proyecto.market.Model.Producto;
import com.proyecto.market.Model.Vendedor;

import java.util.ArrayList;

public class MuroController {

    ModelFactory modelFactory;

    public MuroController() {
        this.modelFactory = modelFactory.getInstance();
    }


    public int crearMuro(Muro muro, Vendedor vendedor) throws MuroException {
        if (modelFactory.addMuro(muro,vendedor) == 1) {
            return 1;
        }else {
            return 0;}}


    public int eliminarMuro(Muro muro, Vendedor vendedor) throws MuroException {
        if (modelFactory.deleteMuro(muro,vendedor) == 1) {
            return 1;
        }else {
            return 0;}}

    public int modificarMuro(Muro muro, Vendedor vendedor) throws MuroException {
        if (modelFactory.updateMuro(muro,vendedor,muro.getId())){
            return 1;
        }
        else {
            return 0;}}

    public ArrayList<Muro> mostrarMuros(){
        return modelFactory.obtenerMuro();
    }

    public Muro buscarMuro(String codigo) {
        return modelFactory.hallarMuro(codigo);}


    public int crearComentarioMuro(Muro muro, Comentario comentario) throws ComentarioException {
        if (modelFactory.addComentarioMuro(muro,comentario) == 1) {
            return 1;
        }else {
            return 0;}}

    public int eliminarComentarioMuro(Muro muro,Comentario comentario) throws ComentarioException {
        if (modelFactory.deleteComentarioMuro(muro,comentario) == 1) {
            return 1;
        }else {
            return 0;}}

    public ArrayList<Comentario> mostrarComentarios(Muro muro) throws ComentarioException {
        return modelFactory.obtenerComentarioMuro(muro);
    }

}
