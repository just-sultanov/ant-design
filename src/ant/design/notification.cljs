(ns ant.design.notification
  "Display a notification message globally.

   When to use:
     To display a notification message at any of the four corners of the viewport.
     Typically it can be used in the following cases:
       - A notification with complex content
       - A notification providing a feedback based on the user interaction
         Or it may show some details about upcoming steps the user may have
         to follow
       - A notification that is pushed by the application"
  (:require [ant.design.utils :refer [apply-fn]]))

;;
;; Helper functions
;;

(defn- notification [& args]
  (apply-fn "notification" args))


;;
;; Notification API
;;

(defn configure
  "Set global configuration for all notifications.
  Usage:
    (configure {:top       24
                :bottom    24
                :duration  2
                :placement \"topRight\"})
  Returns:
    nil"
  [options]
  (notification "config" options))


(def ^:private levels
  #{nil :success :error :info :warning :warn :loading})

(defn show
  "Show notification.
  Usage:
    (show {:level       :success
           :message     \"Notification title...\"
           :description \"Notification content...\"
           :duration    3
           :on-click    #(js/console.log :callback/on-click)
           :on-close    #(js/console.log :callback/on-close)})
  Returns:
    nil"
  [{:as config :keys [level duration on-click on-close]}]
  {:pre [(contains? levels level)]}
  (let [level (if level
                (name level)
                "info")
        props (cond-> config
                      (or (pos? duration)
                          (zero? duration)) (assoc :duration duration)
                      (fn? on-click) (assoc :onClick on-click)
                      (fn? on-close) (assoc :onClose on-close)
                      :always (dissoc :level))]
    (notification level props)))


(defn close
  "Close notification.
  Usage:
    (close :key)

  Returns:
    nil"
  [key]
  (notification "close" key))


(defn destroy
  "Destroy notification.
  Usage:
    (destroy)

  Returns:
    nil"
  []
  (notification "destroy"))
