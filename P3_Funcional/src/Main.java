public class Main {
    public static void main(String[] args) {
        long [][] matrizmapa={{0,4,5,999999999,999999999},
                {999999999,0,3,6,3},
                {3,999999999,0,999999999,999999999},
                {5,2,999999999,0,999999999},
                {999999999,1,999999999,999999999,0}};
        CaminosMinimos ruta=new CaminosMinimos();
        System.out.println(ruta.algoritmoFloyd(matrizmapa));
    }
}