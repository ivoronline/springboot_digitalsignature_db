package com.ivoronline.springboot_digitalsignature_db.utils;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;

public class UtilSignature {

  //====================================================================================
  // CREATE SIGNATURE
  //====================================================================================
  // UtilSignature.creatSignature("Data.txt", "Signature.txt", "SHA256withRSA", privateKey);
  public static byte[] createSignature(
    byte[]     data,
    String     format,         //"SHA1withDSA", "SHA256withRSA"
    PrivateKey privateKey
  ) throws Exception {

    //INITIALIZE SIGNATURE
    Signature  signature = Signature.getInstance(format);
               signature.initSign(privateKey);
               signature.update(data, 0, data.length);

    //CREATE SIGNATURE
    byte[]     signatureBytes = signature.sign();

    //RETURN SIGNATURE
    return signatureBytes;

  }

  //====================================================================================
  // VERIFY SIGNATURE
  //====================================================================================
  // UtilSignature.verifySignature("Data.txt", "Signature.txt", "SHA256withRSA", publicKeyFromFile);
  public static boolean verifySignature(
    byte[]    dataBytes,
    byte[]    signatureBytes,
    String    format,          //"SHA1withDSA", "SHA256withRSA"
    PublicKey publicKey
  ) throws Exception {

    //INITIALIZE SIGNATURE
    Signature signature = Signature.getInstance(format);
              signature.initVerify(publicKey);
              signature.update(dataBytes, 0, dataBytes.length);

    //VERIFY SIGNATURE
    boolean   verified = signature.verify(signatureBytes);

    //DISPLAY RESULT
    System.out.println("Signature verified: " + verified);

    //RETURN RESULT
    return verified;

  }

}
