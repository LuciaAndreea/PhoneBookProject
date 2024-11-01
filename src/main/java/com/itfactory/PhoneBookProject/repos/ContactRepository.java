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

    public void addContact(Contact newContact) {
        try(Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)){
            System.out.println("Connected to PostgreSQL database!");
            System.out.println("Prepare statement");

            PreparedStatement statement = connection.prepareStatement("INSERT INTO contacts(name,surname,phoneNumber,email) VALUES(?,?,?,?)");
            statement.setString(1, newContact.getName());
            statement.setString(2, newContact.getSurname());
            statement.setString(3, newContact.getPhoneNumber());
            statement.setString(4, newContact.getEmail());

            statement.execute();
            System.out.println("Insert executed succesfully");
        }
        catch (SQLException e){
            System.out.println("Connection failure");
            e.printStackTrace();
        }
    }
}
