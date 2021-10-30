FROM openjdk:15
WORKDIR /app/
COPY ./src/* /app/
RUN javac Lab2_Token.java
RUN javac Lab2_LexicalAnalysisForGA.java
RUN javac Lab2_GrammarAnalysis.java
RUN javac Lab2_Test.java

