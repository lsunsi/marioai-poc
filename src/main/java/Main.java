package main;

import java.util.Scanner;
import ch.idsia.tools.MarioAIOptions;
import burlap.behavior.singleagent.learning.LearningAgent;

class Main {
  public static void main(String[] args) {
    MarioAIOptions options = new MarioAIOptions();
    options.setLevelDifficulty(1);
    options.setLevelRandSeed(7);
    options.setLevelLength(100);
    options.setArgs("-lca off");
    options.setArgs("-lco off");
    options.setArgs("-ltb off");
    options.setEnemies("g,rk");
    options.setMarioMode(1);

    int stepsize = 10000;
    Scanner in = new Scanner(System.in);
    Task task = new Task(options, stepsize);;

    double alpha   = .01;
    double gamma   = .99;
    double epsilon = .01;
    double lambda  = .99;
    double weight  = .50;

    LearningAgent agent = MarioFuncSarsa.generate(
      task.domain, alpha, gamma, epsilon, lambda, weight
    );

    task.learn(agent, in, 1);

    System.exit(0);
  }
}