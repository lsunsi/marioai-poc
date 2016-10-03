package main;

import java.util.Random;
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

    for (;;) {
      double alpha   = random.nextDouble();
      double gamma   = random.nextDouble();
      double epsilon = random.nextDouble();
      double lambda  = random.nextDouble();

      LearningAgent[] agents = new LearningAgent[sessions];
      for (int i=0; i<sessions; i++) agents[i] = MarioFuncSarsa.generate(
        task.domain, alpha, gamma, epsilon, lambda
      );

      double benchmark = task.benchmark(agents, episodes);
      System.out.printf("%f %f %f %f %f\n", alpha, gamma, epsilon, lambda, benchmark);
    }

    // System.exit(0);
  }
}