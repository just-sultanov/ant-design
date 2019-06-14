(ns app.ui.subs
  (:require [re-frame.core :as rf]))

(rf/reg-sub
  ::x
  (fn [db]
    (get db :x 42)))
