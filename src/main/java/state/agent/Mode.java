package main;

import java.util.List;
import java.util.Arrays;
import burlap.mdp.core.state.State;
import burlap.mdp.core.oo.state.ObjectInstance;
import burlap.mdp.core.state.UnknownKeyException;
import burlap.mdp.core.state.annotations.DeepCopyState;

@DeepCopyState
class ModeState implements State, ObjectInstance {
  final static String[] keys = {"mode"};
  final static String className = "ModeState";

  String name;
  int mode;

  // Constructors

  ModeState(int mode, String name) {
    this.mode = mode;
    this.name = name;
  }

  // State implementation

  @Override
  public List<Object> variableKeys() {
    return Arrays.asList((Object[])keys);
  }

  @Override
  public Object get(Object key) {
    if (keys[0].equals(key)) return mode;
    throw new UnknownKeyException(key);
  }

  @Override
  public State copy() {
    return new ModeState(mode, name);
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
    return new ModeState(mode, name);
  }
}