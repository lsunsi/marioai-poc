package main;

import java.util.Scanner;
import ch.idsia.tools.MarioAIOptions;
import burlap.behavior.singleagent.learning.LearningAgent;

class Main {
  public static void main(String[] args) {
    MarioAIOptions options = new MarioAIOptions();
    options.setLevelDifficulty(3);
    options.setLevelRandSeed(123);
    options.setLevelLength(100);
    options.setArgs("-lca off");
    options.setArgs("-lco off");
    options.setArgs("-ltb off");
    options.setEnemies("off");
    options.setMarioMode(1);

    int stepsize = 1000;
    Scanner in = new Scanner(System.in);
    Task task = new Task(options, stepsize);;

    double alpha   = .01;
    double gamma   = .99;
    double epsilon = .01;
    double lambda  = .99;
    double weight  = .05;

    LearningAgent agent1 = MarioTableSarsa.generate(task.domain, alpha, gamma, epsilon, lambda);
    LearningAgent  agent2 = MarioFuncSarsa.generate(task.domain, alpha, gamma, epsilon, lambda, weight);    

    task.learn(agent2, in, 1);

    System.exit(0);
  }
}