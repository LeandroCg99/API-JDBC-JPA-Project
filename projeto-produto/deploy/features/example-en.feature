Feature: Sample for Access Validation

	All Scenarios with "@review" tag will be executed only on branches
	All Scenarios with "@production" tag will be executed right after production's deployment
	All scenários, with or without tag, will be executed on staging area
	
@review
 Scenario: Verificar tela inicial ok
	Given I navigate to "/"
	Then element having xpath "/html/body" should have partial text as "hostname"
	Then I take a screenshot

 Scenario: Verificar tela inicial com erro (forçando falha)
	Given I navigate to "/"
	Then element having xpath "/html/body" should have partial text as "falha"

@production
 Scenario: Verificar tela inicial
	Given I navigate to "/"
	Then element having xpath "/html/body" should have partial text as "hostname"

