use kano_api_db;

-- 接口信息表
create table if not exists kano_api_db.`interface_info`
(
    `id` bigint not null auto_increment comment '主键' primary key,
    `name` varchar(256) not null comment '名称',
    `description` varchar(256) not null comment '描述',
    `url` varchar(512) not null comment '接口地址',
    `requestHeader` text not null comment '请求头',
    `responseHeader` text not null comment '响应头',
    `status` int default 0 not null comment '接口状态（0-关闭 ，1-开启）',
    `method` varchar(256) not null comment '请求类型',
    `userId` bigint not null comment '创建人',
    `createTime` datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    `updateTime` datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    `isDeleted` tinyint default 0 not null comment '是否删除(0-未删, 1-已删)'
    ) comment '接口信息表';

insert into kano_api_db.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('蔡天宇', '万炫明', 'www.hung-mertz.biz', '钟哲瀚', '董昊强', 0, '田明轩', 5966037023);
insert into kano_api_db.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('雷旭尧', '董笑愚', 'www.kirby-kuhlman.biz', '贾绍齐', '顾琪', 0, '魏航', 75088605);
insert into kano_api_db.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('覃哲瀚', '许浩', 'www.cole-thiel.biz', '韩雨泽', '韦正豪', 0, '魏鹏涛', 235);
insert into kano_api_db.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('段嘉熙', '周志泽', 'www.thanh-gorczany.net', '江智宸', '武哲瀚', 0, '蔡浩轩', 7055177);
insert into kano_api_db.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('尹潇然', '秦思聪', 'www.iliana-legros.name', '蒋鸿煊', '丁果', 0, '刘弘文', 1435);
insert into kano_api_db.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('许君浩', '龚潇然', 'www.jacqui-hauck.name', '王彬', '赖修洁', 0, '谭雨泽', 61);
insert into kano_api_db.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('吴驰', '韦梓晨', 'www.coleman-price.com', '谭哲瀚', '蒋鹏煊', 0, '罗伟宸', 42710);
insert into kano_api_db.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('郭烨霖', '黎荣轩', 'www.kendrick-nitzsche.info', '秦金鑫', '赵航', 0, '汪熠彤', 148);
insert into kano_api_db.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('沈雨泽', '胡弘文', 'www.omar-ruecker.info', '白雨泽', '陆昊然', 0, '贾立诚', 67);
insert into kano_api_db.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('段晓博', '马明杰', 'www.arlie-farrell.io', '潘思', '魏正豪', 0, '顾风华', 4814);
insert into kano_api_db.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('洪熠彤', '孟君浩', 'www.lovella-bergnaum.biz', '田烨华', '高子轩', 0, '万致远', 34);
insert into kano_api_db.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('吴弘文', '毛瑾瑜', 'www.russell-kessler.com', '姜彬', '许浩', 0, '方子骞', 50114748);
insert into kano_api_db.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('罗语堂', '熊耀杰', 'www.loren-prohaska.com', '莫天翊', '邱烨伟', 0, '戴昊天', 82231406);
insert into kano_api_db.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('王泽洋', '薛擎苍', 'www.skye-yost.org', '崔雨泽', '田立轩', 0, '龚志泽', 7747348604);
insert into kano_api_db.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('姚浩轩', '董煜城', 'www.carol-mclaughlin.com', '邹黎昕', '赖钰轩', 0, '胡修洁', 240233);
insert into kano_api_db.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('段耀杰', '薛立诚', 'www.louis-bahringer.net', '郝语堂', '黎越彬', 0, '贺明辉', 76222);
insert into kano_api_db.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('孙锦程', '郝建辉', 'www.janee-weber.org', '何驰', '万鹏涛', 0, '白弘文', 44556374);
insert into kano_api_db.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('毛凯瑞', '彭智宸', 'www.jamel-harvey.biz', '徐鹭洋', '钟智辉', 0, '龙乐驹', 409804);
insert into kano_api_db.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('覃胤祥', '陶雨泽', 'www.reyes-block.io', '吕锦程', '袁荣轩', 0, '胡天翊', 9604335026);
insert into kano_api_db.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('钱天宇', '赵昊焱', 'www.isaura-simonis.biz', '沈峻熙', '邓荣轩', 0, '曹皓轩', 5217);
