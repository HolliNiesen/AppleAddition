package com.mathtastic.entity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * User represents a single user
 * Created by Holli on 7/11/2017.
 */
public class User {
    private int userId;
    private String firstName;
    private String lastName;
    private String email;
    private String dateOfBirth;
    private String password;

    /**
     * Empty constructor
     */
    public User() {}

    /**
     * Constructor
     * @param userId user's id as assigned by database
     * @param firstName user's first name
     * @param lastName user's last name
     * @param email user's email
     * @param dateOfBirth user's date of birth
     * @param password user's password
     */
    public User(int userId, String firstName, String lastName, String email, LocalDate dateOfBirth, String password) {

        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dateOfBirth = formatDateOfBirth(dateOfBirth);
        this.password = password;
    }

    /**
     * Formats a LocalDate and converts it to a string.
     * @param dateOfBirth The date to convert.
     * @return The converted date.
     */
    private String formatDateOfBirth(LocalDate dateOfBirth) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d, yyyy");
        return dateOfBirth.format(formatter);
    }

    /**
     * Gets the user's id
     * @return the user's id
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Gets the user's first name
     * @return the user's first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Gets the user's last name
     * @return the user's last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Gets the user's email
     * @return the user's email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Gets the user's date of birth
     * @return the user's date of birth
     */
    public String getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * Gets the user's password
     * @return the user's password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Gets a string representation of the class.
     * @return The string representation of the class.
     */
    public String toString() {
        return "User { "
                + "userId= '" + userId + "', "
                + "firstName= '" + firstName + "', "
                + "lastName= '" + lastName + "', "
                + "email= '" + email + "', "
                + "dateOfBirth= '" + dateOfBirth + "', "
                + "password= '" + password + "'"
                + "}";
    }
}
