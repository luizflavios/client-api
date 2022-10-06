package br.com.client.api.enums;

import lombok.Getter;

@Getter
public enum ErrorType {

    DATABASE_INTEGRITY("Request violate database rules"), BUSINESS_INTEGRITY("Request violate business rules");

    private final String title;

    ErrorType(String title) {
        this.title = title;
    }

}
