package main;

import java.util.List;
import java.util.Arrays;
import java.util.stream.Collectors;
import burlap.mdp.core.state.State;
import burlap.mdp.core.oo.state.OOState;
import burlap.mdp.core.oo.state.ObjectInstance;
import burlap.mdp.core.state.UnknownKeyException;
import burlap.mdp.core.state.annotations.DeepCopyState;
import burlap.mdp.core.oo.state.exceptions.UnknownClassException;
import burlap.mdp.core.oo.state.exceptions.UnknownObjectException;

class MarioState implements OOState, State {
  final static String[] keys = {
    "mode", "direction", "pits", "radar", "enemies", "scene"
  };

  ModeState mode;
  DirectionState direction;
  PitsState pits;
  RadarState radar;
  LevelState scene;
  LevelState enemies;

  int status, size, kills;
  double x, y;

  MarioState(int status, int size, int kills, double x, double y, ModeState mode, DirectionState direction, PitsState pits, RadarState radar, LevelState scene, LevelState enemies) {
    this.status = status;
    this.size = size;
    this.kills = kills;
    this.x = x;
    this.y = y;
    this.mode = mode;
    this.direction = direction;
    this.pits = pits;
    this.radar = radar;
    this.scene = scene;
    this.enemies = enemies;
  }

  // OOState implementation

  @Override
  public int numObjects() {
    return 6;
  }

  @Override
  public ObjectInstance object(String name) {
    ObjectInstance[] values = {mode, direction, pits, radar, scene, enemies};
    for (ObjectInstance value : values) {
      if (name.equals(value.name())) return value;
    } throw new UnknownObjectException(name);
  }

  @Override
  public List<ObjectInstance> objects() {
    return Arrays.asList(mode, direction, pits, radar, scene, enemies);
  }

  @Override
  public List<ObjectInstance> objectsOfClass(String name) {
    if (name.equals(ModeState.className)) return Arrays.asList(mode);
    if (name.equals(DirectionState.className)) return Arrays.asList(direction);
    if (name.equals(PitsState.className)) return Arrays.asList(pits);
    if (name.equals(RadarState.className)) return Arrays.asList(radar);
    if (name.equals(LevelState.className)) return Arrays.asList(scene, enemies);
    throw new UnknownClassException(name);
  }

  // State implementation

  @Override
  public List<Object> variableKeys() {
    return Arrays.asList((Object[])keys);
  }

  @Override
  public Object get(Object key) {
    if (keys[0].equals(key)) return mode;
    if (keys[1].equals(key)) return direction;
    if (keys[2].equals(key)) return pits;
    if (keys[3].equals(key)) return radar;
    if (keys[4].equals(key)) return scene;
    if (keys[5].equals(key)) return enemies;
    throw new UnknownKeyException(key);
  }

  @Override
  public State copy() {
    return new MarioState(
      status, size, kills, x, y,
      (ModeState)mode.copy(),
      (DirectionState)direction.copy(),
      (PitsState)pits.copy(),
      (RadarState)radar.copy(),
      (LevelState)scene.copy(),
      (LevelState)enemies.copy()
    );
  }
}