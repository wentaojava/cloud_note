/**
 * 
 */
package com.wentao.cloud_note.utils;

import com.wentao.cloud_note.exceptions.NoteBookException;
import com.wentao.cloud_note.exceptions.PasswordException;
import com.wentao.cloud_note.exceptions.UserNameException;
import com.wentao.cloud_note.exceptions.UserNotFoundException;

/**
 * 
 * @author wentao
 */
public class JsonForResult {
	private static final Integer SUCCESS=0;
	private static final Integer USER_ERROR=1;
	private static final Integer PASSWORD_ERROR=2;
	private static final Integer NOTE_BOOK_ERROR=3;
	private Integer state;
	private String message;
	private Object data;
	
	public JsonForResult() {
	}
	
	public JsonForResult(Object data) {
		state=SUCCESS;
		this.data=data;
		
	}
	
	public JsonForResult(Throwable e) {
		if(e instanceof UserNotFoundException ||e instanceof UserNameException) {
		    state=USER_ERROR;
		   }
		if(e instanceof PasswordException) {
			state=PASSWORD_ERROR;
			}
		if(e instanceof NoteBookException){
			state=NOTE_BOOK_ERROR;
		}
		this.message=e.getMessage();
		
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	@Override
	public String toString() {
		return "JsonForException [State=" + state + ", message=" + message + ", data=" + data + "]";
	}
	

}
