.PHONY: help
.DEFAULT_GOAL := help

SHELL = bash

SCM_URL=https://github.com/just-sultanov/ant-design


help: ## Show help
	@awk 'BEGIN {FS = ":.*?## "} /^[a-zA-Z_-]+:.*?## / {printf "\033[36m%-30s\033[0m %s\n", $$1, $$2}' $(MAKEFILE_LIST)


clean: ## Clean
	rm -f pom.xml && rm -rf target


build: clean ## Build jar
	clojure -A:build
	mv target/ant.design*.jar target/ant.design.jar


patch: ## Increment patch version
	clojure -A:version patch --pom --scm-url ${SCM_URL}


minor: ## Increment minor version
	clojure -A:version minor --pom --scm-url ${SCM_URL}


major: ## Increment major version
	clojure -A:version major --pom --scm-url ${SCM_URL}


tag: ## Create a new git tag based on the given version
	clojure -A:version --pom --tag --message "release a new version"


deploy: build ## Deploy to clojars
	sleep 10
	CLOJARS_USERNAME=${CLOJARS_USERNAME} \
	CLOJARS_PASSWORD=${CLOJARS_PASSWORD} \
	clojure -A:deploy


release: deploy tag ## Release a new version
