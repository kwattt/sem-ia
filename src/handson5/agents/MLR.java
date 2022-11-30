package handson5.agents;
import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;

import java.util.Scanner;

import general.Dataset;
import general.Operations;

public class MLR extends Agent{
    public void setup() {
        addBehaviour(new runBehaviour());
        }
    }
    
    class runBehaviour extends OneShotBehaviour {
        @Override
        public void action() {
            System.out.println("Procesando dataset [h3]");

            double[][] dataX = new Dataset("h3").getJoinedDataX();
            if(dataX == null) {
                System.out.println("Error al cargar el dataset [no encontrado]");
                return;
            }
            double [][] dataY = new Dataset("h3").getJoinedDataY();

            System.out.println("Dataset obtenido [h3]");
            System.out.println("Iniciando calculos de matrices [MLR]");
            System.out.println("Independiente [0,1,2], dependiente [3]");

            // CALCULO DE MLR

            // 1. Calcular la matriz de transpuesta de X
            Operations op = new Operations();
            double[][] Transposed = op.Transposed(dataX);

            // 2. Calcular la matriz de producto de la transpuesta de X por X
            double[][] Product = op.Product(Transposed, dataX);

            // 3. Obtener la inverza del Producto
            double[][] Inverse = op.Inverse(Product);

            // 4. Obtener el producto de la transpuesta y datosY
            double[][] Product2 = op.Product(Transposed, dataY);

            // 5. Obtener el producto de la inversa y el producto anterior
            double[][] Result = op.Product(Inverse, Product2);

            // 6. Imprimir el resultado
            System.out.println("Resultado: ");
            op.PrintMatrix(Result);

            // pedir valores para predecir

            try (Scanner sc = new Scanner(System.in)) {
                System.out.println("Introduce el valor de X1");
                double x1 = sc.nextDouble();
                System.out.println("Introduce el valor de X2");
                double x2 = sc.nextDouble();
                System.out.println("Introduce el valor de X3");
                double x3 = sc.nextDouble();

                // 7. Calcular el valor de Y con los valores de X

                double[][] X = new double[1][3];
                X[0][0] = x1;
                X[0][1] = x2;
                X[0][2] = x3;

                double[][] Y = op.Product(X, Result);

                System.out.println("El valor de Y es: " + Y[0][0]);
            }
        }

        @Override
            public int onEnd() {
            System.out.println("Agente terminado");
            myAgent.doDelete();
            return super.onEnd();
        }
    }