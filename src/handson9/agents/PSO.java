package handson9.agents;
import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;

import java.util.Scanner;

import general.Dataset;
import general.Operations;

public class PSO extends Agent{
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

            // dataX = primera columna
            // dataY = ultima columna

            double[][] dataX = new double[data.length][1];
            double[][] dataY = new double[data.length][1];

            for(int i = 0; i < data.length; i++) {
                dataX[i][0] = data[i][0]; // dividimos entre mil para reducir los numeros altisimos
                dataY[i][0] = data[i][3]; // dividimos entre mil para reducir los numeros altisimos
            }

            // CALCULO DE PARTICLE SWARM OPTIMIZATION (PSO)
            
            //     vᵢ(t + 1) = ωvᵢ(t) + c1r1(y(t) − xᵢ(t)) + c2r2(z(t) − xᵢ(t))

            //     xᵢ(t + 1) = xᵢ(t) + vᵢ(t + 1)

            // xᵢ(t) = posición de la partícula i en el instante t
            // vᵢ(t) = velocidad de la partícula i en el instante t
            // y(t) = posición de la mejor partícula  
            // z(t) = posición de la mejor partícula global
            // ω = factor de inercia
            // c1 = constante de aceleración cognitiva
            // c2 = constante de aceleración social

            // xᵢ(t + 1) = posición de la partícula i en el instante t + 1
            // vᵢ(t + 1) = velocidad de la partícula i en el instante t + 1
            // r1 = número aleatorio entre 0 y 1
            // r2 = número aleatorio entre 0 y 1

            // y(t) = posición de la mejor partícula en el instante t
            // z(t) = posición de la mejor partícula global en el instante t


            // 1. Inicializar las partículas con valores aleatorios
            // 2. Calcular el valor de la función objetivo para cada partícula
            // 3. Asignar la mejor posición de cada partícula
            // 4. Asignar la mejor posición global
            // 5. Actualizar la velocidad y la posición de cada partícula
            // 6. Repetir los pasos 2 a 5 hasta que se cumpla una condición de parada

            // 1. Inicializar las partículas con valores aleatorios

            int N = 20; // Número de partículas
            int D = 3; // Número de dimensiones 

            double[][] X = new double[N][D]; // Posición de la partícula
            double[][] V = new double[N][D]; // Velocidad de la partícula
            double[][] Y = new double[N][D]; // Mejor posición de la partícula
            double[][] Z = new double[1][D]; // Mejor posición global

            double[][] TempX = new double[N][D]; // Posición de la partícula temporal
            double[][] TempV = new double[N][D]; // Velocidad de la partícula temporal

            double[] F = new double[N]; // Valor de la función objetivo para cada partícula

            double[] R1 = new double[N]; // Números aleatorios entre 0 y 1
            double[] R2 = new double[N]; // Números aleatorios entre 0 y 1

            double w = 0.65; // Factor de inercia
            double c1 = 1.4; // Constante de 1
            double c2 = 1.3; // Constante de 2

            for(int i = 0; i < N; i++) {
                for(int j = 0; j < D; j++) {
                    X[i][j] = Math.random();
                    V[i][j] = Math.random();
                    Y[i][j] = X[i][j];
                }
            }

            // 2. Calcular el valor de la función objetivo para cada partícula

            for(int i = 0; i < N; i++) {
                F[i] = FuncionObjetivo(dataX, dataY, X[i]);
            }

            // 3. Asignar la mejor posición de cada partícula

            for(int i = 0; i < N; i++) {
                for(int j = 0; j < D; j++) {
                    Y[i][j] = X[i][j];
                }
            }

            // 4. Asignar la mejor posición global

            double Fmin = F[0];
            int FminIndex = 0;

            for(int i = 0; i < N; i++) {
                if(F[i] < Fmin) {
                    Fmin = F[i];
                    FminIndex = i;
                }
            }

            for(int j = 0; j < D; j++) {
                Z[0][j] = X[FminIndex][j];
            }

            // 5. Actualizar la velocidad y la posición de cada partícula

            for(int i = 0; i < N; i++) {
                for(int j = 0; j < D; j++) {
                    R1[i] = Math.random();
                    R2[i] = Math.random();
                }
            }

            for(int i = 0; i < N; i++) {
                for(int j = 0; j < D; j++) {
                    TempV[i][j] = w * V[i][j] + c1 * R1[i] * (Y[i][j] - X[i][j]) + c2 * R2[i] * (Z[0][j] - X[i][j]);
                    TempX[i][j] = X[i][j] + TempV[i][j];
                }
            }

            for(int i = 0; i < N; i++) {
                for(int j = 0; j < D; j++) {
                    X[i][j] = TempX[i][j];
                    V[i][j] = TempV[i][j];
                }
            }

            // 6. Repetir los pasos 2 a 5 hasta que se cumpla una condición de parada

            // 1000 iteraciones
            int iterations = 0;
            while(iterations < 10000){
                
                // 2. Calcular el valor de la función objetivo para cada partícula

                for(int i = 0; i < N; i++) {
                    F[i] = FuncionObjetivo(dataX, dataY, X[i]);
                }

                // 3. Asignar la mejor posición de cada partícula

                for(int i = 0; i < N; i++) {
                    if(F[i] < FuncionObjetivo(dataX, dataY, Y[i])) {
                        for(int j = 0; j < D; j++) {
                            Y[i][j] = X[i][j];
                        }
                    }
                }

                // 4. Asignar la mejor posición global

                Fmin = F[0];
                FminIndex = 0;

                for(int i = 0; i < N; i++) {
                    if(F[i] < Fmin) {
                        Fmin = F[i];
                        FminIndex = i;
                    }
                }

                for(int j = 0; j < D; j++) {
                    Z[0][j] = X[FminIndex][j];
                }

                // 5. Actualizar la velocidad y la posición de cada partícula

                for(int i = 0; i < N; i++) {
                    for(int j = 0; j < D; j++) {
                        R1[i] = Math.random();
                        R2[i] = Math.random();
                    }
                }

                for(int i = 0; i < N; i++) {
                    for(int j = 0; j < D; j++) {
                        TempV[i][j] = w * V[i][j] + c1 * R1[i] * (Y[i][j] - X[i][j]) + c2 * R2[i] * (Z[0][j] - X[i][j]);
                        TempX[i][j] = X[i][j] + TempV[i][j];
                    }
                }

                for(int i = 0; i < N; i++) {
                    for(int j = 0; j < D; j++) {
                        X[i][j] = TempX[i][j];
                        V[i][j] = TempV[i][j];
                    }
                }

                iterations++;

            }

            // Mostrar mejor posición global

            System.out.println("Mejor posición global: ");
            for(int j = 0; j < D; j++) {
                System.out.println(Z[0][j]);
            }

            System.out.println("Mejor valor de la función objetivo: " + FuncionObjetivo(dataX, dataY, Z[0]));


            // Solicitar valor de X Para predecir Y

            while(true){
                // Solicitar valor de X
                // si es 0 break

                System.out.println("Ingrese valor de X para predecir Y: ");
                Scanner sc = new Scanner(System.in);
                double x = sc.nextDouble();

                if(x == 0) break;
                
                double y = 0;

                for(int j = 0; j < D; j++) {
                    y += Z[0][j] * Math.pow(x, j);
                }

                System.out.println("Y = " + y*0.001);
            }

        }

        private double FuncionObjetivo(double[][] dataX, double[][] dataY, double[] ds) {

            double[] y = new double[dataX.length];

            for(int i = 0; i < dataX.length; i++) {
                for(int j = 0; j < dataX[0].length; j++) {
                    y[i] += dataX[i][j] * ds[j];
                }
            }

            double sse = 0;

            for(int i = 0; i < dataX.length; i++) {
                sse += Math.pow(dataY[i][0] - y[i], 2);
            }

            return sse;
        }

        @Override
            public int onEnd() {
            System.out.println("Agente finalizado");
            myAgent.doDelete();
            return super.onEnd();
        }
    }