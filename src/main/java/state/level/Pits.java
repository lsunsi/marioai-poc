package main;

import java.util.List;
import java.util.Arrays;
import burlap.mdp.core.state.State;
import burlap.mdp.core.oo.state.ObjectInstance;
import burlap.mdp.core.state.annotations.DeepCopyState;

class PitsState implements State, ObjectInstance, Featurable {
  final static String className = "PitsState";
  final static int[] center = {9, 9};
  final static int bottom = 18;

  int size;
  String name;
  boolean[] values;

  // Constructors

  PitsState(int back, int front, byte[][] scene, String name) {
    this.name = name;
    size = (back+front+1);
    values = new boolean[size];
    int k = 0;
    for (int i=center[0]-back; i<=center[0]+front; i++) {
      boolean pit = true;
      for (int j=center[1]+1; pit && j<=bottom; j++) {
        if (scene[j][i] != 0) pit = false;
      } values[k++] = pit;
    }
  }

  PitsState(PitsState state, String name) {
    this.name = name;
    size = state.size;
    values = state.values.clone();
  }

  // Featurable implementation

  public double[] getFeatures() {
    double[] features = new double[values.length];
    for (int i=0; i<features.length; i++) features[i] = values[i]?0:1;
    return features;
  }

  // State implementation

  @Override
  public List<Object> variableKeys() {
    Integer[] keys = new Integer[size];
    for (int i=0; i<size; i++) keys[i] = i;
    return Arrays.asList((Object[])keys);
  }

  @Override
  public Object get(Object key) {
    return values[(Integer)key];
  }

  @Override
  public State copy() {
    return new PitsState(this, name);
  }

  // ObjectInstance implementation

  @Override
  public String name() {
    return name;
  }

  @Override
  public String className() {
    return className;
  }

  @Override
  public ObjectInstance copyWithName(String name) {
    return new PitsState(this, name);
  }
}