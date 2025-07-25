#SELECT B.BOOK_ID, A.AUTHOR_NAME, DATE_FORMAT(B.PUBLISHED_DATE, "%Y-%m-%d") AS PUBLISHED_DATE
#FROM BOOK AS B
#JOIN AUTHOR AS A ON B.AUTHOR_ID = A.AUTHOR_ID
#WHERE B.CATEGORY = '경제'
#ORDER BY PUBLISHED_DATE



select B.BOOK_ID, A.AUTHOR_NAME, date_format(B.PUBLISHED_DATE, '%Y-%m-%d') as PUBLISHED_DATE
from BOOK B join AUTHOR A on B.AUTHOR_ID = A.AUTHOR_ID
where B.CATEGORY = '경제'
order by B.PUBLISHED_DATE