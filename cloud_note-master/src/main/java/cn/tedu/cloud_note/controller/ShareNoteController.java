package cn.tedu.cloud_note.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.cloud_note.entity.Share;
import cn.tedu.cloud_note.service.ShareService;
import cn.tedu.cloud_note.util.NoteResult;

@Controller
@RequestMapping("/share")
public class ShareNoteController {
	@Resource
	private ShareService shareService;
	
	@RequestMapping("/add.do")
	@ResponseBody
	public NoteResult<Object> execute(String noteId){
		NoteResult<Object> result = shareService.shareNote(noteId);
		return result;
	}
	
	
	@RequestMapping("/search.do")
	@ResponseBody
	public NoteResult<List<Share>> search(String keyword,int page){
		NoteResult<List<Share>> result = shareService.searchLikeNotes(keyword,page);
		
		if (result.getData().size()!=0) {
			result.setStatus(0);
			result.setMsg("查询成功");
			result.setData(result.getData());
		}else{
			result.setStatus(1);
			result.setMsg("没有符合的数据");
		}
		
		return result;
	}
	
}
