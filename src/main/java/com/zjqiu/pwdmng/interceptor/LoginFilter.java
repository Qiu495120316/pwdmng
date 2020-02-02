package com.zjqiu.pwdmng.interceptor;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class LoginFilter {

    private List<String> path = new ArrayList<>();
    private List<String> page = new ArrayList<>();
}
