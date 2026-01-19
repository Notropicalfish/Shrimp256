import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.File;
import java.io.IOException;

public class ShrimpStorage {

  private static final byte[] MAGIC_BYTES = {0x53, 0x48, 0x4d};
  
  public static void saveFile(String fileName, byte[] data, boolean asHex) throws IOException {
    if (!fileName.endsWith(".shrimp")){
      fileName += ".shrimp";
    }
    
    try (FileOutputStream fos = new FileOutputStream(fileName)){
      fos.write(MAGIC_BYTES);

      if (asHex){
        String hexData = ShrimpCore.bytesToHex(data);
        fos.write(hexData.getBytes());
      } else {
        fos.write(data);
      }
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

    byte[] fileData = new byte[(int) file.length() - MAGIC_BYTES.length];

    try (FileInputStream fis = new FileInputStream(file)){
      byte[] header = new byte[MAGIC_BYTES.length];
      fis.read(header);
      fis.read(fileData);
    }
    return fileData;
  }

  public static byte[] loadPlainFile(String path) throws IOException {
    File file = new File(path);
    byte[] fileData = new byte[(int) file.length()];
    
    try (FileInputStream fis = new FileInputStream(file)) {
        fis.read(fileData);
    }
    return fileData;
  }
}