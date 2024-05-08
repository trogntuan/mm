package com.member.management.controller;

import com.member.management.model.dto.ChangePasswordDto;
import com.member.management.model.entity.HandlingViolation;
import com.member.management.model.entity.UsageInformation;
import com.member.management.service.HandlingViolationService;
import com.member.management.service.MemberService;
import com.member.management.service.UsageInformationService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    private final UsageInformationService usageInformationService;
    private final HandlingViolationService handlingViolationService;

    @GetMapping("/change-password")
    public String showChangePassword(HttpSession session, Model model) {
        User user = (User) session.getAttribute("loggedInUser");
        String name = memberService.getNameById(user.getUsername());
        ChangePasswordDto changePasswordDto = new ChangePasswordDto();
        model.addAttribute("changePasswordDto", changePasswordDto);
        model.addAttribute("name", name);
        return "/user/change-password";
    }

    @PostMapping("/change-password/save")
    public String changePassword(@Valid ChangePasswordDto changePasswordDto,
                                 HttpSession session,
                                 Model model,
                                 BindingResult bindingResult,
                                 RedirectAttributes redirectAttributes) {
        User user = (User) session.getAttribute("loggedInUser");
        if (!memberService.checkPassword(user.getUsername(), changePasswordDto.getOldPassword())) {
            bindingResult.rejectValue("oldPassword", "common.error.invalid", "Mật khẩu cũ không đúng");
        }
        if (bindingResult.hasErrors()) {
            model.addAttribute("errorMessage", "Mật khẩu cũ không đúng, vui lòng nhập lại");
            return "/user/change-password";
        }
        memberService.changePassword(user.getUsername(), changePasswordDto.getNewPassword());
        redirectAttributes.addFlashAttribute("successMessage", "Đổi mật khẩu thành công");
        return "redirect:/user/change-password";
    }

    @GetMapping("/violations")
    public String showViolations(HttpSession session,
                                 @RequestParam(defaultValue = "0") int page,
                                 @RequestParam(defaultValue = "2") int size,
                                 Model model) {
        User user = (User) session.getAttribute("loggedInUser");
        PageRequest pageRequest = PageRequest.of(page, size);
        Specification<HandlingViolation> specification = (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("member").get("id"), user.getUsername());
        Page<HandlingViolation> handlingViolationPage = handlingViolationService.getViolationsByMemberId(specification, pageRequest);
        model.addAttribute("violations", handlingViolationPage);
        String name = memberService.getNameById(user.getUsername());
        model.addAttribute("name", name);
        return "/user/violations";
    }

    @GetMapping("/usage-information")
    public String showUsageInformation(HttpSession session,
                                       @RequestParam(defaultValue = "0") int page,
                                       @RequestParam(defaultValue = "2") int size,
                                       Model model) {
        User user = (User) session.getAttribute("loggedInUser");
        PageRequest pageRequest = PageRequest.of(page, size);
        Specification<UsageInformation> specification = (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("member").get("id"), user.getUsername());
        Page<UsageInformation> usageInformationPage = usageInformationService.getUsageInformationByMemberId(specification, pageRequest);
        model.addAttribute("usageInformation", usageInformationPage);
        String name = memberService.getNameById(user.getUsername());
        model.addAttribute("name", name);
        return "/user/usage-information";
    }

    @GetMapping("/borrowing-device")
    public String showBorrowingDevice(HttpSession session,
                                      @RequestParam(defaultValue = "0") int page,
                                      @RequestParam(defaultValue = "2") int size,
                                      Model model) {
        User user = (User) session.getAttribute("loggedInUser");
        PageRequest pageRequest = PageRequest.of(page, size);
        Specification<UsageInformation> specification = (root, query, criteriaBuilder) -> {
            return criteriaBuilder.and(
                    criteriaBuilder.equal(root.get("member").get("id"), user.getUsername()),
                    criteriaBuilder.isNull(root.get("checkIn")),
                    criteriaBuilder.lessThanOrEqualTo(root.get("borrowTime"), java.time.LocalDateTime.now()),
                    criteriaBuilder.greaterThanOrEqualTo(root.get("returnTime"), java.time.LocalDateTime.now())
            );
        };
        Page<UsageInformation> usageInformationPage = usageInformationService.getUsageInformationByMemberId(specification, pageRequest);
        model.addAttribute("usageInformation", usageInformationPage);
        String name = memberService.getNameById(user.getUsername());
        model.addAttribute("name", name);
        return "/user/borrowing-device";
    }




}
