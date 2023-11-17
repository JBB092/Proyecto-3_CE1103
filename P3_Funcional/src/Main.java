import java.util.Arrays;
import java.util.Random;


public class Main {
    public static void main(String[] args) {
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
        long [][] matrizmapa=crearMatrizAleatoria();
        Grafo ruta=new Grafo();
        System.out.println(Arrays.toString(ruta.ciudadescaminocorto(matrizmapa,2,4)));

    }

    /**
     * Metodo que crea un mapa aleatorio con 41 ciudades diferentes y asignando pesos de tiempo de 1 a 10 y pesos de
     * 999 cuando es infinito y 0 cuando es de la una ciudad a la misma ciudad
     * @return array de adjacencia del grado del mapa
     */
    public static long[][] crearMatrizAleatoria() {
        Random random = new Random();

        int numerociudades = 41;

        long[][] matriz = new long[numerociudades][numerociudades];


        for (int i = 0; i < numerociudades; i++) {
            for (int j = 0; j < numerociudades; j++) {
                if(i==j){
                    matriz[i][j] = 0;
                }else{
                    int haycamino= random.nextInt(4);
                    if (haycamino==0){
                        matriz[i][j] = random.nextInt(10) + 1;
                    }else{
                        matriz[i][j] = 999;
                    }
                }
            }
        }
        return matriz;
    }
}