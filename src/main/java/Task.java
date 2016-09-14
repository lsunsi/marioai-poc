package main;

import ch.idsia.tools.MarioAIOptions;
import burlap.behavior.singleagent.Episode;
import burlap.behavior.singleagent.learning.LearningAgent;
import ch.idsia.benchmark.mario.environments.MarioEnvironment;
import burlap.mdp.singleagent.environment.SimulatedEnvironment;

class Task {
  MarioDomain domain;
  MarioAIOptions options;
  MarioEnvironment menv;
  SimulatedEnvironment senv;

  Task(MarioAIOptions options) {
    this.options = options;
    menv = MarioEnvironment.getInstance();
    domain = new MarioDomain(menv);
    senv = new SimulatedEnvironment(
      domain, MarioSample.initialState
    );
  }

  void train(LearningAgent agent, int episodes) {
    options.setVisualization(false);
    for (int i=0; i<episodes; i++) {
      menv.reset(options);
      senv.resetEnvironment();
      Episode e = agent.runLearningEpisode(senv);
      double reward = e.rewardSequence.stream().parallel().mapToDouble(Double::doubleValue).sum();
      System.out.println(i + " " + menv.getMarioStatus() + " " + e.maxTimeStep() + " " + reward);
    }
  }

  void play(LearningAgent agent) {
    options.setVisualization(true);
    menv.reset(options);
    senv.resetEnvironment();
    agent.runLearningEpisode(senv);
  }
}