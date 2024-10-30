package com.proyecto.market.Utils;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class ArchivoUtil {

    static String fechaSistema = "";

    public static void guardarRegistroLOG(String mensajeLOG, int nivel, String accion, String rutaArchivo){

        String Log = "";
        Logger logger = Logger.getLogger(accion);
        FileHandler fh = null;
        cargarFechaSistema();

        try{
            fh = new FileHandler(rutaArchivo, true);
            fh.setFormatter(new SimpleFormatter());
            logger.addHandler(fh);

            switch(nivel){
                case 1:
                    logger.log(Level.INFO, accion+ " "+ mensajeLOG + " "+ fechaSistema);
                    break;

                case 2:
                    logger.log(Level.WARNING, accion+ " "+ mensajeLOG + " "+ fechaSistema);
                    break;

                case 3:
                    logger.log(Level.CONFIG, accion+ " "+ mensajeLOG + " "+ fechaSistema);
                    break;

                case 4:
                    logger.log(Level.SEVERE, accion+ " "+ mensajeLOG + " "+ fechaSistema);
            }
        }catch (SecurityException e){
            logger.log(Level.SEVERE, e.getMessage());
        }catch (IOException e){
            logger.log(Level.SEVERE, e.getMessage());
        }finally {
            fh.close();
        }

    }

    public static void cargarFechaSistema(){
        String dia_n = "";
        String mes_n = "";

        Calendar cal = Calendar.getInstance();

        int dia = cal.get(Calendar.DATE);
        int mes = cal.get(Calendar.MONTH);
        int anio = cal.get(Calendar.YEAR);
        int hora = cal.get(Calendar.HOUR);

        if(dia < 10 ){
            dia_n = "0"+dia;
        }else{
            dia_n = ""+dia;
        }
        if(mes < 10 ){
            mes_n = "0"+mes;
        }else{
            mes_n = ""+mes;
        }

        fechaSistema = dia_n+"/"+mes_n+"/"+anio+"/"+hora;
    }

    public static void guardarArchivo(String rutaArchivo, String contendio, boolean bandera) throws IOException {
        FileWriter fw = new FileWriter(rutaArchivo, bandera);
        BufferedWriter br = new BufferedWriter(fw);
        br.write(contendio);
        br.close();
        fw.close();

    }

    public static ArrayList<String> leerArchivos(String rutaArchivo) throws IOException {

        ArrayList<String> leerArchivos = new ArrayList<>();

        FileReader fr = new FileReader(rutaArchivo);
        BufferedReader br = new BufferedReader(fr);
        String linea = "";

        while ((linea = br.readLine()) != null){
            leerArchivos.add(linea);
        }
        br.close();
        fr.close();

        return leerArchivos;
    }

    public static void salvarSerializable(String rutaArchivo, Object objeto) throws IOException {
        ObjectOutputStream oos = null;

        try{
            oos = new ObjectOutputStream(new FileOutputStream(rutaArchivo));
            oos.writeObject(objeto);
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            if(oos != null){
                oos.close();
            }
        }
    }

    @SuppressWarnings("unchecked")
    public static Object leerSerializable(String rutaArchivo) throws Exception {
        ObjectInputStream ois = null;
        Object objeto = null;

        try{
            ois = new ObjectInputStream(new FileInputStream(rutaArchivo));
            objeto = ois.readObject();
        }catch(Exception e){
            throw e;
        }finally {
            if(ois != null){
                ois.close();
            }
            return objeto;
        }
    }

    public static Object cargarArchivoXml(String rutaArchivo) throws IOException {

        XMLDecoder decodificadorXml;
        Object objetoXml;

        decodificadorXml = new XMLDecoder(new FileInputStream(rutaArchivo));
        objetoXml = decodificadorXml.readObject();
        decodificadorXml.close();
        return objetoXml;
    }

    public static void salvarArchivoXml(String rutaArchivo, Object objeto) throws IOException {
        XMLEncoder codificadorXml;

        codificadorXml = new XMLEncoder(new FileOutputStream(rutaArchivo));
        codificadorXml.writeObject(objeto);
        codificadorXml.close();

    }
}