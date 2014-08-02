package com.github.goive.steamapi.data;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Represents a category for the application. Examples may be "single player", "multi player", "co-op", etc.
 * 
 * @author Ivan Antes-Klobucar
 * @version 2.1
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
        return new HashCodeBuilder() //
            .append(id) //
            .append(description) //
            .hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof Category)) {
            return false;
        }

        Category other = (Category)obj;

        return new EqualsBuilder() //
            .append(id, other.id) //
            .append(description, other.description) //
            .isEquals();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(id) //
            .append(description) //
            .toString();
    }

}
