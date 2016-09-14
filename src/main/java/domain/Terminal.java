package main;

import burlap.mdp.core.state.State;
import burlap.mdp.core.TerminalFunction;
import ch.idsia.benchmark.mario.environments.MarioEnvironment;

class MarioTerminal implements TerminalFunction {
  MarioEnvironment environment;
  MarioTerminal(MarioEnvironment env) {
    this.environment = env;
  }

  @Override
  public boolean isTerminal(State s) {
    return environment.isLevelFinished();
  }
}