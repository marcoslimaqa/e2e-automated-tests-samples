@ui-desktop-test
Feature: Process Explorer

  Scenario: Analyze components
		Given that I have the Process Explorer open
		Then a "save-button.png" must be displayed
		And a "refresh-button.png" must be displayed
		And a "system-information-button.png" must be displayed
		And a "show-process-tree-button.png" must be displayed
		
  Scenario: Run a program as administrator
		Given that I have the Process Explorer open
		When I run "calc.exe" as administrator
		Then the "Calculator.exe" program must be presented on the path "wininit.exe > services.exe > calc.exe"
	
	Scenario: Kill a process
		Given that I have the Process Explorer open
		And I run "calc.exe" as administrator
		When the "Calculator.exe" program must be presented on the path "wininit.exe > services.exe > calc.exe"
		Then I kill the process