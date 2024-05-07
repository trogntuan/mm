//package com.member.management.model.dto;
//
//import fa.training.eventbox.constant.DateTimeConstant;
//import jakarta.validation.constraints.NotBlank;
//import jakarta.validation.constraints.NotNull;
//import lombok.Data;
//import org.springframework.format.annotation.DateTimeFormat;
//
//import java.time.LocalDateTime;
//
//@Data
////@Getter
////@Setter
////@ToString
////@EqualsAndHashCode
////@RequiredArgsConstructor
//public class EventFormDto {
//
//    private Long id;
//
//    @NotBlank(message = "{common.error.required}")
//    private String name;
//
//    private String description;
//
//    @NotNull(message = "{common.error.required}")
//    @DateTimeFormat(pattern = DateTimeConstant.DATE_TIME_FORMAT)
//    private LocalDateTime startDateTime;
//
//    @NotNull(message = "{common.error.required}")
//    @DateTimeFormat(pattern = DateTimeConstant.DATE_TIME_FORMAT)
//    private LocalDateTime endDateTime;
//}
