select I.ANIMAL_ID, I.ANIMAL_TYPE, I.NAME
from ANIMAL_INS I join ANIMAL_OUTS O on I.ANIMAL_ID = O.ANIMAL_ID
where I.SEX_UPON_INTAKE like "Intact%" and (O.SEX_UPON_OUTCOME like "Spayed%" or O.SEX_UPON_OUTCOME like "Neutered%")