.DEFAULT_GOAL := help


help: ## Show help
	@awk 'BEGIN {FS = ":.*?## "} /^[a-zA-Z_-]+:.*?## / {printf "\033[36m%-30s\033[0m %s\n", $$1, $$2}' $(MAKEFILE_LIST)


clean: ## Clean
	@mvn clean


build: ## Build uberjar
	@clj -Abuild


pom: ## Generate pom.xml
	@clj -Spom


deploy: ## Deploy to clojars
	@mvn deploy


.PHONY: clean build pom deploy
