FROM openjdk:15-alpine
WORKDIR /app/
COPY ./src/* /app/
RUN javac Lab1_Token
RUN javac Lab1_LexicalAnalysisForGA
RUN javac Lab1_GrammarAnalysis
RUN javac Lab1_Test

