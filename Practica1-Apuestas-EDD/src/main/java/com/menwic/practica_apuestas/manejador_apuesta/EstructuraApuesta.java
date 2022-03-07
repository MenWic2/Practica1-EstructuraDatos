package com.menwic.practica_apuestas.manejador_apuesta;

import com.menwic.practica_apuestas.lista_enlazada.Lista;
import com.menwic.practica_apuestas.lista_enlazada.Nodo;

/**
 *
 * @author Luis
 */
public class EstructuraApuesta {

    private static EstructuraApuesta apuestas;
    private Lista<Apuesta> sinVerificar;
    private Lista<Apuesta> aceptadas;
    private Lista<Apuesta> rechazadas;
    private boolean validated; //Para condicional de validados
    private boolean finished; //{Para luego imprimir en consola rechazados
    private boolean calculated;
    private int posicionesFinales[];
    private static int pasos = 0;

    private EstructuraApuesta() {
        sinVerificar = new Lista();
        aceptadas = new Lista();
        rechazadas = new Lista();
        validated = false;
        finished = false;
        calculated = false;
    }

    public static EstructuraApuesta getEstructuraApuesta() {
        if (apuestas == null) {
            apuestas = new EstructuraApuesta();
        }
        return apuestas;
    }

    public int[] getPosicionesFinales() {
        return posicionesFinales;
    }

    public void setPosicionesFinales(int[] finalPositions) {
        this.posicionesFinales = finalPositions;
    }

    public void addSinVerificar(Apuesta bet) {
        sinVerificar.push(bet);
    }

    public void addAceptadas(Apuesta bet) {
        aceptadas.push(bet);
    }

    public void addSinVerificar(String gambler, int amount, int positions[]) {
        Apuesta bet = new Apuesta(gambler, amount, positions);
        sinVerificar.push(bet);
    }

    public void addRechazadas(Apuesta bet) {
        rechazadas.push(bet);
    }

    public void validar() {
        if (!validated) {
            int contPasos = 0;
            int maxPasos = contPasos;
            int minPasos = contPasos + 10;
            Long start = System.nanoTime();
            Nodo<Apuesta> aux = sinVerificar.pop();
            while (aux != null) {
                if (Verificar.validar(aux.getData().getPositions())) { //Validar = PanelResultados
                    aceptadas.push(aux);
                } else {
                    rechazadas.push(aux);
                }
                aux = sinVerificar.pop();
                int pasosActuales = Verificar.getPasos(); //Pedir pasos de Clase Verificar
                contPasos += pasosActuales;
                if (pasosActuales > maxPasos) {
                    maxPasos = pasosActuales;
                }
                if (pasosActuales < minPasos) {
                    minPasos = pasosActuales;
                }
            }
            Long end = System.nanoTime();
            double promedioTiempo = end.doubleValue() - start.doubleValue();
            promedioTiempo = promedioTiempo / sinVerificar.getLen();
            float promedioPasos = contPasos / sinVerificar.getLen();

            System.out.println("------------------------------");
            System.out.println("Pasos promedio: " + promedioPasos + " pasos");
            System.out.println("Cant. Max. pasos: " + maxPasos + " pasos");
            System.out.println("Cant. min. pasos: " + minPasos + " pasos");
            System.out.println("Time promed.  pasos: " + promedioTiempo + "ms");
        }
        validated = true;
    }

    //Metodo para Resetear el programa
    public static void limpiar() {
        apuestas = null;
        System.gc();
    }

    public boolean validado() {
        return validated;
    }

    //Listado de Apuestas, obtener apuestas sin verificar
    public Lista<Apuesta> getSinVerificar() {
        return sinVerificar;
    }

    //Listado de Apuestas, obtener apuestas aceptadas
    public Lista<Apuesta> getAceptadas() {
        return aceptadas;
    }

    //Listado de Apuestas, obtener apuestas rechazadas
    public Lista<Apuesta> getRechazadas() {
        return rechazadas;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    //Ordenar por puntos (Sin funcionar)
    public void sortByPoints() {
        Long start = System.nanoTime();
        pasos = 0;
        Nodo<Apuesta> newHead = mergeSort(aceptadas.getHead(), true);
        aceptadas.setHead(newHead);
        //
        Long end = System.nanoTime();
        double promedioTiempo = end.doubleValue() - start.doubleValue();
        promedioTiempo = promedioTiempo / aceptadas.getLen();
        double promedioPasos = pasos / aceptadas.getLen();
        System.out.println("----------------------------");
        System.out.println("Pasos promedio: " + promedioPasos + " pasos");
        System.out.println("Time promedio: " + promedioTiempo + "ms");

    }

    //Ordeamiento por nombres (Default)
    public void sortByNames() {
        Long start = System.nanoTime();
        //
        pasos = 0;
        Nodo<Apuesta> newHead = mergeSort(aceptadas.getHead(), false);
        aceptadas.setHead(newHead);
        //
        Long end = System.nanoTime();
        double promedioTiempo = end.doubleValue() - start.doubleValue();
        promedioTiempo = promedioTiempo / aceptadas.getLen();
        double promedioPasos = pasos / aceptadas.getLen();
        System.out.println("---------------Ordenamiento----------------");
        System.out.println("Time promedio: " + promedioTiempo + "ms");
        System.out.println("Pasos promedio: " + promedioPasos + "pasos");
    }

    //Unir ordenamiento
    private Nodo<Apuesta> mergeSort(Nodo<Apuesta> head, boolean points) {
        pasos++;
        if (head == null || head.getNext() == null) {
            return head;
        }
        Nodo middle = getMiddle(head);
        Nodo nextoMiddle = middle.getNext();
        middle.setNext(null);
        Nodo izq = mergeSort(head, points);
        Nodo der = mergeSort(nextoMiddle, points);
        if (points) {
            return SortedMergeForPoints(izq, der);
        }
        return SortedMergeForNames(izq, der);

    }

    //Metodo Ordenar por puntaje (Sin Funcionar)
    private Nodo<Apuesta> SortedMergeForPoints(Nodo<Apuesta> a, Nodo<Apuesta> b) {
        pasos++;
        Nodo<Apuesta> result = null;
        if (a == null) {
            return b;
        }
        if (b == null) {
            return a;
        }
        if (a.getData().getPuntaje() >= b.getData().getPuntaje()) {
            result = a;
            result.setNext(SortedMergeForPoints(a.getNext(), b));
        } else {
            result = b;
            result.setNext(SortedMergeForPoints(a, b.getNext()));
        }
        return result;
    }

    //metodo ordenar por nombres (default)
    private Nodo<Apuesta> SortedMergeForNames(Nodo<Apuesta> a, Nodo<Apuesta> b) {
        pasos++;
        Nodo<Apuesta> result = null;
        if (a == null) {
            return b;
        }
        if (b == null) {
            return a;
        }
        if (a.getData().getNickApostador().compareToIgnoreCase(b.getData().getNickApostador()) <= 0) {
            result = a;
            result.setNext(SortedMergeForNames(a.getNext(), b));
        } else {
            result = b;
            result.setNext(SortedMergeForNames(a, b.getNext()));
        }
        return result;
    }

    private Nodo getMiddle(Nodo head) {
        pasos++;
        if (head == null) {
            return head;
        }

        Nodo slow = head;
        Nodo fast = head;
        while (fast.getNext() != null && fast.getNext().getNext() != null) {
            slow = slow.getNext();
            fast = fast.getNext().getNext();
            pasos++;
        }
        return slow;
    }

    //Calculo de resultados finales
    public void calcularResultados() {
        if (!calculated) {
            Long start = System.nanoTime();
            Double promedio = 0.0;
            Nodo<Apuesta> aux = aceptadas.getHead();
            while (aux != null) {
                getPuntaje(aux.getData());
                aux = aux.getNext();
            }
            promedio = promedio / aceptadas.getLen();
                                
            Long end = System.nanoTime();
            double promedioTiempo = 0.0;
            promedioTiempo = (end.doubleValue() - start.doubleValue());
            promedioTiempo = promedioTiempo / aceptadas.getLen();
            System.out.println("------------Resultados------------");
            System.out.println("Time promedio: " + promedioTiempo + "ms");
            System.out.println("Max: 10 pasos");
            System.out.println("Min: 10 pasos");
            System.out.println("Promedio de pasos: 10 pasos");

        }
        calculated = true;
    }

    //Funcion: O(1), siempre son 10 pasos
    private void getPuntaje(Apuesta bet) {
        int gamblerPositions[] = bet.getPositions();
        int mayPts = 10;
        int pts = 0;
        for (int x = 0; x < 10; x++) {
            pts += gamblerPositions[x] == posicionesFinales[x] ? mayPts - x : 0;
        }
        bet.addPuntaje(pts);
    }

}
