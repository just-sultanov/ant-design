(ns app.ui.events
  (:require [re-frame.core :as rf]))

(rf/reg-event-db
  ::init-db
  []
  (fn [_ _]
    {:x 0}))


(rf/reg-event-db
  ::update-x
  (fn [db [_ f]]
    (update db :x f)))
