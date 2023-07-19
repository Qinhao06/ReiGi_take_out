package com.qh.reigi.service;

import com.qh.reigi.common.R;
import com.qh.reigi.entity.AddressBook;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public interface AddressBookService {
    List<?> getAddressBookList(HttpServletRequest request);

    void addAddressBook(HttpServletRequest request, AddressBook addressBook);

    void defaultAddress(HttpServletRequest request, Long addressBookId);

    AddressBook getAddressBookById(Long id);

    void editAddressBook(HttpServletRequest request, AddressBook addressBook);

    void deleteAddressBook(Long id);

    AddressBook defaultAddress(HttpServletRequest request);
}
