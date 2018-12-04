-- Turn on strict mode while reading this file - warnings become errors
-- SET SESSION sql_mode = 'STRICT_ALL_TABLES';

-- DROP DATABASE IF EXISTS Project.db;
-- CREATE DATABASE Project.db;
-- USE Project.db;

CREATE TABLE base_type (
  id INTEGER PRIMARY KEY,
  type TEXT NOT NULL
);

CREATE TABLE gov_type (
  id INTEGER PRIMARY KEY,
  type TEXT NOT NULL
);

CREATE TABLE port_type (
  id INTEGER PRIMARY KEY,
  type VARCHAR(1) NOT NULL
);

-- longest tag is actually length 20
CREATE TABLE tag_type (
  id INTEGER PRIMARY KEY,
  type VARCHAR(24) NOT NULL
);

CREATE TABLE trade_type (
  id INTEGER PRIMARY KEY,
  type VARCHAR(2) NOT NULL,
  name TEXT NOT NULL
);

CREATE TABLE world_type (
  id INTEGER PRIMARY KEY,
  type VARCHAR(12) NOT NULL
);

-- here begin the star system tables
CREATE TABLE star_system (
  id INTEGER PRIMARY KEY,
  sector TINYINT(2) NOT NULL,
  subsector TINYINT(2) NOT NULL
);

-- TODO: bases/facilitices need more details
CREATE TABLE base (
  id INTEGER PRIMARY KEY,
  base_type INTEGER,
  FOREIGN KEY (base_type) REFERENCES base_type (id)
);

-- TODO: resources available (RA), labor base(?), GWP
-- I've seen GWP as high as 5 digits, plus 3 after the decimal (total 7)
CREATE TABLE economy (
  id INTEGER PRIMARY KEY,
  resources TINYINT(2) NOT NULL,
  labor TINYINT(2) NOT NULL,
  infrastructure TINYINT(2) NOT NULL,
  culture TINYINT(2) NOT NULL
);

-- TODO: bases/facilitices need more details
CREATE TABLE port (
  id INTEGER PRIMARY KEY,
  port_type INTEGER,
  FOREIGN KEY (port_type) REFERENCES port_type (id)
);

CREATE TABLE star (
  id INTEGER PRIMARY KEY,
  system_id INTEGER NOT NULL,
  orbit TINYINT(2) NOT NULL,
  name VARCHAR(32) NOT NULL,
  size TINYINT(1) NOT NULL,
  color VARCHAR(1) NOT NULL,
  max_orbits TINYINT(2) NOT NULL,
  FOREIGN KEY (system_id) REFERENCES star_system (id)
);

CREATE TABLE world (
  id INTEGER PRIMARY KEY,
  system_id INTEGER NOT NULL,
  orbit TINYINT(2) NOT NULL,
  suborbit TINYINT(2) NOT NULL,
  name VARCHAR(32) NOT NULL,
  world_type INTEGER NOT NULL,
  size TINYINT(2) NOT NULL,
  atmosphere TINYINT(2) NOT NULL,
  hydrosphere TINYINT(2) NOT NULL,
  population TINYINT(2) NOT NULL,
  government TINYINT(2) NOT NULL,
  law_level TINYINT(2) NOT NULL,
  tech_level TINYINT(2) NOT NULL,
  FOREIGN KEY (system_id) REFERENCES star_system (id),
  FOREIGN KEY (world_type) REFERENCES world_type (id)
);

-- begin reference tables
CREATE TABLE main_worlds (
  system_id INTEGER,
  world_id INTEGER,
  FOREIGN KEY (system_id) REFERENCES star_system (id),
  FOREIGN KEY (world_id) REFERENCES world (id),
  PRIMARY KEY (system_id, world_id)
);

CREATE TABLE world_base (
  world_id INTEGER,
  base_id INTEGER,
  FOREIGN KEY (world_id) REFERENCES world (id),
  FOREIGN KEY (base_id) REFERENCES base_type (id),
  PRIMARY KEY (world_id, base_id)
);

CREATE TABLE world_tag (
  world_id INTEGER,
  tag_type INTEGER,
  FOREIGN KEY (world_id) REFERENCES world (id),
  FOREIGN KEY (tag_type) REFERENCES tag_type (id),
  PRIMARY KEY (world_id, tag_type)
);

CREATE TABLE world_trade (
  world_id INTEGER,
  trade_type INTEGER,
  FOREIGN KEY (world_id) REFERENCES world (id),
  FOREIGN KEY (trade_type) REFERENCES trade_type (id),
  PRIMARY KEY (world_id, trade_type)
);

CREATE TABLE world_port (
  world_id INTEGER,
  port_id INTEGER,
  FOREIGN KEY (world_id) REFERENCES world (id),
  FOREIGN KEY (port_id) REFERENCES spaceport (id),
  PRIMARY KEY (world_id, port_id)
);

-- begin data insertion
INSERT INTO base_type (type) VALUES
('NAVY'), ('SCOUT'), ('FARM'), ('MINE'), ('COLONY'), ('LAB'), ('MILITARY');

INSERT INTO gov_type (type) VALUES
('No Government'),
('Corporation'),
('Democracy, Participating'),
('Oligarchy, Self-perpetuating'),
('Democracy, Representative'),
('Technocracy, Feudal'),
('Captive Government'),
('Balkanization'),
('Bureaucracy, Civil Service'),
('Bureaucracy, Impersonal'),
('Dictatorship, Charismatic'),
('Dictatorship, Non-charismatic'),
('Oligarchy, Charismatic'),
('Dictatorship, Religious');

INSERT INTO port_type (type) VALUES
('A'), ('B'), ('C'), ('D'), ('E'), ('F'), ('G'), ('H');

INSERT INTO tag_type (type) VALUES
('ABANDONED_COLONY'), ('ALIEN_RUINS'), ('ALTERED_HUMANITY'), ('AREA_51'),
('BADLANDS_WORLD'), ('BUBBLE_CITIES'), ('CIVIL_WAR'), ('COLD_WAR'),
('COLONIZED_POPULATION'), ('DESERT_WORLD'), ('EUGENIC_CULT'),
('EXCHANGE_CONSULATE'), ('FERAL_WORLD'), ('FLYING_CITIES'), ('FORBIDDEN_TECH'),
('FREAK_GEOLOGY'), ('FREAK_WEATHER'), ('FRIENDLY_FOE'), ('GOLD_RUSH'),
('HEAVY_INDUSTRY'), ('HEAVY_MINING'), ('HOSTILE_BIOSPHERE'), ('HOSTILE_SPACE'),
('LOCAL_SPECIALTY'), ('LOCAL_TECH'), ('MAJOR_SPACEYARD'), ('MINIMAL_CONTACT'),
('OCEANIC_WORLD'), ('OUTPOST_WORLD'), ('OUT_OF_CONTACT'), ('PERIMETER_AGENCY'),
('PILGRIMAGE_SITE'), ('POLICE_STATE'), ('PRECEPTOR_ARCHIVE'),
('PRETECH_CULTISTS'), ('PRIMITIVE_ALIENS'), ('PSIONICS_ACADEMY'),
('PSIONICS_FEAR'), ('PSIONICS_WORSHIP'), ('QUARANTINE_WORLD'),
('RADICAL_RACISM'), ('RADICAL_SEXISM'), ('RADIOACTIVE_WORLD'),
('REGIONAL_HEGEMON'), ('RESTRICTIVE_LAWS'), ('RIGID_CULTURE'),
('SEAGOING_CITIES'), ('SEALED_MENACE'), ('SECRET_MASTERS'), ('SECTARIANS'),
('SEISMIC_INSTABILITY'), ('THEOCRACY'), ('TOMB_WORLD'), ('TRADE_HUB'),
('TYRANNY'), ('UNBRAKED_AI'), ('WARLORDS'), ('XENOPHILES'), ('XENOPHOBES'),
('ZOMBIES');

INSERT INTO trade_type (type, name) VALUES
('AG', 'Agricultural'),
('AS', 'Asteroid'),
('BA', 'Barren'),
('DE', 'Desert'),
('FL', 'Fluid Oceans'),
('HI', 'High Population'),
('IC', 'Ice-Capped'),
('IN', 'Industrial'),
('LO', 'Low Population'),
('NA', 'Non-Agricultural'),
('NI', 'Non-Industrial'),
('PO', 'Poor'),
('RI', 'Rich'),
('VA', 'Vacuum'),
('WA', 'Water World');

INSERT INTO world_type (type) VALUES
('EMPTY'), ('STANDARD'), ('ASTEROID'), ('CAPTURED'), ('SATELLITE'), ('RING'),
('SMALL_GIANT'), ('LARGE_GIANT');
