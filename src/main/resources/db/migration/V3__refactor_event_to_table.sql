alter table if exists guests add column table_id bigint;
create table tables (id bigserial not null, size integer, primary key (id));
alter table if exists guests drop constraint if exists UK_cfa6h1ebw9n029fqwdg6t7oj5;
alter table if exists guests add constraint UK_cfa6h1ebw9n029fqwdg6t7oj5 unique (table_id);
alter table if exists guests add constraint FK8csao8vjkq1dw6pq55akkd59w foreign key (table_id) references tables;
alter table if exists guests drop column if exists event_id;
drop table if exists events;
