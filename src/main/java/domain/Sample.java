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
      new DirectionState(
        ModelOptions.posTolerance,
        s.x, s.y, pos[0], pos[1], "dir"),
      new PitsState(
        ModelOptions.pitsLeft,
        ModelOptions.pitsRight,
        scene, "pits"),
      new RadarState(
        ModelOptions.radarOffset,
        ModelOptions.radarThickness,
        enemies, "radar"),
      new LevelState(
        ModelOptions.sceneLeft,
        ModelOptions.sceneRight,
        ModelOptions.sceneUp,
        ModelOptions.sceneDown,
        scene, "scene"),
      new LevelState(
        ModelOptions.enemiesLeft,
        ModelOptions.enemiesRight,
        ModelOptions.enemiesUp,
        ModelOptions.enemiesDown,
        enemies, "enemies")
    );
  }

  final static MarioState initialState;
  static {
    initialState = new MarioState(
      ModelOptions.initialStatus,
      ModelOptions.initialMode,
      ModelOptions.initialKills,
      ModelOptions.initialPos[0],
      ModelOptions.initialPos[1],
      new ModeState(
        ModelOptions.initialMode, "mode"),
      new DirectionState(
        ModelOptions.posTolerance,
        ModelOptions.initialPos[0],
        ModelOptions.initialPos[1],
        ModelOptions.initialPos[0],
        ModelOptions.initialPos[1], "dir"),
      new PitsState(
        ModelOptions.pitsLeft,
        ModelOptions.pitsRight,
        ModelOptions.initialLevel, "pits"),
      new RadarState(
        ModelOptions.radarOffset,
        ModelOptions.radarThickness,
        ModelOptions.initialLevel, "radar"),
      new LevelState(
        ModelOptions.sceneLeft,
        ModelOptions.sceneRight,
        ModelOptions.sceneUp,
        ModelOptions.sceneDown,
        ModelOptions.initialLevel, "scene"),
      new LevelState(
        ModelOptions.enemiesLeft,
        ModelOptions.enemiesRight,
        ModelOptions.enemiesUp,
        ModelOptions.enemiesDown,
        ModelOptions.initialLevel, "enemies")
    );
  }
}