package main;

import ch.idsia.tools.MarioAIOptions;

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

    Task task = new Task(options);

    double alpha   = .01;
    double gamma   = .99;
    double epsilon = .01;
    double lambda  = .99;

    MarioTableSarsa agent = new MarioTableSarsa(task.domain, alpha, gamma, epsilon, lambda);
    task.train(agent, 1000);
    task.play(agent);
  }
}