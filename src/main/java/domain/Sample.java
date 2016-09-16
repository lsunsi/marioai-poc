package main;

import burlap.mdp.core.state.State;
import burlap.mdp.core.action.Action;
import ch.idsia.benchmark.mario.environments.MarioEnvironment;
import burlap.mdp.singleagent.model.statemodel.SampleStateModel;

class MarioSample implements SampleStateModel {
  MarioEnvironment environment;
  MarioSample(MarioEnvironment env) {
    environment = env;
  }

  @Override
  public State sample(State s, Action a) {
    return sample((MarioState)s, (MarioAction)a);
  }

  MarioState sample(MarioState s, MarioAction a) {
    environment.performAction(a.raw);
    environment.tick();

    int status = environment.getMarioStatus();
    int kills = environment.getKillsTotal();
    int mode = environment.getMarioMode();
    float[] pos = environment.getMarioFloatPos();
    byte[][] scene = environment.getLevelSceneObservationZ(2);
    byte[][] enemies = environment.getEnemiesObservationZ(2);

    return new MarioState(
      status, mode, kills,
      pos[0], pos[1],
      new ModeState(mode, "mode"),
      new DirectionState(0, s.x, s.y, pos[0], pos[1], "dir"),
      new PitsState(2, 2, scene, "pits"),
      new RadarState(3, 3, enemies, "radar"),
      new LevelState(1, 1, 1, 1, scene, "scene"),
      new LevelState(1, 1, 1, 1, enemies, "enemies")
    );
  }

  final static MarioState initialState;
  static {
    byte[][] level = new byte[19][19];
    for (int i=0; i<19; i++) {
      level[i] = new byte[19];
    }

    initialState = new MarioState(
      2, 1, 0, 0, 0,
      new ModeState(1, "mode"),
      new DirectionState(0, 0, 0, 0, 0, "dir"),
      new PitsState(0, 0, level, "pits"),
      new RadarState(0, 0, level, "radar"),
      new LevelState(0, 0, 0, 0, level, "scene"),
      new LevelState(0, 0, 0, 0, level, "enemies")
    );
  }
}