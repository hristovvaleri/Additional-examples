package filecopier;

import filecopier.FileCopier;
import org.junit.Assert;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;
import static org.junit.Assert.*;

/**
 * Created by valeri on 7.6.2017 г..
 */
public class FileCopierTest {

    @org.junit.Test
    public void overwrite() throws IOException, NotReadableFileException {
        FileCopier fc = new FileCopier();
        fc.copy(file,resultFile,true);


        try (BufferedReader br = new BufferedReader(new FileReader(resultFile));
             BufferedReader br1stFile = new BufferedReader((new FileReader(file)))) {
            String line;
            String line1stFile;
            while ((line = br.readLine()) != null && (line1stFile = br1stFile.readLine()) != null) {
                assertEquals(line1stFile, line);
            }
        }
    }



    private File file, resultFile;

    @org.junit.Before
    public void initializeTestFile() {
        file = new File("zdrasti.txt");
        resultFile = new File("zdrasti_copy.txt");

        try (FileWriter fw = new FileWriter(file);FileWriter fw1 = new FileWriter(resultFile)) {
            fw.write("Milan is \n");
            fw.append("\n");
            fw.write("the best \n");
            fw.append("\n");
            fw.write("team! \n");
            fw.append("\n");
            fw1.write("fd");
        } catch (IOException e) {
            Assert.fail();
        }
    }

    @org.junit.After
    public void after() {
        file.delete();
        resultFile.delete();
    }

}