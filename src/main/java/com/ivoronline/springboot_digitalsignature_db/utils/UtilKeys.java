package com.ivoronline.springboot_digitalsignature_db.utils;

import java.security.KeyPair;
import java.security.KeyPairGenerator;

public class UtilKeys {

  //====================================================================================
  // GENERATE KEY PAIR RSA
  //====================================================================================
  // Keys             keyPair    = UtilKeys.generateKeyPairRSA();
  // PrivateKey          privateKey = keyPair.getPrivate();
  // PublicKey           publicKey  = keyPair.getPublic();
  // System.err.println("Private key format: " + privateKey.getFormat());   //PKCS#8
  // System.err.println("Public  key format: " + publicKey .getFormat() );  //X.509
  public static KeyPair generateKeyPairRSA() throws Exception {

    //GENERATE KEY PAIR
    KeyPairGenerator  keyPairGenerator  = KeyPairGenerator.getInstance("RSA");
                      keyPairGenerator.initialize(2048);
    KeyPair           keyPair          = keyPairGenerator.generateKeyPair();

    //RETURN KEY PAIR
    return keyPair;

  }

}
