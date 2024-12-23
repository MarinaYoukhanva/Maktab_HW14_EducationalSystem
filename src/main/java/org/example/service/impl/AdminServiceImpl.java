package org.example.service.impl;

import org.example.base.service.BaseServiceImpl;
import org.example.entity.Admin;
import org.example.exception.NotFoundException;
import org.example.repository.AdminRepository;
import org.example.service.AdminService;
import org.example.service.Authentication.AdminAuthentication;
import org.hibernate.Session;

public class AdminServiceImpl extends BaseServiceImpl<Long, Admin, AdminRepository>
        implements AdminService {

    public AdminServiceImpl(AdminRepository repository) {
        super(repository);
    }

    @Override
    public void infoLogicCheck(Session session, Admin admin) {
    }

    @Override
    public void updateColumns(Admin admin, Admin foundAdmin) {
        foundAdmin.setUsername(admin.getUsername());
        foundAdmin.setPassword(admin.getPassword());
    }

    @Override
    public Admin login(String username, String password) {
        Admin admin = getRepository().findByUsername(username)
                .orElseThrow(() -> new NotFoundException(Admin.class));
        if (!admin.getPassword().equals(password))
            throw new NotFoundException(Admin.class);
        AdminAuthentication.setLoggedInAdmin(admin);
        return admin;
    }
}
