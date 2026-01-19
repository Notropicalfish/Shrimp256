import java.util.Scanner;

public class Shrimp {

  private static final String VERSION = "v1.0.0";
  private static final String CODENAME = "ONEMILLIONMONSTER";

  public static void main(String[] args){
    Scanner scanner = new Scanner(System.in);

    printHeader();
    System.out.println("Shrimp Encryption/Decription Tool");
    System.out.println("Press Enter to start: ");
    scanner.nextLine();

    System.out.println("Enter Secret Key:");
    String key = scanner.nextLine();
    System.out.println("Key entered: '" + key + "' (length: " + key.length() + ")");
    System.out.println("Shrimp256 Initilized");

    System.out.println("Enter target filename:");
    String baseName = scanner.nextLine();

    System.out.println("Would you like to timestamp your file Y/n:");
    String timeChoice = scanner.nextLine();

    System.out.println("<---Select Input Method--->");
    System.out.println("1. Manual Text Entry");
    System.out.println("2. Load .txt File");
    System.out.println("3. Load .shrimp File (Decrypt)");
    System.out.println("Enter a number to select method");
    System.out.println("Selection:");

    String choice = scanner.nextLine();
    String finalName = baseName;
    if (timeChoice.equalsIgnoreCase("y")){
      String ts = new java.text.SimpleDateFormat("yyyyMMdd_HHmm").format(new java.util.Date());
      finalName = baseName + "_" + ts;
    }

    byte[] inputData = null;
    
    byte[] outputData;
    
    try {
      if (choice.equals("1")) {
        System.out.println("Enter text: ");
        inputData = scanner.nextLine().getBytes();
      } else if (choice.equals("2")) {
        System.out.println("Enter file path: ");
        inputData = ShrimpStorage.loadPlainFile(scanner.nextLine());
      } else if (choice.equals("3")) {
        System.out.println("Enter file path: ");
        inputData = ShrimpStorage.loadFile(scanner.nextLine());
      }
      
      if (inputData != null) {
        System.out.println("Input data hex: " + ShrimpCore.bytesToHex(inputData));
        System.out.println("Setup Complete Ready for engine");
      }

      if (choice.equals("3")) {
        String hexInput = new String(inputData);
        byte[] rawEncrypted = ShrimpCore.hexToBytes(hexInput);
        outputData = ShrimpCore.decryptBlock(rawEncrypted, key, 256);
        
        ShrimpStorage.saveFile(finalName + ".txt", outputData, false);
        System.out.println("Decrypted! Saved as " + finalName + ".txt");
      } else {
        outputData = ShrimpCore.encryptBlock(inputData, key, 256);
        
        ShrimpStorage.saveFile(finalName + ".shrimp", outputData, true);
        System.out.println("Encrypted! Saved as " + finalName + ".shrimp");
      }

    } catch (Exception e) {
      System.out.println("Error: " + e.getMessage());
    }
  }

  private static void printHeader() {

    System.out.println("----------------------------------------------------");
    System.out.println(" _______  __   __  ______    ___   __   __  _______ ");
    System.out.println("|       ||  | |  ||    _ |  |   | |  |_|  ||       |");
    System.out.println("|  _____||  |_|  ||   | ||  |   | |       ||    _  |");
    System.out.println("| |_____ |       ||   |_||_ |   | |       ||   |_| |");
    System.out.println("|_____  ||       ||    __  ||   | |       ||    ___|");
    System.out.println(" _____| ||   _   ||   |  | ||   | | ||_|| ||   |    ");
    System.out.println("|_______||__| |__||___|  |_||___| |_|   |_||___|    ");
    System.out.println("                                      " + CODENAME + " " +  VERSION);
    System.out.println("----------------------------------------------------");
    }
}
