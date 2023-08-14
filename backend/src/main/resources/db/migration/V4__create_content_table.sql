/**
 * Author:  TEGA
 * Created: Apr 29, 2023
 */
CREATE TABLE content (
    id SERIAL PRIMARY KEY,
    uri VARCHAR(255) NOT NULL UNIQUE
);
