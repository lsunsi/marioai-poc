import matplotlib.pyplot as plt
import statsmodels.api as sm
import scipy.signal as sig
import numpy as np

labels  = []
states  = []
steps   = []
rewards = []

with open('logs') as f:
  for line in f.readlines():
    cols = line.split()
    if len(cols) == 4:
      try:
        label, state, step, reward = cols
        labels.append(int(label))
        states.append(int(state))
        steps.append(int(step))
        rewards.append(float(reward))
      except:
        pass

window_size = len(labels)//10 - 1
smoothed_rewards = sig.savgol_filter(rewards, window_size, 1)
plt.plot(labels, smoothed_rewards)

win_rewards = []
for s, r in zip(states, rewards):
  if s == 1:
    win_rewards.append(r)
meanwinreward = np.average(win_rewards)
plt.plot(labels, [meanwinreward for _ in labels])

print(np.average(states))
print(meanwinreward)
plt.show()