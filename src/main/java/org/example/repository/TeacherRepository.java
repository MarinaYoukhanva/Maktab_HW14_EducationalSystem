package org.example.repository;

import org.example.base.repository.BaseRepository;
import org.example.entity.Teacher;
import org.hibernate.Session;

import java.util.Optional;

public interface TeacherRepository extends BaseRepository<Long, Teacher> {

    Long emailExistence(Session session, Teacher teacher);
    Long usernameExistence(Session session, Teacher teacher);
    Long nationalCodeExistence(Session session, Teacher teacher);
    Long personnelCodeExistence(Session session, String personnelCode);
    Optional<Teacher> findByPersonnelCode(String personnelCode);
}
