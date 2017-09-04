package matrixmultiply;

import java.util.concurrent.Callable;

/**
 * This class can multiply a row of a matrix with an other matrix
 * Created by valeri on 12.7.2017 г..
 */
public final class Multiplication implements Callable<int[]> {

    private final int[] matrixOneRow;
    private final int[][] matrixTwo;

    /**
     * @param matrixOneRow    should not be null and its length has
     *                        to equals the length of matrixTwo columns
     * @param matrixTwo       should not be null
     */
    public Multiplication(int[] matrixOneRow, int[][] matrixTwo) {
        assert matrixOneRow != null;
        assert matrixTwo != null;
        assert matrixOneRow.length != 0;
        assert matrixTwo.length != 0;

        this.matrixOneRow = matrixOneRow;
        this.matrixTwo = matrixTwo;
    }

    /**
     * @return the row of a multiplication of a column with a matrix
     */
    public int[] getColumn() {

        int[] newColumn = new int[matrixTwo[0].length];
        for (int row = 0; row < matrixTwo[0].length; row++) {
            newColumn[row] = this.getSum(row);
        }

        return newColumn;
    }

    private int getSum(int row) {

        assert row > -1;

        int sum = 0;
        for (int column = 0; column < matrixTwo.length; column++) {
            sum += matrixOneRow[column] * matrixTwo[column][row];
        }

        return sum;
    }


    @Override
    public int[] call() {
        return this.getColumn();
    }
}


