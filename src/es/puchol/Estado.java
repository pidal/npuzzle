package es.puchol;

import java.util.Arrays;

public class Estado implements Comparable {

    Estado padre;
    int[][] numeros;
    int coste;

    public Estado(int[][] numeros) {
        this.numeros = numeros;
        this.coste = 0;
        this.padre = null;
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

    public boolean esEstadoFinal(Estado estadoFinal) {
        // Comprobar que estan los numeros ordenados
        return this.equals(estadoFinal);
    }


    @Override
    public boolean equals(Object obj) {

        Estado e = (Estado) obj;
        return (Arrays.deepEquals(e.getNumeros(), this.numeros));

    }

    @Override
    public int compareTo(Object o) {
        if( ((Estado)o).getCoste() > this.getCoste())
            return 1;
        return 0;
    }

    @Override
    public String toString() {
        String estado = "";
        for(int i = 0; i < numeros.length; i++)
            for(int j = 0; j < numeros.length; j++)
                estado += numeros[i][j] + "";

       return "<" + estado + "|c:" + this.getCoste() + ">";


    }
}
