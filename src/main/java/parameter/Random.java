package main;

import java.util.Random;
import java.util.function.ToDoubleFunction;

class RandomParameterOptimizer extends BaseParameterOptimizer {
  Random random;

  RandomParameterOptimizer(ToDoubleFunction<ParameterContainer> benchmark) {
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
