package com.member.management.controller;

import com.member.management.service.DeviceService;
import com.member.management.service.HandlingViolationService;
import com.member.management.service.MemberService;
import com.member.management.service.UsageInformationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    private final UsageInformationService usageInformationService;
    private final HandlingViolationService handlingViolationService;
    private final DeviceService deviceService;



}
