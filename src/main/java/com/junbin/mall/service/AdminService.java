package com.junbin.mall.service;

import com.junbin.mall.domain.Admin;
import com.junbin.mall.domain.Company;
import com.junbin.mall.domain.User;
import com.junbin.mall.dto.AdminDto;
import com.junbin.mall.dto.AdminLoginDto;
import com.junbin.mall.dto.AdminUserDto;
import com.junbin.mall.dto.CompanyDto;
import com.junbin.mall.exception.*;
import com.junbin.mall.repository.AdminRepository;
import com.junbin.mall.repository.CompanyRepository;
import com.junbin.mall.repository.UserRepository;
import com.junbin.mall.utils.ConvertTool;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class AdminService {
    private final AdminRepository adminRepository;

    private final UserRepository userRepository;

    private final CompanyRepository companyRepository;

    public AdminService(AdminRepository adminRepository, UserRepository userRepository,
    CompanyRepository companyRepository) {
        this.adminRepository = adminRepository;
        this.userRepository = userRepository;
        this.companyRepository = companyRepository;
    }

    public AdminLoginDto login(AdminLoginDto adminLoginDto) {
        Admin admin = adminRepository.findAdminByName(adminLoginDto.getName())
                .orElseThrow(() -> new UserIsNotExistException(ExceptionMessage.ADMIN_NOT_EXIST));
        if(!admin.getPassword().equals(admin.getPassword())) {
            throw new UserPasswordIsNotCorrectException(ExceptionMessage.ADMIN_PASSWORD_NOT_CORRECT);
        }
        return ConvertTool.convertObject(admin,AdminLoginDto.class);
    }

    @Transactional
    public AdminDto register(AdminDto adminDto) {
        Optional<Admin> admin = adminRepository.findAdminByName(adminDto.getName());
        if(admin.isPresent()) {
            throw new UserIsExistException(ExceptionMessage.ADMIN_IS_EXIST);
        }
        Admin newAdmin = adminRepository.save(ConvertTool.convertObject(adminDto, Admin.class));
        return ConvertTool.convertObject(newAdmin, AdminDto.class);
    }

    public List<AdminUserDto> getUsers(){
        List<User> users = userRepository.findAll();

        return ConvertTool.convertList(users, AdminUserDto.class);
    }

    @Transactional
    public AdminUserDto setUserTag(Long id, String tag) {
        Optional<User> user = userRepository.findById(id);
        if(!user.isPresent()) {
            throw new UserIsNotExistException(ExceptionMessage.USER_NOT_EXIST);
        }
        user.get().setTag(tag);
        User userAfterSetTag = userRepository.save(user.get());
        return ConvertTool.convertObject(userAfterSetTag, AdminUserDto.class);
    }

    @Transactional
    public CompanyDto regCompany(CompanyDto companyDto) {
        Optional<Company> company = companyRepository.findByName(companyDto.getName());
        if(company.isPresent()) {
            throw new CompanyIsExistException(ExceptionMessage.COMPANY_NAME_IS_NOT_CORRECT);
        }
        Company newCompany = companyRepository.save(ConvertTool.convertObject(companyDto,Company.class));
        return ConvertTool.convertObject(newCompany, CompanyDto.class);
    }
}
