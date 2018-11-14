/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author user
 */
@Controller
@RequestMapping("error")
public class ErrorController {
    @RequestMapping(value = "/mail", method = RequestMethod.GET)
    public String errorMail(Model model){
        model.addAttribute("pageTitle", "Error Encounted");
        model.addAttribute("errorEmail", true);
        return "error-master";
    }
}
