package com.ll.base;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

public class Rq {
    private String cmd;
    @Getter
    private String action;
    private Map<String, String> paramMap;
    //request == 수정?id=3&author=오제민

    public Rq(String cmd) {
        String[] cmdBits = cmd.split("\\?", 2);
        this.cmd = cmd;
        action = cmdBits[0].trim();
        if (cmdBits.length == 1) return;
        String[] query = cmdBits[1].split("&");
        paramMap = new HashMap<>();
        for (int i = 0; i < query.length; i++) {
            String[] param = query[i].split("=");
            paramMap.put(param[0], param[1]);
        }
    }

    public int getParamAsInt(String paramName, int defaultValue) {
        try {
            int index = Integer.parseInt(paramMap.get(paramName));
            return index;
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }
}
