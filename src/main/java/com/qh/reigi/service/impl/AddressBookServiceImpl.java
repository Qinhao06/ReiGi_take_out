package com.qh.reigi.service.impl;

import com.qh.reigi.dao.mapper.AddressBookMapper;
import com.qh.reigi.entity.AddressBook;
import com.qh.reigi.entity.User;
import com.qh.reigi.service.AddressBookService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AddressBookServiceImpl implements AddressBookService {


    @Autowired
    AddressBookMapper addressBookMapper;

    @Override
    public List<?> getAddressBookList(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        return addressBookMapper.getAddressBookList(user.getId());
    }

    @Override
    public void addAddressBook(HttpServletRequest request, AddressBook addressBook) {
        User user = (User) request.getSession().getAttribute("user");
        addressBook.setUserId(user.getId());
        addressBook.setCreateUser(user.getId());
        addressBook.setUpdateUser(user.getId());
        addressBook.setCreateTime(LocalDateTime.now());
        addressBook.setUpdateTime(LocalDateTime.now());
        addressBook.setIsDefault(0);
        addressBook.setIsDeleted(0);
        addressBookMapper.addAddressBook(addressBook);
    }

    @Override
    public void defaultAddress(HttpServletRequest request, Long addressBook) {
        User user = (User) request.getSession().getAttribute("user");
        AddressBook defaultAddress = addressBookMapper.getDefaultAddress(user.getId());
        addressBookMapper.changeDefaultAddress(defaultAddress.getId(), 0L);
        addressBookMapper.changeDefaultAddress(addressBook, 1L);

    }

    @Override
    public AddressBook getAddressBookById(Long id) {
        return addressBookMapper.getAddressBookById(id);
    }

    @Override
    public void editAddressBook(HttpServletRequest request, AddressBook addressBook) {
        addressBookMapper.editAddressBook(addressBook);
    }

    @Override
    public void deleteAddressBook(Long id) {
        addressBookMapper.deleteAddressBook(id);
    }

    @Override
    public AddressBook defaultAddress(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        return addressBookMapper.getDefaultAddress(user.getId());
    }
}
