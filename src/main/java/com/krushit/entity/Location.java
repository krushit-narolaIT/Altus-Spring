package com.krushit.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "locations")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "location_id")
    private int id;

    @Column(name = "name", nullable = false, unique = true, length = 20)
    private String name;

    @Column(name = "is_active", nullable = false, columnDefinition = "DEFAULT TRUE")
    private boolean isActive;

    public Location() {}

    public Location(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Location(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean active) {
        isActive = active;
    }

    @Override
    public String toString() {
        return "Location{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
