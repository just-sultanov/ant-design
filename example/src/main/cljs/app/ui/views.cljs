(ns app.ui.views
  (:require [re-frame.core :as rf]
            [ant.design :as ant]
            [app.ui.subs :as subs]
            [app.ui.events :as events]))

(defn root []
  (let [x @(rf/subscribe [::subs/x])]
    [ant/layout {:style {:minHeight "100vh"}}
     [ant/layout-content {:style {:padding "50px"}}
      [:<>
       [ant/button {:style   {:margin-left "20px"}
                    :onClick #(rf/dispatch [::events/update-x dec])}
        "-"]
       [:span x]
       [ant/button {:style   {:margin-left "20px"}
                    :onClick #(rf/dispatch [::events/update-x inc])}
        "+"]]]]))