create table bucket_shop
(
    id      bigint not null,
    user_id bigint,
    primary key (id)
) ;

create table bucket_shop_seq
(
    next_val bigint
);

insert into bucket_shop_seq values (1);


create table buckets_products
(
    bucket_id  bigint not null,
    product_id bigint not null
);

create table categories_shop
(
    id    bigint not null,
    title varchar(255),
    primary key (id)
) ;

create table category_shop_seq
(
    next_val bigint
);

insert into category_shop_seq values (1);


create table order_details_shop
(
    id         bigint not null,
    amount     decimal(19, 2),
    price      decimal(19, 2),
    user_id    bigint,
    product_id bigint,
    primary key (id)
);

create table order_details_shop_seq
(
    next_val bigint
);

insert into order_details_shop_seq values (1);


create table order_shop
(
    id      bigint not null,
    address varchar(255),
    created datetime(6),
    status  varchar(255),
    summma  decimal(19, 2),
    updated datetime(6),
    user_id bigint,
    primary key (id)
);

create table order_shop_detelis
(
    OrderShop_id bigint not null,
    detelis_id   bigint not null
);
create table order_shop_seq
(
    next_val bigint
);

insert into order_shop_seq values (1);


create table product_shop
(
    id    bigint not null,
    price decimal(19, 2),
    title varchar(255),
    primary key (id)
);

create table product_shop_seq
(
    next_val bigint
);

insert into product_shop_seq values (1);


create table products_categories
(
    bucket_id   bigint not null,
    category_id bigint not null
) ;

create table user_shop
(
    id        bigint not null,
    archive   bit    not null,
    email     varchar(255),
    name      varchar(255),
    password  varchar(255),
    role      varchar(255),
    backed_id bigint,
    primary key (id)
);

create table user_shop_seq
(
    next_val bigint
) ;

insert into user_shop_seq values (1);


alter table order_shop_detelis
    add constraint UK_pir8glf9ird2c76jg9ox4m2r8 unique (detelis_id);

alter table bucket_shop
    add constraint FKsk10sn788ijky106sm0sg2ta1
        foreign key (user_id)
            references user_shop (id);

alter table buckets_products
    add constraint FKo4tlixwend19f5ul8svufv5vy
        foreign key (product_id)
            references product_shop (id) ;

alter table buckets_products
    add constraint FKon6syi26m5aw6i0vfgn78x3ug
        foreign key (bucket_id)
            references bucket_shop (id);

alter table order_details_shop
    add constraint FKahc37s86wy35qtjog1el4e9f
        foreign key (user_id)
            references order_shop (id) ;

alter table order_details_shop
    add constraint FKisk8900b21d7qbr3vr2wh24q2
        foreign key (product_id)
            references product_shop (id);

alter table order_shop
    add constraint FK2dq1w9hm01llfd4ke7tm8jv8r
        foreign key (user_id)
            references user_shop (id) ;

alter table order_shop_detelis
    add constraint FKj6gv1n15pwca0gdc3cjvrwsw7
        foreign key (detelis_id)
            references order_details_shop (id);

alter table order_shop_detelis
    add constraint FK6c8qu5x4cukmr3acxikqxvqbs
        foreign key (OrderShop_id)
            references order_shop (id);

alter table products_categories
    add constraint FKhh02o4uwqil1oc5oduw5o4sk3
        foreign key (category_id)
            references categories_shop (id);

alter table products_categories
    add constraint FKnr7lrp13nwqf4qaq8l0b3wdjp
        foreign key (bucket_id)
            references product_shop (id);

alter table user_shop
    add constraint FK5nnsfgie5aepb65573a8ignhs
        foreign key (backed_id)
            references bucket_shop (id)