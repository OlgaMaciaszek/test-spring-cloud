# Demo Code

1. `docker run --name nacos-quick -e MODE=standalone -p 8848:8848 -d nacos/nacos-server:2.0.2`   
2. waiting 1 min
3. mvn clean install(jdk17)
4. run test.gateway.TestGatewayServerBootstrap
5. run test.ConsumerBootstrap
6. `curl 127.0.0.1:8766/api/auth/config/name`    throw NPE
7. Comment lines 29-36 of the TestGatewayServerBootstrap class
8. `curl 127.0.0.1:8766/api/auth/config/name`    SUCCESS