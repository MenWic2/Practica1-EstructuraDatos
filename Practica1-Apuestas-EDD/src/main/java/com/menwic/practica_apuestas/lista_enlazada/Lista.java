
package com.menwic.practica_apuestas.lista_enlazada;

/**
 *
 * @author Luis
 */
public class Lista<T> {
    private Nodo<T> head;
    private int len;
    
    public Lista(){
        len = 0;
    }
    
    //Push de Lista
    public void push(T data){
        Nodo<T> node = new Nodo(data);
        if(head == null){
            head = node;
        }else{                
            node.setNext(head);
            head = node;            
        }
        len++;
    }
    
    //Generic Class
    public void push(Nodo<T> node){        
        if(head == null){
            head = node;
        }else{                
            node.setNext(head);
            head = node;            
        }
        len++;
    }
    
    //Pop de Lista
    public Nodo pop(){
        if(head != null){
            Nodo aux = head;
            head = aux.getNext();
            aux.setNext(null);
            return aux;
        }
        return null;
    }

    public Nodo getHead() {
        return head;
    }

    public void setHead(Nodo<T> head) {
        this.head = head;
    }
        

    public int getLen() {
        return len;
    }               
    
    //Metodod para jalar csv
    public String getCsv(){
        StringBuilder csv = new StringBuilder();
        Nodo aux = head;
        while(aux != null){
            csv.append(aux.csv());
            aux = aux.getNext();
        }
        return csv.toString();
    }
    
    //Escribir o imprimir datos
    public void escribir(){
        Nodo aux = head;
        while(aux != null){ //Mientras auxiliar no sea fin de cadena
            System.out.println(aux.getData());
            aux = aux.getNext();
        }
    }
          
}
