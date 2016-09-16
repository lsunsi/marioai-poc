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
    double dx = s1.x - s0.x;
    int ds = s1.size - s0.size;
    int dk = s1.kills - s0.kills;
    return (
      + (s1.status == 1 ? +100:0)
      + (s1.status == 0 ? -100:0)
      + (ds < 0 ? -50:0)
      + (dk > 0 ? +10:0)
      + (dx > 1 ? +1:-2)
    );
  }
}