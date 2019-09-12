package cn.tedu.cloud_note.dao;

import java.util.List;
import java.util.Map;

import cn.tedu.cloud_note.entity.Share;

public interface ShareDao {
	public void save(Share share);
	public List<Share> searchLikeNotes(Map params);
}
