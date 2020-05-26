import matplotlib
import matplotlib.pyplot as plt
import numpy as np

r1 = 1.00
r2 = 1.00
K1 = 1
K2 = 1
h = 1
S1 = np.zeros(100)
S2 = np.zeros(100)
E1 = np.zeros(100)
E2 = np.zeros(100)
S1[0] = 0.5
S2[0] = 0.5
E1[0] = 0.50
E2[0] = 0.90


for n in range(1, 100):
    S1[n] = S1[n - 1] + h * (
            (S2[n - 1] * E1[n - 1]) / (S1[n - 1] + S2[n - 1]) - (S1[n - 1] * E2[n - 1]) / (S1[n - 1] + S2[n - 1]))
    S2[n] = S2[n - 1] + h * (
            -(S2[n - 1] * E1[n - 1]) / (S1[n - 1] + S2[n - 1]) + (S1[n - 1] * E2[n - 1]) / (S1[n - 1] + S2[n - 1]))
    E1[n] = E1[n - 1] + h * ((r1 * E1[n - 1] * (K1 - E1[n - 1])) - (S2[n - 1] * E1[n - 1]) / (S1[n - 1] + S2[n - 1]))
    E2[n] = E2[n - 1] + h * ((r2 * E2[n - 1] * (K2 - E2[n - 1])) - (S1[n - 1] * E2[n - 1]) / (S1[n - 1] + S2[n - 1]))

# plot(np.arange(100),E1)

# y = [] * 100
# for i in range(0,100):
#     print("HEY")
#     y[i] = i
y = list(range(0, 100))
fig, ax = plt.subplots()
ax.plot(y, S1, S2)
ax.set(xlabel='Time (years)', ylabel='Safety', title="Safeties of both countries")
plt.legend(["S1", "S2"], loc='upper left')
ax.grid()
fig.savefig("Safety.png")
# plt.show()

fig2, ax2 = plt.subplots()
ax2.plot(y, E1, E2)
ax2.set(xlabel='Time (years)', ylabel='Economy', title="Economies of both countries")
plt.legend(["E1", "E2"], loc='upper left')
ax2.grid()
fig2.savefig("Economy.png")
plt.show()
