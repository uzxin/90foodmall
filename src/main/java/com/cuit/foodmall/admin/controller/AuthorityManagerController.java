package com.cuit.foodmall.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.cuit.foodmall.entity.*;
import com.cuit.foodmall.service.*;
import com.cuit.foodmall.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
    @Autowired
    private UserService userService;

    /**
     * @description: 查询角色权限
     * @param: roleId
     * @return: java.lang.Object
     */
    @GetMapping("getRoleAuths")
    public Object getRoleAuths(Long roleId){
        //查询角色所有权限
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

    /**
     * @description: 获取用户权限
     * @param: session
     * @return: java.lang.Object
     */
    @GetMapping("getAuths")
    public Object getAuths(HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("admin");
        if (null != user){
            List<Auth> auths = userService.getAuths(user.getId());
            if (auths.size() == 0){
                return Result.error("没有权限");
            }
            return Result.ok(auths);
        }
        return Result.error("未登录");
    }

}
