package com.wentao.cloud_note.dao;

import java.util.List;
import java.util.Map;

/**
 * 笔记本列表
 *
 * @time: 2018年04月04日
 * @author: wentao
 * @copyright: Wuxi Yazuo ,Ltd.copyright 2015-2025
 */
public interface NoteBookDao {

    List<Map<String,Object>> findNoteBookByUserId(String userId);
}
