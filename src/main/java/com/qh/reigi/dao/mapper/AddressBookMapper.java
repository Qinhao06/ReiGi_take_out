package com.qh.reigi.dao.mapper;

import com.qh.reigi.entity.AddressBook;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AddressBookMapper {
    List<AddressBook> getAddressBookList(@Param("id") Long id);

    void addAddressBook(@Param("addressBook") AddressBook addressBook);

    AddressBook getDefaultAddress(@Param("id") Long id);

    void changeDefaultAddress(@Param("id") Long defaultAddress, @Param("isDefault") Long isDefault);

    AddressBook getAddressBookById(@Param("id") Long id);

    void editAddressBook(@Param("addressBook") AddressBook addressBook);


    void deleteAddressBook(@Param("id") Long id);
}
