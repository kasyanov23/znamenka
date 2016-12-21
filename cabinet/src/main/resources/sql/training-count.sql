SELECT sum(ca.training_count) AS "count"
FROM znamenka.clients_abonements ca
WHERE client_id = 31
GROUP BY ca.client_id