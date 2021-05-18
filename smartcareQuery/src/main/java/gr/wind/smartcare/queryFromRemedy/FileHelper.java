package gr.wind.smartcare.queryFromRemedy;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class FileHelper
{
    // Read from a file
    public static String readFile(String filePath) throws IOException
    {
        File file = new File(filePath);
        byte[] data = Files.readAllBytes(Paths.get(filePath));
        String st = new String(data, StandardCharsets.UTF_8);
        return st;
    }

    // Write to a file
    public static void writeFile(String filePath, String textData) throws IOException
    {
        byte[] binData = textData.getBytes(StandardCharsets.UTF_8);
        Path path = new File(filePath).toPath();
        Files.write(path, binData, StandardOpenOption.CREATE_NEW);
    }
}
