package org.example.repository;

import org.example.base.repository.BaseRepository;
import org.example.entity.Admin;
import org.hibernate.Session;

import java.util.Optional;

public interface AdminRepository extends BaseRepository<Long, Admin> {

    Optional<Admin> findByUsername(Session session, String username);
}
