#ifndef TABLERO_H
#define TABLERO_H

#include"Matriz.h"
//#include"ComponentesMatriz.h"

/* Esqueleto del programa */

void tablero(int numero[]){
	matrizOrtogonal* tablero;
	tablero=new matrizOrtogonal();
	tablero->llenarMatriz(4,4,numero); //Recordar de dejar opcion para generar tablero de forma dinamica
	tablero->imprimirMatriz();
	tablero->jugar();
}
#endif
