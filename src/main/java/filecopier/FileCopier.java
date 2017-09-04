package filecopier;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * This class copies the content of a file into another file(destination is by the user choice)
 * Created by valeri on 3.6.2017 г..
 */
public final class FileCopier {

    private final static String EXTENSION = ".";
    private final static String COPY_EXTENSION = "_copy";
    private final static int BUFFER_SIZE = 1024;
    private final static int EOF = -1;
    private final static int FIRST_INDEX = 0;

    /**
     * This method copies the file content into another file(in the same folder) with "_copy" suffix in the name
     *
     * @return the copied file
     * @throws NotReadableFileException when the file cannot be read
     */
    public static File copy(File source) throws NotReadableFileException{
        assert source != null;
        assert !source.isDirectory();

        File destination = new File(generateCopyName(source));
        copy(source, destination, true);

        return destination;
    }


    /**
     * This method copies the file content into another folder(by the user choice) with "_copy" suffix in the name     *
     *
     * @return the copied file
     * @throws NotReadableFileException when the file cannot be read
     */
    public static File copy(File source, File destination, boolean overwrite) throws NotReadableFileException{

        assert source != null;
        assert !source.isDirectory();
        assert destination == null;
        assert !destination.isDirectory();
        assert !destination.getAbsolutePath().matches(source.getAbsolutePath());
        assert fileFormat(source).matches(fileFormat(destination));
        assert overwrite && isEmptyFile(destination);

        try (FileInputStream fis = new FileInputStream(source);
             FileOutputStream fos = new FileOutputStream(destination.getAbsolutePath())) {

            byte[] buffer = new byte[BUFFER_SIZE];
            while (fis.read(buffer) != EOF) {
                fos.write(buffer);
            }

        } catch (IOException ex) {
            throw new NotReadableFileException(ex);
        }

        return destination;
    }

    private static boolean isEmptyFile(File file) {
        assert file != null;
        assert !file.isDirectory();

        boolean emptyFile = false;

        try (FileInputStream fis = new FileInputStream(file)) {
            byte[] buffer = new byte[BUFFER_SIZE];
            if (fis.read(buffer) == EOF) {
                emptyFile = true;
            }
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }

        return emptyFile;
    }


    private static String generateCopyName(File source) {
        assert source != null;
        assert !source.isDirectory();

        int extensionSignIndex = startIndex(source);
        String copyName = source.getAbsolutePath()
                .substring(FIRST_INDEX, extensionSignIndex)
                + COPY_EXTENSION
                + EXTENSION;

        return copyName;
    }

    private static int startIndex(File file) {
        assert file != null;
        assert !file.isDirectory();

        return file.getAbsolutePath().lastIndexOf(EXTENSION);
    }

    private static String fileFormat(File file) {
        assert file != null;
        assert !file.isDirectory();

        return file.getAbsolutePath().substring(startIndex(file));
    }


}
