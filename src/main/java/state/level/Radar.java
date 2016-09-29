package main;

import java.util.List;
import java.util.Arrays;
import burlap.mdp.core.state.State;
import burlap.mdp.core.oo.state.ObjectInstance;
import burlap.mdp.core.state.annotations.DeepCopyState;

@DeepCopyState
class RadarState implements State, ObjectInstance, Featurable {
  final static String className = "RadarState";
  final static int[] center = {9, 9};
  final static int size = 8;

  String name;
  int[] values;

  // Constructors

  RadarState(int offset, int thickness, byte[][] enemies, String name) {
    this.name = name;
    values = new int[size];
    for (int i=center[0]-offset-thickness; i<=center[0]+offset+thickness; i++) {
      for (int j=center[1]-offset-thickness; j<=center[1]+offset+thickness; j++) {
        int hor = ((i > center[0] + offset) ? 0 : (i < center[0] - offset) ? 1 : 2);
        int ver = ((j > center[1] + offset) ? 0 : (j < center[1] - offset) ? 1 : 2);
        if (hor + ver < 4) values[hor*3+ver] += enemies[j][i];
      }
    }
  }

  RadarState(RadarState state, String name) {
    values = state.values.clone();
    this.name = name;
  }

  // Featurable implementation

  public double[] getFeatures() {
    double[] features = new double[values.length];
    for (int i=0; i<features.length; i++) features[i] = values[i]==0?0:1;
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
    return values[(Integer)key]==0?false:true;
  }

  @Override
  public State copy() {
    return new RadarState(this, name);
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
    return new RadarState(this, name);
  }
}