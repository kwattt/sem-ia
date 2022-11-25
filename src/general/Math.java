package general;

public class Math {
    public double sumax(int[][] matrix){

        double sumatoriax = 0;
        for(int i=0;i<9;++i){
            sumatoriax+=matrix[i][0];
        }
        return sumatoriax;

    }public double sumay(int[][] matrix){
        double sumatoriay = 0;
        for(int i=0;i<9;++i){
                sumatoriay+=matrix[i][1];
        }
        return sumatoriay;
    }public double sumaXY(int[][] matrix){
        double sumatoriaxy = 0;
        for(int i=0;i<9;++i){
            sumatoriaxy+=matrix[i][0]*matrix[i][1];
        }
        return sumatoriaxy;
    }
    public double sumaxcuad(int[][] matrix){
        double sumatoriaxcuadrada=0;
        for(int i=0;i<9;++i){
            sumatoriaxcuadrada+=(matrix[i][0]*matrix[i][0]);
        }
        return sumatoriaxcuadrada;
    }
    public double ds(int[][] as){

        double DS=0;
        DS=((9*sumaxcuad(as))-(sumax(as)*sumax(as)));
        return DS;
    }

    public double db0(int[][] as){

        double Db0=0;
        Db0=((9*sumaXY(as))-(sumax(as)*sumay(as)));
        return Db0;
    }
    public double b0(int[][] as){

        double B0=0;
        B0=(sumay(as)-(b1(as)*sumax(as)))/9;
        return B0;
    }
    public double b1(int[][] as){

        double B1=0;
        B1=db0(as)/ds(as);
        return B1;
    }
    public double yhat (int[][] as,int valor){
        double Yhat = 0;
        Yhat=(b0(as)+(b1(as)*valor));
        return Yhat;
    }
}