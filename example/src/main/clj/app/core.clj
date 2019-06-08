(ns app.core
  (:require [mount.core :as mount]
            [io.pedestal.log :as log]
            [app.web])
  (:import (org.apache.commons.daemon DaemonContext))
  (:gen-class :implements [org.apache.commons.daemon.Daemon]))

;;
;; Helper functions
;;

(defn square [x]
  (* x x))


(defn set-global-exception-hook []
  (Thread/setDefaultUncaughtExceptionHandler
    (reify Thread$UncaughtExceptionHandler
      (uncaughtException [_ thread ex]
        (log/error :msg "uncaught exception" :thread (.getName thread) :description ex)))))


(defn set-sigterm-hook [f]
  (.addShutdownHook
    (Runtime/getRuntime)
    (Thread. (fn []
               (log/info :msg "JVM interrupted!")
               (f)
               (shutdown-agents)))))


;;
;; Entry point
;;

(defn -main [& args]
  (set-global-exception-hook)
  (set-sigterm-hook mount/stop)
  (println "Press Ctrl-C to exit.")
  (mount/start))


;;
;; Apache Daemon implementation
;;

(defn -init
  "Init phase of Apache Daemon"
  [this ^DaemonContext ctx]
  (.getArguments ctx))


(defn -start
  "Start phase of Apache Daemon"
  [this]
  @(future (-main))
  (Thread/sleep 1000))


(defn -stop
  "Stop phase of Apache Daemon"
  [this]
  (mount/stop))
