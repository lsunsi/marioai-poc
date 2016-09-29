package main;

import java.util.ArrayList;
import burlap.mdp.core.state.State;
import burlap.behavior.functionapproximation.dense.DenseStateFeatures;

interface Featurable {
  double[] getFeatures();
}

class MarioFeatures implements DenseStateFeatures {
  static int size;
  static {
    for (Object f : MarioSample.initialState.objects()) {
      size += ((Featurable)f).getFeatures().length;
    }
  }

  double[] values;

  MarioFeatures() {}
  MarioFeatures(MarioFeatures state) {
    values = state.values.clone();
  }

  @Override
  public double[] features(State root) {
    values = new double[size];

    int i = 0;
    for (Object o : ((MarioState)root).objects()) {
      for (double f : ((Featurable)o).getFeatures()) {
        values[i++] = f;
      }
    }

    return values;
  }

  @Override
  public DenseStateFeatures copy() {
    return new MarioFeatures(this);
  }
}