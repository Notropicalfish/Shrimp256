import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.File;
import java.io.IOException;

public class ShrimpStorage {

  
  private static final byte[] MAGIC_BYTES = {0x53, 0x48, 0x4d};
  
  public static void saveFile(String fileName, byte[] data) throws IOException {
    if (!fileName.endsWith(".shrimp")){
      fileName += ".shrimp";
    }

    try (FileOutputStream fos = new FileOutputStream(fileName)){
      fos.write(MAGIC_BYTES);
      fos.write(data);

      System.out.println("Successfully saved file as:" + fileName);
    } catch (IOException e){
      System.out.println("Failed to save file:" + fileName);
      System.out.println("With Error: " + e.getMessage());
      throw e;
    }
  }

  public static byte[] loadFile(String path) throws IOException{
    File file = new File(path);
    int totalSize = (int) file.length();

    if (totalSize < MAGIC_BYTES.length){
      throw new IOException("File is too small to be a valid .shrimp file");
    }

    byte[] fileData = new byte[(int) file.length()];

    try (java.io.FileInputStream fis = new java.io.FileInputStream(file)){
      byte[] header = new byte[MAGIC_BYTES.length];
      fis.read(fileData);

      fis.read(header);
    }
    return fileData;
  }
}