# Spring + JPA + Hibernate test

## Quick links:

* [Entities](/src/main/java/com/school/registration/model)
* [StudentRepository](/src/main/java/com/school/registration/repository/StudentRepository.java)
  (using basically Spring data JPA with some custom queries. Transactional part would be done in the service class - I added a draft of the
  student creation
  [here](/src/main/java/com/school/registration/service/StudentService.java))
* [DDL](/src/main/resources/db/migration/V1.0__Student_Course.sql) using H2 language to make the setup easier 