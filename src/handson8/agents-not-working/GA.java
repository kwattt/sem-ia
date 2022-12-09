package handsons7.agents;
import java.util.Arrays;

import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;

public class GA extends Agent{
    public void setup() {
        addBehaviour(new runBehaviour());
        }
    }
    
    class runBehaviour extends OneShotBehaviour {
        @Override
        public void action() {
            
        }

        private int[] mutate(int[] is) {

            int[] mutation = new int[is.length];
            int mutationPoint = (int) (Math.random() * is.length);
            int mutationValue = (int) (Math.random() * 10);
            for (int i = 0; i < is.length; i++) {
                if (i == mutationPoint) {
                    mutation[i] = mutationValue;
                } else {
                    mutation[i] = is[i];
                }
            }
            return mutation;
        }

        private int[][] recombine(int[] parents, int[][] population) {
            
            int[][] offspring = new int[2][population[0].length];
            int crossoverPoint = (int) (Math.random() * population[0].length);
            for (int i = 0; i < population[0].length; i++) {
                if (i < crossoverPoint) {
                    offspring[0][i] = population[parents[0]][i];
                    offspring[1][i] = population[parents[1]][i];
                } else {
                    offspring[0][i] = population[parents[1]][i];
                    offspring[1][i] = population[parents[0]][i];
                }
            }
            return offspring;

        }

        private int selectParents(int populationSize, int[] fitness) {

            // Select parents
            int parent = (int) (Math.random() * populationSize);
            int parent2 = (int) (Math.random() * populationSize);
            if (fitness[parent] > fitness[parent2]) {
                return parent;
            } else {
                return parent2;
            }
        }

        private int fitness(int[] chromosome, int target) {

            int sum = 0;
            for (int i = 0; i < chromosome.length; i++) {
                sum += chromosome[i];
            }
            int fitness = (int) (100 * (1 - Math.abs((double) (sum - target) / target)));
            return fitness;

        }

        @Override
            public int onEnd() {
            System.out.println("Agente terminado");
            myAgent.doDelete();
            return super.onEnd();
        }
    }