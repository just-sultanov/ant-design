.PHONY: help
.DEFAULT_GOAL := help

SHELL = bash

SCM_URL="https://github.com/just-sultanov/ant-design"
TAG_MSG="release a new version"

help: ## Show help
	@awk 'BEGIN {FS = ":.*?## "} /^[a-zA-Z_-]+:.*?## / {printf "\033[36m%-30s\033[0m %s\n", $$1, $$2}' $(MAKEFILE_LIST)


clean: ## Clean
	rm -f pom.xml && rm -rf target


build: clean ## Build jar
	clojure -A:build
	clojure -A:version --pom --scm-url ${SCM_URL}
	mv target/ant.design*.jar target/ant.design.jar


patch: ## Increment patch version
	clojure -A:version patch --tag --message ${TAG_MSG}


minor: ## Increment minor version
	clojure -A:version minor --tag --message ${TAG_MSG}


major: ## Increment major version
	clojure -A:version major --tag --message ${TAG_MSG}


deploy: build ## Deploy to clojars
	clojure -A:deploy
