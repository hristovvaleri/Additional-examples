package filecopier;

import java.io.IOException;

/**
 * This exception is when a current file cannot be read
 * Created by valeri on 3.6.2017 г..
 */
public class NotReadableFileException extends Exception {

    public NotReadableFileException(IOException ex) {
        super(ex);
    }
}
