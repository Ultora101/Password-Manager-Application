package com.password.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class PasswordEntry {

    private String id;
    private String name;
    private String username;
    private String encryptedPassword;
    private String category;
    private LocalDateTime lastModified;

    public PasswordEntry() {
    }

    public PasswordEntry(String name, String username, String encryptedPassword, String category) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.username = username;
        this.encryptedPassword = encryptedPassword;
        this.category = category;
        this.lastModified = LocalDateTime.now();
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public String getCategory() {
        return category;
    }

    public LocalDateTime getLastModified() {
        return lastModified;
    }

    // Setters
    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEncryptedPassword(String p) {
        this.encryptedPassword = p;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setLastModified(LocalDateTime lastModified) {
        this.lastModified = lastModified;
    }

    public void touchModified() {
        this.lastModified = LocalDateTime.now();
    }

}
