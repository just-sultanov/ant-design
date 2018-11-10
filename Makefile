build:
		clj -Abuild


pom:
		clj -Spom


deploy:
		mvn deploy


.PHONY: build pom deploy
