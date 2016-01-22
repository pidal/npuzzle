package es.puchol;

public class Main {

    public static void main(String[] args) {

        int[][] numeros = {{2,3,4}, {1,7,5}, {0,6,8}};

        Estado estadoInicial = new Estado(numeros);

        Puzzle puzzle = new Puzzle(estadoInicial);

        puzzle.busquedaProfundidad();





    }
}
