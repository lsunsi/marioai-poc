package main;

import java.util.List;
import java.util.Arrays;
import burlap.mdp.core.state.State;
import burlap.mdp.core.oo.state.ObjectInstance;
import burlap.mdp.core.state.UnknownKeyException;
import burlap.mdp.core.state.annotations.DeepCopyState;

@DeepCopyState
class DirectionState implements State, ObjectInstance {
  final static String[] keys = {"dxpos", "dxneg", "dypos", "dyneg"};  
  final static String className = "DirectionState";

  String name;
  boolean dxpos, dxneg;
  boolean dypos, dyneg;

  // Constructors

  DirectionState(double tolerance, double x0, double y0, double x1, double y1, String name) {
    this.name = name;
    double dx = x1-x0, dy = y1-y0;
    dxpos = dx > +tolerance;
    dxneg = dx < -tolerance;
    dypos = dy > +tolerance;
    dyneg = dy < -tolerance;
  }

  DirectionState(DirectionState state, String name) {
    this.name = name;
    dxpos = state.dxpos;
    dxneg = state.dxneg;
    dypos = state.dypos;
    dyneg = state.dyneg;    
  }

  // State implementation

  @Override
  public List<Object> variableKeys() {
    return Arrays.asList((Object[])keys);
  }

  @Override
  public Object get(Object key) {
    if (keys[0].equals(key)) return dxpos;
    if (keys[1].equals(key)) return dxneg;
    if (keys[2].equals(key)) return dypos;
    if (keys[3].equals(key)) return dyneg;   
    throw new UnknownKeyException(key);
  }

  @Override
  public State copy() {
    return new DirectionState(this, name);
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
    return new DirectionState(this, name);
  }
}