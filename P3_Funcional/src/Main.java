import java.util.Random;



public class Main {
    public static void main(String[] args) {


        long [][] matrizmapa=crearMatrizAleatoria();
        CaminosMinimos ruta=new CaminosMinimos();
        System.out.println(ruta.algoritmoFloyd(matrizmapa));

    }
    public static long[][] crearMatrizAleatoria() {
        Random random = new Random();

        int numerociudades = random.nextInt(11) + 30;
        System.out.println(numerociudades);
        long[][] matriz = new long[numerociudades][numerociudades];


        for (int i = 0; i < numerociudades; i++) {
            for (int j = 0; j < numerociudades; j++) {
                int haycamino= random.nextInt(10);
                if (haycamino==0){
                    matriz[i][j] = random.nextInt(10) + 1;
                }else{
                    matriz[i][j] = 999;
                }

            }
        }
        return matriz;
    }
}