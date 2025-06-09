#SELECT M.MEMBER_NAME, R.REVIEW_TEXT, DATE_FORMAT(R.REVIEW_DATE, "%Y-%m-%d") AS REVIEW_DATE
#FROM MEMBER_PROFILE M
#JOIN REST_REVIEW R ON M.MEMBER_ID = R.MEMBER_ID
#WHERE M.MEMBER_ID = (SELECT MEMBER_ID
#                      FROM REST_REVIEW 
#                     GROUP BY MEMBER_ID
#                     ORDER BY COUNT(MEMBER_ID) DESC LIMIT 1)
#ORDER BY R.REVIEW_DATE ASC, R.REVIEW_TEXT ASC



select M.MEMBER_NAME, R.REVIEW_TEXT, date_format(R.REVIEW_DATE, '%Y-%m-%d') as REVIEW_DATE
from REST_REVIEW R join MEMBER_PROFILE M on R.MEMBER_ID = M.MEMBER_ID
where M.MEMBER_ID = (select R2.MEMBER_ID 
                      from REST_REVIEW R2
                      group by R2.MEMBER_ID
                    order by count(*) desc limit 1)
order by R.REVIEW_DATE asc, R.REVIEW_TEXT asc