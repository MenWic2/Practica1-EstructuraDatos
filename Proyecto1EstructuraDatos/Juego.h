#ifndef OPCIONESJUEGO_H
#define OPCIONESJUEGO_H

#include <iostream>
#include <cstdlib>
#include <string.h>
#include<ctime>

#include"Tablero.h"

using namespace std;

/* Inicio, seleccion de tipo-juego y datos */

int opcion;
int numeros[15]; //
string username;
string texto=" ";
bool repetido(int num, int i);
bool verificarRepetidos(int numeros[]);

//Metodo para identificar que modalidad quiere seguir el usuario (y construir por ultimo la matriz)
void generarMatriz(int num){ //Dependiende el numero que se ingrese, se envia a cierto constructor de matriz
	if(num==1){
		//Sin implementar
	} else if(num==2){
		cout<<" - Ingrese 15 numeros (separados por comas Ej: 0,1,2,3...15) = "<<endl;
		cin>>texto;
		
		char cadena[texto.length()+1];
		strcpy(cadena,texto.c_str()); //Cadena de texto (prefiero Java xd)
		char *ptr=strtok(cadena,","); //Equivalente al Split en java para separar cadenas
		int contador=0;
		
		while(ptr!=NULL){ //Mientras caena no este vacia
			numeros[contador]=atoi(ptr); //Video del YT
			ptr=strtok(NULL,",");
			contador++;
		}
		if(!verificarRepetidos(numeros)){
			tablero(numeros);
		}else {
			cout<<"Numeros no admitidos (ya existe alguno)";
		}
		
	}else if(num==3){
		for(int i=0;i<15;i++)
		{
			int nume=1+rand()%(16-1);
			
			while(repetido(nume,i)){
				nume=1+rand()%(16-1);;
			}
			numeros[i]=	nume;
		}
		tablero(numeros);
	}
	
}

//
bool verificarRepetidos(int numeros[])
{
	for(int i=0; i<15;i++){
		if(numeros[i]>15 || numeros[i]<0){
			return true;
		}
	}
	return false;
}
bool repetido(int num, int iteracion)
{
	for(int i=0; i<iteracion;i++){
		if(num ==numeros[i]){
			return true;
		}
	
	}
	return false;
}


int menuJuego(){
	cout<< "\n * * * * * BIENVENIDO AL JUEGO DEL 15 * * * * * \n";
	cout<< " . . . Ordena los 15 numeros (puedes?) . .  \n";
	cout<< " - - - Menu - - -\n";
	cout<< "1.- Cargar de Archivo\n";
	cout<< "2.- Jugar (Forma personalizada) \n";
	cout<< "3.- Jugar (Forma predeterminada)\n";
	cout<<"4.- Salir del Juego\n";
	cout<< "- Tu opcion = ";
	cin>>opcion;
	
	if(opcion==1){
		cout<<"- Tu Username = ";
		cin>>username;
		generarMatriz(1); //Cargar archivo
		cout<< " * Carga de archivo (Sin funcionar)\n";
	} else if(opcion==2){
		cout<<"- Tu Username = ";
		cin>>username;
		generarMatriz(2); //Ingresar valor para cada nodo al gusto
	} else if(opcion==3){
		cout<<"- Tu Username = ";
		cin>>username;
		generarMatriz(3); //Generacion de numeros aleatoria
	}else if(opcion==4){
		cout<< "* * * Hasta luego :') * * *\n";
	} else {
		cout<<" * Numero no aceptado, ingrese una opcion valida... *\n";
		opcion=0;
	}
	return opcion;
}
#endif
