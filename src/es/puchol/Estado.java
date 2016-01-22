package es.puchol;

import java.util.Arrays;
import java.util.Comparator;

public class Estado implements Comparator{

    Estado padre;
    int[][] numeros;
    int coste;

    public Estado(int[][] numeros) {
        this.numeros = numeros;
    }

    public Estado(Estado padre, int[][] numeros, int coste) {
        this.padre = padre;
        this.numeros = numeros;
        this.coste = coste;
    }

    public Estado getPadre() {
        return padre;
    }

    public void setPadre(Estado padre) {
        this.padre = padre;
    }

    public int[][] getNumeros() {
        return numeros;
    }

    public void setNumeros(int[][] numeros) {
        this.numeros = numeros;
    }

    public int getCoste() {
        return coste;
    }

    public void setCoste(int coste) {
        this.coste = coste;
    }

    public boolean esEstadoFinal() {
        return false;
    }

    @Override
    public int compare(Object o1, Object o2) {
        if( ((Estado)o1).getCoste() > ((Estado)o2).getCoste())
            return 1;
        return -1;

    }

    @Override
    public boolean equals(Object obj) {

        Estado e = (Estado) obj;
        return (Arrays.deepEquals(e.numeros, this.numeros));

    }


}
