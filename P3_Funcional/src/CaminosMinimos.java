public class CaminosMinimos {
    //Metodo para determinar todos los caminos Floyd
    public String algoritmoFloyd(long [][] matrizAdj){
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
                if (matrizAdj[i][j]!=1000000000) {
                    if(i!=j){
                        if (caminos[i][j].equals("")){
                            caminitos+="De ("+(i+1)+"------>"+(j+1)+") Irse Por....("+(i+1)+", "+(j+1)+")\n";
                        }else {
                            caminitos+="De ("+(i+1)+"------>"+(j+1)+") Irse Por....("+(i+1)+", "+caminos[i][j]+", "+(j+1)+")\n";
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
}
