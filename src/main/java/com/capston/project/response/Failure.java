package com.capston.project.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Failure<T> implements Result {

    private T data;
    private String msg;

}