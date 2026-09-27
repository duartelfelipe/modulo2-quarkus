@echo off
:loop
  echo Enviando requisicao...
  curl -X GET "http://localhost:8080/api/fault-tolerance/circuit-breaker"
goto loop
