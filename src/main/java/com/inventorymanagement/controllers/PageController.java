package com.inventorymanagement.controllers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.inventorymanagement.entities.Contact;
import com.inventorymanagement.entities.User;
import com.inventorymanagement.forms.ContactForm;
import com.inventorymanagement.forms.UserForm;
import com.inventorymanagement.helpers.Message;
import com.inventorymanagement.helpers.MessageType;
import com.inventorymanagement.repositories.ContactRepo;
import com.inventorymanagement.services.UserService;
import com.inventorymanagement.services.impl.OrderServiceImpl;

import jakarta.servlet.http.HttpSession;

@Controller
public class PageController {

    @Autowired
    UserService userService;
    @Autowired
    OrderServiceImpl orderServiceImpl;

    @Autowired
    ContactRepo contactRepo;

    @RequestMapping("/")
    public String ind() {
        return "redirect:index";
    }

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @RequestMapping("/about")
    public String about() {
        return "about";
    }

    @PostMapping("/clear-message")
    public ResponseEntity<Void> clearMessage(HttpSession session) {
        session.removeAttribute("message");
        return ResponseEntity.ok().build();
    }

    @RequestMapping("/contact")
    public String contact(Model model) {
        ContactForm contactForm = new ContactForm();
        model.addAttribute("contactForm", contactForm);
        return "contact";
    }

    @RequestMapping(value = "/send-contact", method = RequestMethod.POST)
    public String sendContact(@ModelAttribute ContactForm contactForm, HttpSession session) {
        Contact contact = Contact.builder()
                .email(contactForm.getEmail())
                .Subject(contactForm.getSubject())
                .message(contactForm.getMessage())
                .build();

        if (contactRepo.save(contact) != null) {
            Message message = Message.builder().messagetext("We Will Contact You Soon..").type(MessageType.green)
                    .build();

            session.setAttribute("message", message);

            return "contact";
        }

        return "contact";

    }
    @RequestMapping("/api/orders")
    public ResponseEntity<List<Object[]>> getOrdersByDateRange(
            @RequestParam("fromDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fromDate,
            @RequestParam("toDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date toDate) {

        List<Object[]> orderCounts = orderServiceImpl.getOrdersBetweenDates(fromDate, toDate);
        if (orderCounts.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(orderCounts);
    }

    @GetMapping("/register")
    public String register(Model model) {
        UserForm userForm = new UserForm();

        model.addAttribute("userForm", userForm);

        return "register";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/do-register", method = RequestMethod.POST)
    public String registerProcess(@ModelAttribute UserForm userForm, HttpSession session) {

        System.out.println(userForm.getEmail());

        User user = User.builder()
                .fullname(userForm.getName())
                .email(userForm.getEmail())
                .contact(userForm.getContact())
                .password(userForm.getPassword())
                .build();

        User saved = userService.saveUser(user);

        if (saved != null) {
            Message message = Message.builder().messagetext("Registration Successful").type(MessageType.green).build();

            session.setAttribute("message", message);
        } else {
            Message message = Message.builder().messagetext("User Exist with this Credentials").type(MessageType.red)
                    .build();

            session.setAttribute("message", message);
            return "redirect:/register";
        }
        return "redirect:/login";
    }

}




    // @RequestMapping(value="/do-login", method=RequestMethod.POST)
    // public String requestMethodName(@ModelAttribute UserForm userForm,HttpSession
    // session) {S

    // return "user/home";
    // }