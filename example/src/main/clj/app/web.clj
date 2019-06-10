(ns app.web
  (:require [mount.core :as mount]
            [environ.core :refer [env]]
            [io.pedestal.log :as log]
            [org.httpkit.server :as http]
            [reitit.ring :as ring]
            [ring.middleware.defaults :as defaults]))

(defn ->int [^String s]
  (try
    (Integer/parseInt s)
    (catch Exception _)))


(def handler
  (ring/ring-handler
    (ring/router
      ["/ping" (constantly {:status 200, :body "pong"})])
    (ring/routes
      (ring/create-resource-handler {:path "/"})
      (ring/create-default-handler))))


(defn- start []
  (let [host   (or (:example-app-host env)
                   "localhost")
        port   (or (->int (:example-app-port env))
                   8080)
        thread (or (->int (:example-app-thread env))
                   4)]
    (log/info :msg (format "Server starting on port: %s" port))
    (http/run-server
      (defaults/wrap-defaults handler defaults/api-defaults)
      {:host   host
       :port   port
       :thread thread})))


(defn- stop [instance]
  (when instance
    (instance :timeout 200)
    true))


(mount/defstate server
  :start (start)
  :stop (stop server))
