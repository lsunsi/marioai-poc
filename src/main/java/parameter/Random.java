package main;

import java.util.Random;
import java.util.function.Consumer;

class RandomParameterOptimizer extends BaseParameterOptimizer {
  Random random;

  RandomParameterOptimizer(Consumer<ParameterContainer> benchmark) {
    super(benchmark);
    random = new Random();
  }

  @Override
  public void iterate() {
    append(new ParameterContainer(
      random.nextDouble(),
      random.nextDouble(),
      random.nextDouble(),
      random.nextDouble()
    ));
  }
}
