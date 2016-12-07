package com.springapp.mvc.integration.shop.dto;

import java.math.BigDecimal;

/**
 * Created by Иван on 05.12.2016.
 */
public class Software extends Item {
    private static final long serialVersionUID = -6217859636747384825L;

    private String version;

    public Software() {
        super();
    }

    public Software(String title, String publisher, BigDecimal price, int yearPublished,
                    String version) {
        super(title, publisher, price, yearPublished);
        this.version = version;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((version == null) ? 0 : version.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        Software other = (Software) obj;
        if (version == null) {
            if (other.version != null)
                return false;
        } else if (!version.equals(other.version))
            return false;
        return true;
    }
}