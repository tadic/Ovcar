create table aktivnost (
  id                        integer primary key AUTOINCREMENT,
  vrsta_aktivnosti_id       integer,
  dan_id                    integer,
  vreme_pocetka             integer,
  vreme_zavrsetka           integer,
  lokacija                  varchar(255),
  napomena                  varchar(255),
  bilans                    varchar(255),
  troskovi                  float)
;

create table dan (
  id                        integer primary key AUTOINCREMENT,
  datum                     integer)
;

create table jagnjenje (
  id                        integer primary key AUTOINCREMENT,
  aktivnost_id              integer,
  ovca_id                   integer,
  sheep_id                  integer,
  jel_zivo                  integer(1),
  napomena                  varchar(255))
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
  linija_id                 integer,
  tezina_na_rodjenju        float,
  aktuelno                  varchar(255))
;

create table prodaja (
  id                        integer primary key AUTOINCREMENT,
  aktivnost_id              integer,
  ovca_id                   integer,
  cena_grla                 double,
  tezina                    double,
  cena_kg                   double,
  kupac                     varchar(255),
  napomena                  varchar(255))
;

create table radovi (
  id                        integer primary key AUTOINCREMENT,
  aktivnost_id              integer,
  razlog                    varchar(255),
  sta_se_radilo_nabavilo    varchar(255),
  kolicina                  varchar(255))
;

create table uginuce (
  id                        integer primary key AUTOINCREMENT,
  razlog                    varchar(255),
  a_id                      integer,
  o_id                      integer)
;

create table vakcinacija (
  id                        integer primary key AUTOINCREMENT,
  aktivnost_id              integer,
  ovca_id                   integer,
  jel_redovno               integer(1),
  razlog                    varchar(255),
  sredstvo                  varchar(255),
  napomena                  varchar(255))
;

create table vrste_aktivnosti (
  id                        integer primary key AUTOINCREMENT,
  name                      varchar(255),
  color                     integer)
;

create index ix_aktivnost_vrstaAktivnosti_1 on aktivnost (vrsta_aktivnosti_id);
create index ix_aktivnost_dan_2 on aktivnost (dan_id);
create index ix_jagnjenje_aktivnost_3 on jagnjenje (aktivnost_id);
create index ix_jagnjenje_ovca_4 on jagnjenje (ovca_id);
create index ix_jagnjenje_sheep_5 on jagnjenje (sheep_id);
create index ix_nabavka_ovaca_aktivnost_6 on nabavka_ovaca (aktivnost_id);
create index ix_nabavka_ovaca_sheep_7 on nabavka_ovaca (sheep_id);
create index ix_ovca_otac_8 on ovca (otac_id);
create index ix_ovca_majka_9 on ovca (majka_id);
create index ix_ovca_linija_10 on ovca (linija_id);
create index ix_prodaja_aktivnost_11 on prodaja (aktivnost_id);
create index ix_prodaja_ovca_12 on prodaja (ovca_id);
create index ix_radovi_aktivnost_13 on radovi (aktivnost_id);
create index ix_uginuce_a_14 on uginuce (a_id);
create index ix_uginuce_o_15 on uginuce (o_id);
create index ix_vakcinacija_aktivnost_16 on vakcinacija (aktivnost_id);
create index ix_vakcinacija_ovca_17 on vakcinacija (ovca_id);


