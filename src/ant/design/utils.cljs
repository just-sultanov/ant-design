(ns ant.design.utils
  "Common helper functions"
  (:require
    [cljsjs.antd]
    [goog.object :as gobj]
    [clojure.string :as str]
    [reagent.core :as reagent]))

(defn- get-path
  "Returns the component path."
  [component-name]
  (str/split component-name #"\."))


(defn component
  "Returns the component by the given component name."
  [component-name]
  (let [path (get-path component-name)]
    (reagent/adapt-react-class
      (apply gobj/getValueByKeys js/antd path))))


(defn apply-fn
  "Invokes the function by the given name and applies to the given args."
  [fn-name & args]
  (apply js-invoke
         (gobj/get js/antd fn-name)
         (clj->js args)))
