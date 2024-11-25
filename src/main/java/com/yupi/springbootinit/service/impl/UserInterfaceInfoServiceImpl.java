package com.yupi.springbootinit.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kano.kanoapicommon.model.dto.userInterfaceInfo.UserInterfaceInfoQueryRequest;
import com.kano.kanoapicommon.model.entity.UserInterfaceInfo;
import com.yupi.springbootinit.common.ErrorCode;
import com.yupi.springbootinit.exception.BusinessException;
import com.yupi.springbootinit.model.vo.UserInterfaceInfoVO;
import com.yupi.springbootinit.service.UserInterfaceInfoService;
import com.yupi.springbootinit.mapper.UserInterfaceInfoMapper;
import com.yupi.springbootinit.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
* @author 86136
* @description 针对表【user_interface_info(用户调用接口关系)】的数据库操作Service实现
* @createDate 2024-11-14 10:41:47
*/
@Service
public class UserInterfaceInfoServiceImpl extends ServiceImpl<UserInterfaceInfoMapper, UserInterfaceInfo>
    implements UserInterfaceInfoService{
    @Resource
    private UserService userService;


    @Override
    public void validUserInterfaceInfo(UserInterfaceInfo userInterfaceInfo, boolean add) {
        if (userInterfaceInfo == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        // 如果是添加操作
        if (add) {
            if (userInterfaceInfo.getInterfaceInfoId()<= 0 || userInterfaceInfo.getUserId() <=0){
                throw new BusinessException(ErrorCode.NOT_FOUND_ERROR,"用户或者接口不存在");
            }
        }
        // 有参数则校验

        if (userInterfaceInfo.getLeftNum() < 0){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"剩余调用次数不能小于0");
        }

    }

    @Override
    public UserInterfaceInfoVO getUserInterfaceInfoVO(UserInterfaceInfo userUserInterfaceInfo, HttpServletRequest request) {
        return UserInterfaceInfoVO.objToVo(userUserInterfaceInfo);
    }

    @Override
    public Page<UserInterfaceInfoVO> getUserInterfaceInfoVOPage(Page<UserInterfaceInfo> userInterfaceInfoPage, HttpServletRequest request) {
        List<UserInterfaceInfo> records = userInterfaceInfoPage.getRecords();
        Page<UserInterfaceInfoVO> userInterfaceInfoVOPage = new Page<>(userInterfaceInfoPage.getCurrent(), userInterfaceInfoPage.getSize(), userInterfaceInfoPage.getTotal());
        if (CollUtil.isEmpty(records)){
            return userInterfaceInfoVOPage;
        }
        return userInterfaceInfoVOPage;
    }

    @Override
    public QueryWrapper<UserInterfaceInfo> getQueryWrapper(UserInterfaceInfoQueryRequest userInterfaceInfoQueryRequest) {
        QueryWrapper<UserInterfaceInfo> queryWrapper = new QueryWrapper<>();
        if (userInterfaceInfoQueryRequest == null){
            return queryWrapper;
        }
//        Long id = interfaceInfoQueryRequest.getId();
//        String name = interfaceInfoQueryRequest.getName();
//        String description = interfaceInfoQueryRequest.getDescription();
//        String url = interfaceInfoQueryRequest.getUrl();
//        String requestHeader = interfaceInfoQueryRequest.getRequestHeader();
//        String responseHeader = interfaceInfoQueryRequest.getResponseHeader();
//        Integer status = interfaceInfoQueryRequest.getStatus();
//        String method = interfaceInfoQueryRequest.getMethod();
//        Long userId = interfaceInfoQueryRequest.getUserId();
//        String sortField = interfaceInfoQueryRequest.getSortField();
//        String sortOrder = interfaceInfoQueryRequest.getSortOrder();

//        //拼查询条件
//        if (StrUtil.isNotBlank(name)){
//            queryWrapper.like("name",name);
//        }
//        if (StrUtil.isNotBlank(description)){
//            queryWrapper.like("description",description);
//        }
//        queryWrapper.eq(ObjectUtils.isNotEmpty(userId), "userId", userId);
//        queryWrapper.orderBy(SqlUtils.validSortField(sortField), sortOrder.equals(CommonConstant.SORT_ORDER_ASC),
//                sortField);
        return queryWrapper;
    }

    /**
     * 更新接口调用次数
     * @param interfaceInfoId
     * @param userId
     * @return
     */
    @Override
    public boolean invokeCount(long interfaceInfoId, long userId) {
        //todo 分布式环境下加锁，防止多线程修改同一个数据
        UpdateWrapper<UserInterfaceInfo> updateWrapper = new UpdateWrapper<>();
        updateWrapper.lambda().eq(UserInterfaceInfo::getInterfaceInfoId,interfaceInfoId);
        updateWrapper.lambda().eq(UserInterfaceInfo::getUserId,userId);
        updateWrapper.setSql("leftNum = leftNum -1,totalNum = totalNum + 1");
        return this.update(updateWrapper);
    }


}




