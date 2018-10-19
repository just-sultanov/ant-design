build:
		clj -Abuild


pom:
		clj -Spom


deploy:
		mvn deploy


all:
		@make build
		@make pom
		@make deploy


.PHONY: build pom deploy all
