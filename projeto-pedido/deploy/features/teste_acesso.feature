Feature: Teste de Acesso

Scenario: Verificar tela inicial
	Given Eu navego para "/"
	Then I take a screenshot
	And Eu navego para "/lastn"
	Then I take a screenshot