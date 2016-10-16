package main;

import java.util.function.Consumer;

class GridParameterOptimizer extends BaseParameterOptimizer {
  ParameterContainer current, low, high, step;

  GridParameterOptimizer(ParameterContainer low, ParameterContainer high, ParameterContainer step, Consumer<ParameterContainer> benchmark) {
    super(benchmark);
    this.low = low;
    this.high = high;
    this.step = step;
    current = low;
  }

  @Override
  public void iterate() {
    append(current);
    current = current.next(low, high, step);
    if (current == null) done = true;
  }
}