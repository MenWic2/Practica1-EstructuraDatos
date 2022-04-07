#ifndef NODOSMATRIZ_H
#define NODOSMATRIZ_H

#include <iostream>
#include <cstdlib>
//#include "ListasOrtogonales.h"

using namespace std;

//Objeto: NodoOrtogonal (Base de la matriz)
struct nodoOrtogonal{
	int dato,x,y;
	nodoOrtogonal* arriba;
	nodoOrtogonal* abajo;
	nodoOrtogonal* izquierda;
	nodoOrtogonal* derecha;
	
	//Constructor
	nodoOrtogonal(int dato, int x, int y){
		this->dato=dato;
		this->x=x;
		this->y=y;
		arriba=NULL;
		abajo=NULL;
		izquierda=NULL;
		derecha=NULL; 
	}
};

//Objeto> listaVertical (columnas que contienen nodos)
struct listaVertical{
	nodoOrtogonal* primero;
	nodoOrtogonal* ultimo;
	
	//Constructor
	listaVertical(){
		primero=NULL;
		ultimo=NULL;
	}
	
	//Metodo: Agregar nodos al final de una listaVertical
	void insertar(nodoOrtogonal* insertar){
		if(vacio()){
			primero=ultimo=insertar;
		}else {
			insertarFinal(insertar); 	
		}
	}
	
	//Metodo: vacio(), verifica si la lista contien al menos un nodo
	bool vacio(){
		if(primero==NULL){
			return true;
		} 
		else {
			return false;
		}
	}
	
	//Metodo: insertarInicio, inserta nodo al incia de la lista
	void insertarInicio(nodoOrtogonal* insertar){
		primero->arriba=insertar;
		insertar->abajo=primero;
		primero=primero->arriba;
	}
	
	//Metodo: insertarFinal, inserta nodo al final de la lista
	void insertarFinal(nodoOrtogonal* insertar){
		ultimo->abajo=insertar;
		insertar->arriba=ultimo;
		ultimo=ultimo->abajo;
	}
	//Metodo: insertarMedio, inserta nodo al medio de la lista (comparando coordenada y entre el nodo a insertar y los de la lista)
	void insertarMedio(nodoOrtogonal* insertar){
		nodoOrtogonal* tmp1;
		nodoOrtogonal* tmp2;
		tmp1=primero;
		
		while(tmp1->y<insertar->y){	
			tmp1=tmp1->abajo;
		}
		tmp2=tmp1->arriba;
		tmp2->abajo=insertar;
		tmp1->arriba=insertar;
		insertar->abajo=tmp1;
		insertar->arriba=tmp2;
		}
		
	//Metodo: recorrer(), verifica que no este vacia y la recorre
	void recorrer(){
		if(!vacio()){
			nodoOrtogonal* temporal=primero;
			
			while(temporal!=NULL){
				cout<<"y= "<<temporal->y<<"\n";
				temporal=temporal->abajo;
			}
		}else{
			cout<<"La lista esta vacia";
		}
	}	
};

//Objeto: listaHorizontal, contiene nodos
struct listaHorizontal{
	nodoOrtogonal* primero;
	nodoOrtogonal* ultimo;
	
	//Constructor
	listaHorizontal(){
		primero=NULL;
		ultimo=NULL;
	}
		
		//Metodo: insertar nodos a la lista
		void insertar(nodoOrtogonal* insertar){
			if(vacio()){
				primero=ultimo=insertar;
			}else{
				insertarFinal(insertar); 	
			}
		}
			
			//Metodo para verificar si la lista tiene al menos un nodo
			bool vacio(){
				if(primero==NULL){
					return true;
				} 
				else{
					return false;
				}
			}
				
				//Metodo: insertarInicio, agrega nodos al inicia de la lista
				void insertarInicio(nodoOrtogonal* insertar){
					primero->izquierda=insertar;
					insertar->derecha=primero;
					primero=primero->izquierda ;
				}
					
					//Metodo: insertarFinal, agrega nodos al final de la lista	
					void insertarFinal(nodoOrtogonal* insertar){
						ultimo->derecha=insertar;
						insertar->izquierda=ultimo;
						ultimo=ultimo->derecha;
					}
						
						//Metodo: insertarMedio, agrega nodos al al medio de la lista, comparando la coordenada x del nodo a insertar con los de la lista
						void insertarMedio(nodoOrtogonal* insertar){
							nodoOrtogonal* tmp1;
							nodoOrtogonal* tmp2;
							tmp1=primero;
							
							while(tmp1->y<insertar->y){	
								tmp1=tmp1->abajo;
							}
							
							tmp2=tmp1->izquierda;
							tmp2->derecha=insertar;
							tmp1->izquierda=insertar;
							insertar->derecha=tmp1;
							insertar->izquierda =tmp2;
						}
							
							//Metodo: Recorrer, recorre la lista si tiene al menos un nodo
							void recorrer(){
								if(!vacio()){
									nodoOrtogonal* temporal=primero;
									
									while(temporal!=NULL){
										cout<<"x= "<<temporal->x<<"\n";
										temporal=temporal->derecha;
									}
								}else {
									cout<<"La lista esta vacia";
								}
							}
};


//Objeto: nodocabecera, nodo principal que tiene asignada una columna
struct nodoCabecera{
	int x;
	nodoCabecera* siguiente;
	nodoCabecera* anterior;
	listaVertical* columnas;
	
	//COnstructor
	nodoCabecera(int x){
		this->x=x;
		siguiente=NULL;
		columnas=new listaVertical();
		anterior=NULL;
	}
};

//Objeto: nodoLateral, nodo principal que tiene asiganda una fila
struct nodoLateral{
	int y;
	nodoLateral* siguiente;
	nodoLateral* anterior;
	listaHorizontal* filas;
	
	//Constructor
	nodoLateral(int y){
		this->y=y;
		siguiente=NULL;
		filas=new listaHorizontal();
		anterior=NULL;
	}
};

#endif
