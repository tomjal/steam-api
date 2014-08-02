package com.github.goive.steamapi.data;

import java.net.URL;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Represents information about support for the application and contains relevant fields.
 * 
 * @author Ivan Antes-Klobucar
 * @version 2.1
 */
public class SupportInfo {

    private URL url;
    private String email;

    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder() //
            .append(url) //
            .append(email) //
            .hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof SupportInfo)) {
            return false;
        }

        SupportInfo other = (SupportInfo)obj;

        return new EqualsBuilder() //
            .append(url, other.url) //
            .append(email, other.email) //
            .isEquals();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(url) //
            .append(email) //
            .toString();
    }

}
