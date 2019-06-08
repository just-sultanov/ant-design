(ns app.ui.core-test
  (:require [cljs.test :refer-macros [deftest is testing]]
            [app.ui.core :as sut]))

(deftest ^:unit square-test
  (testing "square test:"
    (testing "expected 4"
      (let [expected 4
            actual   (sut/square 2)]
        (is (= actual expected))))))
