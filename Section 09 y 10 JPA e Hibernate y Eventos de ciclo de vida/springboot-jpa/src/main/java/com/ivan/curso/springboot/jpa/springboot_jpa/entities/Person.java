package com.ivan.curso.springboot.jpa.springboot_jpa.entities;


import jakarta.persistence.*;


@Entity
@Table(name = "persons")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String lastName;

    @Embedded
    private Audit audit = new Audit();

    @Column(name = "programming_language")
    private String programmingLanguage;

    public Person() {
    }

    public Person(String name, String lastName) {
        this.name = name;
        this.lastName = lastName;
    }

    public Person(String name, String lastName, String programmingLanguage) {
        this.name = name;
        this.lastName = lastName;
        this.programmingLanguage = programmingLanguage;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getProgrammingLanguage() {
        return programmingLanguage;
    }

    public void setProgrammingLanguage(String programmingLanguage) {
        this.programmingLanguage = programmingLanguage;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", programmingLanguage='" + programmingLanguage + '\'' +
                ", createdAt=" + audit.getCreatedAt() +
                ", updatedAt=" + audit.getUpdatedAt() +
                '}';
    }
}
