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

            // CALCULO DE GRADIENTE DESCENDIENTE (GD)

    }

        @Override
            public int onEnd() {
            System.out.println("Agente terminado");
            myAgent.doDelete();
            return super.onEnd();
        }
    }