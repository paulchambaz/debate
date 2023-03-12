debat:
	@javac -d bin -g $(shell find src -name "*.java" | grep -v 'Test.java')
	@cd bin && java up.mi.Debat_Chambaz_Cibier_Xu.Main
