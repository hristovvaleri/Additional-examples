package matrixmultiply;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by valeri on 2.9.2017 г..
 */
public class MatricesTest {
    @Test
    public void multiplyNotNull() throws Exception {
        int[][] matrixOne = new int[][]{{1, 1, 1}, {1, 1, 1}, {1, 1, 1}};
        int[][] matrixTwo = new int[][]{{1, 1, 1}, {1, 1, 1}, {1, 1, 1}};
        assertNotNull(Matrices.multiply(matrixOne, matrixTwo));

    }

    @Test
    public void multiply3x3() throws Exception {
        int[][] matrixOne = new int[][]{{1, 1, 1}, {1, 1, 1}, {1, 1, 1}};
        int[][] matrixTwo = new int[][]{{1, 1, 1}, {1, 1, 1}, {1, 1, 1}};
        int[][] result = new int[][]{{3, 3, 3}, {3, 3, 3}, {3, 3, 3}};
        assertEquals(result, Matrices.multiply(matrixOne, matrixTwo));
    }

    @Test
    public void multiplyDifferentRows() throws Exception {
        int[][] matrixOne = new int[][]{{1, 1, 1, 1},
                {1, 1, 1, 1},
                {1, 1, 1, 1}};
        int[][] matrixTwo = new int[][]{{1, 1, 1},
                {1, 1, 1},
                {1, 1, 1}, {1, 1, 1}};
        int[][] result = new int[][]{{4, 4, 4}, {4, 4, 4}, {4, 4, 4}};
        assertEquals(result, Matrices.multiply(matrixOne, matrixTwo));
    }

    @Test
    public void notEqual() throws Exception {
        int[][] matrixOne = new int[][]{{1, 1, 1, 1},
                {1, 1, 1, 1},
                {1, 1, 1, 1}};
        int[][] matrixTwo = new int[][]{{1, 1, 1},
                {1, 1, 1},
                {1, 1, 1}, {1, 1, 1}};
        int[][] result = new int[][]{{4, 4, 4}, {4, 4, 4}, {4, 4, 5}};
        assertNotEquals(result, Matrices.multiply(matrixOne, matrixTwo));
    }

}