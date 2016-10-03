package main;

import burlap.behavior.policy.EpsilonGreedy;
import burlap.behavior.functionapproximation.sparse.LinearVFA;
import burlap.behavior.functionapproximation.sparse.tilecoding.TilingArrangement;
import burlap.behavior.functionapproximation.sparse.tilecoding.TileCodingFeatures;
import burlap.behavior.singleagent.learning.tdmethods.vfa.GradientDescentSarsaLam;

class MarioFuncSarsa {
  final static GradientDescentSarsaLam generate(MarioDomain domain, double alpha, double gamma, double epsilon, double lambda) {
    TileCodingFeatures tiling = new TileCodingFeatures(new MarioFeatures());
    double[] features = new MarioFeatures().features(MarioSample.initialState);
    for (int i=0; i<features.length; i++) features[i] = 0.5;
    tiling.addTilingsForAllDimensionsWithWidths(features, 2, TilingArrangement.RANDOM_JITTER);
    LinearVFA vfa = tiling.generateVFA(0.5);
    EpsilonGreedy policy = new EpsilonGreedy(epsilon);
    GradientDescentSarsaLam agent = new GradientDescentSarsaLam(domain, gamma, vfa, alpha, policy, 9999, lambda);
    policy.setSolver(agent);
    return agent;
  }
}
