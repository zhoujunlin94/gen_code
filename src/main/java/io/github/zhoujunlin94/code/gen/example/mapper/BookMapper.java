package io.github.zhoujunlin94.code.gen.example.mapper;

import io.github.zhoujunlin94.code.gen.example.entity.Book;
import io.github.zhoujunlin94.meet.tk_mybatis.mapper.TKMapper;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface BookMapper extends TKMapper<Book> {

}