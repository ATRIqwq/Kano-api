package com.yupi.springbootinit.service.innerImp;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kano.kanoapicommon.model.entity.User;
import com.kano.kanoapicommon.service.InnerUserService;
import com.yupi.springbootinit.common.ErrorCode;
import com.yupi.springbootinit.exception.BusinessException;
import com.yupi.springbootinit.mapper.UserMapper;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;


@DubboService
public class InnerUserServiceImpl implements InnerUserService {

    @Resource
    private UserMapper userMapper;
    @Override
    public User getInvokeUser(String accessKey) {

        if (StrUtil.isBlank(accessKey)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"请求参数为空");
        }

        QueryWrapper queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("accessKey",accessKey);
        User user = userMapper.selectOne(queryWrapper);

        if (user == null){
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR,"用户不存在");
        }


        return user;
    }
}
