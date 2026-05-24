package ru.khalov.tests.productmicroservice.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionMessage {
    private LocalDateTime timestamp;
    private String msg;
}
