//package com.motang.service.controller;
//
//
//
//import com.motang.admin.entity.Book;
//import com.motang.service.service.IBookService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
///**
// * @author liuhu
// * @Description 书籍表 前端控制器
// * @Date 2020/12/10 21:07
// */
//@RestController
//@RequestMapping("/book")
//public class BookController {
//
//    @Autowired
//    private IBookService bookService;
//
//    @GetMapping
//    public Book book(){
//        return bookService.selectByBookName("武道神尊");
//    }
//
////    @Autowired
////    private MinioClient minioClient;
//
////    @GetMapping("upload")
////    public void upload() {
////        try {
////            minioClient.putObject("book", "test.jpg", "D:\\lh\\test.jpg");
////        } catch (InvalidBucketNameException e) {
////            e.printStackTrace();
////        } catch (Exception e) {
////            e.printStackTrace();
////        }
////    }
////
////    @GetMapping("image")
////    public String image() {
////        try {
////            return minioClient.presignedGetObject("book", "test.jpg");
////        } catch (InvalidBucketNameException e) {
////            e.printStackTrace();
////        } catch (Exception e) {
////            e.printStackTrace();
////        }
////        return null;
////    }
//
//}
