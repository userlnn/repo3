package test.dao;

import java.util.List;
import java.util.Map;

import org.junit.Test;

import cn.tedu.cloud_note.dao.NoteDao;
import cn.tedu.cloud_note.entity.Note;
import test.TestCase;

public class TestNoteDao extends TestCase{
	NoteDao dao = super.getContext().getBean("noteDao", NoteDao.class);
	
	@Test
	public void testDao(){
		List<Map> list = dao.findByBookId("6d763ac9-dca3-42d7-a2a7-a08053095c08");
		for (Map note:list) {
			System.out.println("id£º"+note.get("cn_note_id")+"\ntitle£º"+note.get("cn_note_title"));
		}
	}
	
	@Test
	public void loadNoteByNoteId(){
		Note note = dao.findByNoteId("11a06d13-3285-46a9-bbd5-44cf932fee28");
		System.out.println(note.getCn_note_title());
		System.out.println(note.getCn_note_body());
		System.out.println(note.getCn_note_create_time());
	}
	
	
	@Test
	public void update(){
		Note note = new Note();
		note.setCn_note_id("11a06d13-3285-46a9-bbd5-44cf932fee28");
		note.setCn_note_title("mixinan");
		note.setCn_note_body("java ok");
		Long time = System.currentTimeMillis();
		note.setCn_note_last_modify_time(time);
		
		int num = dao.updateNote(note);
		
		System.out.println(num);
	}
	
}
