import java.util.Scanner;

public class Shrimp {

  private static final String VERSION = "v0.4.154";
  private static final String CODENAME = "ENCRIPT";

  public static void main(String[] args){
    Scanner scanner = new Scanner(System.in);

    printHeader();
    System.out.println("Shrimp Encryption/Decription Tool");
    System.out.println("Press Enter to start: ");

    System.out.println("Enter Secret Key:");
    String key = scanner.nextLine();
    System.out.println("Shrimp256 Initilized");

    System.out.println("Enter target filename:");
    String baseName = scanner.nextLine();

    System.out.println("Would you like to timestamp your file Y/n:");
    String timeChoice = scanner.nextLine();

    System.out.println("<---Select Input Method--->");
    System.out.println("1. Manual Text Entry");
    System.out.println("2. Load .txt File");
    System.out.println("3. Load .hpr60 FIle (Decrypt)");
    System.out.println("Enter a number to select method");
    System.out.println("Selection:");

    String choice = scanner.nextLine();
    byte[] inputData = null;

    switch (choice){
      case "1":
        System.out.println("Enter text: ");
        inputData = scanner.nextLine().getBytes();
        break;
    case "2":
    case "3":
      System.out.println("Enter file path: ");
      String path = scanner.nextLine();
      try {
        inputData = ShrimpStorage.loadFile(path);
      } catch (java.io.IOException e){
        System.out.println("file not found!");
        return;
      }
      break;
    default:
      System.out.println("Invalid choice. Exiting");
      return;
    }
    
    System.out.println("Setup Complete Ready for engine");
    byte[] outputData;

    if (choice.equals("3")){
      outputData = ShrimpCore.decryptBlock(inputData, key, 256, 8);
      System.out.println("Decrypted" + new String(outputData));
    } else {
      outputData = ShrimpCore.encryptBlock(inputData, key, 256);
      System.out.println("DEBUG RAW BYTES: " + ShrimpCore.bytesToHex(outputData));
      System.out.println("Encryption applied");
    }
    
    String finalName = baseName;
    if (timeChoice.equalsIgnoreCase("y")){
      String ts = new java.text.SimpleDateFormat("yyyyMMdd_HHmm").format(new java.util.Date());
      finalName = baseName + "_" + ts;
    }
    
    try{
    ShrimpStorage.saveFile(finalName, outputData);
    System.out.println("File Saved💥💥💥💥💥💥💥💥");
    } catch (java.io.IOException e){
      System.out.println("failed t save file");
      System.out.println("Deatils: " + e.getMessage());
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
