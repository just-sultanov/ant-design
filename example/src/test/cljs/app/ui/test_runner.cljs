(ns ^:figwheel-hooks app.ui.test-runner
  (:require [cljs.test :refer-macros [run-tests]]
            [figwheel.main.testing :refer-macros [run-tests-async]]
            [cljs-test-display.core :as td]
            [app.ui.utils-test]))

(defn ^:after-load run []
  (run-tests
    (td/init! "test-root")
    'app.ui.utils-test))


(defn ^:export init []
  (run))


(defn -main [& args]
  (run-tests-async 5000))