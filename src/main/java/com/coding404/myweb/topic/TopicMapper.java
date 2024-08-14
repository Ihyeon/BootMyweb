package com.coding404.myweb.topic;

import com.coding404.myweb.command.TopicVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

@Mapper
public interface TopicMapper {

    public ArrayList<TopicVO> getList(); // 글 목록 조회 (전체)
    public ArrayList<TopicVO> getListMe(String topicWriter); // 글 목록 조회 (사용자)
    public int topicInsert(TopicVO vo); // 글 등록


}
