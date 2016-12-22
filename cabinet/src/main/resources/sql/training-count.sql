WITH paid AS (
    SELECT
      sum(pr.training_count) AS number
    FROM znamenka.purchase p
      INNER JOIN common.products pr ON p.product_id = pr.product_id
    WHERE p.client_id = :clientId
), conducted AS (
    SELECT
      count(t.training_id) AS "count"
    FROM znamenka.trainings t
    WHERE t.status_id IN (2, 4)
    AND t.client_id = :clientId
)
SELECT
  c.client_id,
  paid.number - coalesce(conducted.count, 0) AS "count"
FROM common.clients c, paid, conducted
WHERE c.client_id = :clientId
