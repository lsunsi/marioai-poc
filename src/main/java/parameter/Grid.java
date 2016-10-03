package main;

import java.util.function.ToDoubleFunction;

class GridParameterOptimizer extends BaseParameterOptimizer {
  ParameterContainer current;
  double step;

  GridParameterOptimizer(double step, ToDoubleFunction<ParameterContainer> benchmark) {
    super(benchmark);
    this.step = step;
    current = new ParameterContainer(0., 0., 0., 0.);
  }

  @Override
  public void iterate() {
    current = current.next(step);
    if (current == null) done = true;
    else append(current);
  }
}