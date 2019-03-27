-- 内容
create table `content_info` (
    `content_id` varchar(32) not null,
    `user_id` `user_id` varchar(32) not null comment '内容发布者',
    `content_title` varchar(100) not null comment '内容标题',
    `content_summary` varchar(150) not null comment '内容摘要',
    `content_price` decimal(10,2) not null comment '内容单价',
    `content_stock` int(11) NOT NULL COMMENT '内容库存',
    `content_description` varchar(1024) comment '内容详细信息/正文',
    `content_icon` varchar(512) comment '内容图标',
    `create_time` timestamp not null default current_timestamp comment '发布时间',
    `update_time` timestamp not null default current_timestamp on update current_timestamp comment '修改时间',
    primary key (`content_id`),
    key `idx_user_id` (`user_id`)
);

-- 用户
create table `user_info` (
    `user_id` varchar(32) not null,
    `username` varchar(32) not null,
    `password` varchar(32) not null,
    `is_seller` tinyint COMMENT '是否为卖家,0表示非卖家（买家）1表示卖家',
    `create_time` timestamp not null default current_timestamp comment '创建时间',
    `update_time` timestamp not null default current_timestamp on update current_timestamp comment '修改时间',
    primary key (`user_id`),
) comment '卖家/买家信息表';

-- 购物车详情
create table `cart_detail` (
    `cart_detail_id` varchar(32) not null,
    `user_id` varchar(32) not null,
    `content_id` varchar(32) not null,
    `quantity` int not null comment '数量',
    `create_time` timestamp not null default current_timestamp comment '创建时间',
    `update_time` timestamp not null default current_timestamp on update current_timestamp comment '修改时间',
    primary key (`cart_detail_id`),
    key `idx_user_id` (`user_id`)
);

-- 订单
create table `order_master` (
    `order_id` varchar(32) not null,
    `user_id` varchar(32) not null,
    `username` varchar(32) not null,
    `order_amount` decimal(10,2) not null comment '订单总金额',
    `order_status` tinyint not null default '0' comment '订单状态, 默认为新下单',
    `pay_status` tinyint not null default '0' comment '支付状态, 默认未支付',
    `create_time` timestamp not null default current_timestamp comment '创建时间',
    `update_time` timestamp not null default current_timestamp on update current_timestamp comment '修改时间',
    primary key (`order_id`),
    key `idx_user_id` (`user_id`)
);

-- 订单详情
create table `order_detail` (
    `order_detail_id` varchar(32) not null,
    `order_id` varchar(32) not null,
    `content_id` varchar(32) not null,
    `content_title` varchar(100) not null comment '内容标题',
    `content_price` decimal(10,2) not null comment '内容单价',
    `quantity` int not null comment '数量',
    `content_icon` varchar(512) comment '内容图标',
    `create_time` timestamp not null default current_timestamp comment '创建时间',
    `update_time` timestamp not null default current_timestamp on update current_timestamp comment '修改时间',
    primary key (`order_detail_id`),
    key `idx_order_id` (`order_id`)
);