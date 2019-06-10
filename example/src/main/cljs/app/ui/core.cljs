(ns ^:figwheel-hooks app.ui.core
  (:require [reagent.core :as r]
            [re-frame.core :as rf]
            [app.ui.events :as events]
            [app.ui.views :as views]))

(defn ^:after-load mount-root []
  (rf/clear-subscription-cache!)
  (r/render [views/root]
            (.getElementById js/document "root")))


(defn ^:export init []
  (rf/dispatch-sync [::events/init-db])
  (mount-root))
