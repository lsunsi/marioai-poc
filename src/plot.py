import matplotlib.pyplot as plt
import scipy.signal as sig
import numpy as np
import sys

labels  = []
states  = []
steps   = []
rewards = []

# reading input
with open(sys.argv[1]) as f:
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

# smoothing
window_size = len(labels)//10 - 1
smoothed_rewards = sig.savgol_filter(rewards, window_size, 1)
smoothed_states  = sig.savgol_filter(states,  window_size, 1)

# plotting
_, ax1 = plt.subplots()
l1 = ax1.plot(labels, smoothed_rewards, 'r', label='reward')

ax2 = ax1.twinx()
ax2.set_ylim([0, 1])
l2 = ax2.plot(labels, smoothed_states, 'k', label='winrate')

# showing
lns = l1+l2
ax1.legend(lns, [l.get_label() for l in lns], loc=4)
plt.show()