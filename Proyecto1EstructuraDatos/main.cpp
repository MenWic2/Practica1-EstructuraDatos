#include<iostream>

#include"Juego.h"
//#include"Tablero.h"

using namespace std;

/* @MenWic */
/* Clae Principal */

int main (int argc, char *argv[]) {
	int numero=0;
	
	while(numero!=4){
		numero=menuJuego();
		//generarMatriz(2); //Ingreso manual
		//generarMatriz(3); //Ingreso aleatorio
	}
	system("pause");
	return 0;
}




