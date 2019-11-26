package com.sinosoft.demo.infrastructure.persistance.mapper;

import com.sinosoft.demo.domain.entity.Student;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @author: 王黄鹏
 * @date: 2019/11/26
 * @time: 下午2:45
 * @description:
 */
public interface StudentMapper {

    @Insert("<script>insert into student values <foreach item='student' index='index' collection='students' separator=',' > (#{student.id},#{student.name},#{student.birthday}) </foreach> </script> ")
    int saveStudent(@Param("students") List<Student> students);

    int updateStudent(@Param("students") Map<Integer, Student> students);

    @DeleteProvider(type = StudentMapperProvider.class, method = "deleteStudent")
    int deleteStudent(@Param("ids") List<Integer> ids);

    @Select("<script>select * from student where id in " +
            "<foreach collection='ids' item='id' index='index' separator=',' open='(' close=')'>#{id}</foreach></script>")
    List<Student> listStudent(@Param("ids") List<Integer> ids);
}
