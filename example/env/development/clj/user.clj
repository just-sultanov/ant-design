(ns user
  (:require [mount.core :as mount]
            [figwheel.main.api :as figwheel]
            [app.core]))

;;
;; Backend
;;

(defn start []
  (mount/start))


(defn stop []
  (mount/stop))


(defn restart []
  (stop)
  (start))


;;
;; Frontend
;;

(defn figwheel-start
  ([]
   (figwheel-start "development"))
  ([build-id]
   (figwheel/start {:mode :serve} build-id)))


(defn figwheel-stop
  ([]
   (figwheel/stop-all))
  ([build-id]
   (figwheel/stop build-id)))


(defn cljs-repl
  ([]
   (cljs-repl "development"))
  ([build-id]
   (figwheel/cljs-repl build-id)))
