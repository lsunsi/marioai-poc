package main;

import burlap.behavior.policy.EpsilonGreedy;
import burlap.statehashing.simple.SimpleHashableStateFactory;
import burlap.behavior.singleagent.learning.tdmethods.SarsaLam;

class MarioTableSarsa extends SarsaLam {
  MarioTableSarsa(MarioDomain domain, double alpha, double gamma, double epsilon, double lambda) {
    super(
      domain, gamma,
      new SimpleHashableStateFactory(),
      0.0, alpha,
      new EpsilonGreedy(epsilon),
      9999, lambda
    );
    ((EpsilonGreedy)learningPolicy).setSolver(this);
  }
}
