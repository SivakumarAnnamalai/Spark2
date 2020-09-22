package com.siva.poc.test;

/**
 * Created by Sivakumar on 12/24/2017.
 */
public class Customer {
    int id;
    String first_name,last_name,email,gender,ip_address;
    String delimiter = "\t";

    public Customer(){

    }

    public String toString(){
        return "Customer["+id+delimiter+first_name+delimiter+last_name+delimiter+email+delimiter+gender+delimiter+ip_address+"]";
    }
    public Customer(int id,String first_name,String last_name,String email,String gender,String ip_address){
        this.id=id;
        this.first_name=first_name;
        this.last_name=last_name;
        this.email=email;
        this.gender = gender;
        this.ip_address=ip_address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIp_address() {
        return ip_address;
    }

    public void setIp_address(String ip_address) {
        this.ip_address = ip_address;
    }
}
