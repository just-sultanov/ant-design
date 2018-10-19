deploy:
		clj -Abuild
		clj -Spom
		mvn deploy


.PHONY: deploy
