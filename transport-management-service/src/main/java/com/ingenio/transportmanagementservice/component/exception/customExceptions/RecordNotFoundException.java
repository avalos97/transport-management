package com.ingenio.transportmanagementservice.component.exception.customExceptions;

import static com.ingenio.transportmanagementservice.component.util.Constants.GENERIC_RECORD_NOT_FOUND_MESSAGE;

import com.ingenio.transportmanagementservice.component.exception.error.ErrorCode;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecordNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private final String errMsgKey;
    private final String errorCode;

    public RecordNotFoundException(ErrorCode code) {
        super(code.getErrMsgKey());
        this.errMsgKey = code.getErrMsgKey();
        this.errorCode = code.getErrCode();
    }

    public RecordNotFoundException(final Long id) {
        super(GENERIC_RECORD_NOT_FOUND_MESSAGE + id);
        this.errMsgKey = ErrorCode.RECORD_NOT_FOUND.getErrMsgKey();
        this.errorCode = ErrorCode.RECORD_NOT_FOUND.getErrCode();
    }
}
