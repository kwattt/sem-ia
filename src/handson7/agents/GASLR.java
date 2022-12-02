package handson7.agents;
import java.util.Arrays;

import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;

public class GASLR extends Agent{
    public void setup() {
        addBehaviour(new runBehaviour());
        }
    }
    
    class runBehaviour extends OneShotBehaviour {
        @Override
        public void action() {
            // Let us suppose the equation to solve is:  a + 2b - 3c + d + 4e + f = 30

            // Initialize the population
            int populationSize = 6; // We will create 6 chromosomes
            int chromosomeSize = 6; // Each chromosome will have 6 genes
            int[][] population = new int[populationSize][chromosomeSize];
            int[] fitness = new int[populationSize];
            int[] newFitness = new int[populationSize];
            int[] parents = new int[2];
            int[][] offspring = new int[2][chromosomeSize];
            int[] mutation = new int[chromosomeSize];

            // Evaluate the fitness of each individual
            int fittest = 0;
            int fitnessFittest = 0;
            int generation = 0;
            int maxGeneration = 30;
            int target = 30;

            // Repeat until a solution is found or a maximum number of generations is reached
            while (fitnessFittest != 100 && generation < maxGeneration) {
                generation++;
                // 1. Initialize the population
                for (int i = 0; i < populationSize; i++) {
                    for (int j = 0; j < chromosomeSize; j++) {
                        population[i][j] = (int) (Math.random() * 10);
                    }
                }
                // 2. Evaluate the fitness of each individual
                for (int i = 0; i < populationSize; i++) {
                    fitness[i] = fitness(population[i], target);
                }
                // 3. Repeat until a solution is found or a maximum number of generations is reached
                // 3.1 Select parents
                for (int i = 0; i < 2; i++) {
                    parents[i] = selectParents(populationSize, fitness);
                }
                // 3.2 Recombine parents to form new offspring
                offspring = recombine(parents, population);

                // 3.3 Mutate offspring
                for (int i = 0; i < 2; i++) {
                    mutation = mutate(offspring[i]);
                    offspring[i] = mutation;
                }

                // 3.4 Evaluate the fitness of each individual
                for (int i = 0; i < populationSize; i++) {
                    newFitness[i] = fitness(population[i], target);
                }

            }

            // 4. Return the best solution in current population

            for (int i = 0; i < populationSize; i++) {
                if (newFitness[i] > fitnessFittest) {
                    fittest = i;
                    fitnessFittest = newFitness[i];
                }
            }

            System.out.println("The fittest chromosome is: " + Arrays.toString(population[fittest]));
            System.out.println("The fitness of the fittest chromosome is: " + fitnessFittest);
            System.out.println("The generation is: " + generation);
            System.out.println("The fittesst chromosome number is: " + fittest);

            // sum of the fittest chromosome

            int sum = 0;
            for (int i = 0; i < chromosomeSize; i++) {
                sum += population[fittest][i];
            }

            System.out.println("The sum of the fittest chromosome is: " + sum);


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