package com.example.demo.controllers;

import com.example.demo.dtos.AdminDTO;
import com.example.demo.dtos.PasswordDTO;
import com.example.demo.models.Admin;
import com.example.demo.serviceImpl.AdminServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin")
@Slf4j
public class AdminController {
    private AdminServiceImpl adminService;
    @Autowired
    public AdminController(AdminServiceImpl adminService) {
        this.adminService = adminService;
    }
    @GetMapping("/admin-register")
    public String indexPage(Model model){
        model.addAttribute("admin", new AdminDTO());
        return "admin-signup";
    }
    @GetMapping("/admin-login")
    public ModelAndView loginPage(){
        return  new ModelAndView("admin-login")
                .addObject("admin", new AdminDTO());
    }
    @PostMapping("/admin-signup")
    public String signUp(@ModelAttribute AdminDTO adminDto){
        Admin admin = adminService.saveAdmin.apply(new Admin(adminDto));
        log.info("Admin details ---> {}", admin);
        return "admin-successful-register";
    }
    @PostMapping("/admin-login")
    public String loginUser(@ModelAttribute AdminDTO adminDto, HttpServletRequest request, Model model){
        Admin admin = adminService.findUsersByUsername.apply(adminDto.getUsername());
        log.info("Admin details ---> {}", admin);
        if (adminService.verifyUserPassword
                .apply(PasswordDTO.builder()
                        .password(adminDto.getPassword())
                        .hashPassword(admin.getPassword())
                        .build())){
            HttpSession session = request.getSession();
            session.setAttribute("AdminID", admin.getId());
            return "redirect:/products/admin/all";
        }
        return "redirect:/admin/admin-login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "index";
    }
}
