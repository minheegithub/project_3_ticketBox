--ticketbox 1234 계정으로 접속
----------------------------[게시판]----------------------------------
create table board(
    aid number(10) primary key not null,
    rid number(10),
    pwd varchar2(50),
    dbsubject varchar2(50)
        CONSTRAINT board_dbsubject_fk REFERENCES performance(p_name),
    dbname varchar2(50) 
        CONSTRAINT board_dbname_fk REFERENCES member(id),
    dbdate date default sysdate,
    dbhits number(10),
    dbmemo varchar2(500),
    jumsoo varchar2(10)
);


select * from board;
-------------------------------------------[회원정보]----------------------
drop table member;
CREATE TABLE member(
name varchar2(20), 
id varchar2(20) primary key, 
pw varchar2(30), 
phone varchar2(20), 
gender varchar2(10),
address varchar2(100),
email varchar2(50)
);

---------------------------------------------[공연정보]------------------- 
create table performance(
    p_name varchar2(30) constraint p_name_pk primary key,
    cast varchar2(50),
    p_desc varchar2(50),
    image_name varchar2(50)
);

insert into performance values('마리앙뚜아네트','김주연,이조연,박조연','image/url','mari.jpg');
insert into performance values('헤드윅','최주연, 강조연,서조연','image/url','hedwig.jpg');
insert into performance values('모차르트','정주연, 나조연, 문조연','image/url','mozart.jpg');
commit;

create table performance(
    p_name varchar2(30) constraint p_name_pk primary key,
    cast varchar2(50),
    p_desc varchar2(50)
);

insert into performance values('마리앙뚜아네트','김주연,이조연,박조연','image/url');
insert into performance values('헤드윅','최주연, 강조연,서조연','image/url');
insert into performance values('모차르트','정주연, 나조연, 문조연','image/url');
commit;
select * from performance;
---------------------------------------------[공연 회차 정보]-------------------
create table performance_each(
    p_id varchar2(10) primary key, 
    p_name varchar2(30) constraint p_name_fk references performance(p_name),
    p_date date,
    p_time varchar2(10),
    vip number(2),
    r number(3),
    s number(3)     
);
insert into performance_each values('MA08281400','마리앙뚜아네트',TO_DATE('2021-08-28','yyyy-mm-dd'),'14:00',80,150,150);
insert into performance_each values('MA08281800','마리앙뚜아네트',TO_DATE('2021-08-28','yyyy-mm-dd'),'18:00',80,150,150);
insert into performance_each values('MA08251400','마리앙뚜아네트',TO_DATE('2021-08-25','yyyy-mm-dd'),'14:00',80,150,150);
commit;
insert into performance_each values('HE08281400','헤드윅',TO_DATE('2021-08-28','yyyy-mm-dd'),'14:00',80,150,150);
insert into performance_each values('HE08281800','헤드윅',TO_DATE('2021-08-28','yyyy-mm-dd'),'18:00',80,150,150);

insert into performance_each values('MO08281400','모차르트',TO_DATE('2021-08-28','yyyy-mm-dd'),'14:00',80,150,150);
insert into performance_each values('MO08281800','모차르트',TO_DATE('2021-08-28','yyyy-mm-dd'),'18:00',80,150,150);

select * from performance_each;
commit;
----------------------------------------------[좌석 선택 ]-----------------------------
drop table member_seat;
create table member_seat(
    id varchar2(20) constraint id_fk references member(id),
    s1_id varchar2(30),
    s2_id varchar2(30),
    s3_id varchar2(30),
    p_id varchar2(10) constraint p_id_fk references performance_each(p_id),
    p_name varchar2(30) constraint p_name_seat_fk references performance(p_name)
);

select id from member;
select * from member;
---------------------------------------[예매 내역]-------------------------------------------
create table total_ticket(
    ticket_number varchar2(20) primary key,
    user_id varchar2(10) constraint u_id_fk references member(id),
    payment_number varchar2(20)
);
commit;
select * from total_ticket;
------------------------------------[결제]--------------------------------------------
create table payment(
    id varchar2(20) constraint id_pay_fk references member(id),
    rcv_ticket varchar2(10),
    pay_ticket varchar2(10),
    total number(6)
);
cnullommit;
select * from payment;
-----------------------------------------------------------------------
select * from member_seat;
update performance_each set VIP = VIP-1 , R = R-1 , S = s-1 
where performance_each.p_id = memer_seat.p_id;
select * from member;
delete from member where name = '수현' or name = '심수현';
commit;
select * from performance_each;
select * from member_seat;
commit;
update performance_each set VIP = VIP-0 , R= R-0, S=S-1 
where p_id = 'MA08281400';

