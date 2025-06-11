select U.USER_ID, 
    U.NICKNAME, 
    concat(U.CITY, ' ', U.STREET_ADDRESS1, ' ', U.STREET_ADDRESS2) as 전체주소, 
    concat(substring(U.TLNO, 1, 3), '-', substring(U.TLNO, 4, 4), '-', substring(U.TLNO, 8, 4)) as 전화번호
from USED_GOODS_USER U
where U.USER_ID in (select WRITER_ID
                   from USED_GOODS_BOARD
                   group by WRITER_ID
                   having count(*) >= 3)
order by U.USER_ID desc