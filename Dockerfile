FROM openjdk:15-alpine
WORKDIR /app/
COPY ./src/* /app/
RUN javac Lab0_LexicalAnalysis.java
RUN javac Test.java

