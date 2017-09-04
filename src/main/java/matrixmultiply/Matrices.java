package matrixmultiply;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * This is a class used for matrices multiplication
 * Created by valeri on 12.7.2017 г..
 */
public final class Matrices {

    /**
     * @param matrixOne should not be null and its length has to equals the
     *                  length of the other matrix
     * @param matrixTwo should not be null
     * @return the multiplication of two matrices
     */
    public static int[][] multiply(int[][] matrixOne, int[][] matrixTwo) {
        assert matrixOne != null;
        assert matrixTwo != null;
        assert matrixOne.length == matrixTwo[0].length;

        int[][] resultMatrix = new int[matrixOne.length][matrixTwo.length];
        int numberOfThreads = Runtime.getRuntime().availableProcessors();

        ExecutorService executor = Executors.newFixedThreadPool(numberOfThreads);
        List<Future<int[]>> futures = new ArrayList<>();

        for (int row = 0; row < matrixOne.length; row++) {
            futures.add(executor.submit(new Multiplication(matrixOne[row], matrixTwo)));
        }
        executor.shutdown();
        while (!executor.isTerminated()) {
        }

        for (int row = 0; row < resultMatrix.length; row++) {
            try {
                resultMatrix[row] = futures.get(row).get();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } catch (ExecutionException e) {
                throw new HardwareMalfunctionException("There has been a hardware malfunction");
            }
        }

        return resultMatrix;
    }

}
