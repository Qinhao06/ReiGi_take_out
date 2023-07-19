package com.qh.reigi.controller;


import com.qh.reigi.common.R;
import com.qh.reigi.entity.AddressBook;
import com.qh.reigi.service.AddressBookService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/addressBook")
public class AddressBookController {

    @Autowired
    AddressBookService addressBookService;

    @GetMapping("/list")
    public R<List<?>> list(HttpServletRequest request){
        return R.success(addressBookService.getAddressBookList(request));
    }

    @PostMapping("/add")
    public R<String> add(HttpServletRequest request, @RequestBody AddressBook addressBook) {
        addressBookService.addAddressBook(request, addressBook);
        return R.success("添加成功");
    }

    @PutMapping("/default")
    public R<String> defaultAddress(HttpServletRequest request, @RequestBody AddressBook addressBook) {
        addressBookService.defaultAddress(request, addressBook.getId());
        return R.success("设置成功");
    }

    @GetMapping("/default")
    public R<AddressBook> defaultAddress(HttpServletRequest request) {
        AddressBook addressBook = addressBookService.defaultAddress(request);
        return R.success(addressBook);
    }

    @GetMapping("/{id}")
    public R<AddressBook> getAddressBookById(@PathVariable("id") Long id) {
        AddressBook addressBook = addressBookService.getAddressBookById(id);
        return R.success(addressBook);
    }

    @PutMapping("/edit")
    public R<String> edit(HttpServletRequest request, @RequestBody AddressBook addressBook) {
        addressBookService.editAddressBook(request, addressBook);
        return R.success("修改成功");
    }

    @DeleteMapping("/delete")
    public R<String> delete(@RequestParam("ids") Long id) {
        addressBookService.deleteAddressBook(id);
        return R.success("删除成功");
    }
}
