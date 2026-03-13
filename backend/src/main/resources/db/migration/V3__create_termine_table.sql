CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE termine (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),

    region VARCHAR(100),
    exact_location VARCHAR(255),

    name VARCHAR(255) NOT NULL,
    hall_or_location VARCHAR(255) NOT NULL,

    occasion VARCHAR(50),
    package_name VARCHAR(255),
    booking_type VARCHAR(100),
    duration VARCHAR(100),

    description VARCHAR(1000),

    status VARCHAR(50),

    created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMPTZ NOT NULL DEFAULT NOW()
);