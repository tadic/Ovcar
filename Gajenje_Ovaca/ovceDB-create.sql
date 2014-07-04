create table aktivnost (
  id                        integer primary key AUTOINCREMENT,
  vrsta_aktivnosti_id       integer,
  dan_id                    integer,
  vreme_pocetka             integer,
  vreme_zavrsetka           integer,
  lokacija                  varchar(255),
  napomena                  varchar(255),
  troskovi                  float)
;

create table dan (
  id                        integer primary key AUTOINCREMENT,
  datum                     integer)
;

create table linija (
  id                        integer primary key AUTOINCREMENT,
  ime_linije                varchar(255),
  opis                      varchar(255))
;

create table nabavka_ovaca (
  id                        integer primary key AUTOINCREMENT,
  aktivnost_id              integer,
  sheep_id                  integer,
  cena                      float,
  napomena                  varchar(255))
;

create table ovca (
  id                        integer primary key AUTOINCREMENT,
  oznaka                    varchar(255),
  nadimak                   varchar(255),
  procenat_r                float,
  datum_rodjenja            varchar(255),
  pol                       varchar(255),
  otac_id                   integer,
  majka_id                  integer,
  opis                      varchar(255),
  pracenje                  varchar(255),
  status                    varchar(255),
  leglo                     integer,
  linija_id                 integer)
;

create table vrste_aktivnosti (
  id                        integer primary key AUTOINCREMENT,
  name                      varchar(255),
  color                     integer)
;

create index ix_aktivnost_vrstaAktivnosti_1 on aktivnost (vrsta_aktivnosti_id);
create index ix_aktivnost_dan_2 on aktivnost (dan_id);
create index ix_nabavka_ovaca_aktivnost_3 on nabavka_ovaca (aktivnost_id);
create index ix_nabavka_ovaca_sheep_4 on nabavka_ovaca (sheep_id);
create index ix_ovca_otac_5 on ovca (otac_id);
create index ix_ovca_majka_6 on ovca (majka_id);
create index ix_ovca_linija_7 on ovca (linija_id);


