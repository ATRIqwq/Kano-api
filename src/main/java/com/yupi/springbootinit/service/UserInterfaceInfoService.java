package com.yupi.springbootinit.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kano.kanoapicommon.model.dto.userInterfaceInfo.UserInterfaceInfoQueryRequest;
import com.kano.kanoapicommon.model.entity.UserInterfaceInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yupi.springbootinit.model.vo.UserInterfaceInfoVO;

import javax.servlet.http.HttpServletRequest;

/**
* @author 86136
* @description 针对表【user_interface_info(用户调用接口关系)】的数据库操作Service
* @createDate 2024-11-14 10:41:47
*/
public interface UserInterfaceInfoService extends IService<UserInterfaceInfo> {
    /**
     * 校验
     *
     * @param userInterfaceInfo
     * @param add
     */
    void validUserInterfaceInfo(UserInterfaceInfo userInterfaceInfo, boolean add);

    /**
     * 获取封装
     *
     * @param userUserInterfaceInfo
     * @param request
     * @return
     */
    UserInterfaceInfoVO getUserInterfaceInfoVO(UserInterfaceInfo userUserInterfaceInfo, HttpServletRequest request);

    Page<UserInterfaceInfoVO> getUserInterfaceInfoVOPage(Page<UserInterfaceInfo> userInterfaceInfoPage, HttpServletRequest request);

    QueryWrapper<UserInterfaceInfo> getQueryWrapper(UserInterfaceInfoQueryRequest userInterfaceInfoQueryRequest);

    /**
     * 更新接口调用次数
     * @param interfaceInfoId
     * @param userId
     * @return
     */
     boolean invokeCount(long interfaceInfoId,long userId);

}
