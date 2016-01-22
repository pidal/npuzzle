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
    Estado estadoActual;

    public Puzzle(Estado estadoInicial) {

        this.estadoInicial = estadoInicial;
        this.abiertos = new ArrayList<>();
        this.cerrados = new ArrayList<>();

    }

    private void prepararBusqueda() {
        abiertos.clear();
        cerrados.clear();
    }


    public void busquedaProfundidad() {

        prepararBusqueda();
        abiertos.add(estadoInicial);

        while(!abiertos.isEmpty())
        {
            // coger el primer elemento de abiertos y sacarlo de la lista
            estadoActual = abiertos.remove(0);

            // añadir estado actual a cerrados
            cerrados.add(estadoActual);

            if(estadoActual.esEstadoFinal())
            {
                devolverCamino(estadoActual);
                return;
            }

            ArrayList<Estado> sucesores = generarSucesores(estadoActual);

            gestionarColaProfundidad(sucesores);

            // if(busqueda es coste uniforme ordenamos por el coste)
            Collections.sort(abiertos);

        }

    }

    private void gestionarColaProfundidad(ArrayList<Estado> sucesores) {

        for(Estado sucesor : sucesores) {

            if(abiertos.contains(sucesor))
                continue;

            abiertos.add(0, sucesor);

        }

    }

    private ArrayList<Estado> generarSucesores(Estado estadoActual) {

        ArrayList<Estado> sucesores = new ArrayList<>();

        int i = 0, j = 0;
        int ladoPuzzle = estadoActual.getNumeros().length ;

        for( i = 0; i < ladoPuzzle; i++) {
            for( j = 0; j < ladoPuzzle; j++) {
                if(estadoActual.getNumeros()[i][j] == 0)
                    break;
            }
        }

        // Comprobar si es posible mover arriba
        if(j > 0)
            sucesores.add(moverFichaBlanca(estadoActual, i, j, 1, 0));

        // Comprobar si es posible mover abajo
        if(j < ladoPuzzle)
            sucesores.add(moverFichaBlanca(estadoActual, i, j, -1, 0));

        // Comprobar si es posible mover derecha
        if(i > ladoPuzzle)
            sucesores.add(moverFichaBlanca(estadoActual, i, j, 0, 1));

        // Comprobar si es posible mover izquierda
        if(i > 0)
            sucesores.add(moverFichaBlanca(estadoActual, i, j, 0, -1));

        return sucesores;

    }

    private Estado moverFichaBlanca(Estado estadoActual, int i, int j, int iShift, int jShift) {

        int[][] nuevoOrden = estadoActual.getNumeros();

        int iNueva = i + iShift;
        int jNueva = j + jShift;

        nuevoOrden[i][j] = nuevoOrden[iNueva][jNueva];
        nuevoOrden[iNueva][jNueva] = 0;

        return new Estado(estadoActual, nuevoOrden, estadoActual.getCoste() + estadoActual.getNumeros()[i][j]);

    }

    private void devolverCamino(Estado estadoActual) {

        if(estadoActual.getPadre() != null)
            devolverCamino(estadoActual.getPadre());

        System.out.println(estadoActual);

    }

}
