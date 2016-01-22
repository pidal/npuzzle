package es.puchol;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

//        int[][] numeros    = {{2, 1, 3}, {7, 5, 6}, {0, 4, 8}};
//        int[][] ordenFinal = {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};
//
        int[][] numeros    = {{3, 1}, {2, 0}};
        int[][] ordenFinal = {{1, 2}, {3, 0}};

        int metodo = 0;

        Scanner in = new Scanner(System.in);

        System.out.println("Eliga metodo de resolucion:");
        System.out.println("1: Profundidad");
        System.out.println("2: Anchura");
        System.out.println("3: Dijsktra");

        metodo = in.nextInt();

        Estado estadoInicial = new Estado(numeros);
        Estado estadoFinal = new Estado(ordenFinal);

        Puzzle puzzle = new Puzzle(estadoInicial, estadoFinal);

        puzzle.busqueda(metodo);

        puzzle.imprimirFichero();


    }
}
