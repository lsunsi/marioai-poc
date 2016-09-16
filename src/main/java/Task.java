package main;

import java.util.Scanner;
import ch.idsia.tools.MarioAIOptions;
import burlap.behavior.singleagent.Episode;
import burlap.behavior.singleagent.learning.LearningAgent;
import ch.idsia.benchmark.mario.environments.MarioEnvironment;
import burlap.mdp.singleagent.environment.SimulatedEnvironment;

class Task {
  int stepsize;
  MarioDomain domain;
  MarioAIOptions options;
  MarioEnvironment menv;
  SimulatedEnvironment senv;

  Task(MarioAIOptions options, int stepsize) {
    this.stepsize = stepsize;
    this.options = options;
    menv = MarioEnvironment.getInstance();
    domain = new MarioDomain(menv);
    senv = new SimulatedEnvironment(
      domain, MarioSample.initialState
    );
  }

  void play(LearningAgent agent) {
    options.setVisualization(true);
    menv.reset(options);
    senv.resetEnvironment();
    agent.runLearningEpisode(senv);
  }

  void train(LearningAgent agent, int start, int end) {
    options.setVisualization(false);
    for (int i=start; i<end; i++) {
      menv.reset(options);
      senv.resetEnvironment();
      Episode e = agent.runLearningEpisode(senv);
      double reward = e.rewardSequence.stream().parallel().mapToDouble(Double::doubleValue).sum();
      System.out.println(i + " " + menv.getMarioStatus() + " " + e.maxTimeStep() + " " + reward);
    }
  }

  void learn(LearningAgent agent, Scanner in, int init) {
    int credits = init;
    for (int i=0; credits > 0; i++) {
      train(agent, i*stepsize, (i+1)*stepsize);
      credits -= 1;
      if (credits == 0) {
        do {
          play(agent);
          credits = in.nextInt();
        } while (credits == -1);
      }
    }
  }
}