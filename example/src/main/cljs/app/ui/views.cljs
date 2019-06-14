(ns app.ui.views
  (:require [re-frame.core :as rf]
            [ant.design :as ant]
            [app.ui.subs :as subs]
            [app.ui.events :as events]
            [ant.design.forms :as forms]))

(defn simple-form []
  (fn [{:keys [x] :as props}]
    (let [form (forms/current-form)]
      [ant/form
       [ant/form-item
        (forms/decorate-field {:id      "number"
                               :form    form
                               :options {:initialValue x
                                         :rules        [{:required true}]}}
          [ant/input-number {:onChange #(rf/dispatch [::events/update-x %])}])]

       [ant/form-item
        (forms/decorate-field {:id      "email"
                               :form    form
                               :options {:initialValue "john@doe.com"
                                         :rules        [{:required true}
                                                        {:type "email"}]}}

          [ant/input])]

       [ant/form-item
        (forms/decorate-field {:id      "date"
                               :form    form
                               :options {:initialValue (js/moment)
                                         :rules        [{:required true}]}}
          [ant/date-picker {:format "DD.MM.YYYY"}])]

       [ant/form-item
        [ant/button-group
         [ant/button {:type "primary" :on-click #(forms/validate-fields form)}
          "Submit"]
         [ant/button {:on-click #(forms/reset-fields form)}
          "Reset"]]]])))


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
        "+"]
       
       [:h1 {:style {:margin-top "40px"}}
        "simple form"]
       (forms/create {:options {:name "simple-form"}
                      :props   {:x x}}
                     (simple-form))]]]))
