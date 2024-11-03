package com.proyecto.market.Controller;

import com.proyecto.market.Exceptions.AdminException;
import com.proyecto.market.Model.Administrador;


import java.util.ArrayList;

public class AdminController {

    ModelFactory modelFactory;

    public AdminController() {
        this.modelFactory = modelFactory.getInstance();
    }

    public int crearAdmin(Administrador admin) throws AdminException {
        if (modelFactory.addAdmin(admin) == 1) {
            return 1;
        }else {
            return 0;}}


    public int eliminarAdmin(Administrador admin) throws AdminException {
        if (modelFactory.deleteAdmin(admin) == 1) {
            return 1;
        }else {
            return 0;}}


    public int modificarAdmin(Administrador admin, String codigo) throws AdminException {
        if (modelFactory.updateAdmin(admin, codigo)) {
            return 1;
        }
        else {
            return 0;}}


    public ArrayList<Administrador> mostrarAdmin() {
        return modelFactory.obtenerAdmin();}


    public Administrador buscarAdmin(String cedula) throws AdminException {
        return modelFactory.hallarAdmin(cedula);
    }

}
