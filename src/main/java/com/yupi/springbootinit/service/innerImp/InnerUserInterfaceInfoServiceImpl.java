package com.yupi.springbootinit.service.innerImp;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.kano.kanoapicommon.enums.UserInterfaceStatusEnum;
import com.kano.kanoapicommon.model.entity.UserInterfaceInfo;
import com.kano.kanoapicommon.service.InnerUserInterfaceInfoService;
import com.yupi.springbootinit.common.ErrorCode;
import com.yupi.springbootinit.exception.BusinessException;
import com.yupi.springbootinit.mapper.UserInterfaceInfoMapper;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;
import java.util.List;

@DubboService
public class InnerUserInterfaceInfoServiceImpl implements InnerUserInterfaceInfoService {

    @Resource
    private UserInterfaceInfoMapper userInterfaceInfoMapper;

    /**
     * 更新接口调用次数
     * @param interfaceInfoId
     * @param userId
     * @return
     */
    @Override
    public boolean invokeCount(long interfaceInfoId,long userId) {

        LambdaQueryWrapper<UserInterfaceInfo> queryWrapper = new LambdaQueryWrapper<UserInterfaceInfo>();
        queryWrapper.eq(UserInterfaceInfo::getInterfaceInfoId,interfaceInfoId);
        queryWrapper.eq(UserInterfaceInfo::getUserId,userId);

        UserInterfaceInfo userInterfaceInfo = userInterfaceInfoMapper.selectOne(queryWrapper);

        if (ObjectUtil.isEmpty(userInterfaceInfo)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"用户调用接口不存在");
        }

        if (UserInterfaceStatusEnum.BAN.equals(userInterfaceInfo.getStatus())){
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR,"用户被禁用接口");
        }

        if (userInterfaceInfo.getLeftNum() < 0){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"调用次数小于0");
        }

        //todo 加锁
        UpdateWrapper<UserInterfaceInfo> updateWrapper = new UpdateWrapper<>();
        updateWrapper.setSql("totalNum = totalNum + 1,leftNum = leftNum -1");
        UserInterfaceInfo updateUserInterfaceInfo = new UserInterfaceInfo();
        updateUserInterfaceInfo.setUserId(userId);
        updateUserInterfaceInfo.setInterfaceInfoId(interfaceInfoId);


        userInterfaceInfoMapper.update(updateUserInterfaceInfo,updateWrapper);


        return false;
    }
}
