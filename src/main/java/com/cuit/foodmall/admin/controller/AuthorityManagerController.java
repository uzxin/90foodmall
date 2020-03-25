package com.cuit.foodmall.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.cuit.foodmall.entity.Auth;
import com.cuit.foodmall.entity.Role;
import com.cuit.foodmall.entity.RoleAuth;
import com.cuit.foodmall.entity.UserRole;
import com.cuit.foodmall.service.AuthService;
import com.cuit.foodmall.service.RoleAuthService;
import com.cuit.foodmall.service.RoleService;
import com.cuit.foodmall.service.UserRoleService;
import com.cuit.foodmall.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author: YX
 * @date: 2020/3/23 13:33
 * @description: 权限管理接口
 */
@RestController
@RequestMapping("admin/authorityManager")
public class AuthorityManagerController {

    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private RoleAuthService roleAuthService;
    @Autowired
    private AuthService authService;

    /**
     * @description: 查询角色权限
     * @param: roleId
     * @return: java.lang.Object
     */
    @GetMapping("getRoleAuths")
    public Object getRoleAuths(Long roleId){
        //查询所有角色权限
        List<RoleAuth> roleAuths = roleAuthService.list(new LambdaQueryWrapper<RoleAuth>().eq(RoleAuth::getRoleId, roleId));
        if(null != roleAuths){
            if(roleAuths.size()>0){
                //保存权限id
                List<Long> authIds = new ArrayList<>(roleAuths.size());
                //遍历添加权限id
                for (RoleAuth roleAuth : roleAuths) {
                    authIds.add(roleAuth.getAuthId());
                }
                //查询权限集合
                Collection<Auth> auths = authService.listByIds(authIds);
                return new Result(0, "查询成功", auths);
            }else{
                return Result.ok("查询成功");
            }
        }
        return Result.error("查询失败");
    }

    /**
     * @description: 更新角色权限
     * @param: role
     * @param: authIds 权限id，以逗号分隔
     * @return: java.lang.Object
     */
    @PostMapping("updateRoleAuths")
    public Object updateRoleAuths(Role role, String authIds){
        if(roleAuthService.remove(new LambdaQueryWrapper<RoleAuth>().eq(RoleAuth::getRoleId, role.getId()))){
            String[] auths = authIds.split(",");
            List<RoleAuth> roleAuths = new ArrayList<>(auths.length);
            for (String auth : auths) {
                RoleAuth roleAuth = new RoleAuth();
                roleAuth.setRoleId(role.getId());
                roleAuth.setAuthId(Long.parseLong(auth));
                roleAuths.add(roleAuth);
            }
            if (roleAuthService.saveBatch(roleAuths)){
                return Result.ok("设置成功");
            }
        }
        return Result.ok("设置失败");
    }

}
