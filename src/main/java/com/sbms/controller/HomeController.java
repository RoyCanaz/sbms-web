/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbms.controller;

import com.sbms.domain.User;
import com.sbms.service.UserService;
import com.sbms.utilities.AppMessage;
import com.sbms.utilities.MessageType;
import java.io.File;
import java.util.Map;
import java.util.Optional;
import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author user
 */
@Controller
@RequestMapping("/user/account")
public class HomeController extends BaseController {

    @Resource
    private UserService userService;
    private static final String TEMP_DIRECTORY = "/uploads";

    @GetMapping("/")
    public String myAccount(ModelMap model, HttpSession session) {
        model.addAttribute("clickMyAccount", true);
        ServletContext context = session.getServletContext();
        File uploadFile = new File(context.getRealPath(TEMP_DIRECTORY) + "/signature");
        if (!uploadFile.exists()) {
            uploadFile.mkdirs();
        }
        //File destinationFile = new File(uploadFile.getPath() + File.separator + originalFilename);
        //inputFile.transferTo(destinationFile);
        return "users/user-master";
    }

    @PostMapping("/change_password")
    public String setNewPassword(ModelMap model, @RequestParam Map<String, String> requestParams, RedirectAttributes redir) {

        User resetUser = userService.getCurrentUser();

        if (resetUser != null) {
            if (requestParams.get("password").length() < 6) {
                model.addAttribute("clickMyAccount", true);
                model.addAttribute("error", "Password Weak.");
                return "users/user-master";
            }
            if (!requestParams.get("password").equals(requestParams.get("confirmPassword"))) {
                model.addAttribute("clickMyAccount", true);
                model.addAttribute("error", "Password do not match");
                return "users/user-master";
            }

            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            resetUser.setPassword(encoder.encode(requestParams.get("password")));
            resetUser.setResetToken(null);
            userService.save(resetUser);
            redir.addFlashAttribute("message", new AppMessage.MessageBuilder().build());
            redir.addFlashAttribute("message", new AppMessage.MessageBuilder(Boolean.TRUE).message("You have successfully changed your password.").messageType(MessageType.MESSAGE).build());
            return "redirect:/user/account/";

        }
        return "redirect:/user/account/";

    }

}
