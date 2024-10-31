package com.itfactory.PhoneBookProject.repos;

import com.itfactory.PhoneBookProject.model.Contact;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ContactRepository {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/phone_book";
    private static final String DB_USER = "postgres";
    private static final String DB_PASS = "Lucia5255688665";

    public List<Contact> getAllContacts(){
        List<Contact> contactList = new ArrayList<>();
        try(Connection connection = DriverManager.getConnection(DB_URL,DB_USER,DB_PASS)){
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM contacts");
            while(resultSet.next()){
                Contact newContact = new Contact();
                newContact.setId(Integer.parseInt(resultSet.getString("id")));
                newContact.setName(resultSet.getString("name"));
                newContact.setSurname(resultSet.getString("surname"));
                newContact.setPhoneNumber(resultSet.getString("phoneNumber"));
                newContact.setEmail(resultSet.getString("email"));

                contactList.add(newContact);
            }
        }catch (SQLException e){
            System.out.println("Connection failure");
            e.printStackTrace();
        }

        return contactList;
    }
}
