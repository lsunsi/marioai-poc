package main;

import burlap.mdp.core.state.State;
import burlap.mdp.core.action.Action;
import burlap.mdp.singleagent.model.RewardFunction;

class MarioReward implements RewardFunction {
  @Override
  public double reward(State s0, Action a, State s1) {
    return reward((MarioState)s0, (MarioAction)a, (MarioState)s1);
  }

  double reward(MarioState s0, MarioAction a, MarioState s1) {
    double dx = s1.x - s0.x, dy = s0.y - s1.y;
    return dx;
  }
}