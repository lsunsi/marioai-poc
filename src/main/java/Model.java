package main;

class ModelOptions {
  final static int initialStatus = 2;
  final static int initialMode = 1;
  final static int initialKills = 0;
  final static int[] initialPos = {0, 0};

  final static double posTolerance = 0.;

  final static int pitsLeft = 3;
  final static int pitsRight = 3;

  final static int radarOffset = 2;
  final static int radarThickness = 3;

  final static int sceneLeft = 2;
  final static int sceneRight = 2;
  final static int sceneUp = 2;
  final static int sceneDown = 2;

  final static int enemiesLeft = 2;
  final static int enemiesRight = 2;
  final static int enemiesUp = 2;
  final static int enemiesDown = 2;

  static byte[][] initialLevel;
  static {
    initialLevel = new byte[19][19];
    for (int i=0; i<19; i++) {
      initialLevel[i] = new byte[19];
    }
  }
}