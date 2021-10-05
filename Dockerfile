FROM openjdk:15
WORKDIR /app/
COPY ./src/* /app/
RUN javac Operations.java
RUN javac Test.java

