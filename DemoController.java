package com.bonc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bonc.domain.Demo;
import com.bonc.repository.DemoDao;  
   
@RestController  
public class DemoController {  
     
    @Autowired  
    private DemoDao demoDao;  
     
    @RequestMapping("save")  
    public String save(){  
       Demo demoInfo = new Demo();  
       demoInfo.setName("张三");  
       demoInfo.setAge(20);  
       demoDao.save(demoInfo);  
        
       demoInfo = new Demo();  
       demoInfo.setName("李四");  
       demoInfo.setAge(30);  
       demoDao.save(demoInfo);  
        
       return "ok";  
    }  
     
    @RequestMapping("find")  
    public List<Demo> find(){  
       return (List<Demo>) demoDao.findAll();  
    }  
     
    @RequestMapping("findByName")  
    public Demo findByName(){  
       return demoDao.findByName("张三");  
    }  
} 
