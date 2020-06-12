# quarkus-infinispan-failure

Quarkus 1.5.1.Final issue with infinispan extension

## Testing steps

### Start Infinispan

```
docker run -d --rm -p 11222:11222 \
  -v $(pwd)/infinispan/infinispan.xml:/opt/infinispan/server/conf/infinispan.xml \
  --entrypoint /opt/infinispan/bin/server.sh \
  infinispan/server:10.1.3.Final
```

### Run maven commands sequentially to see successful outcome

```
mvn clean
mvn compile
mvn test
```

### Run maven commands together to see failure

```
mvn clean compile test
```

This also fails
```
mvn clean
mvn compile test
```

Additional detail:
* It appears that the Marshaller java files are created, but not compiled into class files, when it fails. You can see the java and class files easily after the above commands by executing `find target -name '*Marshaller*'`

```
mvn clean
mvn compile
find target -name '*Marshaller*'
# output
target/generated-sources/annotations/org/acme/Book$___Marshaller_f3251d80b7f97c5316431ab6fd7b3cce42c9d8c8189856dba3e3f2c585f19c79.java
target/generated-sources/annotations/org/acme/Author$___Marshaller_58f9cfc92d6df0e524374380ae551ca945f9755247b3ced4618717b9b7152c47.java
target/classes/org/acme/Book$___Marshaller_f3251d80b7f97c5316431ab6fd7b3cce42c9d8c8189856dba3e3f2c585f19c79.class
target/classes/org/acme/Author$___Marshaller_58f9cfc92d6df0e524374380ae551ca945f9755247b3ced4618717b9b7152c47.class
```
versus the failure case:
```
mvn clean
mvn compile test
find target -name '*Marshaller*'
# output
target/generated-sources/annotations/org/acme/Book$___Marshaller_f3251d80b7f97c5316431ab6fd7b3cce42c9d8c8189856dba3e3f2c585f19c79.java
target/generated-sources/annotations/org/acme/Author$___Marshaller_58f9cfc92d6df0e524374380ae551ca945f9755247b3ced4618717b9b7152c47.java
```