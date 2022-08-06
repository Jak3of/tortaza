
INSERT INTO rols(rol_id,rol)
SELECT * FROM (SELECT 1 as rol_id, "USER" as rol) AS temp
WHERE NOT EXISTS (select roL_id from rols where rol_id=1);

INSERT INTO rols(rol_id,rol)
SELECT * FROM (SELECT 2 as rol_id, "EMPLEADO" as rol) AS temp
WHERE NOT EXISTS (select rol_id from rols where rol_id=2);

INSERT INTO rols(rol_id,rol)
SELECT * FROM (SELECT 3 as rol_id, "ADMIN" as rol) AS temp
WHERE NOT EXISTS (select roL_id from rols where rol_id=3);