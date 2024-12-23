package org.example.service;

import org.example.base.service.BaseService;
import org.example.entity.Student;
import org.example.entity.Teacher;
import org.example.entity.dto.StudentDto;
import org.example.entity.dto.TeacherDto;

import java.util.List;

public interface TeacherService extends BaseService<Long, Teacher> {

    Teacher login (String personnelCode, String password);
    List<TeacherDto> findAllDto();
}
