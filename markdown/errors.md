[Back to Main Documentation](../README.md)
# Bugs
> [!NOTE]
> This is a list of bugs I encountered that may still be relevant today. 

### Crash at startup, hybernate, alter table BlogPost drop foreign key FKpsf0ikr3q06ymuy6l0baxqnbs" via JDBC
```
2024-08-22 00:57:52,164 WARN  [org.hib.too.sch.int.ExceptionHandlerLoggedImpl] (JPA Startup Thread) GenerationTarget encountered exception accepting command : Error executing DDL "alter table BlogPost drop foreign key FKpsf0ikr3q06ymuy6l0baxqnbs" via JDBC [Table 'quarkus.BlogPost' doesn't exist]: org.hibernate.tool.schema.spi.CommandAcceptanceException: Error executing DDL "alter table BlogPost drop foreign key FKpsf0ikr3q06ymuy6l0baxqnbs" via JDBC [Table 'quarkus.BlogPost' doesn't exist]
        at org.hibernate.tool.schema.internal.exec.GenerationTargetToDatabase.accept(GenerationTargetToDatabase.java:94)
        at org.hibernate.tool.schema.internal.Helper.applySqlString(Helper.java:233)
        at org.hibernate.tool.schema.internal.Helper.applySqlStrings(Helper.java:217)
        at org.hibernate.tool.schema.internal.SchemaDropperImpl.applyConstraintDropping(SchemaDropperImpl.java:470)
        at org.hibernate.tool.schema.internal.SchemaDropperImpl.dropConstraintsTablesSequences(SchemaDropperImpl.java:242)
        at org.hibernate.tool.schema.internal.SchemaDropperImpl.dropFromMetadata(SchemaDropperImpl.java:215)
        at org.hibernate.tool.schema.internal.SchemaDropperImpl.performDrop(SchemaDropperImpl.java:185)
        at org.hibernate.tool.schema.internal.SchemaDropperImpl.doDrop(SchemaDropperImpl.java:155)
        at org.hibernate.tool.schema.internal.SchemaDropperImpl.doDrop(SchemaDropperImpl.java:115)
        at org.hibernate.tool.schema.spi.SchemaManagementToolCoordinator.performDatabaseAction(SchemaManagementToolCoordinator.java:244)
        at org.hibernate.tool.schema.spi.SchemaManagementToolCoordinator.lambda$process$5(SchemaManagementToolCoordinator.java:145)
        at java.base/java.util.HashMap.forEach(HashMap.java:1429)
        at org.hibernate.tool.schema.spi.SchemaManagementToolCoordinator.process(SchemaManagementToolCoordinator.java:142)
        at io.quarkus.hibernate.orm.runtime.observers.SessionFactoryObserverForSchemaExport.sessionFactoryCreated(SessionFactoryObserverForSchemaExport.java:21)
        at org.hibernate.internal.SessionFactoryObserverChain.sessionFactoryCreated(SessionFactoryObserverChain.java:35)
        at org.hibernate.internal.SessionFactoryImpl.<init>(SessionFactoryImpl.java:315)
        at io.quarkus.hibernate.orm.runtime.boot.FastBootEntityManagerFactoryBuilder.build(FastBootEntityManagerFactoryBuilder.java:87)
        at io.quarkus.hibernate.orm.runtime.FastBootHibernatePersistenceProvider.createEntityManagerFactory(FastBootHibernatePersistenceProvider.java:72)
        at jakarta.persistence.Persistence.createEntityManagerFactory(Persistence.java:80)
        at jakarta.persistence.Persistence.createEntityManagerFactory(Persistence.java:55)
        at io.quarkus.hibernate.orm.runtime.JPAConfig$LazyPersistenceUnit.get(JPAConfig.java:154)
        at io.quarkus.hibernate.orm.runtime.JPAConfig$1.run(JPAConfig.java:61)
        at java.base/java.lang.Thread.run(Thread.java:1583)
Caused by: java.sql.SQLSyntaxErrorException: Table 'quarkus.BlogPost' doesn't exist
        at com.mysql.cj.jdbc.exceptions.SQLError.createSQLException(SQLError.java:121)
        at com.mysql.cj.jdbc.exceptions.SQLExceptionsMapping.translateException(SQLExceptionsMapping.java:122)
        at com.mysql.cj.jdbc.StatementImpl.executeInternal(StatementImpl.java:770)
        at com.mysql.cj.jdbc.StatementImpl.execute(StatementImpl.java:653)
        at io.agroal.pool.wrapper.StatementWrapper.execute(StatementWrapper.java:220)
        at org.hibernate.tool.schema.internal.exec.GenerationTargetToDatabase.accept(GenerationTargetToDatabase.java:80)
        ... 22 more


2024-08-22 00:57:52,174 WARN  [org.hib.too.sch.int.ExceptionHandlerLoggedImpl] (JPA Startup Thread) GenerationTarget encountered exception accepting command : Error executing DDL "alter table BlogPost_Comment drop foreign key FKnrn7rc107lq0dy5ibkdvtgsmb" via JDBC [Table 'quarkus.BlogPost_Comment' doesn't exist]: org.hibernate.tool.schema.spi.CommandAcceptanceException: Error executing DDL "alter table BlogPost_Comment drop foreign key FKnrn7rc107lq0dy5ibkdvtgsmb" via JDBC [Table 'quarkus.BlogPost_Comment' doesn't exist]
        at org.hibernate.tool.schema.internal.exec.GenerationTargetToDatabase.accept(GenerationTargetToDatabase.java:94)
        at org.hibernate.tool.schema.internal.Helper.applySqlString(Helper.java:233)
        at org.hibernate.tool.schema.internal.Helper.applySqlStrings(Helper.java:217)
        at org.hibernate.tool.schema.internal.SchemaDropperImpl.applyConstraintDropping(SchemaDropperImpl.java:470)
        at org.hibernate.tool.schema.internal.SchemaDropperImpl.dropConstraintsTablesSequences(SchemaDropperImpl.java:242)
        at org.hibernate.tool.schema.internal.SchemaDropperImpl.dropFromMetadata(SchemaDropperImpl.java:215)
        at org.hibernate.tool.schema.internal.SchemaDropperImpl.performDrop(SchemaDropperImpl.java:185)
        at org.hibernate.tool.schema.internal.SchemaDropperImpl.doDrop(SchemaDropperImpl.java:155)
        at org.hibernate.tool.schema.internal.SchemaDropperImpl.doDrop(SchemaDropperImpl.java:115)
        at org.hibernate.tool.schema.spi.SchemaManagementToolCoordinator.performDatabaseAction(SchemaManagementToolCoordinator.java:244)
        at org.hibernate.tool.schema.spi.SchemaManagementToolCoordinator.lambda$process$5(SchemaManagementToolCoordinator.java:145)
        at java.base/java.util.HashMap.forEach(HashMap.java:1429)
        at org.hibernate.tool.schema.spi.SchemaManagementToolCoordinator.process(SchemaManagementToolCoordinator.java:142)
        at io.quarkus.hibernate.orm.runtime.observers.SessionFactoryObserverForSchemaExport.sessionFactoryCreated(SessionFactoryObserverForSchemaExport.java:21)
        at org.hibernate.internal.SessionFactoryObserverChain.sessionFactoryCreated(SessionFactoryObserverChain.java:35)
        at org.hibernate.internal.SessionFactoryImpl.<init>(SessionFactoryImpl.java:315)
        at io.quarkus.hibernate.orm.runtime.boot.FastBootEntityManagerFactoryBuilder.build(FastBootEntityManagerFactoryBuilder.java:87)
        at io.quarkus.hibernate.orm.runtime.FastBootHibernatePersistenceProvider.createEntityManagerFactory(FastBootHibernatePersistenceProvider.java:72)
        at jakarta.persistence.Persistence.createEntityManagerFactory(Persistence.java:80)
        at jakarta.persistence.Persistence.createEntityManagerFactory(Persistence.java:55)
        at io.quarkus.hibernate.orm.runtime.JPAConfig$LazyPersistenceUnit.get(JPAConfig.java:154)
        at io.quarkus.hibernate.orm.runtime.JPAConfig$1.run(JPAConfig.java:61)
        at java.base/java.lang.Thread.run(Thread.java:1583)
Caused by: java.sql.SQLSyntaxErrorException: Table 'quarkus.BlogPost_Comment' doesn't exist
        at com.mysql.cj.jdbc.exceptions.SQLError.createSQLException(SQLError.java:121)
        at com.mysql.cj.jdbc.exceptions.SQLExceptionsMapping.translateException(SQLExceptionsMapping.java:122)
        at com.mysql.cj.jdbc.StatementImpl.executeInternal(StatementImpl.java:770)
        at com.mysql.cj.jdbc.StatementImpl.execute(StatementImpl.java:653)
        at io.agroal.pool.wrapper.StatementWrapper.execute(StatementWrapper.java:220)
        at org.hibernate.tool.schema.internal.exec.GenerationTargetToDatabase.accept(GenerationTargetToDatabase.java:80)
        ... 22 more


2024-08-22 00:57:52,180 WARN  [org.hib.too.sch.int.ExceptionHandlerLoggedImpl] (JPA Startup Thread) GenerationTarget encountered exception accepting command : Error executing DDL "alter table BlogPost_Comment drop foreign key FK4tucfsii58h13uhrg8jwhs9hr" via JDBC [Table 'quarkus.BlogPost_Comment' doesn't exist]: org.hibernate.tool.schema.spi.CommandAcceptanceException: Error executing DDL "alter table BlogPost_Comment drop foreign key FK4tucfsii58h13uhrg8jwhs9hr" via JDBC [Table 'quarkus.BlogPost_Comment' doesn't exist]                                                                                                                                                      
        at org.hibernate.tool.schema.internal.exec.GenerationTargetToDatabase.accept(GenerationTargetToDatabase.java:94)
        at org.hibernate.tool.schema.internal.Helper.applySqlString(Helper.java:233)
        at org.hibernate.tool.schema.internal.Helper.applySqlStrings(Helper.java:217)
        at org.hibernate.tool.schema.internal.SchemaDropperImpl.applyConstraintDropping(SchemaDropperImpl.java:470)
        at org.hibernate.tool.schema.internal.SchemaDropperImpl.dropConstraintsTablesSequences(SchemaDropperImpl.java:242)
        at org.hibernate.tool.schema.internal.SchemaDropperImpl.dropFromMetadata(SchemaDropperImpl.java:215)
        at org.hibernate.tool.schema.internal.SchemaDropperImpl.performDrop(SchemaDropperImpl.java:185)
        at org.hibernate.tool.schema.internal.SchemaDropperImpl.doDrop(SchemaDropperImpl.java:155)
        at org.hibernate.tool.schema.internal.SchemaDropperImpl.doDrop(SchemaDropperImpl.java:115)
        at org.hibernate.tool.schema.spi.SchemaManagementToolCoordinator.performDatabaseAction(SchemaManagementToolCoordinator.java:244)
        at org.hibernate.tool.schema.spi.SchemaManagementToolCoordinator.lambda$process$5(SchemaManagementToolCoordinator.java:145)
        at java.base/java.util.HashMap.forEach(HashMap.java:1429)
        at org.hibernate.tool.schema.spi.SchemaManagementToolCoordinator.process(SchemaManagementToolCoordinator.java:142)
        at io.quarkus.hibernate.orm.runtime.observers.SessionFactoryObserverForSchemaExport.sessionFactoryCreated(SessionFactoryObserverForSchemaExport.java:21)
        at org.hibernate.internal.SessionFactoryObserverChain.sessionFactoryCreated(SessionFactoryObserverChain.java:35)
        at org.hibernate.internal.SessionFactoryImpl.<init>(SessionFactoryImpl.java:315)
        at io.quarkus.hibernate.orm.runtime.boot.FastBootEntityManagerFactoryBuilder.build(FastBootEntityManagerFactoryBuilder.java:87)
        at io.quarkus.hibernate.orm.runtime.FastBootHibernatePersistenceProvider.createEntityManagerFactory(FastBootHibernatePersistenceProvider.java:72)
        at jakarta.persistence.Persistence.createEntityManagerFactory(Persistence.java:80)
        at jakarta.persistence.Persistence.createEntityManagerFactory(Persistence.java:55)
        at io.quarkus.hibernate.orm.runtime.JPAConfig$LazyPersistenceUnit.get(JPAConfig.java:154)
        at io.quarkus.hibernate.orm.runtime.JPAConfig$1.run(JPAConfig.java:61)
        at java.base/java.lang.Thread.run(Thread.java:1583)
Caused by: java.sql.SQLSyntaxErrorException: Table 'quarkus.BlogPost_Comment' doesn't exist
        at com.mysql.cj.jdbc.exceptions.SQLError.createSQLException(SQLError.java:121)
        at com.mysql.cj.jdbc.exceptions.SQLExceptionsMapping.translateException(SQLExceptionsMapping.java:122)
        at com.mysql.cj.jdbc.StatementImpl.executeInternal(StatementImpl.java:770)
        at com.mysql.cj.jdbc.StatementImpl.execute(StatementImpl.java:653)
        at io.agroal.pool.wrapper.StatementWrapper.execute(StatementWrapper.java:220)
        at org.hibernate.tool.schema.internal.exec.GenerationTargetToDatabase.accept(GenerationTargetToDatabase.java:80)
        ... 22 more
```

Got fixed by:
```
quarkus.hibernate-orm.database.generation=update
```
in application.properties.

ChatGPT explained the bug like this:

```
Certainly! The quarkus.hibernate-orm.database.generation=update setting fixed the issue because it controls how Hibernate handles the database schema when the application starts up. Understanding why this resolved the problem involves understanding the different schema generation strategies Hibernate provides and how they interact with your database.

Hibernate Schema Generation Strategies
Hibernate offers several options for schema generation, and they dictate how Hibernate interacts with your database schema:

drop-and-create:

Behavior: Drops the existing database schema (tables, constraints, etc.) and then recreates it based on the current entity mappings.
Use Case: This is typically used during development when you want a fresh schema every time the application starts. It's not suitable for production because it will result in data loss.
Potential Issue: If tables or foreign keys don't exist but Hibernate attempts to drop them, you'll encounter errors like the ones you saw. This can happen if the schema is out of sync with your entity mappings or if the database was manually modified.
create:

Behavior: Creates the schema from scratch based on the entity mappings but does not drop the existing schema. Existing tables or constraints will not be removed.
Use Case: Useful when you want to ensure the schema is created without losing data. However, it won’t update the schema if changes were made to the entities.
Potential Issue: If the schema already exists, this might not detect or fix inconsistencies, potentially leading to runtime errors.
update:

Behavior: Updates the existing database schema to match the entity mappings without dropping tables. It adds or alters columns, constraints, and tables as needed but preserves existing data.
Use Case: Ideal for development when you need to evolve the database schema as the application grows, without dropping existing data or tables.
Why It Worked: In your case, setting the strategy to update prevented Hibernate from trying to drop non-existent tables or foreign keys, which was causing the errors. Instead, it only made the necessary changes to align the existing schema with your current entity mappings.
none:

Behavior: Disables all schema generation. Hibernate will neither create, drop, nor update the schema.
Use Case: Suitable for production environments where the schema is managed externally (e.g., via manual SQL scripts or a tool like Flyway or Liquibase).
The Problem You Encountered
When you initially started your Quarkus application, Hibernate was attempting to drop tables or foreign keys that didn't exist in your database, which led to the errors. This likely happened because:

The previous schema generation strategy (drop-and-create or similar) was causing Hibernate to try and drop tables or constraints that were expected to exist but didn’t.
There might have been inconsistencies between your entity mappings and the actual database schema, possibly due to previous schema alterations, incomplete migrations, or manual changes to the database.
Why update Fixed the Issue
By switching to update, Hibernate stopped trying to drop non-existent tables or foreign keys and instead focused on aligning the current schema with your entity mappings:

No Drops: It avoided dropping tables or constraints that might not exist, which was causing the errors.
Schema Evolution: It checked the existing schema and made the necessary changes (e.g., adding new columns, creating missing tables) to match your entity definitions without destroying existing data.
Summary
The Problem: Hibernate was trying to drop non-existent tables or foreign keys, leading to SQL errors because the schema generation strategy was too aggressive for the current state of your database.
The Solution (update): The update strategy only updates the schema, making it less likely to run into issues related to missing tables or constraints. It’s a safer option for development when you want to evolve the schema without dropping and recreating everything.
This approach is often recommended during development, especially when working with a team or when you’re iterating on your schema design, as it minimizes disruptions and preserves existing data.
```
