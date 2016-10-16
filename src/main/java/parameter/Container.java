package main;

import java.util.Objects;

class ParameterContainer {
  double alpha, gamma, epsilon, lambda, fitness;
  ParameterContainer(double alpha, double gamma, double epsilon, double lambda) {
    this.alpha = alpha;
    this.gamma = gamma;
    this.epsilon = epsilon;
    this.lambda = lambda;
  }

  ParameterContainer next(ParameterContainer low, ParameterContainer high, ParameterContainer step) {
    ParameterContainer next = new ParameterContainer(alpha, gamma, epsilon, lambda);

    next.alpha += step.alpha;
    if (next.alpha > high.alpha) {
      next.alpha = low.alpha;
      next.gamma += step.gamma;
      if (next.gamma > high.gamma) {
        next.gamma = low.gamma;
        next.epsilon += step.epsilon;
        if (next.epsilon > high.epsilon) {
          next.epsilon = low.epsilon;
          next.lambda += step.lambda;
          if (next.lambda > high.lambda) {
            return null;
          }
        }
      }
    }

    return next;
  }

  @Override
  public int hashCode() {
    return Objects.hash(alpha, gamma, epsilon, lambda);
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof ParameterContainer)) return false;
    ParameterContainer pc = (ParameterContainer)o;
    return (true
      && this.alpha == pc.alpha
      && this.gamma == pc.gamma
      && this.epsilon == pc.epsilon
      && this.lambda == pc.lambda
    );
  }

  @Override
  public String toString() {
    return String.format(
      "(%f %f %f %f) -> %f",
      alpha, gamma, epsilon, lambda, fitness
    );
  }
}
