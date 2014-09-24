/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conteo_de_inversiones_tsb;

/**
 *
 * @author matychp
 */
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Main {

    //Estructuras
    static int otroArreglo[], unArreglo[], arregloOrdenado[];
    static ArrayList<Integer> unaLista;
    static SimpleList<Integer> unaSimpleList;
    static Heap<Integer> unHeapAscendente;

    //Cargas
    static final int LOTE01 = 0, LOTE02 = 1, PRUEBA01 = 2;
    static final int ARREGLO = 0, ARRAYLIST = 1, SIMPLELIST = 2, ARREGLO_HEAP = 3;
    static final int SELECCION_DIRECTA = 0, ORDEN_N_FOR = 1, ORDEN_N_WHILE = 2, CONTEO_PRIMERO_ARREGLO = 3,
            MENOR_LISTA = 4, MENOR_SIMPLELIST = 5, MENOR_ELIMINA_INDICE = 6, MENOR_ARREGLO = 7,
            MENOR_ARREGLO_HEAP = 8, MAYORES_MENORES_ARREGLO = 9;

    //Variables
    static long unContador = 0;//Contador de inversiones.

    public static void main(String args[]) {

        cargarColeccion(LOTE02, ARREGLO);

        elegirAlgoritmo(MENOR_ARREGLO);

        System.out.println("Cantidad de inversiones: " + unContador);

        //mostrarObjetos(ARREGLO);
    }

    public static void cargarColeccion(int archivo, int tipoColeccion) {
        File f = null;

        switch (tipoColeccion) {
            case ARREGLO:
                switch (archivo) {
                    case LOTE01:
                        unArreglo = new int[30000];
                        f = new File("./resources/lote01.txt");
                        break;
                    case LOTE02:
                        unArreglo = new int[300000];
                        f = new File("./resources/lote02.txt");
                        break;
                    case PRUEBA01:
                        unArreglo = new int[6];
                        f = new File("./resources/prueba01.txt");
                        break;
                }
                break;
            case ARRAYLIST:
                switch (archivo) {
                    case LOTE01:
                        unaLista = new ArrayList<>();
                        f = new File("./resources/lote01.txt");
                        break;
                    case LOTE02:
                        unaLista = new ArrayList<>();
                        f = new File("./resources/lote02.txt");
                        break;
                }
                break;
            case SIMPLELIST:
                switch (archivo) {
                    case LOTE01:
                        unaSimpleList = new SimpleList<>();
                        f = new File("./resources/lote01.txt");
                        break;
                    case LOTE02:
                        unaSimpleList = new SimpleList<>();
                        f = new File("./resources/lote02.txt");
                        break;
                }
                break;
            case ARREGLO_HEAP:
                switch (archivo) {
                    case LOTE01:
                        unHeapAscendente = new Heap<>(30000, true);
                        unArreglo = new int[30000];
                        f = new File("./resources/lote01.txt");
                        break;
                    case LOTE02:
                        unHeapAscendente = new Heap<>(300000, true);
                        unArreglo = new int[300000];
                        f = new File("./resources/lote02.txt");
                        break;
                    case PRUEBA01:
                        unHeapAscendente = new Heap<>(6, true);
                        unArreglo = new int[6];
                        f = new File("./resources/prueba01.txt");
                        break;
                }
        }

        try (Scanner sc = new Scanner(f)) {
            int pos = 0; //usado para la carga ARREGLO y ARREGLO_HEAP
            int unNumCargar;
            switch (tipoColeccion) {
                case ARREGLO:
                    while (sc.hasNextInt()) {
                        unArreglo[pos] = sc.nextInt();
                        pos++;
                    }
                    break;
                case ARRAYLIST:
                    while (sc.hasNextInt()) {
                        unaLista.add(sc.nextInt());
                    }
                    break;
                case SIMPLELIST:
                    //Con .addLast() la carga tarda 5 minutos 55 segundos para el lote02.
                    while (sc.hasNextInt()) {
                        unaSimpleList.addLast(sc.nextInt());
                    }
                    break;
                case ARREGLO_HEAP:
                    while (sc.hasNextInt()) {
                        unNumCargar = sc.nextInt();
                        unHeapAscendente.add(unNumCargar);
                        unArreglo[pos] = unNumCargar;
                        pos++;
                    }
                    break;
                default:
                    System.out.println("Tipo de Carga incorrecta");
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void elegirAlgoritmo(int unAlgoritmo) {

        int l, m, elMenorDeLaLista, posMenor, contadorJ;//Variables utilizadas.

        switch (unAlgoritmo) {
            case SELECCION_DIRECTA:
                //O(n^2)
                //Primer Algortimo
                //prueba01 (0 segundos).
                //lote01 (2 segundos).
                //lote01 (3 segundos).Con cambio de inversiones.
                //lote02 (53 segundos).
                //lote02 (3 minutos 22 segundos).
                for (int j = 0; j < unArreglo.length - 1; j++) {
                    for (int k = j + 1; k < unArreglo.length; k++) {
                        if (unArreglo[j] > unArreglo[k]) {
                            unContador++;

                            //Con intercambio de casilleros. SI FUNCIONA D:!
                            //int aux = unArreglo[j];
                            //unArreglo[j] = unArreglo[k];
                            //unArreglo[k] = aux;
                        }
                    }
                }
                break;
            case ORDEN_N_FOR:
                //O(n)
                //Segundo Algoritmo
                //Funciona con el prueba01 (0 segundos).
                //Funciona con el lote01 (2 segundos).
                //Funciona con el lote02 (2 minutos 12 segundos).
                l = 0;
                for (m = l + 1; m < unArreglo.length; m++) {
                    if (unArreglo[l] > unArreglo[m]) {
                        unContador++;
                    }
                    if (m == (unArreglo.length - 1)) {
                        l++;
                        m = l; //Luego el m++ le suma el +1 que le falta.
                    }
                }
                break;
            case ORDEN_N_WHILE:
                //O(n)
                //Tercer Algoritmo.
                //Funciona con el prueba01 (0 segundos).
                //Funciona con el lote01 (2 segundos).
                //Funciona con el lote02 (2 minutos 12 segundos)(Si tarda lo mismo que su equivalente en ciclo for, arl).
                l = 0;
                m = l + 1;
                while (m < unArreglo.length) {
                    if (unArreglo[l] > unArreglo[m]) {
                        unContador++;
                    }
                    if (m == (unArreglo.length - 1)) {
                        //Esto es molesto porque lo pregunta en todos los ciclos m.
                        l++;
                        m = l; //Luego el m++ le suma el +1 que le falta.
                    }
                    m++;
                }
                break;
            case CONTEO_PRIMERO_ARREGLO:
                //O(n)
                //Cuarto Algoritmo.
                //Funciona con el prueba01 (0 segundos).
                //Funciona con el lote01 (2 segundos).
                //Funciona con el lote02 (3 minutos 56 segundos).
                l = 0;
                m = l + 1;
                boolean centinela = true;
                while (centinela) {
                    if (m < unArreglo.length - 1) {
                        //Que compare.
                        if (unArreglo[l] > unArreglo[m]) {
                            unContador++;
                        }
                    } else if (m == unArreglo.length - 1) {
                        //Que compare.
                        if (unArreglo[l] > unArreglo[m]) {
                            unContador++;
                        }
                        //Pero ademas la siguiente vuelta, arranque con otro l y el m que le sigue al nuevo l.
                        l++;
                        m = l; //Luego el m++ le suma el +1 que le falta.
                    } else {
                        centinela = false;
                    }
                    m++;
                }
                break;
            case MENOR_LISTA:
                //Quinto Algortimo. 
                //Funciona con el prueba01 (0 segundos).
                //Funciona con el lote01 (3 segundos).
                //Funciona con el lote02 (12 minutes 15 seconds).
                for (int i = 0; i < unaLista.size() - 1; i = 0) {
                    for (int j = i + 1; j < unaLista.size(); j++) {
                        if (unaLista.get(i) > unaLista.get(j)) {
                            i = j;
                            j = i;
                        }
                    }
                    unContador += i;
                    unaLista.remove(i);
                }
                break;
            case MENOR_SIMPLELIST:
                //Sexto Algortimo. 
                //Funciona con el prueba01 (0 segundos).
                //Funciona con el lote01 (11 segundos).
                //Funciona con el lote02 (21 minutes 40 seconds).
                while (!unaSimpleList.isEmpty()) {
                    //Toma el primer numero de la lista.
                    elMenorDeLaLista = unaSimpleList.getFirst();
                    posMenor = 0;
                    //Usa el ciclo para determinar si existe un numero menor al tomado
                    contadorJ = 0;
                    for (Integer j : unaSimpleList) {
                        if (j < elMenorDeLaLista) {
                            elMenorDeLaLista = j;//Se detectó un numero menor, lo guarda.
                            posMenor = contadorJ;//Ytambien guarda su posicion en la lista.
                        }
                        contadorJ++;
                    }
                    unContador += posMenor;//Suma las posiciones que se restaron a la lista (que serian la cantidad de inversiones).
                    unaSimpleList.remove(posMenor);//Y elimina el elemento de la lista.
                }
                break;
            case MENOR_ELIMINA_INDICE:
                //Septimo Algortimo.
                //Funciona con el prueba01 (0 segundos).
                //Funciona con el lote01 (11 segundos).
                //Funciona con el lote02 (15 minutes 16 seconds).
                int cantidadInversionesMenor;
                while (!unaSimpleList.isEmpty()) {
                    cantidadInversionesMenor = unaSimpleList.getIndiceMenor();
                    unContador += cantidadInversionesMenor;
                }
                break;
            case MENOR_ARREGLO:
                //Octavo Algortimo.
                //Funciona con el prueba01 (0 segundos).
                //Funciona con el lote01 (2 segundos).
                //Funciona con el lote02 (2 minutos 30 segundos).
                while (unArreglo.length != 0) {
                    //Toma el primer numero de la lista.
                    elMenorDeLaLista = unArreglo[0];
                    posMenor = 0;
                    //Usa el ciclo para determinar si existe un numero menor al tomado
                    for (int i = 0; i < unArreglo.length; i++) {
                        if (unArreglo[i] < elMenorDeLaLista) {
                            elMenorDeLaLista = unArreglo[i];//Se detectó un numero menor, lo guarda.
                            posMenor = i;//Y tambien guarda su posicion en la lista.
                        }
                    }
                    unContador += posMenor;//Suma las posiciones que se restaron a la lista (que serian la cantidad de inversiones).
                    unArreglo = eliminarIndice(unArreglo, posMenor);
                }
                break;
            case MENOR_ARREGLO_HEAP:
                // Algortimo.
                //Funciona con el prueba01 ( segundos).
                //Funciona con el lote01 ( segundos).
                //Funciona con el lote02 ().
                int menor;
                while(! unHeapAscendente.isEmpty()){
                    menor = unHeapAscendente.remove();
                    for(Integer i: unArreglo){
                        if(unArreglo[i] > menor){//Inversion
                            unContador += 1;
                        }
                        else{
                            break;
                        }
                    }
                }
                break;
            case MAYORES_MENORES_ARREGLO:
                //Noveno Algoritmo
                //lote01(3 segundos)
                //lote02(2 minutos 30 segundos)
                int contadorMayores;
                int temporalMenores[];
                int indicesABorrar[];
                while (unArreglo.length != 0) {
                    temporalMenores = new int[0];
                    indicesABorrar = new int[0];
                    contadorMayores = 1;
                    for (int i = 1; i < unArreglo.length; i++) {
                        if (unArreglo[0] < unArreglo[i]) {
                            contadorMayores++;
                        } else {
                            unContador += contadorMayores;
                            for (int k = 0; k < temporalMenores.length; k++) {
                                if (temporalMenores[k] > unArreglo[i]) {
                                    unContador++;
                                }
                            }
                            temporalMenores = agregarUltimo(temporalMenores, unArreglo[i]);
                            indicesABorrar = agregarUltimo(indicesABorrar, i);
                        }
                    }
                    unArreglo = eliminarIndice(unArreglo, 0);
                    indicesABorrar = actualizarIndices(indicesABorrar);
                    for (int x = 0; x < temporalMenores.length; x++) {
                        unArreglo = eliminarIndice(unArreglo, indicesABorrar[x]);
                        indicesABorrar = actualizarIndices(indicesABorrar);
                    }
                }
                break;
            case 10:
                //Algoritmo Nueve
                //lote01(3 segundos)
                //lote02(2 minutos 30 segundos)
                int menorAMayor[] = new int[unArreglo.length + 1];
                int unIContador = 0;
                for (Integer i : unArreglo) {
                    unIContador++;
                    menorAMayor[i] = unIContador;
                }
                for (Integer j : menorAMayor) {
                    System.out.println(j);
                }
                break;
        }
    }

    public static int[] eliminarIndice(int[] unArreglo, int indice) {

        otroArreglo = new int[(unArreglo.length - 1)];

        System.arraycopy(unArreglo, 0, otroArreglo, 0, indice);
        System.arraycopy(unArreglo, (indice + 1), otroArreglo, indice, (unArreglo.length - indice - 1));
        return otroArreglo;
    }

    public static int[] agregarUltimo(int[] unArreglo, int unEnteroAAgregar) {

        otroArreglo = new int[(unArreglo.length + 1)];

        System.arraycopy(unArreglo, 0, otroArreglo, 0, unArreglo.length);
        otroArreglo[otroArreglo.length - 1] = unEnteroAAgregar;

        return otroArreglo;
    }

    public static int[] actualizarIndices(int[] unArreglo) {
        for (int x = 0; x < unArreglo.length; x++) {
            unArreglo[x]--;
        }
        return unArreglo;
    }

    public static void mostrarObjetos(int tipoColeccion) {

        switch (tipoColeccion) {
            case ARREGLO:
                for (int i = 0; i < unArreglo.length; i++) {
                    System.out.println(unArreglo[i]);
                }
                break;
            case ARRAYLIST:
                for (Integer i : unaLista) {
                    System.out.println(i);
                }
                break;
            case SIMPLELIST:
                for (Integer i : unaSimpleList) {
                    System.out.println(i);
                }
                break;
        }
        //Ciclo For (12 segundos en mostrar todos los numeros).
        //Ciclo For Each(12 segundos)
        //Ambos ciclos tardan lo mismo al hacer S.o.p, o usando un contador.
        //Conclusion, no hay diferencias entre for y for each para el lote02.
        //En cuanto a recorrer la lista, O(n).
    }
}

