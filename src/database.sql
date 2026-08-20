-- =========================================================
-- database.sql
-- Script de création de la base de données (schéma relationnel)
-- Correspond au modèle : User / CashFlow (Donation, Expense)
-- =========================================================

-- Nettoyage (ordre inverse des dépendances) pour un script rejouable
DROP TABLE IF EXISTS expense;
DROP TABLE IF EXISTS donation;
DROP TABLE IF EXISTS cash_flow;
DROP TABLE IF EXISTS app_user;

-- =========================================================
-- Table : app_user
-- =========================================================
CREATE TABLE app_user (
    id         VARCHAR(64)  NOT NULL,
    ref        VARCHAR(64),
    first_name VARCHAR(100) NOT NULL,
    last_name  VARCHAR(100) NOT NULL,
    email      VARCHAR(150) NOT NULL,
    phone      VARCHAR(30),

    CONSTRAINT pk_app_user PRIMARY KEY (id),
    CONSTRAINT uq_app_user_email UNIQUE (email),
    CONSTRAINT uq_app_user_ref UNIQUE (ref)
);

-- =========================================================
-- Table : cash_flow
-- Table "parente" représentant tout mouvement financier.
-- 'type' discrimine la sous-table à joindre (donation / expense).
-- =========================================================
CREATE TABLE cash_flow (
    id         VARCHAR(64)     NOT NULL,
    created_at TIMESTAMP       NOT NULL,
    amount     DECIMAL(19,2)   NOT NULL,
    type       VARCHAR(20)     NOT NULL,
    user_id    VARCHAR(64)     NOT NULL,

    CONSTRAINT pk_cash_flow PRIMARY KEY (id),
    CONSTRAINT fk_cash_flow_user FOREIGN KEY (user_id)
        REFERENCES app_user (id) ON DELETE CASCADE,
    CONSTRAINT ck_cash_flow_type CHECK (type IN ('DONATION', 'EXPENSE')),
    CONSTRAINT ck_cash_flow_amount_positive CHECK (amount >= 0)
);

CREATE INDEX idx_cash_flow_user_id ON cash_flow (user_id);
CREATE INDEX idx_cash_flow_type ON cash_flow (type);

-- =========================================================
-- Table : donation
-- Extension 1-1 de cash_flow pour le type DONATION.
-- =========================================================
CREATE TABLE donation (
    cash_flow_id VARCHAR(64) NOT NULL,
    comment      TEXT,

    CONSTRAINT pk_donation PRIMARY KEY (cash_flow_id),
    CONSTRAINT fk_donation_cash_flow FOREIGN KEY (cash_flow_id)
        REFERENCES cash_flow (id) ON DELETE CASCADE
);

-- =========================================================
-- Table : expense
-- Extension 1-1 de cash_flow pour le type EXPENSE.
-- =========================================================
CREATE TABLE expense (
    cash_flow_id VARCHAR(64) NOT NULL,
    reason       VARCHAR(255),
    frequency    VARCHAR(20) NOT NULL DEFAULT 'NONE',

    CONSTRAINT pk_expense PRIMARY KEY (cash_flow_id),
    CONSTRAINT fk_expense_cash_flow FOREIGN KEY (cash_flow_id)
        REFERENCES cash_flow (id) ON DELETE CASCADE,
    CONSTRAINT ck_expense_frequency CHECK (
        frequency IN ('NONE', 'MONTHLY', 'WEEKLY', 'YEARLY')
    )
);
