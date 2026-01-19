# 🍤 Shrimp Encryption/Decryption Tool

<div align="center">

![Aucon Converter](https://img.shields.io/badge/version-0.4.154%20-blue.svg)
![License](https://img.shields.io/badge/license-GNU--2.0-green.svg)
![Platform](https://img.shields.io/badge/platform-Universal-lightgrey.svg)
![Made by](https://img.shields.io/badge/made%20by-Crunch%20Impposible-red.svg)
![Written in](https://img.shields.io/badge/written%20in-Java-orange.svg)

** Custom made Encryption and Decryption method and tool! **

</div>

A command-line tool for encrypting and decrypting files using the Shrimp256 algorithm.

## Features

*   **Encryption and Decryption:** Securely encrypt and decrypt your files.
*   **Multiple Input Methods:**
    *   Manual text entry.
    *   Load from a `.txt` file.
    *   Decrypt from a `.shrimp` file.
*   **Timestamping:** Optionally add a timestamp to the output file name.
*   **Algorithm:** Uses the Shrimp256 encryption algorithm.

## Getting Started

### Prerequisites

*   Java Development Kit (JDK) installed.

### Compilation

1.  Open your terminal or command prompt.
2.  Navigate to the directory containing the `Shrimp.java` file.
3.  Compile the Java source files:
    ```bash
    javac Shrimp.java ShrimpCore.java ShrimpStorage.java
    ```

### Running the Application

1.  After successful compilation, run the application:
    ```bash
    java Shrimp
    ```
2.  Follow the on-screen prompts to encrypt or decrypt your data.

## How to Use

1.  **Run the application:** `java Shrimp`
2.  **Enter a Secret Key:** This key will be used for encryption or decryption.
3.  **Enter a target filename:** The base name for the output file.
4.  **Timestamp:** Choose whether to add a timestamp to the filename (Y/n).
5.  **Select Input Method:**
    *   `1`: Enter text directly in the console.
    *   `2`: Load data from a `.txt` file.
    *   `3`: Decrypt a `.shrimp` file.
6.  **Provide Input:** Depending on your selection, either enter the text or the file path.
7.  The application will then encrypt or decrypt the data and save it to a new file.
