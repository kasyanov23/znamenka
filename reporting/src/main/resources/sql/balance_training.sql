SELECT
  c.name || ' ' || c.surname client_name,
  p.product_name,
  ca.training_count
FROM znamenka.clients_abonements ca
  INNER JOIN common.clients c ON ca.client_id = c.client_id
  INNER JOIN common.products p ON ca.product_id = p.product_id