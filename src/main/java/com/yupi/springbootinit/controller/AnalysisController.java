package com.yupi.springbootinit.controller;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.kano.kanoapicommon.model.entity.InterfaceInfo;
import com.kano.kanoapicommon.model.entity.UserInterfaceInfo;
import com.yupi.springbootinit.annotation.AuthCheck;
import com.yupi.springbootinit.common.BaseResponse;
import com.yupi.springbootinit.common.ErrorCode;
import com.yupi.springbootinit.common.ResultUtils;
import com.yupi.springbootinit.exception.BusinessException;
import com.yupi.springbootinit.mapper.UserInterfaceInfoMapper;
import com.yupi.springbootinit.model.vo.InterfaceInfoVO;
import com.yupi.springbootinit.service.InterfaceInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@RestController
@Slf4j
@RequestMapping("/analysis")
public class AnalysisController {

    @Resource
    private UserInterfaceInfoMapper userInterfaceInfoMapper;

    @Resource
    private InterfaceInfoService interfaceInfoService;

    //获取前几名
    private static final int LIMIT_NUMBER = 3;


    /**
     * 获取调用次数排名前几名 的接口信息
     * @return
     */
    @GetMapping("/top/interface/invoke")
    @AuthCheck(mustRole = "admin")
    public BaseResponse<List<InterfaceInfoVO>> listTopInvokeInterfaceInfo(){

        List<UserInterfaceInfo> userInterfaceInfoList = userInterfaceInfoMapper.listTopInvokeInterfaceInfo(LIMIT_NUMBER);
        Map<Long, List<UserInterfaceInfo>> interfaceInfoObjMap = userInterfaceInfoList.stream().collect(Collectors.groupingBy(UserInterfaceInfo::getInterfaceInfoId));

//        List<Long> interfaceIdList = userInterfaceInfoList.stream().map(UserInterfaceInfo::getInterfaceInfoId).collect(Collectors.toList());

        LambdaQueryWrapper<InterfaceInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(InterfaceInfo::getId,interfaceInfoObjMap.keySet());
        List<InterfaceInfo> interfaceInfoList = interfaceInfoService.list(queryWrapper);

        if (CollectionUtil.isEmpty(interfaceInfoList)){
            throw new BusinessException(ErrorCode.SYSTEM_ERROR);
        }

        List<InterfaceInfoVO> interfaceInfoVOList = interfaceInfoList.stream().map(interfaceInfo -> {
            InterfaceInfoVO interfaceInfoVO = new InterfaceInfoVO();
            BeanUtil.copyProperties(interfaceInfo, interfaceInfoVO);
            interfaceInfoVO.setTotalNum(interfaceInfoObjMap.get(interfaceInfo.getId()).get(0).getTotalNum());
            return interfaceInfoVO;
        }).collect(Collectors.toList());

        return ResultUtils.success(interfaceInfoVOList);

    }


}
