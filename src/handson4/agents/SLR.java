package handson4.agents;
import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;

import java.util.Scanner;

import general.Dataset;
import general.Operations;

public class SLR extends Agent{
    public void setup() {
        addBehaviour(new runBehaviour());
        }
    }
    
    class runBehaviour extends OneShotBehaviour {
        @Override
        public void action() {
            System.out.println("Procesando dataset [h3]");
            double[][] data = new Dataset("h3").getData();
            if(data == null) {
                System.out.println("Error al cargar el dataset [no encontrado]");
                return;
            }

            System.out.println("Dataset obtenido [h3]");
            System.out.println("Iniciando calculos de matrices [SLR]");
            System.out.println("Independiente [0], dependiente [3]");

            // data x es la primera columna
            // data y es la ultima columna

            double[][] dataX = new double[data.length][1];
            double[][] dataY = new double[data.length][1];

            for(int i = 0; i < data.length; i++) {
                dataX[i][0] = data[i][0];
                dataY[i][0] = data[i][3];
            }

            // CALCULO DE SLR

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

            // Pedir X para predecir Y

            try (Scanner sc = new Scanner(System.in)) {
                System.out.println("Introduce el valor de X");
                double x1 = sc.nextDouble();

                double[][] X = new double[1][1];
                X[0][0] = x1;

                double[][] Y = op.Product(X, Result);

                System.out.println("Y = " + Y[0][0]);
            }

        }

        @Override
            public int onEnd() {
            System.out.println("Agente finalizado");
            myAgent.doDelete();
            return super.onEnd();
        }
    }