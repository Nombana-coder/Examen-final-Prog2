-- =========================================================
-- inserts.sql
-- Jeu de données de test
-- À exécuter après database.sql
-- =========================================================

-- =========================================================
-- Utilisateurs
-- =========================================================
INSERT INTO app_user (id, ref, first_name, last_name, email, phone) VALUES
('user-1', 'REF-001', 'Alice',   'Martin',   'alice.martin@example.com',   '+33600000001'),
('user-2', 'REF-002', 'Bob',     'Durand',   'bob.durand@example.com',     '+33600000002'),
('user-3', 'REF-003', 'Chloé',   'Bernard',  'chloe.bernard@example.com',  '+33600000003');

-- =========================================================
-- CashFlow : entrées communes (id, created_at, amount, type, user_id)
-- =========================================================
INSERT INTO cash_flow (id, created_at, amount, type, user_id) VALUES
-- Donations d'Alice
('cf-1', '2025-01-05 09:15:00', 50.00,  'DONATION', 'user-1'),
('cf-2', '2025-02-10 14:30:00', 120.00, 'DONATION', 'user-1'),
-- Dépenses d'Alice
('cf-3', '2025-01-20 08:00:00', 30.00,  'EXPENSE',  'user-1'),
('cf-4', '2025-03-01 18:45:00', 75.50,  'EXPENSE',  'user-1'),

-- Donations de Bob
('cf-5', '2025-02-15 11:00:00', 200.00, 'DONATION', 'user-2'),
-- Dépenses de Bob
('cf-6', '2025-02-18 10:00:00', 40.00,  'EXPENSE',  'user-2'),
('cf-7', '2025-03-05 09:30:00', 15.90,  'EXPENSE',  'user-2'),

-- Chloé : uniquement une donation, aucune dépense
('cf-8', '2025-03-10 16:00:00', 60.00,  'DONATION', 'user-3');

-- =========================================================
-- Détails Donation (comment)
-- =========================================================
INSERT INTO donation (cash_flow_id, comment) VALUES
('cf-1', 'Don ponctuel pour soutenir le projet'),
('cf-2', 'Don mensuel'),
('cf-5', 'Don en soutien à la campagne de printemps'),
('cf-8', 'Premier don de Chloé');

-- =========================================================
-- Détails Expense (reason, frequency)
-- =========================================================
INSERT INTO expense (cash_flow_id, reason, frequency) VALUES
('cf-3', 'Achat de fournitures de bureau',      'NONE'),
('cf-4', 'Abonnement logiciel de gestion',      'MONTHLY'),
('cf-6', 'Frais de déplacement',                'NONE'),
('cf-7', 'Abonnement hébergement site web',     'YEARLY');
