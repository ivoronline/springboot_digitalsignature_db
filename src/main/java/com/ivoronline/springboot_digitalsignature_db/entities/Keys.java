package com.ivoronline.springboot_digitalsignature_db.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Keys {

  @Id
  public Integer id;
  public byte[]  privateKey;
  public byte[]  publicKey;

}
