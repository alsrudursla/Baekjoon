select D.ID, D.EMAIL, D.FIRST_NAME, D.LAST_NAME
from DEVELOPERS D
where D.SKILL_CODE & (select sum(CODE) from SKILLCODES where CATEGORY = 'Front End') > 0
order by D.ID