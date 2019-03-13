(ns ant.design.utils
  (:require [cljsjs.antd]
            [goog.object :as gobj]
            [clojure.string :as str]
            [reagent.core :as reagent]
            [reagent.interop :as interop]
            [reagent.impl.template :as template]))

;;
;; Helper functions
;;

(defn- get-path [component-name]
  (str/split component-name #"\."))


(defn component [component-name]
  (let [path (get-path component-name)]
    (reagent/adapt-react-class
      (apply gobj/getValueByKeys js/antd path))))


(defn apply-fn [fn-name & args]
  (apply js-invoke
         (gobj/get js/antd fn-name)
         (clj->js args)))


;;
;; Patch for reagent (doesn't work with advanced compile optimization)
;;

(defn- get-component-name [x]
  (interop/$ x :name))


(defn fix-caret-position! []
  (set! template/input-component?
        (fn [x]
          (or (= x "input")
              (= x "textarea")
              (= (get-component-name x) "Input")
              (= (get-component-name x) "TextArea")))))
