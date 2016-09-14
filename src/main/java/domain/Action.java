package main;

import burlap.mdp.core.action.Action;

enum MarioAction implements Action {
  // 0 keys
  None(new boolean[]{false,false,false,false,false,false}),
  // 1 key
  Run  (new boolean[]{false,false,false,false,true,false}),
  Jump (new boolean[]{false,false,false,true,false,false}),
  Right(new boolean[]{false,true,false,false,false,false}),
  Left (new boolean[]{true,false,false,false,false,false}),
  // 2 keys
  RunJump  (new boolean[]{false,false,false,true,true,false}),
  RightRun (new boolean[]{false,true,false,false,true,false}),
  RightJump(new boolean[]{false,true,false,true,false,false}),
  LeftRun  (new boolean[]{true,false,false,false,true,false}),
  LeftJump (new boolean[]{true,false,false,true,false,false}),
  // 3 keys
  RightJumpRun(new boolean[]{false,true,false,true,true,false}),
  LeftJumpRun (new boolean[]{true,false,false,true,true,false});

  boolean[] raw;
  MarioAction(boolean[] raw) {
    this.raw = raw;
  }

  @Override
  public String actionName() {
    return this.name();
  }

  @Override
  public Action copy() {
    return this;
  }
}
