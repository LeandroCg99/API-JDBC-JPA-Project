# encoding: utf-8
# language: pt

Funcionalidade: Teste em portugues

Cenário: Teste de acesso
    Quando Eu navego para "https://google.com"
    E Eu aguardo 5 seg
    E Eu digito "agilitynetworks" no campo com name "q"
    Então Eu tiro um screenshot
    E Eu clico no elemento com class "lsb"
    E Eu aguardo 10 seg
    Então Eu tiro um screenshot
    E Eu vou até o fim da página
    E Eu diminuo o zoom na página
    Então Eu tiro um screenshot