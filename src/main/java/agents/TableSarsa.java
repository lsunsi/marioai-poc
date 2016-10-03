package main;

import burlap.behavior.policy.EpsilonGreedy;
import burlap.behavior.singleagent.learning.LearningAgent;
import burlap.statehashing.simple.SimpleHashableStateFactory;
import burlap.behavior.singleagent.learning.tdmethods.SarsaLam;

class MarioTableSarsaGenerator extends BaseMarioAgentGenerator {

  MarioTableSarsaGenerator(MarioDomain domain, double alpha, double gamma, double epsilon, double lambda) {
    super(domain, alpha, gamma, epsilon, lambda);
  }

  @Override
  public LearningAgent generate() {
    SimpleHashableStateFactory factory = new SimpleHashableStateFactory();
    EpsilonGreedy policy = new EpsilonGreedy(epsilon);
    SarsaLam agent = new SarsaLam(domain, gamma, factory, 0.0, alpha, policy, 9999, lambda);
    policy.setSolver(agent);
    return agent;
  }
}
