package handson6.agents;
import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;

import java.util.Scanner;

import general.Dataset;
import general.Operations;

public class GD extends Agent{
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

            double[][] dataY = new Dataset("h3").getJoinedDataY();
            if(dataY == null) {
                System.out.println("Error al cargar el dataset [no encontrado]");
                return;
            }

            System.out.println("Dataset obtenido [h3]");

            // data x es la primera columna
            // data y es la ultima columna


            // CALCULO DE GRADIENTE DESCENDIENTE (GD)

            // 1. valores iniciales de theta

            double[][] theta = new double[2][1];
            theta[0][0] = 0;
            theta[1][0] = 0;
            
            // 2. valores iniciales de alpha y numero de iteraciones

            double alpha = 0.01;
            int iterations = 1500;

            // 3. calcular el costo

            double cost = 0;
            double[][] hypothesis = new double[dataY.length][1];
            for(int i = 0; i < dataY.length; i++) {
                hypothesis[i][0] = theta[0][0] + theta[1][0] * dataX[i][0];
                cost += (hypothesis[i][0] - dataY[i][0]) * (hypothesis[i][0] - dataY[i][0]);
            }
            cost = cost / (2 * dataY.length);
            System.out.println("Costo inicial: " + cost);

            // 4. iterar

            for(int i = 0; i < iterations; i++) {
                // 4.1. calcular la hipotesis

                for(int j = 0; j < dataY.length; j++) {
                    hypothesis[j][0] = theta[0][0] + theta[1][0] * dataX[j][0];
                }

                // 4.2. calcular el error

                double[][] error = new double[dataY.length][1];
                for(int j = 0; j < dataY.length; j++) {
                    error[j][0] = hypothesis[j][0] - dataY[j][0];
                }

                // 4.3. calcular el gradiente

                double[][] gradient = new double[2][1];
                for(int j = 0; j < dataY.length; j++) {
                    gradient[0][0] += error[j][0];
                    gradient[1][0] += error[j][0] * dataX[j][0];
                }
                gradient[0][0] = gradient[0][0] / dataY.length;
                gradient[1][0] = gradient[1][0] / dataY.length;

                // 4.4. actualizar theta

                theta[0][0] = theta[0][0] - alpha * gradient[0][0];
                theta[1][0] = theta[1][0] - alpha * gradient[1][0];

                // 4.5. calcular el costo

                cost = 0;
                for(int j = 0; j < dataY.length; j++) {
                    hypothesis[j][0] = theta[0][0] + theta[1][0] * dataX[j][0];
                    cost += (hypothesis[j][0] - dataY[j][0]) * (hypothesis[j][0] - dataY[j][0]);
                }

                cost = cost / (2 * dataY.length);
                System.out.println("Costo iteracion " + i + ": " + cost);
        }

        // 5. mostrar theta

        System.out.println("Theta: " + theta[0][0] + " " + theta[1][0]);

        // 6. mostrar hipotesis

        
        System.out.println("Hipotesis: " + theta[0][0] + " + " + theta[1][0] + "x");

        // 7. mostrar grafica

        // 7.1. obtener valores de x

        double[][] x = new double[dataX.length][1];
        for(int i = 0; i < dataX.length; i++) {
            x[i][0] = dataX[i][0];
        }

        // 7.2. obtener valores de y

        double[][] y = new double[dataY.length][1];
        for(int i = 0; i < dataY.length; i++) {
            y[i][0] = dataY[i][0];
        }

        // 7.3. obtener valores de y estimados

        double[][] yEstimados = new double[dataY.length][1];
        for(int i = 0; i < dataY.length; i++) {
            yEstimados[i][0] = theta[0][0] + theta[1][0] * dataX[i][0];
        }


        // 8. mostrar prediccion

        System.out.println("Prediccion: " + theta[0][0] + " + " + theta[1][0] + " * 3.5 = " + (theta[0][0] + theta[1][0] * 3.5));

        System.out.println("Prediccion: " + theta[0][0] + " + " + theta[1][0] + " * 7 = " + (theta[0][0] + theta[1][0] * 7));

        // 9. mostrar error

        double error = 0;

        for(int i = 0; i < dataY.length; i++) {
            error += (yEstimados[i][0] - y[i][0]) * (yEstimados[i][0] - y[i][0]);
        }

        error = error / (2 * dataY.length);

        System.out.println("Error: " + error);


    }

        @Override
            public int onEnd() {
            System.out.println("Agente terminado");
            myAgent.doDelete();
            return super.onEnd();
        }
    }