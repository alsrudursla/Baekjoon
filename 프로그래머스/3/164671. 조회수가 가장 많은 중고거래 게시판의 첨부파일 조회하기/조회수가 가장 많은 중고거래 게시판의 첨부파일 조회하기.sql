select concat('/home/grep/src/', F.BOARD_ID, '/', F.FILE_ID, F.FILE_NAME, F.FILE_EXT) as FILE_PATH
from USED_GOODS_FILE F
where F.BOARD_ID = (select U.BOARD_ID
                   from USED_GOODS_BOARD U
                   order by U.VIEWS desc limit 1)
order by F.FILE_ID desc