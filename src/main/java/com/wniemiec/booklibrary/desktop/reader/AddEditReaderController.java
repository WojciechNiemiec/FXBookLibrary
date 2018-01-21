package com.wniemiec.booklibrary.desktop.reader;

import com.wniemiec.booklibrary.business.address.Address;
import com.wniemiec.booklibrary.business.reader.Reader;
import com.wniemiec.booklibrary.business.reader.ReaderRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Objects;

public class AddEditReaderController {

    private final ReaderRepository readerRepository = new ReaderRepository();

    @FXML
    private TextField pesel;

    @FXML
    private TextField name;

    @FXML
    private TextField surname;

    @FXML
    private TextField city;

    @FXML
    private TextField postalCode;

    @FXML
    private DatePicker date;

    private Reader readerToUpdate;

    public void loadReaderToUpdate(Long id) {
        readerToUpdate = readerRepository.findById(id);
        pesel.setText(String.valueOf(readerToUpdate.getPesel()));
        name.setText(readerToUpdate.getName());
        surname.setText(readerToUpdate.getSurname());

        Address address = readerToUpdate.getAddress();
        if (Objects.nonNull(address)) {
            city.setText(address.getCity());
            postalCode.setText(address.getPostalCode());
        }
        if (Objects.nonNull(readerToUpdate.getBirthDate())) {
            date.setValue(LocalDate.from(readerToUpdate.getBirthDate()));
        }
    }

    public void save(ActionEvent e) {
        Reader reader = (Objects.nonNull(readerToUpdate)) ? readerToUpdate : new Reader();

        reader.setName(name.getText());
        reader.setSurname(surname.getText());
        reader.setBirthDate(ZonedDateTime.of(date.getValue(), LocalTime.MIN, ZoneId.systemDefault()));

        if (pesel.getText().matches("^[0-9]+$")) {
            reader.setPesel(BigDecimal.valueOf(Long.valueOf(pesel.getText())));
        }
        Address address = new Address();
        address.setCity(city.getText());
        address.setPostalCode(postalCode.getText());

        reader.setAddress(address);

        readerRepository.saveOrUpdate(reader);
    }
}
