/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entities.Person;

/**
 *
 * @author Nicol
 */
public class PersonDTO {
  private int id;
  private String fName;
  private String lName;
  private int phone;
  private String street;
  private int zip;
  private String city;
  
  
  public PersonDTO(Person p) {
    this.id = p.getId();
    this.fName = p.getfName();
    this.lName = p.getlName();
    this.phone = p.getPhone();
    this.street = p.getAdress().getStreet();
    this.zip= p.getAdress().getZip();
    this.city= p.getAdress().getCity();
  }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getZip() {
        return zip;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    
    
}
