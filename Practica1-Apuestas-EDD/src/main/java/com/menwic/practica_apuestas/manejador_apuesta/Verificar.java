package com.menwic.practica_apuestas.manejador_apuesta;

import com.menwic.practica_apuestas.lista_enlazada.Lista;
import com.menwic.practica_apuestas.lista_enlazada.Nodo;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Luis
 */
public class Verificar {

    private static int pasos = 0;

    //Funcion: O(1), 10 pasos o menos, arreglo de tamaño 10 
    public static boolean validar(int array[]) {
        pasos = 0;
        boolean[] repetido = new boolean[10]; //No funiona el repetido

        for (int pos : array) {
            pasos++;
            int index = pos - 1;
            if (repetido[index]) {
                return false;
            }
            repetido[index] = true;
        }
        return true;

    }

    public static int getPasos() {
        return pasos;
    }

    public static DefaultTableModel getModel(Lista<Apuesta> list) {//Libreria swing
        Nodo<Apuesta> aux = list.getHead();
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Num.");
        model.addColumn("Apostador");
        model.addColumn("Puntaje");
        Integer line = 1;
        while (aux != null) {
            Apuesta bet = aux.getData();
            Object[] data = {line, bet.getNickApostador(), bet.getPuntaje()};
            model.addRow(data);
            line++;
            aux = aux.getNext();
        }
        return model;
    }
}
