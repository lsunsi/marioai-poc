import matplotlib.pyplot as plt
import scipy.signal as sig
import numpy as np
import sys

colors = ['r','b','g']

# setting up axis
plots = []
ax1 = plt.subplot()
ax2 = ax1.twinx()
ax2.set_ylim([0, 1])
plt.tight_layout(pad=5)

# all files
for fname, color in zip(sys.argv[1:], colors):
  labels  = []
  states  = []
  steps   = []
  rewards = []

  # reading input
  with open(fname) as f:
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
  window_size = len(labels)//10
  if window_size % 2 == 0: window_size -= 1
  smoothed_rewards = sig.savgol_filter(rewards, window_size, 1)
  smoothed_states  = sig.savgol_filter(states,  window_size, 1)

  # plotting
  p1 = ax1.plot(labels, smoothed_rewards, color, label=fname+' reward')
  p2 = ax2.plot(labels, smoothed_states, color, linestyle='dotted', label=fname+' winrate')
  plots = plots + p1 + p2

# showing
ax2.legend(plots, [x.get_label() for x in plots], loc=3, mode='expand', bbox_to_anchor=(0., 1.02, 1., .102), ncol=2, borderaxespad=0.)
plt.show()