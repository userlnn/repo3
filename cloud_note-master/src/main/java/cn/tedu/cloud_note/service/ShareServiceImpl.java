package cn.tedu.cloud_note.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.tedu.cloud_note.dao.NoteDao;
import cn.tedu.cloud_note.dao.ShareDao;
import cn.tedu.cloud_note.entity.Note;
import cn.tedu.cloud_note.entity.Share;
import cn.tedu.cloud_note.util.NoteResult;
import cn.tedu.cloud_note.util.NoteUtil;
@Service("shareService")
public class ShareServiceImpl implements ShareService {
	@Resource
	private ShareDao shareDao;
	
	@Resource
	private NoteDao noteDao;
	
	public NoteResult<Object> shareNote(String noteId) {
		//向cn_share表中插入记录
		Share share = new Share();
		share.setCn_note_id(noteId);
		share.setCn_share_id(NoteUtil.createId());
		
		Note note = noteDao.findByNoteId(noteId);
		
		share.setCn_share_title(note.getCn_note_title());
		share.setCn_share_body(note.getCn_note_body());
		
		shareDao.save(share);
		
		NoteResult<Object> result = new NoteResult<Object>();
		result.setStatus(0);
		result.setMsg("分享成功");
		
		return result;
	}


	public NoteResult<List<Share>> searchLikeNotes(String keyword,int page) {
		Map<String,Object> params = new HashMap<String, Object>();
		int begin = (page-1)*3;
		params.put("keyword", keyword);
		params.put("begin", begin);
		
		List<Share> list = shareDao.searchLikeNotes(params);
		
		NoteResult<List<Share>> result = new NoteResult<List<Share>>();
		result.setStatus(0);
		result.setMsg("查询成功");
		result.setData(list);
		
		return result;
	}

}
