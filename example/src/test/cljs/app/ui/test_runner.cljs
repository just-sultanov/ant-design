(ns app.ui.test-runner
  (:require [goog.object :as gobj]
            [cljs-test-display.core :as td]
            [cljs.test :refer-macros [run-tests]]
            [figwheel.main.testing :refer-macros [run-tests-async]]
            [app.ui.utils-test]))

(defn run []
  (when (= "/tests.html"
           (gobj/getValueByKeys goog/global "location" "pathname"))
    (run-tests
      (td/init! "root")
      'app.ui.utils-test)))

(run)

(defn -main [& args]
  (run-tests-async 10000))
