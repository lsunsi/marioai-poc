package main;

import java.util.Arrays;
import burlap.mdp.core.action.ActionType;
import burlap.mdp.singleagent.oo.OOSADomain;
import burlap.mdp.singleagent.model.FactoredModel;
import burlap.mdp.core.action.UniversalActionType;
import ch.idsia.benchmark.mario.environments.MarioEnvironment;

class MarioDomain extends OOSADomain {
  MarioDomain(MarioEnvironment env) {
    this.addActionTypes(
      Arrays.stream(MarioAction.values())
      .map(UniversalActionType::new)
      .toArray(ActionType[]::new)
    );

    this.setModel(new FactoredModel(
      new MarioSample(env),
      new MarioReward(),
      new MarioTerminal(env)
    ));
  }
}