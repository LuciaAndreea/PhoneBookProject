package com.itfactory.PhoneBookProject.controllers;

import com.itfactory.PhoneBookProject.model.Contact;
import com.itfactory.PhoneBookProject.repos.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ContactController {
    private ContactRepository contactRepo;

    @Autowired
    public void setContactRepo(ContactRepository contactRepo) {
        this.contactRepo = contactRepo;
    }

    @GetMapping("/contacts")
    public String getAllContacts(Model myModel){
        List<Contact> contactList = contactRepo.getAllContacts();
        myModel.addAttribute("contacte", contactList);
        return  "contacts";
    }

    @PostMapping("/contacts")
    public RedirectView addNewContact(Contact newContact){
        contactRepo.addContact(newContact);

        return new RedirectView("http://localhost/contacts");
    }
}
