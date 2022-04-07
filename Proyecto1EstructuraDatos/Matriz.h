#ifndef MATRIZ_H
#define MATRIZ_H

#include <iostream>
#include <cstdlib>
#include "NodosMatriz.h"

using namespace std;

//Objeto: laterales, es una columna de nodoLateral (primera que se rcorre al imprimir tablero)
struct laterales{
	nodoLateral* primero;
	nodoLateral* ultimo;
	
	//Constructor
	laterales(){
		primero=NULL;
		ultimo=NULL;
	}
	
	//insertar
	void insertar(nodoLateral* insertar) {
		if(vacio()){
			primero=ultimo=insertar;
		}else{
			insertarFinal(insertar); 	
		}
	}
	
	//metodo para verificar si la lista tiene al menos un nodo
	bool vacio(){
		if(primero==NULL){
			return true;
		} 
		else{
			return false;
		}
	}
	
	//Metodo: insertarInicio
	void insertarInicio(nodoLateral* insertar){
		primero->anterior=insertar;
		insertar->siguiente=primero;
		primero=primero->anterior;
	}
	
	//Metodo: insertarFinal
	void insertarFinal(nodoLateral* insertar){
		ultimo->siguiente=insertar;
		insertar->anterior=ultimo;
		ultimo=ultimo->siguiente;
	}
	
	//Metodo: insertarMedio
	void insertarMedio(nodoLateral* insertar){
		nodoLateral* tmp1;
		nodoLateral* tmp2;
		tmp1=primero;
		
		while(tmp1->y<insertar->y){	
			tmp1=tmp1->siguiente;
		}
		tmp2=tmp1->anterior;++
		tmp2->siguiente=insertar;
		tmp1->anterior=insertar;
		insertar->siguiente=tmp1;
		insertar->anterior =tmp2;
	}
		
	//Metodo: Recorrer
	void recorrer(){
		if(!vacio()){
			nodoLateral* temporal=primero;
			
			while(temporal!=NULL){
				cout<<"y= "<<temporal->y<<"\n";
				temporal=temporal->siguiente;
			}
		}else {
			cout<<"La lista esta vacia";
		}
	}
	
	//Busqueda de nodo por coordenada vertical
	nodoLateral* busqueda(int y){
		if(existe(y)){
			nodoLateral* temporal;
			temporal=primero;
			
			while(temporal->y!=y){
				temporal=temporal->siguiente;
			}
			return temporal;
		}else {
			return(new nodoLateral(-1)); //Error por repeticion
		}
	}
	
	//verificar existencia del nodo en la lista
	bool existe(int y){
		if(vacio()){
			return false;
		} else {
			nodoLateral* temporal;
			temporal=primero;
			while(temporal!=NULL){
				if(temporal->y==y){
					return true;
				}else if(temporal->siguiente==NULL){
					return false;
				}
				temporal=temporal->siguiente;
			}
		}
		return false;
	}
};

//Objeto: cabeceras
struct cabeceras{
	nodoCabecera* primero;
	nodoCabecera* ultimo;
	
	cabeceras(){
		primero=NULL;
		ultimo=NULL;
	}
	
	//Mtodo: insertar
	void insertar(nodoCabecera* insertar) {
		if(vacio()){
			primero=ultimo=insertar;
		}else{
			insertarFinal(insertar); 	
		}
	}
	
	//Metodo: verificar si la lista tiene al menos un nodo
	bool vacio(){
		if(primero==NULL){
			return true;
		} 
		else {
			return false;
		}
	}
	
	//Metodo: insertarInicio
	void insertarInicio(nodoCabecera* insertar){
		primero->anterior=insertar;
		insertar->siguiente=primero;
		primero=primero->anterior;
	}
	
	//Metodo: insertarFinal	
	void insertarFinal(nodoCabecera* insertar){
		ultimo->siguiente=insertar;
		insertar->anterior=ultimo;
		ultimo=ultimo->siguiente;
	}
	
	//Metodo: insertarMedio	
	void insertarMedio(nodoCabecera* insertar){
		nodoCabecera* tmp1;
		nodoCabecera* tmp2;
		tmp1=primero;
		
		while(tmp1->x<insertar->x){	
			tmp1=tmp1->siguiente;
		}
		tmp2=tmp1->anterior;
		tmp2->siguiente=insertar;
		tmp1->anterior=insertar;
		insertar->siguiente=tmp1;
		insertar->anterior =tmp2;
	}
		
	//Metodo: verificar si existe y lista recorrerla
	void recorrer(){
		if(!vacio())	{
			nodoCabecera* temporal=primero;
			
			while(temporal!=NULL){
				cout<<"x= "<<temporal->x<<"\n";
				temporal=temporal->siguiente;
			}
		}else {
			cout<<"La lista esta vacia";
		}
	}
	
	//Metodo: busqueda por coordenada x
	nodoCabecera* busqueda(int x){
		if(existe(x)){
			nodoCabecera* temporal;
			temporal=primero;
			
			while(temporal->x!=x){
				temporal=temporal->siguiente;
			}
			return temporal;
		}else {
			return(new nodoCabecera(-1));
		}
	}
	
	//metodo: existe(), verfificar si el nodo existe en la lista
	bool existe(int x){
		if(vacio())	{
			return false;
		} else{
			nodoCabecera* temporal;
			temporal=primero;
			
			while(temporal!=NULL){
				if(temporal->x==x){
					return true;
				}else if(temporal->siguiente==NULL){
					return false;
				}
				temporal=temporal->siguiente;
			}
		}
		return false;
	}
};

//Objeto: matrizOrtogonal, tablero de nodos enlazados
struct matrizOrtogonal {
	cabeceras* c;
	laterales* l;
	
	//Constructor
	matrizOrtogonal(){
		c=new cabeceras(); //columnero
		l=new laterales(); //lateraler
	}
	
	//Metodo general para insertar nodos completos
	void insertar(int x, int y, int dato){
		nodoOrtogonal* insercion;
		insercion=new nodoOrtogonal(dato,x,y);
		
		if(c->existe(x)==false){
			c->insertar(new nodoCabecera(x));		
		}if(l->existe(y)==false){
			l->insertar(new nodoLateral(y));
		}
		nodoCabecera* Ctemporal;
		nodoLateral* Ltemporal;
		Ctemporal=c->busqueda(x); //Verifica si existe el nodo en esa coordenada (columna)
		Ltemporal=l->busqueda(y); //Verifica si existe el nodo en esa coordenada (fila)
		Ctemporal->columnas->insertar(insercion);
		Ltemporal->filas->insertar(insercion); //cout<<"Inserto: "<<insercion->dato<<",en "<<insercion->x<<" "<<insercion->y<<"\n";
	}
	
	//Metodo principal, construye la matriz
	void llenarMatriz(int filas, int columnas,int numerosIngresados[]){
		int contador=0;
		
		for(int i=0;i<filas;i++){
			for(int j=0;j<columnas;j++){
				insertar(i,j,numerosIngresados[contador]);
				contador++;
			}
		}
	}
	
	//Metodo para mostrar nodos de la matriz enlazada en orden adecuado
	void imprimirMatriz(){
		cout<<"\n * * * Cargando datos * * *";
		cout<<"\n\nTablero del Juego: \n\n";
		nodoCabecera* ltemp=c->primero;
		nodoOrtogonal* temp=ltemp->columnas->primero;
		
		while(ltemp!=NULL){
			cout<<"\t";
			while(temp!=NULL){
				cout<<temp->dato<<"\t";
				temp=temp->abajo;
			}
			cout<<endl;
			ltemp=ltemp->siguiente;
			if(ltemp!=NULL){
				temp=ltemp->columnas->primero;
			}
		}
	}
		
	//Metodo principal, inicio del juego (sin terminar gran mayoria)
	void jugar(){
		string movimiento;
		cout<<"\nArriba = W *** Abajo = S *** Izquierda = A *** Derecha =D * ^^^ * Pause = P *** Reset = L *** Finish = M\nTecla =";
		cin>>movimiento;
		
		while(!juegoGanado()){	
		}
	}
	
	//Metodo para verificar si gano la partida
	bool juegoGanado(){
		return true;
	}
};
#endif
