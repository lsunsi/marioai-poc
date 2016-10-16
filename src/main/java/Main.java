package main;

import java.util.Random;
import java.util.Arrays;
import ch.idsia.tools.MarioAIOptions;
import burlap.behavior.singleagent.learning.LearningAgent;

class Main {
  public static void main(String[] args) {
    MarioAIOptions options = new MarioAIOptions();
    options.setLevelDifficulty(2);
    options.setLevelRandSeed(123);
    options.setLevelLength(50);
    options.setArgs("-lca off");
    options.setArgs("-lco off");
    options.setArgs("-ltb off");
    options.setEnemies("g,rk");
    options.setMarioMode(1);

    Random random = new Random();
    Task task = new Task(options);

    int sessions = 3;
    int episodes = 1000;

    ParameterOptimizer po = new GridParameterOptimizer(
      new ParameterContainer(.05, .85, .00, .65),
      new ParameterContainer(.15, .95, .05, .75),
      new ParameterContainer(.01, .01, .01, .01),
      pc -> {
        pc.fitness = task.benchmark(new MarioFuncSarsaGenerator(
          task.domain, pc.alpha, pc.gamma, pc.epsilon, pc.lambda
        ), sessions, episodes);
        System.out.println(pc);
      }
    );

    while (!po.done()) po.iterate();

    System.exit(0);
  }
}