package com.wniemiec.booklibrary.desktop.reader;

import com.wniemiec.booklibrary.business.reader.ReaderDTO;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ReaderModel {
    private final Long id;
    private final StringProperty pesel = new SimpleStringProperty("");
    private final StringProperty name = new SimpleStringProperty("");
    private final StringProperty surname = new SimpleStringProperty("");
    private final StringProperty city = new SimpleStringProperty("");
    private final StringProperty postalCode = new SimpleStringProperty("");

    public ReaderModel(ReaderDTO dto) {
        this(
                dto.getId(),
                String.valueOf(dto.getPesel()),
                dto.getName(),
                dto.getSurname(),
                dto.getCity(),
                dto.getPostalCode()
        );
    }

    private ReaderModel(Long id, String pesel, String name, String surname, String city, String postalCode) {
        this.id = id;
        setPesel(pesel);
        setName(name);
        setSurname(surname);
        setCity(city);
        setPostalCode(postalCode);
    }

    public Long getId() {
        return id;
    }

    public String getPesel() {
        return pesel.get();
    }

    public StringProperty peselProperty() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel.set(pesel);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getSurname() {
        return surname.get();
    }

    public StringProperty surnameProperty() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname.set(surname);
    }

    public String getCity() {
        return city.get();
    }

    public StringProperty cityProperty() {
        return city;
    }

    public void setCity(String city) {
        this.city.set(city);
    }

    public String getPostalCode() {
        return postalCode.get();
    }

    public StringProperty postalCodeProperty() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode.set(postalCode);
    }
}
