package handson4.agents;
import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;

import general.Dataset;

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

        }

        @Override
            public int onEnd() {
            System.out.println("\n-Agente muriendo-");
            myAgent.doDelete();
            return super.onEnd();
        }
    }