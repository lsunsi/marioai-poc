package main;

import java.util.Random;
import java.util.function.Consumer;

class RandomParameterOptimizer extends BaseParameterOptimizer {
  ParameterContainer low, high;
  Random random;

  RandomParameterOptimizer(ParameterContainer low, ParameterContainer high, Consumer<ParameterContainer> benchmark) {
    super(benchmark);
    this.random = new Random();
    this.high = high;
    this.low = low;
  }

  double random(double low, double high) {
    return low + random.nextDouble() * (high - low);
  }

  @Override
  public void iterate() {
    append(new ParameterContainer(
      random(low.alpha, high.alpha),
      random(low.gamma, high.gamma),
      random(low.epsilon, high.epsilon),
      random(low.lambda, high.lambda)
    ));
  }
}
