package com.github.goive.steamapi.data;

import java.math.BigDecimal;
import java.util.Currency;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Represents pricing data for the application and contains relevant fields.
 * 
 * @author Ivan Antes-Klobucar
 * @version 2.1
 */
public class Price {

    private Currency currency;
    private BigDecimal initialPrice;
    private BigDecimal finalPrice;
    private int discountPercent;

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public BigDecimal getInitialPrice() {
        return initialPrice;
    }

    public void setInitialPrice(BigDecimal initialPrice) {
        this.initialPrice = initialPrice;
    }

    public BigDecimal getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(BigDecimal finalPrice) {
        this.finalPrice = finalPrice;
    }

    public int getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(int discountPercent) {
        this.discountPercent = discountPercent;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder() //
            .append(currency) //
            .append(initialPrice) //
            .append(finalPrice) //
            .append(discountPercent) //
            .toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof Price)) {
            return false;
        }

        Price other = (Price)obj;

        return new EqualsBuilder() //
            .append(currency, other.currency) //
            .append(initialPrice, other.initialPrice) //
            .append(finalPrice, other.finalPrice) //
            .append(discountPercent, other.discountPercent) //
            .isEquals();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(currency) //
            .append(initialPrice) //
            .append(finalPrice) //
            .append(discountPercent) //
            .toString();
    }

}
