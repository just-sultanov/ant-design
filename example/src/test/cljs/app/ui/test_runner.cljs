(ns ^:figwheel-hooks app.ui.test-runner
  (:require [goog.object :as gobj]
            [cljs-test-display.core :as test-display]
            [cljs.test :refer-macros [run-tests]]
            [figwheel.main.testing :refer [run-tests-async]]
            [app.ui.core-test]))

(defn -main [& args]
  (run-tests-async 3000))


(defn ^:after-load run []
  (when (= "/tests.html"
           (gobj/getValueByKeys goog/global "location" "pathname"))
    (run-tests
      (test-display/init! "root")
      'app.ui.core-test)))

(run)
