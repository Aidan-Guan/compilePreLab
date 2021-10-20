FROM openjdk:15-alpine
WORKDIR /app/
COPY ./src/* /app/
RUN javac LexicalAnalysis.java
RUN javac Test.java

