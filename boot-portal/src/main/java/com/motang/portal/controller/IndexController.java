package com.motang.portal.controller;


import com.motang.portal.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description 首页控制器
 * @author liuhu
 * @Date 2020/12/15 14:45
 */
@RestController
public class IndexController {

    @Autowired
    private IBookService bookService;

    @GetMapping("index")
    public void index(Model model){

    }

}
