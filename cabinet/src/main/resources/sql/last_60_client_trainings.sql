SELECT
  1 AS studio,
  t.start,
  tr.trainer_name,
  ts.status_name,
  pr.product_name
FROM znamenka.trainings t
  INNER JOIN common.trainers tr ON t.trainer_id = tr.trainer_id
  LEFT JOIN znamenka.purchase p ON t.purchase_id = p.purchase_id
  INNER JOIN common.training_status ts ON t.status_id = ts.status_id
  INNER JOIN common.products pr ON p.product_id = pr.product_id
WHERE t.client_id = ?
ORDER BY t.start DESC
LIMIT 60