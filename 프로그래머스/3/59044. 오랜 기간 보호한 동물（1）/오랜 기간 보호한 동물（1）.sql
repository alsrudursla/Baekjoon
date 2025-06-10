select I.NAME, I.DATETIME
from ANIMAL_INS I
where I.ANIMAL_ID not in (select ANIMAL_ID from ANIMAL_OUTS)
order by I.DATETIME
limit 3