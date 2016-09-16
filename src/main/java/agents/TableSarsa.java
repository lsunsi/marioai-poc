package main;

import burlap.behavior.policy.EpsilonGreedy;
import burlap.statehashing.simple.SimpleHashableStateFactory;
import burlap.behavior.singleagent.learning.tdmethods.SarsaLam;

class MarioTableSarsa {
  final static SarsaLam generate(MarioDomain domain, double alpha, double gamma, double epsilon, double lambda) {
    SimpleHashableStateFactory factory = new SimpleHashableStateFactory();
    EpsilonGreedy policy = new EpsilonGreedy(epsilon);
    SarsaLam agent = new SarsaLam(domain, gamma, factory, 0.0, alpha, policy, 9999, lambda);
    policy.setSolver(agent);
    return agent;
  }
}
