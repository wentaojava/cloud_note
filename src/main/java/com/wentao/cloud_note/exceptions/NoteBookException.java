package com.wentao.cloud_note.exceptions;

/**
 * 展示笔记本列表的异常捕获类
 *
 * @time: 2018年04月08日
 * @author: wentao
 * @copyright: Wuxi Yazuo ,Ltd.copyright 2015-2025
 */
public class NoteBookException extends RuntimeException {

    public NoteBookException() {
    }

    public NoteBookException(String message) {
        super(message);
    }

    public NoteBookException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoteBookException(Throwable cause) {
        super(cause);
    }

    public NoteBookException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
