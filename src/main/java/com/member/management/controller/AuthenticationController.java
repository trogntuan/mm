package com.member.management.controller;

import com.member.management.model.dto.MemberDto;
import com.member.management.model.entity.Member;
import com.member.management.service.MemberService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping()
@RequiredArgsConstructor
public class AuthenticationController {

    private final MemberService memberService;

    @GetMapping({"/", "/login"})
    public String showLogin(Model model) {
        return "/login";
    }

    @GetMapping("/register")
    public String showRegister(Model model) {
        MemberDto memberDto = new MemberDto();
        model.addAttribute("memberDto", memberDto);
        return "/register";
    }

    @PostMapping("/register/save")
    public String registration(@Valid MemberDto memberDto,
                               BindingResult result,
                               RedirectAttributes redirectAttributes,
                               Model model){
        Optional<Member> existingMember = memberService.findById(memberDto.getId());
        if (existingMember.isPresent()) {
            result.rejectValue("id", "member.duplicateId", "MSSV da ton tai");
        }
        if (result.hasErrors()) {
            model.addAttribute("memberDto", memberDto);
            model.addAttribute("errorMessage", "MSSV đã tồn tại, vui lòng nhập lại!");
            return "/register";
        }
        memberService.save(memberDto);
        redirectAttributes.addFlashAttribute("successMessage", "Đăng ký thành viên thành công, vui lòng đăng nhập");
        return "redirect:/login";
    }

    @GetMapping("/home")
    public String home(HttpSession session, Model model) {
        User user = (User) session.getAttribute("loggedInUser");
        model.addAttribute("name", user.getUsername());
        model.addAttribute("id", user.getUsername());
        return "/home";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

}
