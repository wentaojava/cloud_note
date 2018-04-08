package com.wentao.cloud_note.service.serviceImpl;

import com.wentao.cloud_note.dao.NoteBookDao;
import com.wentao.cloud_note.exceptions.NoteBookException;
import com.wentao.cloud_note.service.NoteBookService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 笔记本列表业务实现层
 *
 * @time: 2018年04月08日
 * @author: wentao
 * @copyright: Wuxi Yazuo ,Ltd.copyright 2015-2025
 */
@Service("noteBookService")
public class NoteBookServiceImpl implements NoteBookService {
    @Resource
    private NoteBookDao noteBookDao;
    /**
     *
     * 查询笔记本列表
     *
     * @param userId
     * @return
     * @author wentao
     * @time 2018年04月08日
     */
    public List<Map<String,Object>> findNoteBookByUserId(String userId) throws NoteBookException {
        if(userId==null || "".equals(userId)){
            throw new NoteBookException("用户Id不能为空");
        }
        List<Map<String,Object>> noteBooks=noteBookDao.findNoteBookByUserId(userId);
        return noteBooks;
    }
}
