package com.ivoronline.springboot_digitalsignature_db.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;

public class UtilFiles {

  //====================================================================================
  // READ BYTES FROM FILE
  //====================================================================================
  // byte[] dataBytes = UtilFiles.readBytesFromFile(dataFile);
  public static byte[] readBytesFromFile(String fileName) throws IOException {
    Path filePath = Path.of(fileName);
    byte[] content  = Files.readAllBytes(filePath);
    return content;
  }

  //====================================================================================
  // WRITE BYTES TO FILE
  //====================================================================================
  // UtilFiles.writeBytesToFile("Private.key", privateKey.getEncoded());
  public static void writeBytesToFile(String fileName, byte[] content) throws IOException {
    Path filePath = Path.of(fileName);
    Files.write(filePath, content);
  }

  //====================================================================================
  // WRITE STRING TO FILE
  //====================================================================================
  // UtilFiles.writeStringToFile("First Line", "Test.txt");
  static void writeStringToFile(String content, String fileName) throws IOException {
    Path filePath = Path.of(fileName);
    Files.writeString(filePath, content);
  }

  //====================================================================================
  // READ STRING FROM FILE
  //====================================================================================
  // String content = UtilFiles.readStringFromFile("Test.txt");
  static String readStringFromFile(String fileName) throws IOException {
    Path   filePath = Path.of(fileName);
    String content  = Files.readString(filePath);
    return content;
  }

}
