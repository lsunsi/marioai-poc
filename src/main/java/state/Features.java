package main;

import java.util.ArrayList;
import burlap.mdp.core.state.State;
import burlap.behavior.functionapproximation.dense.DenseStateFeatures;

class MarioFeatures implements DenseStateFeatures {
  double[] values;

  MarioFeatures() {}
  MarioFeatures(MarioFeatures state) {
    values = state.values.clone();
  }

  @Override
  public double[] features(State root) {
    ArrayList<Integer> flist = new ArrayList<>();
    for (Object k1 : root.variableKeys()) {
      State branch = (State)root.get(k1);
      for (Object k2 : branch.variableKeys()) {
        Boolean leaf = (Boolean)branch.get(k2);
        flist.add(leaf?0:1);
      }
    }
    values = flist
      .stream()
      .mapToDouble(Integer::doubleValue)
      .toArray();
    return values;
  }

  @Override
  public DenseStateFeatures copy() {
    return new MarioFeatures(this);
  }
}