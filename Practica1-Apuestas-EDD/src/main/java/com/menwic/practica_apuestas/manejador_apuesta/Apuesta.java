package com.menwic.practica_apuestas.manejador_apuesta;

/**
 *
 * @author Luis
 */
public class Apuesta {

    private String nickApostador;
    private int monto;
    private int[] positions;
    private int puntaje;

    public Apuesta(String gamblerName, int amount, int[] positions) {
        this.nickApostador = gamblerName;
        this.monto = amount;
        this.positions = positions;
        puntaje = 0;
    }

    public String getNickApostador() {
        return nickApostador;
    }

    public int getMonto() {
        return monto;
    }

    public int[] getPositions() {
        return positions;
    }

    public void addPuntaje(int points) {
        this.puntaje += points;
    }

    public int getPuntaje() {
        return puntaje;
    }

    @Override
    public String toString() {
        StringBuilder csv = new StringBuilder();
        csv.append(nickApostador);
        csv.append(",");
        csv.append(monto);
        csv.append(",");
        csv.append(positions[0]);
        csv.append(",");
        csv.append(positions[1]);
        csv.append(",");
        csv.append(positions[2]);
        csv.append(",");
        csv.append(positions[3]);
        csv.append(",");
        csv.append(positions[4]);
        csv.append(",");
        csv.append(positions[5]);
        csv.append(",");
        csv.append(positions[6]);
        csv.append(",");
        csv.append(positions[7]);
        csv.append(",");
        csv.append(positions[8]);
        csv.append(",");
        csv.append(positions[9]);
        csv.append("\n");
        return csv.toString();
    }

}
