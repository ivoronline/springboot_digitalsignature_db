package com.ivoronline.springboot_digitalsignature_db.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class UtilFiles {

  //====================================================================================
  // READ BYTES FROM FILE
  //====================================================================================
  // byte[] dataBytes = UtilFiles.readBytesFromFile(dataFile);
  public static byte[] readBytesFromFile(String fileName) throws IOException {
    Path   filePath = Path.of(fileName);
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

}
