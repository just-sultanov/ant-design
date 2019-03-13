(ns ant.design.message
  "Display global messages as feedback in response to user operations.

   When to use:
     - To provide feedback such as success, warning, error etc
     - A message is displayed at top and center and will be dismissed
       automatically, as a non-interrupting light-weighted prompt"
  (:require [ant.design.utils :refer [apply-fn]]))

;;
;; Helper functions
;;

(defn- message [& args]
  (apply-fn "message" args))


;;
;; Message API
;;

(defn configure
  "Set global configuration for all messages.
  Usage:
    (configure {:top      24
                :duration 2
                :maxCount 3})
  Returns:
    nil"
  [options]
  (message "config" options))


(def ^:private levels
  #{nil :success :error :info :warning :warn :loading})

(defn show
  "Show message.
  Usage:
    (show {:level       :loading
           :content     \"Action in progress...\"
           :duration    3
           :on-close    #(js/console.log :callback/on-close)
           :after-close #(js/console.log :callback/after-close)})
  Returns:
    Promise"
  [{:keys [level content duration on-close after-close]}]
  {:pre [(contains? levels level)]}
  (let [level    (if level
                   (name level)
                   "info")
        duration (when (or (pos? duration)
                           (zero? duration))
                   duration)
        callback (cond
                   (and (fn? on-close)
                        (nil? after-close)) :one
                   (every? fn? [on-close after-close]) :both
                   :else :none)
        props    (case callback
                   :one [level content duration on-close]
                   :both [level content duration on-close after-close]
                   :none [level content duration])]
    (let [promise (->> props
                       (remove nil?)
                       (apply message))]
      (if-not (= :both callback)
        promise
        (.then promise after-close)))))


(defn destroy
  "Destroy message.
  Usage:
    (destroy)

  Returns:
    nil"
  []
  (message "destroy"))
