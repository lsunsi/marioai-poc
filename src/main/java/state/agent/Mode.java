package main;

import java.util.List;
import java.util.Arrays;
import burlap.mdp.core.state.State;
import burlap.mdp.core.oo.state.ObjectInstance;
import burlap.mdp.core.state.UnknownKeyException;
import burlap.mdp.core.state.annotations.DeepCopyState;

@DeepCopyState
class ModeState implements State, ObjectInstance {
  final static String[] keys = {"small", "large", "fire"};
  final static String className = "ModeState";

  String name;
  boolean small, large, fire;

  // Constructors

  ModeState(int mode, String name) {
    this.name = name;
    small = mode==0;
    large = mode==1;
    fire  = mode==2;
  }

  ModeState(ModeState state, String name) {
    this.name = name;
    small = state.small;
    large = state.large;
    fire  = state.fire;
  }

  // State implementation

  @Override
  public List<Object> variableKeys() {
    return Arrays.asList((Object[])keys);
  }

  @Override
  public Object get(Object key) {
    if (keys[0].equals(key)) return small;
    if (keys[1].equals(key)) return large;
    if (keys[2].equals(key)) return fire;    
    throw new UnknownKeyException(key);
  }

  @Override
  public State copy() {
    return new ModeState(this, name);
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
    return new ModeState(this, name);
  }
}