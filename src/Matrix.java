public class Matrix {
    double[][] data;
    int ROWS;
    int COLS;

    public Matrix(double[][] data) {
        this.data = data;
        this.ROWS = this.data.length;
        this.COLS = this.data[0].length;

    }

    public Matrix addMatrix(Matrix other) {
        double[][] sumData = new double[ROWS][COLS];
        if (this.ROWS != other.ROWS || this.COLS != other.COLS) {
            System.out.println("Matrices must be of same size");
        } else {
            for (int i = 0; i < ROWS; i++) {
                for (int j = 0; j < COLS; j++) {
                    sumData[i][j] = data[i][j] + other.data[i][j];
                }
            }
        }
        return new Matrix(sumData);
    }

    public Matrix subtractMatrix(Matrix other) {
        double[][] sumData = new double[ROWS][COLS];
        if (this.ROWS != other.ROWS || this.COLS != other.COLS) {
            System.out.println("Matrices must be of same size");
        } else {
            for (int i = 0; i < ROWS; i++) {
                for (int j = 0; j < COLS; j++) {
                    sumData[i][j] = data[i][j] - other.data[i][j];
                }
            }
        }
        return new Matrix(sumData);
    }

    public Matrix multiplyConstant(double constant) {
        double[][] result = new double[ROWS][COLS];
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                result[i][j] = data[i][j] * constant;
            }
        }
        return new Matrix(result);
    }



    public Matrix multiply(Matrix other) {
        if (this.ROWS != other.COLS) {
            throw new IllegalArgumentException("Matrices aren't compatible");
        }

        double[][] result = new double[this.ROWS][other.COLS];
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < other.COLS; j++) {
                double sum = 0;
                for (int k = 0; k < COLS; k++) {
                    sum += data[i][k] * other.data[k][j];

                }
                result[i][j] = sum;
            }

        }

        return new Matrix(result);
    }



    public double getDeterminant() {
        if (ROWS == 2) {
            return data[0][0] * data[1][1] - data[0][1] * data[1][0];
        } else if (ROWS == 3) {
            return
                data[0][0] * data[1][1] * data[2][2] +
                        data[0][1] * data[1][2] * data[2][0] +
                        data[0][2] * data[1][0] * data[2][1] -
                        data[2][0] * data[1][1] * data[0][2] -
                        data[2][1] * data[1][2] * data[0][0] -
                        data[2][2] * data[1][0] * data[0][1];

        }
        double sum = 0;
        for (int j = 0; j < COLS; j++) {
            sum += Math.pow(-1, j) * data[0][j] * getMinor(0, j).getDeterminant();
        }
        return sum;
    }

    public Matrix getMinor(int row, int col) {
        int n = data.length;
        double[][] minor = new double[n - 1][n - 1];

        int y = 0;
        for (int i = 0; i < n; i++) {
            if (i == row) continue;
            int x = 0;
            for (int j = 0; j < n; j++) {
                if (j == col) continue;
                minor[y][x] = data[i][j];
                x++;
            }
            y++;
        }
        return new Matrix(minor);
    }

    public Matrix inverseMatrix() {
        double[][] inverse = new double[ROWS][COLS];
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                inverse[i][j] = Math.pow(-1, i + j) * getMinor(i, j).getDeterminant();
            }
        }
        Matrix inverseMatrix = new Matrix(inverse);
        return inverseMatrix.transposeMatrix().multiplyConstant(1/getDeterminant());
    }

    public Matrix transposeMatrix() {
        double[][] invertedData = new double[ROWS][COLS];
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                invertedData[i][j] = data[j][i];
            }
        }
        return new Matrix(invertedData);
    }



    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                if (j == 0) sb.append("| ");
                String numberStr = data[i][j] + " ";
                sb.append(numberStr);
                if (j == COLS - 1) {
                    String endStr = "|"+System.lineSeparator();
                    sb.append(endStr);
                }
            }
        }
        return sb.toString();
    }


    public static void main(String[] args) {

        double[][] m1 = {
                {1, 2, 3},
                {4, 5, 6}
        };

        double[][] m2 = {
                {-1, 0, 1},
                {2, 1, 1}
        };

        double[][] m3 = {
                {1, 2},
                {-1, 1},
                {0, 1}
        };

        double[][] m4 = {
                {1, 0, -1},
                {0, 2, 1},
                {2, 1, 0}
        };

        double[][] m6 = {
                {-((double) 1 /3), 0, -((double) 1 /3)},
                {((double) 2 /3), -((double) 1 /3), -((double) 1 /3)},
                {-((double) 4 /3), -((double) 1 /3), ((double) 2 /3)}
        };

        Matrix test = new Matrix(m4);
        Matrix test2 = new Matrix(m6);

        System.out.println(test.inverseMatrix());




    }


}
