package com.letty.lettylink.admin.service;

public class UserTableShardingTest {

    private static final String SQL =
            "create table t_user_%d\n" +
            "(\n" +
            "    id            bigint auto_increment\n" +
            "        primary key,\n" +
            "    username      varchar(256) null,\n" +
            "    password      varchar(512) null,\n" +
            "    real_name     varchar(256) null comment 'users'' official name',\n" +
            "    phone         varchar(128) null,\n" +
            "    email         varchar(512) null,\n" +
            "    deletion_time bigint       null comment 'time stamp deletion',\n" +
            "    created_time  datetime     null,\n" +
            "    updated_time  datetime     null,\n" +
            "    del_flag      tinyint      null comment 'deletion flag 0 or 1',\n" +
            "    constraint idx_unique_username\n" +
            "        unique (username)\n" +
            ")\n";

    public static void main(String[] args) {
        for (int i = 0; i < 16; i++) {
            System.out.printf((SQL) + "%n", i);
        }
    }
}
