package com.zjqiu.pwdmng.entity;

import lombok.Data;

@Data
public class BaseResult<T> {

    public final static Integer RESULT_CODE_SUCCESS = 10010;
    public final static Integer RESULT_CODE_FAILED = 10020;
    public final static Integer RESULT_CODE_EXCEPTION = 10030;

    private Integer resultCode;
    private String resultMessage;
    private T resultData;

    public static BaseResult returnSuccessResult( Object resultData ){
        BaseResult result = new BaseResult();
        result.setResultCode( RESULT_CODE_SUCCESS );
        result.setResultMessage("接口成功");
        result.setResultData( resultData );
        return result;
    }

}
