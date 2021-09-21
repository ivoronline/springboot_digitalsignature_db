package com.ivoronline.springboot_digitalsignature_db.controllers;

import com.ivoronline.springboot_digitalsignature_db.entities.Keys;
import com.ivoronline.springboot_digitalsignature_db.repositories.KeysRepository;
import com.ivoronline.springboot_digitalsignature_db.utils.UtilFiles;
import com.ivoronline.springboot_digitalsignature_db.utils.UtilKeys;
import com.ivoronline.springboot_digitalsignature_db.utils.UtilSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

@RestController
public class MyController {

  @Autowired KeysRepository keysRepository;

  //================================================================================
  // GENERATE KEYS
  //================================================================================
  @RequestMapping("/GenerateKeys")
  public String generateKeys() throws Exception {

    //GENERATE KEY PAIR
    KeyPair    keyPair         = UtilKeys.generateKeyPairRSA();

    //GET KEYS
    PrivateKey privateKey      = keyPair.getPrivate();
    PublicKey  publicKey       = keyPair.getPublic();

    //CREATE KEYS ENTITY
    Keys       keys            = new Keys();
               keys.id         = 1;
               keys.privateKey = privateKey.getEncoded();
               keys.publicKey  = publicKey .getEncoded();

    //SAVE KEYS TO DB
    keysRepository.save(keys);

    //RETURN SOMETHING
    return "Keys ID: " + keys.id;

  }

  //================================================================================
  // CREATE SIGNATURE
  //================================================================================
  @RequestMapping("/CreateSignature")
  public String createSignature() throws Exception {

    //READ KEYS FROM DB
    Keys                keys                 = keysRepository.findById(1).get();

    //GENERATE PRIVATE KEY
    byte[]              privateKeyBytes      = keys.privateKey;
    PKCS8EncodedKeySpec pkcs8EncodedKeySpec  = new PKCS8EncodedKeySpec(privateKeyBytes);
    KeyFactory          keyFactory           = KeyFactory.getInstance("RSA");
    PrivateKey          privateKeyFromDB     = keyFactory.generatePrivate(pkcs8EncodedKeySpec);

    //CREATE DIGITAL SIGNATURE
    byte[]              dataBytes            = UtilFiles.readBytesFromFile("Data.txt");
    byte[]              signaturBytes        = UtilSignature.createSignature(dataBytes, "SHA256withRSA", privateKeyFromDB);
    UtilFiles.writeBytesToFile("Signature.bin", signaturBytes);

    //RETURN SOMETHING
    return "Signature created";
  }

  //================================================================================
  // VALIDAT SIGNATURE
  //================================================================================
  @RequestMapping("/ValidateSignature")
  public String validateSignature() throws Exception {

    //READ KEYS FROM DB
    Keys                keys                  = keysRepository.findById(1).get();

    //GENERATE PRIVATE KEY
    byte[]              publicKeyBytes        = keys.publicKey;
    X509EncodedKeySpec  x509EncodedKeySpec    = new X509EncodedKeySpec(publicKeyBytes);
    KeyFactory          keyFactory            = KeyFactory.getInstance("RSA");
    PublicKey           publicKeyFromDB       = keyFactory.generatePublic(x509EncodedKeySpec);

    //VALIDATE DIGITAL SIGNATURE
    byte[]              dataBytesFromFile     = UtilFiles.readBytesFromFile("Data.txt");
    byte[]              signaturBytesFromFile = UtilFiles.readBytesFromFile("Signature.bin");
    boolean             verified              = UtilSignature.verifySignature(dataBytesFromFile, signaturBytesFromFile, "SHA256withRSA", publicKeyFromDB);

    //RETURN SOMETHING
    return "Signature verified: " + verified;
  }

}
