include .env
.DEFAULT_GOAL := help


help: ## Show help
	@awk 'BEGIN {FS = ":.*?## "} /^[a-zA-Z_-]+:.*?## / {printf "\033[36m%-30s\033[0m %s\n", $$1, $$2}' $(MAKEFILE_LIST)


clean: ## Clean
	@echo ==========================
	@echo = Clean
	@echo ==========================
	@rm -rf resources/public/assets/
	@rm -rf target
	@rm -rf logs


assets: ## Build assets
	@echo ==========================
	@echo = Build assets
	@echo ==========================
	@npx webpack


repl: ## Run REPL
	@echo ==========================
	@echo = Run REPL
	@echo ==========================
	@clojure -R:figwheel -A:repl


build-ui: ## Build UI
	@make -s assets
	@echo ==========================
	@echo = Build UI
	@echo ==========================
	@clojure -R:figwheel -A:build-ui


build-backend: ## Build backend
	@echo ==========================
	@echo = Build backend
	@echo ==========================
	@clojure -A:build


build: ## Build all
	@make -s clean
	@make -s build-ui
	@make -s build-backend


run: ## Run application
	@echo ==========================
	@echo = Run application
	@echo ==========================
	@make -s build-ui
	@echo ==========================
	@echo = Run backend
	@echo ==========================
	@clojure -A:run


##
# Tests
##

test-ui: ## Run UI tests
	@echo ==========================
	@echo = Run UI tests
	@echo ==========================
	@clojure -R:figwheel -A:test-ui


test-backend: ## Run backend tests
	@echo ==========================
	@echo = Run backend tests
	@echo ==========================
	@./bin/kaocha


test-all: ## Run all tests
	@make -s test-ui
	@make -s test-backend


test-watch: ## Watch backend tests
	@echo ==========================
	@echo = Watch backend tests
	@echo ==========================
	./bin/kaocha --watch


.PHONY: help
