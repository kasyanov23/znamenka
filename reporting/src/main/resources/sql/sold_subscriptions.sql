SELECT
  c.name || ' ' || c.surname                             AS client_name,
  p.purchase_date,
  pr.product_name,
  CASE WHEN d.discount_id IS NULL
    THEN pr.price
  ELSE pr.price - pr.price * d.discount_amount / 100 END AS price,
  coalesce((SELECT sum(pay.payment_amount)
            FROM znamenka.payments pay
            WHERE pay.purchase_id = p.purchase_id), 0)   AS payd
FROM znamenka.purchase p
  INNER JOIN common.clients C ON p.client_id = C.client_id
  INNER JOIN common.products pr ON p.product_id = pr.product_id
  LEFT JOIN common.discounts d ON p.discount_id = d.discount_id
WHERE p.purchase_date BETWEEN :from AND :to
      AND p.product_id = :product_id