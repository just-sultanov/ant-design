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
	mv target/ant.design*.jar target/ant.design.jar
	sleep 5


patch: ## Increment patch version
	clojure -A:version patch --tag --message ${TAG_MSG} --pom --scm-url ${SCM_URL}


minor: ## Increment minor version
	clojure -A:version minor --tag --message ${TAG_MSG} --pom --scm-url ${SCM_URL}


major: ## Increment major version
	clojure -A:version major --tag --message ${TAG_MSG} --pom --scm-url ${SCM_URL}


deploy: build ## Deploy to clojars
	CLOJARS_USERNAME=${CLOJARS_USERNAME} \
	CLOJARS_PASSWORD=${CLOJARS_PASSWORD} \
	clojure -A:deploy
