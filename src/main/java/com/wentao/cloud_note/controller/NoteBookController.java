package com.wentao.cloud_note.controller;

import com.wentao.cloud_note.service.NoteBookService;
import com.wentao.cloud_note.utils.JsonForResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 笔记本列表控制层
 *
 * @time: 2018年04月08日
 * @author: wentao
 * @copyright: Wuxi Yazuo ,Ltd.copyright 2015-2025
 */
@Controller
@RequestMapping("/noteBook")
public class NoteBookController {
    @Resource
    private NoteBookService noteBookService;

    /**
    *
    * 异常捕捉
    *
     * @param e
    * @return
    * @author wentao
    * @time 2018年04月08日
    */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Object exceptionHandler(Exception e) {
        return new JsonForResult(e);
    }

    /**
    *
    * 查询笔记本列表
    *
     * @param userId
    * @return
    * @author wentao
    * @time 2018年04月08日
    */
    @RequestMapping("/findNoteBook.do")
    @ResponseBody
    public Object findNoteBookByUserId(String userId){
        List<Map<String,Object>> noteBooks=noteBookService.findNoteBookByUserId(userId);
        return new JsonForResult(noteBooks);
    }
}
