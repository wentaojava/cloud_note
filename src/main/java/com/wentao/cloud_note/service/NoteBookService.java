package com.wentao.cloud_note.service;

import com.wentao.cloud_note.exceptions.NoteBookException;

import java.util.List;
import java.util.Map;

/**
 * @time: 2018年04月04日
 * @author: wentao
 * @copyright: Wuxi Yazuo ,Ltd.copyright 2015-2025
 */
public interface NoteBookService {

    /**
    *
    * 查询笔记本列表
    *
     * @param userId
    * @return
    * @author wentao
    * @time 2018年04月08日
    */
    public List<Map<String,Object>> findNoteBookByUserId(String userId) throws NoteBookException;

}
