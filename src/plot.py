import matplotlib.pyplot as plt
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

nstates = np.array(states)
winrates = [np.average(nstates[:i+1]) for i in range(len(nstates))]

window_size = len(labels)//10 - 1
smoothed_rewards = sig.savgol_filter(rewards, window_size, 1)

win_rewards = [r for (s, r) in zip(states, rewards) if s == 1]
minwinreward = np.amin(win_rewards)
meanwinreward = np.average(win_rewards)

_, ax1 = plt.subplots()
ax1.plot(labels, smoothed_rewards, 'r')
ax1.plot(labels, [meanwinreward for _ in labels], 'k')

ax2 = ax1.twinx()
ax2.set_ylim([0, 1])
ax2.plot(labels, winrates, 'k')

print("meanstates", np.average(states))
print("meanrewards", np.average(rewards))
print("minwinreward", minwinreward)
print("meanwinreward", meanwinreward)

plt.show()