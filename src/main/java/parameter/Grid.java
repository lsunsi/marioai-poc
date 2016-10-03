package main;

import java.util.function.Consumer;

class GridParameterOptimizer extends BaseParameterOptimizer {
  ParameterContainer current;
  double step;

  GridParameterOptimizer(double step, Consumer<ParameterContainer> benchmark) {
    super(benchmark);
    this.step = step;
    current = new ParameterContainer(0., 0., 0., 0.);
  }

  @Override
  public void iterate() {
    append(current);
    current = current.next(step);
    if (current == null) done = true;
  }
}