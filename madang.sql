/* 이름: demo_madang.sql */ 
/* 설명 */ 
  
/* root 계정으로 접속, madang 데이터베이스 생성, madang 계정 생성 */ 
/* MySQL Workbench에서 초기화면에서 +를 눌러 root connection을 만들어 접속한다. */ 
DROP DATABASE IF EXISTS  madang; 
DROP USER IF EXISTS  madang@localhost; 
create user madang@localhost identified WITH mysql_native_password  by 'madang'; 
create database madang; 
grant all privileges on madang.* to madang@localhost with grant option; 
commit; 
 
# A: 전체 B: 12 C: 15 D: 청불 
/* madang DB 자료 생성 */ 
/* 이후 실습은 MySQL Workbench에서 초기화면에서 +를 눌러 madang connection을 만들어 접속하여 사용한다. */ 
  
USE madang; 
 
CREATE TABLE Movie (   #1.영화 
  movieNum      INTEGER NOT NULL,   #영화번호 (1) 
  movieName     VARCHAR(40) NOT NULL,            #영화명 
  runningTime   INTEGER NOT NULL,            #상영시간 
  movieRating   VARCHAR(40) NOT NULL,            #상영등급 
  movieDirector VARCHAR(40) NOT NULL,         #감독명 
  movieActor   VARCHAR(100) NOT NULL,         #배우명 
  movieType      VARCHAR(40) NOT NULL,         #장르 
  movieInfo      VARCHAR(100) NOT NULL,         #영화소개 
  openingDate   DATE               #개봉일 
); 

   
CREATE TABLE Schedule (   #2.상영일정 
  scheduleNum      INTEGER NOT NULL,  #상영일정번호 (3) 
  movieNum          INTEGER NOT NULL,           #영화번호 (1) 
  theaterNum       INTEGER NOT NULL,            #상영관번호 (2) 
  screeningStart   DATE,             #상영시작일 
  screeningDate      VARCHAR(40) NOT NULL,        #상영요일 
  screeningSession INTEGER NOT NULL,           #상영회차 
  startTime          VARCHAR(40) NOT NULL              #상영시작시간 
); 

   
 
CREATE TABLE Theater(   #3.상영관 
  theaterNum INTEGER NOT NULL,               #상영관번호 (2) 
  seats   INTEGER NOT NULL,                  #좌석수 
  theaterUsed   VARCHAR(40) NOT NULL               #상영관사용여부 
); 

   
 
CREATE TABLE Ticket(   #4.티켓 
  ticketNum INTEGER NOT NULL,               #티켓번호 
  scheduleNum INTEGER NOT NULL,               #상영일정번호 (3) 
  theaterNum INTEGER NOT NULL,               #상영관번호 (2) 
  seatNum INTEGER NOT NULL,                  #좌석번호 (4) 
  reserveNum INTEGER NOT NULL,               #예매번호 (5) 
  ticketChk VARCHAR(40) NOT NULL,               #발권여부 
  stdPrice INTEGER NOT NULL,                  #표준가격 
  salePrice INTEGER NOT NULL                  #판매가격 
); 
 

 
 
CREATE TABLE Seat(      #5.좌석 
  theaterNum INTEGER NOT NULL,               #상영관번호 (2) 
  seatNum INTEGER NOT NULL,                  #좌석번호 (4) 
  seatUsed VARCHAR(40) NOT NULL,                  #좌석사용여부 
  
  PRIMARY KEY (seatNum, theaterNum)
); 

   
CREATE TABLE Customer(   #6.회원고객 
  custId INTEGER NOT NULL,                  #회원아이디 (6)ㅇ 
  custName VARCHAR(40) NOT NULL,               #고객명 
  phoneNum VARCHAR(40) NOT NULL,               #휴대폰번호 
  email VARCHAR(40)   NOT NULL               #전자메일주소 
);   

 
CREATE TABLE Reservation(   #7.예매정보 
  reserveNum INTEGER NOT NULL,               #예매번호 (5) 
  payMethod VARCHAR(40) NOT NULL,            #결제방법 
  payStatus VARCHAR(40) NOT NULL,            #결제상태 
  payAmount INTEGER NOT NULL,               #결제금액 
  custId INTEGER NOT NULL,                  #회원아이디 
  payDate DATE                     #결제일자 
); 

ALTER TABLE Movie 
  ADD CONSTRAINT movieNum_pk PRIMARY KEY (movieNum); 

ALTER TABLE Schedule 
  ADD CONSTRAINT scheduleNum_pk PRIMARY KEY (scheduleNum); 
   
ALTER TABLE Theater 
  ADD CONSTRAINT theaterNum_pk PRIMARY KEY (theaterNum);   

ALTER TABLE Ticket 
  ADD CONSTRAINT ticketNum_pk PRIMARY KEY (ticketNum); 

ALTER TABLE Customer 
  ADD CONSTRAINT custId_pk PRIMARY KEY (custId); 

ALTER TABLE Reservation 
  ADD CONSTRAINT reserveNum_pk PRIMARY KEY (reserveNum); 
 
   


ALTER TABLE Schedule 
  ADD (CONSTRAINT R_2 FOREIGN KEY (movieNum) REFERENCES Movie (movieNum)); 
   
ALTER TABLE Schedule 
  ADD (CONSTRAINT R_3 FOREIGN KEY (theaterNum) REFERENCES Theater (theaterNum)); 
 
ALTER TABLE Ticket 
  ADD (CONSTRAINT R_4 FOREIGN KEY (scheduleNum) REFERENCES Schedule (scheduleNum)); 
 
ALTER TABLE Ticket 
  ADD (CONSTRAINT R_5 FOREIGN KEY (theaterNum) REFERENCES Theater (theaterNum)); 
   
ALTER TABLE Ticket 
  ADD (CONSTRAINT R_6 FOREIGN KEY (seatNum) REFERENCES Seat (seatNum)); 
   
ALTER TABLE Ticket 
  ADD (CONSTRAINT R_7 FOREIGN KEY (reserveNum) REFERENCES Reservation (reserveNum)); 
 
ALTER TABLE Seat 
  ADD (CONSTRAINT R_8 FOREIGN KEY (theaterNum) REFERENCES Theater (theaterNum)); 

ALTER TABLE Reservation 
  ADD (CONSTRAINT R_9 FOREIGN KEY (custId) REFERENCES Customer (custId)); 
 
INSERT INTO Movie VALUES(1, '범죄도시2', 106, 'C', '이상용', '마동석, 손석구, 최귀화 외', '범죄, 액션, 블랙 코미디', '영화 <범죄도시>의 속편으로', STR_TO_DATE('2021-05-18','%Y-%m-%d')); 
INSERT INTO Movie VALUES(2, '닥터 스트레인지', 126, 'B', '샘 레이미', '베네딕트 컴버배치, 엘리자베스 올슨, 추이텔 에지오포 외', '슈퍼히어로, 다크 판타지, 액션, 어드벤처, SF', '마블 시네마틱 유니버스 페이즈 4의 5번째 영화이자, 닥터 스트레인지 실사영화 시리즈의 2번째 작품이다.', STR_TO_DATE('2021-05-04','%Y-%m-%d')); 
INSERT INTO Movie VALUES(3, '그대가 조국', 124, 'B', '이승준', '조국', '다큐멘터리', '조국소개', STR_TO_DATE('2021-05-25','%Y-%m-%d')); 
INSERT INTO Movie VALUES(4, '배드 가이즈', 100, 'A', '피에르 페리펠', '샘 록웰, 아콰피나, 자씨 비츠 외', '애니메이션, 코미디, 액션, 범죄', '배드가이즈소개', STR_TO_DATE('2021-05-04','%Y-%m-%d')); 
INSERT INTO Movie VALUES(5, '몬스터 싱어', 90, 'A', '비보 베르즈롱', '바네사 파라디, 션 레논, 애덤 골드버그, 대니 휴스턴, 프랑수아 클루제 등', '애니메이션', '몬스터싱어소개', STR_TO_DATE('2021-05-26','%Y-%m-%d')); 
INSERT INTO Movie VALUES(6, '안녕하세요', 118, 'B', '차봉주','김환희, 유선, 이순재 외', '드라마', '호스피스 병동에서 벌어지는 일을 주요 소재로 한다.', STR_TO_DATE('2021-05-25','%Y-%m-%d')); 
INSERT INTO Movie VALUES(7, '비긴어게인', 104, 'C', '존 카니','키이라 나이틀리, 마크 러팔로, 애덤 르빈 외', '드라마, 멜로, 코미디', '원스의 감독 존 카니가 만든 음악 영화. 제87회 아카데미 시상식 주제가상 후보작이다.', STR_TO_DATE('2020-12-31','%Y-%m-%d')); 
INSERT INTO Movie VALUES(8, '극장판 엉덩이', 59, 'A', '자코 아키후미','산페이 유코, 사이토 아야카 등', '애니메이션', '일본의. 2021년 여름, 토에이 만화 축제에서 처음 선보였다. 한국에서는 2021년 5월 5일 어린이날에 맞춰 개봉하였다. 감독은 자코 아키후미이다.', STR_TO_DATE('2021-05-05','%Y-%m-%d')); 
INSERT INTO Movie VALUES(9, '아치의 노래, 정태춘', 113, 'A', '고영재','정태춘, 박은옥', '다큐멘터리', '대한민국의', STR_TO_DATE('2021-05-18','%Y-%m-%d')); 
INSERT INTO Movie VALUES(10, '피는 물보다 진하다', 90, 'C', '김희성','조동혁, 이완, 임정은 등', '액션', '대한민국의', STR_TO_DATE('2021-05-25','%Y-%m-%d'));

INSERT INTO Theater VALUES(1, 15, '사용가능');
INSERT INTO Theater VALUES(2, 15, '사용가능');
INSERT INTO Theater VALUES(3, 15, '사용가능');
INSERT INTO Theater VALUES(4, 15, '사용가능');
INSERT INTO Theater VALUES(5, 15, '사용가능');
INSERT INTO Theater VALUES(6, 15, '사용가능');
INSERT INTO Theater VALUES(7, 15, '사용가능');
INSERT INTO Theater VALUES(8, 15, '사용가능');
INSERT INTO Theater VALUES(9, 15, '사용가능');
INSERT INTO Theater VALUES(10, 15, '사용가능');


INSERT INTO Customer VALUES(1, '강상묵', '010-1342-3423', 'highjelly@naver.com');
INSERT INTO Customer VALUES(2, '김원우', '010-4141-8835', 'dnjsdn2468@naver.com');
INSERT INTO Customer VALUES(3, '이관태', '010-7345-3511', 'godfather@gmail.com');
INSERT INTO Customer VALUES(4, '은정무', '010-8866-1246', 'loveradish@daum.com');
INSERT INTO Customer VALUES(5, '이수혁', '010-5378-9923', 'LSH98@naver.com');
INSERT INTO Customer VALUES(6, '김대웅', '010-2756-1142', 'bigwoong@nate.com');
INSERT INTO Customer VALUES(7, '정준민', '010-9185-0339', 'mayy24th@naver.com');
INSERT INTO Customer VALUES(8, '정휘준', '010-9288-1622', 'hwihwi2@naver.com');
INSERT INTO Customer VALUES(9, '조석근', '010-8683-7704', '3x600@google.com');
INSERT INTO Customer VALUES(10, '송규민', '010-9116-2441', '17011389@sju.ac.kr');

INSERT INTO Reservation VALUES(1, '카드', '결제완료', 7000, 10, STR_TO_DATE('2021-05-26','%Y-%m-%d'));
INSERT INTO Reservation VALUES(2, '현금', '결제대기', 7000, 9, NULL);
INSERT INTO Reservation VALUES(3, '쿠폰', '결제완료', 8000, 8, STR_TO_DATE('2021-05-28','%Y-%m-%d'));
INSERT INTO Reservation VALUES(4, '카드', '결제대기', 7000, 7, NULL);
INSERT INTO Reservation VALUES(5, '카드', '결제완료', 8000, 6, STR_TO_DATE('2021-05-27','%Y-%m-%d'));
INSERT INTO Reservation VALUES(6, '카드', '결제완료', 7000, 5, STR_TO_DATE('2021-05-25','%Y-%m-%d'));
INSERT INTO Reservation VALUES(7, '카드', '결제완료', 7000, 4, STR_TO_DATE('2021-05-26','%Y-%m-%d'));
INSERT INTO Reservation VALUES(8, '현금', '결제대기', 8000, 3, NULL);
INSERT INTO Reservation VALUES(9, '현금', '결제완료', 7000, 2, STR_TO_DATE('2021-05-28','%Y-%m-%d'));
INSERT INTO Reservation VALUES(10, '카드', '결제대기', 8000, 1, NULL);

INSERT INTO Schedule VALUES(1, 1, 1, STR_TO_DATE('2021-05-28','%Y-%m-%d'), '토요일', 1, '17:30');
INSERT INTO Schedule VALUES(2, 2, 2, STR_TO_DATE('2021-05-28','%Y-%m-%d'), '토요일', 1, '17:30');
INSERT INTO Schedule VALUES(3, 3, 3, STR_TO_DATE('2021-05-28','%Y-%m-%d'), '토요일', 1, '17:30');
INSERT INTO Schedule VALUES(4, 4, 3, STR_TO_DATE('2021-05-29','%Y-%m-%d'), '일요일', 1, '21:30');
INSERT INTO Schedule VALUES(5, 5, 5, STR_TO_DATE('2021-05-29','%Y-%m-%d'), '일요일', 1, '17:30');
INSERT INTO Schedule VALUES(6, 6, 6, STR_TO_DATE('2021-05-30','%Y-%m-%d'), '월요일', 1, '17:30');
INSERT INTO Schedule VALUES(7, 1, 7, STR_TO_DATE('2021-05-30','%Y-%m-%d'), '월요일', 2, '17:30');
INSERT INTO Schedule VALUES(8, 8, 8, STR_TO_DATE('2021-05-31','%Y-%m-%d'), '화요일', 1, '17:30');
INSERT INTO Schedule VALUES(9, 9, 9, STR_TO_DATE('2021-05-31','%Y-%m-%d'), '화요일', 1, '17:30');
INSERT INTO Schedule VALUES(10, 10, 10, STR_TO_DATE('2021-06-01','%Y-%m-%d'), '수요일', 1, '17:30');
/*
INSERT INTO Ticket VALUES(1, 1, 1, 1, 1, '발권대기', 8000, 7000);
INSERT INTO Ticket VALUES(2, 2, 2, 1, 2, '발권대기', 8000, 7000);
INSERT INTO Ticket VALUES(3, 3, 3, 1, 3, '발권대기', 8000, 8000);
INSERT INTO Ticket VALUES(4, 4, 4, 1, 4, '발권대기', 8000, 7000);
INSERT INTO Ticket VALUES(5, 5, 5, 1, 5, '발권대기', 8000, 8000);
INSERT INTO Ticket VALUES(6, 6, 6, 1, 6, '발권대기', 8000, 7000);
INSERT INTO Ticket VALUES(7, 7, 7, 1, 7, '발권대기', 8000, 7000);
INSERT INTO Ticket VALUES(8, 8, 8, 1, 8, '발권대기', 8000, 8000);
INSERT INTO Ticket VALUES(9, 9, 9, 1, 9, '발권대기', 8000, 7000);
INSERT INTO Ticket VALUES(10, 10, 10, 1, 10, '발권대기', 8000, 8000);
*/