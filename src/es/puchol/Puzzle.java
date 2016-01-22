package es.puchol;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by pidal on 21/01/16.
 */
public class Puzzle {

    ArrayList<Estado> abiertos;
    ArrayList<Estado> cerrados;
    Estado estadoInicial;
    Estado estadoFinal;
    Estado estadoActual;

    public Puzzle(Estado estadoInicial, Estado estadoFinal) {

        this.estadoInicial = estadoInicial;
        this.estadoFinal = estadoFinal;
        this.abiertos = new ArrayList<>();
        this.cerrados = new ArrayList<>();

    }

    private void prepararBusqueda() {
        abiertos.clear();
        cerrados.clear();
    }


    public void busqueda(int metodo) {

        prepararBusqueda();
        abiertos.add(estadoInicial);

        while (!abiertos.isEmpty()) {
            // Coger el primer elemento de abiertos y sacarlo de la lista
            estadoActual = abiertos.remove(0);

            // Añadir estado actual a cerrados
            cerrados.add(estadoActual);

            if (estadoActual.esEstadoFinal(estadoFinal)) {
                System.out.println("Solucion encontrada:");
                devolverCamino(estadoActual);
                return;
            }

            ArrayList<Estado> sucesores = generarSucesores(estadoActual);

            System.out.println();
            System.out.println("Estado actual: " + estadoActual);
            System.out.println("Sucesores: " + sucesores);

            gestionarCola(sucesores, metodo);

            System.out.println("Abiertos: ("  + abiertos.size() + ") " + abiertos);
            System.out.println("Cerrados: ("  + cerrados.size() + ") " + cerrados);
        }

        System.out.println("Solucion no encontrada");

    }

    private void gestionarCola(ArrayList<Estado> sucesores, int metodo) {

        Collections.reverse(sucesores);

        for (Estado sucesor : sucesores) {

            // TODO: la madre del cordero
            if (!abiertos.contains(sucesor) && !cerrados.contains(sucesor))
                switch (metodo) {
                    case 1:
                        abiertos.add(0, sucesor);
                        break;

                    case 2:
                        abiertos.add(sucesor);
                        break;

                    case 3:
                        abiertos.add(sucesor);
                        Collections.sort(abiertos);
                        break;

                }

        }


    }

    private ArrayList<Estado> generarSucesores(Estado estadoActual) {

        ArrayList<Estado> sucesores = new ArrayList<>();

        int iCero = 0, jCero = 0;
        int ladoPuzzle = estadoActual.getNumeros().length;

        for (int i = 0; i < ladoPuzzle; i++) {
            for (int j = 0; j < ladoPuzzle; j++) {
                if (estadoActual.getNumeros()[i][j] == 0) {
                    iCero = i;
                    jCero = j;
                    break;
                }
            }
        }

        if (estadoActual.getNumeros()[iCero][jCero] != 0)
            return sucesores;


        // Comprobar si es posible mover derecha
        if (jCero < ladoPuzzle - 1)
            sucesores.add(moverFichaBlanca(estadoActual, iCero, jCero, 0, 1));

        // Comprobar si es posible mover izquierda
        if (jCero > 0)
            sucesores.add(moverFichaBlanca(estadoActual, iCero, jCero, 0, -1));

        // Comprobar si es posible mover arriba
        if (iCero > 0)
            sucesores.add(moverFichaBlanca(estadoActual, iCero, jCero, -1, 0));

        // Comprobar si es posible mover abajo
        if (iCero < ladoPuzzle - 1)
            sucesores.add(moverFichaBlanca(estadoActual, iCero, jCero, 1, 0));


        return sucesores;

    }

    private Estado moverFichaBlanca(Estado estadoActual, int i, int j, int iShift, int jShift) {

        int ladoPuzzle = estadoActual.getNumeros().length;
        int[][] nuevoOrden = new int[ladoPuzzle][ladoPuzzle];

        for (int in = 0; in < ladoPuzzle; in++)
            for (int jn = 0; jn < ladoPuzzle; jn++)
                nuevoOrden[in][jn] = estadoActual.getNumeros()[in][jn];

        int iNueva = i + iShift;
        int jNueva = j + jShift;

        nuevoOrden[i][j] = nuevoOrden[iNueva][jNueva];
        nuevoOrden[iNueva][jNueva] = 0;

        Estado nuevoEstado = new Estado(estadoActual, nuevoOrden, estadoActual.getCoste() + estadoActual.getNumeros()[iNueva][jNueva]);

//        System.out.println("Orden Antiguo:" + estadoActual);
//        System.out.println("Orden Nuevo:" + nuevoEstado);

        return nuevoEstado;
    }

    private void devolverCamino(Estado estadoActual) {

        if (estadoActual.getPadre() != null)
            devolverCamino(estadoActual.getPadre());

        System.out.println(estadoActual);

    }

    public void imprimirFichero() {
        // TODO hacer el txt con los datos
    }
}
