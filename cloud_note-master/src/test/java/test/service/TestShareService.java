package test.service;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import cn.tedu.cloud_note.entity.Share;
import cn.tedu.cloud_note.service.ShareService;
import cn.tedu.cloud_note.util.NoteResult;
import test.TestCase;

public class TestShareService extends TestCase{
	private ShareService shareService;
	
	@Before
	public void init(){
		shareService = super.getContext().getBean("shareService", ShareService.class);
	}
	
	@Test
	public void execute(){
		NoteResult<List<Share>> result = shareService.searchLikeNotes("a",1);
		
		System.out.println(result.getData());
		
	}
}
