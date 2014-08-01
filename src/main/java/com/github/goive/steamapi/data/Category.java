package com.github.goive.steamapi.data;

/**
 * Represents a category for the application. Examples may be "single player", "multi player", "co-op", etc.
 * 
 * @author Ivan Antes-Klobucar
 * @version 1.1
 */
public class Category {

    private int id;
    private String description;

    public Category(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Category other = (Category)obj;
        if (id != other.id)
            return false;
        return true;
    }

}
