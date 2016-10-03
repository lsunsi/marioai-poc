package main;

import burlap.behavior.singleagent.learning.LearningAgent;

interface MarioAgentGenerator {
  LearningAgent generate();
}

abstract class BaseMarioAgentGenerator implements MarioAgentGenerator {
  MarioDomain domain;
  double alpha, gamma, epsilon, lambda;

  BaseMarioAgentGenerator(MarioDomain domain, double alpha, double gamma, double epsilon, double lambda) {
    this.domain = domain;
    this.alpha = alpha;
    this.gamma = gamma;
    this.epsilon = epsilon;
    this.lambda = lambda;
  }

  @Override
  public abstract LearningAgent generate();
}