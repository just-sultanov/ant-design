(ns ^:figwheel-hooks app.ui.core
  (:require [reagent.core :as r]
            [re-frame.core :as rf]
            [ant.design :as ant]))

(defn square [x]
  (* x x))


(rf/reg-event-db
  :init/db
  []
  (fn [_ _]
    {:x 0}))


(rf/reg-event-db
  :update-x
  (fn [db [_ f]]
    (update db :x f)))


(rf/reg-sub
  :x
  (fn [db]
    (get db :x 0)))


(defn root []
  (let [x @(rf/subscribe [:x])]
    [ant/layout {:style {:minHeight "100vh"}}
     [ant/layout-content {:style {:padding "50px"}}
      [:<>
       [ant/button {:style   {:margin-left "20px"}
                    :onClick #(rf/dispatch [:update-x dec])}
        "-"]
       [:span x]
       [ant/button {:style   {:margin-left "20px"}
                    :onClick #(rf/dispatch [:update-x inc])}
        "+"]]]]))


(defn ^:after-load mount-root []
  (rf/clear-subscription-cache!)
  (r/render [root] (.getElementById js/document "root")))


(defn ^:export init []
  (rf/dispatch-sync [:init/db])
  (mount-root))


(init)
