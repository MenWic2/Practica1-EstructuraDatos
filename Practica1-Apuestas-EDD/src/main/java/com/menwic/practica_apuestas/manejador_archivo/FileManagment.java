package com.menwic.practica_apuestas.manejador_archivo;

import com.menwic.practica_apuestas.manejador_apuesta.EstructuraApuesta;
import com.menwic.practica_apuestas.lista_enlazada.Lista;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter; //
import java.io.PrintWriter;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Luis
 */
public class FileManagment {

    //String que contiene pattern para clase Java (video YT)
    private static String pattern = "\\w+,[1-9]\\d*(,(10|[1-9])){10}"; //Expresion
    private static File archivoAGuardar;

    //Selector de archivo
    public static boolean abrir() {
        boolean conErrores = true;
        JFileChooser cargar = new JFileChooser();
        String extensions[] = {"txt", "csv"};
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Apuestas", extensions); //No se si funciona (Video YT)
        cargar.setFileFilter(filtro);
        cargar.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        cargar.showOpenDialog(null);
        FileReader fReader = null;
        try {
            File file = cargar.getSelectedFile();
            fReader = new FileReader(file); //Lector de archivo
            BufferedReader bReader = new BufferedReader(fReader); //Lector de lineas
            String line;
            while ((line = bReader.readLine()) != null) {
                if (line.matches(pattern)) { //Si concuerda, agrega apuesta
                    addBet(line);
                } else {
                    System.out.println(line); //Si no, no agrega e imprime en consola
                }
            }
        } catch (Exception ex) {
            //ex.printStackTrace(System.out);
            conErrores = false;
        } finally {
            try {
                if (fReader != null) {
                    fReader.close();
                }
            } catch (Exception ex) {
                //ex.printStackTrace(System.out);
                conErrores = false;
            }
        }
        return conErrores;
    }

    public static boolean seleccionarDir() {
        JFileChooser guardar = new JFileChooser();
        guardar.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        guardar.showSaveDialog(null);
        try {
            archivoAGuardar = guardar.getSelectedFile();
        } catch (Exception ex) {
            archivoAGuardar = null;
        }
        return archivoAGuardar != null;
    }

    //Guardar como Nuevo o Existente
    public static boolean guardar(Lista list) {
        boolean guardado = false;
        try {
            //File file = guardar.getSelectedFile(); //No queda bien
            if (!archivoAGuardar.exists()) {
                String listaDeCsv = list.getCsv();
                PrintWriter pWritee = new PrintWriter(archivoAGuardar.getPath() + ".csv");
                
                pWritee.print(listaDeCsv);
                pWritee.close();
                guardado = true;
            } else {
                //JOptionPane.showConfirmDialog(null, "El archivo ya existe");
                guardado = false;
            }
        } catch (Exception ex) {
            //JOptionPane.showMessageDialog(null, "Error al guardar, ponga nombre al archivo");
            guardado = false;
        }
        return guardado;
    }

    private static void addBet(String line) {
        String values[] = line.split(",");
        int amount = Integer.parseInt(values[1]);
        int c1 = Integer.parseInt(values[2]);
        int c2 = Integer.parseInt(values[3]);
        int c3 = Integer.parseInt(values[4]);
        int c4 = Integer.parseInt(values[5]);
        int c5 = Integer.parseInt(values[6]);
        int c6 = Integer.parseInt(values[7]);
        int c7 = Integer.parseInt(values[8]);
        int c8 = Integer.parseInt(values[9]);
        int c9 = Integer.parseInt(values[10]);
        int c10 = Integer.parseInt(values[11]);
        int positions[] = {c1, c2, c3, c4, c5, c6, c7, c8, c9, c10};
        EstructuraApuesta.getEstructuraApuesta().addSinVerificar(values[0], amount, positions);
    }

    public static boolean isToSave() {
        return archivoAGuardar != null;
    }

}
