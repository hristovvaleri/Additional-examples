package matrixmultiply;

/**
 * This exception represent a hardware malfunction.The user can handle this exception with restarting his API.
 * If this does't help that means the user has hardware problem and has to call service to fix it.
 * Created by valeri on 30.7.2017 г..
 */
public class HardwareMalfunctionException extends RuntimeException {

    /**
     *
     * @param cause
     */
    public HardwareMalfunctionException(String cause) {
        super(cause);
    }
}
