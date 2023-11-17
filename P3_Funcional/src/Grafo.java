/**
 * Clase que analiza el graafo
 * @author Sergio Salazar Núñez
 */

public class Grafo {
    /**
     * Metodo que determina los pesos y caminos mas cortes de una ciudad incial a otra final
     * @param matrizAdj Representacion de grafo mediante un array de tipo long
     * @param posciudad_inicial Posicion de ciudad inicial
     * @param posciudad_final Posicion de ciudad final
     * @return array el cual el primer elemento corresponde al tiempo total en segundos y los demas son las posiciones
     * de las ciudades a recorrer
     */
    public int[] ciudadescaminocorto(long [][] matrizAdj,int posciudad_inicial,int posciudad_final){

        int vertices= matrizAdj.length;

        String caminos[][]=new String[vertices][vertices];
        String caminosAux [][]=new String[vertices][vertices];
        String caminorecorrido="";
        int i,j,k;
        float temporal1,temporal2,temporal3,temporal4,minimo;
        //Constructor de arrays caminos y caminosAux
        for (i=0;i<vertices;i++){
            for (j=0;j<vertices;j++){
                caminos[i][j]="";
                caminosAux[i][j]="";
            }
        }
        //Seccion de determinar caminos cortos
        for (k=0;k<vertices;k++){
            for (i=0;i<vertices;i++){
                for (j=0;j<vertices;j++){
                    temporal1=matrizAdj[i][j];
                    temporal2=matrizAdj[i][k];
                    temporal3=matrizAdj[k][j];
                    temporal4=temporal2+temporal3;
                    minimo=Math.min(temporal1,temporal4);
                    if(temporal1!=temporal4){
                        if (minimo==temporal4){
                            caminorecorrido="";
                            caminosAux[i][j]=k+"";
                            caminos[i][j]=caminosRecursivo(i,k,caminosAux,caminorecorrido)+(k+1);
                        }
                    }
                    matrizAdj[i][j]=(long) minimo;
                }
            }
        }
        //Seccion donde se retonar el array
        if (caminos[posciudad_inicial][posciudad_final].equals("")){
            return new int[]{(int) matrizAdj[posciudad_inicial][posciudad_final],posciudad_inicial, posciudad_final};
        }else {
            int[] Tiemporecorrido={(int) matrizAdj[posciudad_inicial][posciudad_final]};
            int[] Ciudad_inicial = {posciudad_inicial};
            int[] Ciudades_intermedias = convertirStringAArray(caminos[posciudad_inicial][posciudad_final]);
            int[] Ciudad_final = {posciudad_final};
            return concatenarArrays(Tiemporecorrido,Ciudad_inicial, Ciudades_intermedias, Ciudad_final);
        }

    }

    /**
     * Metodo el cual determina las ciudades intermedias
     * @param i parametro entero para analizar caminos auxiliares
     * @param k parametros entero para analizar caminos auxiliares
     * @param caminAux Array de strings de caminos auxiliares
     * @param caminRecorrido String de los caminos recorridos
     * @return Se retonar un string con las poscion de las ciudades en las cuales se pasara
     */
    public String caminosRecursivo(int i, int k, String[][] caminAux,String caminRecorrido){
        if(caminAux[i][k].equals("")){
            return "";
        }else {
            //Recursividad al Millon
            caminRecorrido+=caminosRecursivo(i,Integer.parseInt(caminAux[i][k].toString()),caminAux,caminRecorrido)+(Integer.parseInt(caminAux[i][k].toString())+1)+", ";
            return caminRecorrido;
        }
    }

    /**
     * Funcion que toma un string de enteros con comas para devolver un array de enteros
     * @param entrada String de entrada
     * @return array de enteros
     */
    public static int[] convertirStringAArray(String entrada) {

        String[] partes = entrada.split(",");


        int[] resultado = new int[partes.length];


        for (int i = 0; i < partes.length; i++) {
            try {
                resultado[i] = Integer.parseInt(partes[i].trim())-1;
            } catch (NumberFormatException e) {

                System.err.println("Error: '" + partes[i] + "' no es un número entero válido.");
            }
        }

        return resultado;
    }

    /**
     * Metodo que realiza la concatenacion de los 4 arrays del retorno del metodo ciudadescaminocorto
     * @param array1 primer array
     * @param array2 segundo array
     * @param array3 tercer array
     * @param array4 cuarto array
     * @return array concatenado
     */
    public static int[] concatenarArrays(int[] array1, int[] array2, int[] array3,int[] array4) {
        int longitudTotal = array1.length + array2.length + array3.length+array4.length;
        int[] resultado = new int[longitudTotal];

        System.arraycopy(array1, 0, resultado, 0, array1.length);
        System.arraycopy(array2, 0, resultado, array1.length, array2.length);
        System.arraycopy(array3, 0, resultado, array1.length + array2.length, array3.length);
        System.arraycopy(array4, 0, resultado, array1.length + array2.length+array3.length, array4.length);

        return resultado;
    }


}