(ns ant.design.forms
  (:require [reagent.core :as r]
            [goog.object :as gobj]))

(def enhancer
  (gobj/getValueByKeys js/antd "Form" "create"))


(defn create [params form]
  (let [{:keys [options props]
         :or   {options {}
                props   {}}} params]
    (r/create-element
      ((enhancer (clj->js options))
       (r/reactify-component form))
      (clj->js props))))


(defn current-form []
  (-> (r/current-component)
      (r/props)
      (js->clj :keywordize-keys true)
      :form))


(defn decorate-field [params field]
  (let [{:keys [id form options]
         :or   {options {}}} params
        decorator    (:getFieldDecorator form)
        decorator-fn (decorator id (clj->js options))]
    (decorator-fn (r/as-element field))))


(defn reset-fields [form & args]
  (let [reset-fn (:resetFields form)]
    (apply reset-fn (clj->js args))))


(defn validate-fields [form & args]
  (let [validate-fn (:validateFields form)]
    (apply validate-fn (clj->js args))))
