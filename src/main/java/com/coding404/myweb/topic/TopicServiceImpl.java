package com.coding404.myweb.topic;

import com.coding404.myweb.command.TopicVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service("topicService")
public class TopicServiceImpl implements TopicService{

    @Autowired
    private TopicMapper topicMapper;

    // 글 목록 조회 (전체)
    @Override
    public ArrayList<TopicVO> getList() {
        return topicMapper.getList();
    }

    @Override
    public ArrayList<TopicVO> getListMe(String topicWriter) {
        return topicMapper.getListMe(topicWriter);
    }

    // 글 등록
    @Override
    public int topicInsert(TopicVO vo) {
        return topicMapper.topicInsert(vo);
    }
}
