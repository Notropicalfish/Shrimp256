public class ShrimpCore{
  
  public static byte[] encryptBlock(byte[] data, String key, int rounds){
    if (data == null) return new byte[0];
    byte[] block = new byte[data.length];
    System.arraycopy(data, 0, block, 0, data.length);

    for (int round = 1; round <= rounds; round++){
      block = runEncryptionRound(block , key, round);
    }
    return block;
  }

  private static byte[] runEncryptionRound(byte[] block, String key, int roundNumber){
    byte[] keyBytes = key.getBytes();
    if (keyBytes.length == 0) return block;

    for (int i = 0; i < block.length; i++){
      int keyIndex =i % keyBytes.length;
      int scramble = (block[i] ^ keyBytes[keyIndex]) + roundNumber;

      // int shift = (i + roundNumber) % 8;
      // block[i] = (byte) (block[i] ^ keyBytes[i % keyBytes.length]);
      // block[i] = rotateLeft(block[i], shift);

      block[i] = rotateLeft((byte)scramble, (i + roundNumber) % 8);
    }
    return block;
  }
  
  public static byte[] decryptBlock(byte[] data, String key, int roundNumber, int rounds){
    byte[] block = data.clone();
    for (int round = rounds; round >= 1; round--){
      block = runDecryptionRound(block, key, round);
    }
    return block;
  }
  
  private static byte[] runDecryptionRound(byte[] block, String key, int roundNumber){
    byte[] keyBytes = key.getBytes();
    for (int i = 0; i < block.length; i++){
      int shift = (i + roundNumber) % 8;

      block[i] = (byte) (block[i] ^ keyBytes[i % keyBytes.length]);
      block[i] = rotateLeft(block[i], (8 - shift) % 8);
    }
    return block;
  }

  public static byte rotateLeft(byte b, int n){
    n = n % 8;
    int val = b & 0xFF;
    int result = (val << n) | (val >>> (8 - n));

    return (byte) result;
  }

  public static String bytesToHex(byte[] bytes){
    StringBuilder sb = new StringBuilder();
    for (byte b : bytes){
      sb.append(String.format("%02x", b));
    }
    return sb.toString();
  }

  public static byte[] hexToBytes(String hexString){
    int len = hexString.length();
    byte[] data = new byte[len / 2];
    for (int i = 0; i < len; i += 2){
      data[i / 2] = (byte) ((Character.digit(hexString.charAt(i), 16) << 4) + Character.digit(hexString.charAt(i+1), 16));
    }
    return data;
  }
}