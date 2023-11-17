public class CaminosMinimos {
    //Metodo para determinar todos los caminos Floyd
    public String algoritmoFloyd(long [][] matrizAdj){
        String[] cantones = {
                "San José", "Escazú", "Desamparados", "Puriscal", "Tarrazú",
                "Aserrí", "Mora", "Goicoechea", "Santa Ana", "Alajuelita",
                "Vázquez de Coronado", "Acosta", "Tibás", "Moravia", "Montes de Oca",
                "Turrubares", "Dota", "Curridabat", "Pérez Zeledón", "Empresa",
                "Heredia", "Barva", "Santo Domingo", "Santa Bárbara", "San Rafael",
                "San Isidro", "Belén", "Flores", "San Pablo", "Sarapiquí",
                "Alajuela", "San Ramón", "Grecia", "San Mateo", "Atenas",
                "Naranjo", "Palmares", "Poás", "Orotina", "San Carlos", "Los Chiles"
        };
        int vertices= matrizAdj.length;
        long matrizpesos[][]=matrizAdj;
        String caminos[][]=new String[vertices][vertices];
        String caminosAux [][]=new String[vertices][vertices];
        String caminorecorrido="",cadena="",caminitos="";
        int i,j,k;
        float temporal1,temporal2,temporal3,temporal4,minimo;
        //inicializando las matrices caminos y caminosAux
        for (i=0;i<vertices;i++){
            for (j=0;j<vertices;j++){
                caminos[i][j]="";
                caminosAux[i][j]="";
            }
        }
        for (k=0;k<vertices;k++){
            for (i=0;i<vertices;i++){
                for (j=0;j<vertices;j++){
                    temporal1=matrizAdj[i][j];
                    temporal2=matrizAdj[i][k];
                    temporal3=matrizAdj[k][j];
                    temporal4=temporal2+temporal3;
                    //Econtrando al minimo
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
        //Agregando el camino a cadena
        for (i=0;i<vertices;i++){
            for (j=0;j<vertices;j++){
                cadena+="["+matrizAdj[i][j]+"]";
            }
            cadena+="\n";
        }
        ///////////////
        for (i=0;i<vertices;i++){
            for (j=0;j<vertices;j++){
                if (matrizAdj[i][j]!=1000) {
                    if(i!=j) {
                        if (caminos[i][j].equals("")){
                            caminitos+="De ("+cantones[i]+"------>"+cantones[j]+") Irse Por....("+cantones[i]+", "+cantones[j]+")\n";
                        }else {
                            caminitos+="De ("+cantones[i]+"------>"+cantones[j]+") Irse Por....("+cantones[i]+", "+obtenerCantones(caminos[i][j])+", "+cantones[j]+")\n";
                        }
                    }
                }
            }
        }
        return "La Matriz de Caminos Más Cortos Entre Los diferentes vertices es:\n"+cadena+"\nLos diferentes caminos mas cortos entre vertices son:\n"+caminitos;
    }
    public String caminosRecursivo(int i, int k, String[][] caminAux,String caminRecorrido){
        if(caminAux[i][k].equals("")){
            return "";
        }else {
            //Recursividad al Millon
            caminRecorrido+=caminosRecursivo(i,Integer.parseInt(caminAux[i][k].toString()),caminAux,caminRecorrido)+(Integer.parseInt(caminAux[i][k].toString())+1)+", ";
            return caminRecorrido;
        }
    }

    public static String obtenerCantones(String entrada) {
        StringBuilder resultado = new StringBuilder();


        String[] posiciones = entrada.split(",\\s*");


        for (String posicion : posiciones) {
            try {

                int indice = Integer.parseInt(posicion);


                String canton = obtenerCantonPorIndice(indice);


                resultado.append(canton).append(", ");
            } catch (NumberFormatException e) {

                System.err.println("Error: La posición '" + posicion + "' no es un número válido.");
            }
        }


        if (resultado.length() > 0) {
            resultado.setLength(resultado.length() - 2);
        }

        return resultado.toString();
    }


    private static String obtenerCantonPorIndice(int indice) {

        String[] cantones = {
                "San José", "Escazú", "Desamparados", "Puriscal", "Tarrazú",
                "Aserrí", "Mora", "Goicoechea", "Santa Ana", "Alajuelita",
                "Vázquez de Coronado", "Acosta", "Tibás", "Moravia", "Montes de Oca",
                "Turrubares", "Dota", "Curridabat", "Pérez Zeledón", "León Cortés",
                "Heredia", "Barva", "Santo Domingo", "Santa Bárbara", "San Rafael",
                "San Isidro", "Belén", "Flores", "San Pablo", "Sarapiquí",
                "Alajuela", "San Ramón", "Grecia", "San Mateo", "Atenas",
                "Naranjo", "Palmares", "Poás", "Orotina", "San Carlos"
        };


        int indiceAjustado = Math.max(0, Math.min(indice, cantones.length - 1));

        return cantones[indiceAjustado];
    }

}
