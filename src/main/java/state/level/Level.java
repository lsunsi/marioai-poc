package main;

import java.util.List;
import java.util.Arrays;
import burlap.mdp.core.state.State;
import burlap.mdp.core.oo.state.ObjectInstance;
import burlap.mdp.core.state.annotations.DeepCopyState;

@DeepCopyState
class LevelState implements State, ObjectInstance, Featurable {
  final static String className = "LevelState";
  final static int[] center = {9, 9};

  int size;
  String name;
  boolean[] values;

  // Constructors

  LevelState(int left, int right, int up, int down, byte[][] level, String name) {
    size = (left+right+1)*(up+down+1);
    this.name = name;

    int k = 0;
    values = new boolean[size];
    for (int i=center[1]-up; i<=center[1]+down; i++) {
      for (int j=center[0]-left; j<=center[0]+right; j++) {
        values[k++] = level[i][j]==0;
      }
    }
  }

  LevelState(LevelState state, String name) {
    values = state.values.clone();
    size = state.size;
    this.name = name;
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
    return new LevelState(this, name);
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
    return new LevelState(this, name);
  }
}