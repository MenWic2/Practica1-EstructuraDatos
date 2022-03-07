/*
 * Welcome
 */
package test;

import com.menwic.practica_apuestas.manejador_apuesta.Verificar;

/**
 *
 * @author achess
 */
public class Test {
    public static void main(String[] args) {
        boolean[] test = new boolean[10];
        //System.out.println(test[1]);
        //String pattern = "\\w+,[1-9]\\d*(,(10|[1-9])){10}";
        //String line = "Pedro,334,10,2,3,4,5,6,7,8,9,10";
        //System.out.println(line.matches(pattern));
        //int array[];        
        //System.out.println(array);
        /*
        int array[] = {1,2,3,4,5,6,7,8,9,10};
        int array2[] = {1,3,2,4,6,5,7,9,8,10};
        int array3[] = {1,3,2,3,6,5,7,9,8,10};
        int array4[] = {1,3,2,3,6,5,5,9,10,10};
        int array5[] = {1,3,2,3,6,5,5,9,10,10};
        System.out.println(Verificar.validate(array));
        System.out.println(Verificar.validate(array2));
        System.out.println(Verificar.validate(array3));
        System.out.println(Verificar.validate(array4));
        */
        //int len = 77;
        //int div = len/2 + 1;
        //System.out.println(div);
        String a = "Alexnader";
        String b = "Alaxander";
        int mayor =  b.compareTo(a);
        System.out.println(mayor);
    }
}
