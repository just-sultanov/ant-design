(ns app.ui.events
  (:require [re-frame.core :as rf]))

(rf/reg-event-db
  ::init-db
  []
  (fn [_ _]
    {:x 42}))


(rf/reg-event-db
  ::update-x
  (fn [db [_ x]]
    (cond
      (number? x) (assoc db :x x)
      (fn? x) (update db :x x))))
