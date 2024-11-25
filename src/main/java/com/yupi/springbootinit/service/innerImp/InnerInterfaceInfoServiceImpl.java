package com.yupi.springbootinit.service.innerImp;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.kano.kanoapicommon.model.entity.InterfaceInfo;
import com.kano.kanoapicommon.service.InnerInterfaceInfoService;
import com.yupi.springbootinit.common.ErrorCode;
import com.yupi.springbootinit.exception.BusinessException;
import com.yupi.springbootinit.mapper.InterfaceInfoMapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;

@DubboService
public class InnerInterfaceInfoServiceImpl implements InnerInterfaceInfoService {

    @Resource
    private InterfaceInfoMapper interfaceInfoMapper;

    @Override
    public InterfaceInfo getInterfaceInfo(String path, String method) {

        if (StringUtils.isAnyBlank(path,method)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        LambdaQueryWrapper<InterfaceInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(InterfaceInfo::getUrl,path);
        queryWrapper.eq(InterfaceInfo::getMethod,method);

        return interfaceInfoMapper.selectOne(queryWrapper);
    }
}
