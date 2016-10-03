package main;

import java.util.Set;
import java.util.HashSet;
import java.util.function.ToDoubleFunction;

interface ParameterOptimizer {
  ParameterContainer[] all();
  ParameterContainer best();
  void iterate();
  boolean done();
}

abstract class BaseParameterOptimizer implements ParameterOptimizer {
  ToDoubleFunction<ParameterContainer> benchmark;
  Set<ParameterContainer> containers;
  boolean done;

  BaseParameterOptimizer(ToDoubleFunction<ParameterContainer> benchmark) {
    this.containers = new HashSet<>();
    this.benchmark = benchmark;
    done = false;
  }

  double append(ParameterContainer container) {
    container.fitness = benchmark.applyAsDouble(container);
    containers.add(container);
    return container.fitness;
  }

  @Override
  public ParameterContainer[] all() {
    return containers.toArray(
      new ParameterContainer[containers.size()]
    );
  }

  @Override
  public ParameterContainer best() {
    ParameterContainer best = null;
    for (ParameterContainer c : containers) {
      if (best == null || c.fitness > best.fitness) {
        best = c;
      }
    } return best;
  }

  @Override
  public boolean done() {
    return done;
  }

  @Override
  abstract public void iterate();
}
