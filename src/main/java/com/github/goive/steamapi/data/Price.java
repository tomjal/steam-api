package com.github.goive.steamapi.data;

import java.math.BigDecimal;
import java.util.Currency;

/**
 * Represents pricing data for the application and contains relevant fields.
 * 
 * @author Ivan Antes-Klobucar
 * @version 1.1
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

    public boolean isFreeToPlay() {
        if (finalPrice == null) {
            return true;
        }

        return false;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((currency == null) ? 0 : currency.hashCode());
        result = prime * result + discountPercent;
        result = prime * result + ((finalPrice == null) ? 0 : finalPrice.hashCode());
        result = prime * result + ((initialPrice == null) ? 0 : initialPrice.hashCode());
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
        Price other = (Price)obj;
        if (currency == null) {
            if (other.currency != null)
                return false;
        } else if (!currency.equals(other.currency))
            return false;
        if (discountPercent != other.discountPercent)
            return false;
        if (finalPrice == null) {
            if (other.finalPrice != null)
                return false;
        } else if (!finalPrice.equals(other.finalPrice))
            return false;
        if (initialPrice == null) {
            if (other.initialPrice != null)
                return false;
        } else if (!initialPrice.equals(other.initialPrice))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Price [currency=" + currency + ", initialPrice=" + initialPrice + ", finalPrice=" + finalPrice
            + ", discountPercent=" + discountPercent + "]";
    }

}
