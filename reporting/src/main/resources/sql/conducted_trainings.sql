SELECT
  c.name || ' ' || c.surname AS client_name,
  tr.start :: DATE           AS date,
  tr.start :: TIME           AS time,
  ts.status_name             AS status
FROM znamenka.trainings tr
  INNER JOIN common.clients c ON tr.client_id = c.client_id
  INNER JOIN common.training_status ts ON tr.status_id = ts.status_id
WHERE tr.status_id != 1
      AND tr.start BETWEEN :from AND :to
      AND tr.trainer_id = :trainer_id