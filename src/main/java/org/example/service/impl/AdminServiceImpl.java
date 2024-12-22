package org.example.service.impl;

import org.example.base.service.BaseServiceImpl;
import org.example.entity.Admin;
import org.example.repository.AdminRepository;
import org.example.service.AdminService;

public class AdminServiceImpl extends BaseServiceImpl<Long, Admin, AdminRepository>
        implements AdminService {

    public AdminServiceImpl(AdminRepository repository) {
        super(repository);
    }

    @Override
    public void updateColumns(Admin admin, Admin foundAdmin) {
        foundAdmin.setFirstName(admin.getFirstName());
        foundAdmin.setLastName(admin.getLastName());
        foundAdmin.setUsername(admin.getUsername());
        foundAdmin.setPassword(admin.getPassword());
        foundAdmin.setPhoneNumber(admin.getPhoneNumber());
        foundAdmin.setEmail(admin.getEmail());
    }
}
