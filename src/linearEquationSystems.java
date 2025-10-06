public class linearEquationSystems {

    public static Vector multiplyMatrixVector(Matrix matrix, Vector vector) {
        double[] result = new double[vector.vectorLength];

        for (int m = 0; m < matrix.ROWS; m++) {
            double sum = 0;
            for (int n = 0; n < matrix.COLS; n++) {
                sum += matrix.data[m][n] * vector.data[n];
            }
            result[m] = sum;
        }
        return new Vector(result);
    }

    private static void swapRows(Matrix A, Vector b, int row1, int row2) {
        double[] helperRow = new double[A.COLS];
        double helperCell;
        for (int i = 0; i < A.COLS; i++) {
            helperRow[i] = A.data[row1][i];
            A.data[row1][i] = A.data[row2][i];
            A.data[row2][i] = helperRow[i];
        }
        helperCell = b.data[row1];
        b.data[row1] = b.data[row2];
        b.data[row2] = helperCell;
    }


    public static Matrix gaussElimination(Matrix A, Vector b) {
        double[][] workingMatrixData = new double[A.ROWS][A.COLS];
        double[] workingVectorData = new double[b.vectorLength];
        for (int i = 0; i < A.ROWS; i++) {
            System.arraycopy(A.data[i], 0, workingMatrixData[i], 0, A.COLS);
            workingVectorData[i] = b.data[i];
        }
        Matrix workingMatrix = new Matrix(workingMatrixData);
        Vector workingVector = new Vector(workingVectorData);

        for (int col = 0; col < A.COLS - 1; col++) {
            for (int row = col + 1; row < A.ROWS; row++) {
                if (workingMatrix.data[row][col] == 0) continue;
                else {
                    if (workingMatrix.data[col][col] == 0) {
                        for (int k = col + 1; k < A.ROWS; k++) {
                            if (workingMatrix.data[k][col] != 0) {
                                swapRows(workingMatrix, workingVector, col, k);
                                break;
                            }
                        }
                    }
                    double numOfInterest = workingMatrix.data[row][col];
                    double factor = numOfInterest / workingMatrix.data[col][col];
                    for (int innerCol = col; innerCol < A.COLS; innerCol++) {
                        workingMatrix.data[row][innerCol] -= factor * workingMatrix.data[col][innerCol];
                    }
                    workingVector.data[row] -= factor * workingVector.data[col];
                }
            }
        }
        return workingMatrix;
    }


    public static void main(String[] args) {
        double[][] m1 = {
                {2, 1, -1, 3},
                {1, -1, 2, 0},
                {-1, -5, 8, -6}
        };
        Matrix matrix1 = new Matrix(m1);

        double[] v1 = {(double) 19 /16, (double) 13 /16, (double) -21 /16, (double) 43 /16};
        double[] v2 = {5, 7, 11};
        Vector vector1 = new Vector(v1);
        Vector vector2 = new Vector(v2);

        Vector test = multiplyMatrixVector(matrix1, vector1);
        Matrix test2 = gaussElimination(matrix1, vector2);

        System.out.println(test2);

    }


}
