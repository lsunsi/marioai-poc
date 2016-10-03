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

  ParameterContainer[] neighbors(double step) {
    return new ParameterContainer[] {
      alpha+step > 1 ? null   : new ParameterContainer(alpha+step, gamma, epsilon, lambda),
      alpha-step < 0 ? null   : new ParameterContainer(alpha-step, gamma, epsilon, lambda),
      gamma+step > 1 ? null   : new ParameterContainer(alpha, gamma+step, epsilon, lambda),
      gamma-step < 0 ? null   : new ParameterContainer(alpha, gamma-step, epsilon, lambda),
      epsilon+step > 1 ? null : new ParameterContainer(alpha, gamma, epsilon+step, lambda),
      epsilon-step < 0 ? null : new ParameterContainer(alpha, gamma, epsilon-step, lambda),
      lambda+step > 1 ? null  : new ParameterContainer(alpha, gamma, epsilon, lambda+step),
      lambda-step < 0 ? null  : new ParameterContainer(alpha, gamma, epsilon, lambda-step)
    };
  }

  ParameterContainer next(double step) {
    ParameterContainer next = new ParameterContainer(alpha, gamma, epsilon, lambda);

    next.alpha += step;
    if (next.alpha > 1) {
      next.alpha = 0;
      next.gamma += step;
      if (next.gamma > 1) {
        next.gamma = 0;
        next.epsilon += step;
        if (next.epsilon > 1) {
          next.epsilon = 0;
          next.lambda += step;
          if (next.lambda > 1) {
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
      "%f %f %f %f",
      alpha, gamma, epsilon, lambda
    );
  }

  public String toStringWithFitness() {
    return String.format(
      "(%s) -> %f",
      this.toString(), fitness
    );
  }
}
