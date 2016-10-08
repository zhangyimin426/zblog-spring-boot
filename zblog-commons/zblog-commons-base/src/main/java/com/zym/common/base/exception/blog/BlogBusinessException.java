package com.zym.common.base.exception.blog;


import com.zym.common.base.statuscode.ResultStatus;

/**
 * @author Gavin
 * @date 2016-09-27
 */
public class BlogBusinessException extends RuntimeException {

    private ResultStatus status;

    public BlogBusinessException() {
    }

    public BlogBusinessException(ResultStatus resultStatus) {
        super(resultStatus.getMsg());
        this.status = resultStatus;
    }

    public ResultStatus getStatus() {
        return status;
    }

    public void setStatus(ResultStatus status) {
        this.status = status;
    }
}
