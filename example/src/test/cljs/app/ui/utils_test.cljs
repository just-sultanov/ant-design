(ns app.ui.utils-test
  (:require [cljs.test :refer-macros [deftest is testing]]
            [app.ui.utils :as sut]))

(deftest test-square
  (testing "square test:"
    (testing "expected 4"
      (let [expected 4
            actual   (sut/square 2)]
        (is (= actual expected))))))
