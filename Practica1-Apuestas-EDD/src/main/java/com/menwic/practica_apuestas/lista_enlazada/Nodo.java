package com.menwic.practica_apuestas.lista_enlazada;

/**
 *
 * @author Luis
 */
public class Nodo<T> {

    private T data;
    private Nodo next;

    public Nodo(T data) {
        this.data = data;
        this.next = null;
    }

    public T getData() {
        return data;
    }

    public Nodo getNext() {
        return next;
    }

    public void setNext(Nodo next) {
        this.next = next;
    }

    //metodo para ajalr datos del csv
    public String csv() {
        return data.toString();
    }

}
