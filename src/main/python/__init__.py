import matplotlib.pyplot as plt
import numpy as np
from sklearn.linear_model import LinearRegression
from dataForBigOPlot import data


def main():
    X = np.array([[i] for i in range(1, 501, 1)])
    y = np.array(data)
    reg = LinearRegression().fit(X, y)

    print('time = ' + str(round(reg.coef_[0], 5)) + ' * length + ' + str(round(reg.intercept_, 5)))

    fitted = [X[i] * reg.coef_ + reg.intercept_ for i in range(0, 500, 1)]

    plt.plot(data, 'ro', label='data')
    plt.plot(X, fitted, linewidth=4, label='fitted line')
    plt.title('Проверка гипотезы работы алгоритма за O(N)')
    plt.xlabel('длина строки')
    plt.ylabel('время на разворот строки (в мк с)')
    plt.legend()
    plt.show()


if __name__ == "__main__":
    main()
