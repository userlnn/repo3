package test.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import cn.tedu.cloud_note.dao.ShareDao;
import cn.tedu.cloud_note.entity.Share;
import test.TestCase;

public class TestSearchDao extends TestCase{
	ShareDao dao = super.getContext().getBean("shareDao", ShareDao.class);
	
	@Test
	public void test(){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("keyword", "a");
		map.put("begin", 1);
		List<Share> list = dao.searchLikeNotes(map);
		System.out.println(list);
	}
}
