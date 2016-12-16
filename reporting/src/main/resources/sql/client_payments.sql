WITH payments AS (SELECT
                    coalesce(sum(pay.payment_amount), 0) AS sum_payments,
                    pur.client_id,
                    pur.trainer_id,
                    pur.product_id,
                    pur.discount_id
                  FROM
                    znamenka.purchase pur LEFT JOIN znamenka.payments pay ON pur.purchase_id = pay.purchase_id
                  GROUP BY pur.client_id, pur.trainer_id, pur.product_id, pur.discount_id
)
SELECT
  c.name || c.surname AS client_name,
  t.trainer_name      AS trainer_name,
  pr.product_name     AS product_name,
  p.sum_payments
FROM payments p
  INNER JOIN common.clients c ON p.client_id = c.client_id
  INNER JOIN common.trainers t ON p.trainer_id = t.trainer_id
  INNER JOIN common.products pr ON p.product_id = pr.product_id