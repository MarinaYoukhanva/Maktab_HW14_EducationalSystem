package org.example.repository.impl;

import org.example.base.repository.BaseRepositoryImpl;
import org.example.entity.Admin;
import org.example.repository.AdminRepository;

public class AdminRepositoryImpl extends BaseRepositoryImpl<Long, Admin>
        implements AdminRepository {
    public AdminRepositoryImpl(Class<Admin> entityClass) {
        super(entityClass);
    }
}
