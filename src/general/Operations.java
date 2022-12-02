package general;

public class Operations {

    public double[][] Transposed(double [][] matrix){
        double transposed[][] = new double[matrix[0].length][matrix.length];
        
        for(int i = 0; i < matrix[0].length; i++){
            for(int j = 0; j < matrix.length; j++){
                transposed[i][j] = matrix[j][i];
            }
        }

        return transposed;
    }

    public double[][] Product(double [][] matrix1, double [][] matrix2){
        double product[][] = new double[matrix1.length][matrix2[0].length];
        
        for(int i = 0; i < matrix1.length; i++){
            for(int j = 0; j < matrix2[0].length; j++){
                for(int k = 0; k < matrix1[0].length; k++){
                    product[i][j] += matrix1[i][k] * matrix2[k][j];
                }
            }
        }

        return product;
    }

    // Determinant of a matrix
    public double Determinant(double[][] matrix) {
        double result = 0;
        if (matrix.length == 1) {
            result = matrix[0][0];
            return result;
        }
        if (matrix.length == 2) {
            result = matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];
            return result;
        }
        for (int i = 0; i < matrix[0].length; i++) {
            double temp[][] = new double[matrix.length - 1][matrix[0].length - 1];
            for (int j = 1; j < matrix.length; j++) {
                for (int k = 0; k < matrix[0].length; k++) {
                    if (k < i) {
                        temp[j - 1][k] = matrix[j][k];
                    } else if (k > i) {
                        temp[j - 1][k - 1] = matrix[j][k];
                    }
                }
            }
            result += matrix[0][i] * Math.pow(-1, (double) i) * Determinant(temp);
        }
        return result;
    }

    // Inverse of a matrix
    public double[][] Inverse(double[][] matrix) {
        double result[][] = new double[matrix.length][matrix[0].length];
        double det = Determinant(matrix);
        if (matrix.length == 1) {
            result[0][0] = 1 / det;
            return result;
        }
        double[][] minor = new double[matrix.length - 1][matrix[0].length - 1];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                int p = 0;
                int q = 0;
                for (int k = 0; k < matrix.length; k++) {
                    for (int l = 0; l < matrix[0].length; l++) {
                        if (k != i && l != j) {
                            minor[p][q++] = matrix[k][l];
                            if (q % (matrix.length - 1) == 0) {
                                p++;
                                q = 0;
                            }
                        }
                    }
                }
                result[j][i] = Math.pow(-1, (double) i + j) * Determinant(minor);
            }
        }
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[0].length; j++) {
                result[i][j] = result[i][j] / det;
            }
        }
        return result;
    }


    // print matrix to standard output
    public void PrintMatrix(double[][] matrix) { // pretty print

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.printf("%9.4f ", matrix[i][j]);
            }
            System.out.println();
        }

    }

    // Substract
    public double[][] Substract(double[][] matrix1, double[][] matrix2) {
        double[][] result = new double[matrix1.length][matrix1[0].length];
        for (int i = 0; i < matrix1.length; i++) {
            for (int j = 0; j < matrix1[0].length; j++) {
                result[i][j] = matrix1[i][j] - matrix2[i][j];
            }
        }
        return result;
    }

    public double[][] Sum(double[][] matrix) {
        double[][] result = new double[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                result[i][j] = matrix[i][j] + matrix[i][j];
            }
        }
        return result;
    }

    public double[][] Divide(double[][] matrix, int length) {
    
        double[][] result = new double[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                result[i][j] = matrix[i][j] / length;
            }
        }
        return result;

    }

    public double[][] SquareRoot(double[][] matrix) {

        double[][] result = new double[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                result[i][j] = Math.sqrt(matrix[i][j]);
            }
        }
        return result;

    }

    public double[][] Divide(double[][] matrix1, double[][] matrix2) {

        double[][] result = new double[matrix1.length][matrix1[0].length];
        for (int i = 0; i < matrix1.length; i++) {
            for (int j = 0; j < matrix1[0].length; j++) {
                result[i][j] = matrix1[i][j] / matrix2[i][j];
            }
        }
        return result;


    }

    public double[][] Scalar(double[][] matrix, double i) {
        
        double[][] result = new double[matrix.length][matrix[0].length];
        for (int j = 0; j < matrix.length; j++) {
            for (int k = 0; k < matrix[0].length; k++) {
                result[j][k] = matrix[j][k] * i;
            }
        }
        return result;

    
    }

    public double[][] Power(double[][] matrix, int i) {


        double[][] result = new double[matrix.length][matrix[0].length];
        for (int j = 0; j < matrix.length; j++) {
            for (int k = 0; k < matrix[0].length; k++) {
                result[j][k] = Math.pow(matrix[j][k], i);
            }
        }
        return result;


    }

    public Object multiply(double[][] dataX, double a) {

        double[][] result = new double[dataX.length][dataX[0].length];
        for (int i = 0; i < dataX.length; i++) {
            for (int j = 0; j < dataX[0].length; j++) {
                result[i][j] = dataX[i][j] * a;
            }
        }
        return result;

    }

    public double[][] multiply(double[][] dataX, double[][] dataY) {

        double[][] result = new double[dataX.length][dataY[0].length];
        for (int i = 0; i < dataX.length; i++) {
            for (int j = 0; j < dataY[0].length; j++) {
                for (int k = 0; k < dataY.length; k++) {
                    result[i][j] += dataX[i][k] * dataY[k][j];
                }
            }
        }
        return result;

    }

    public double[][] add(Object multiply, double b) {
        return null;
    }

    public double[][] Join(double[][] dataX, double[][] dataY) {
            
            double[][] result = new double[dataX.length][dataX[0].length + dataY[0].length];
            for (int i = 0; i < dataX.length; i++) {
                for (int j = 0; j < dataX[0].length; j++) {
                    result[i][j] = dataX[i][j];
                }
            }
            for (int i = 0; i < dataY.length; i++) {
                for (int j = 0; j < dataY[0].length; j++) {
                    result[i][j + dataX[0].length] = dataY[i][j];
                }
            }
            return result;
    
    }

    public double[][] Product(double[][] xTransposedX, double[] ds) {

        double[][] result = new double[xTransposedX.length][ds.length];
        for (int i = 0; i < xTransposedX.length; i++) {
            for (int j = 0; j < ds.length; j++) {
                result[i][j] = xTransposedX[i][j] * ds[j];
            }
        }
        return result;

    }

    public static double dotProduct(double[] ds, double[] x) {

        double result = 0;
        for (int i = 0; i < ds.length; i++) {
            result += ds[i] * x[i];
        }
        return result;

    }

    public static double randomDouble(int i, int j) {

        return Math.random() * (j - i) + i;

    }


}